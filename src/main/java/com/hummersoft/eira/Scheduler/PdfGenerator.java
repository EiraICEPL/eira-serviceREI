package com.hummersoft.eira.Scheduler;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.CategoryItemRendererState;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hummersoft.eira.common.DateUtil;
import com.hummersoft.eira.common.DateUtil.HeaderFooter;
import com.hummersoft.eira.common.EiraConstants;
import com.hummersoft.eira.dto.DailyGenerationTodayEnergyDTO;
import com.hummersoft.eira.dto.EnergyPerformanceDTO;
import com.hummersoft.eira.dto.EquipmentDTO;
import com.hummersoft.eira.dto.SpecificYieldDTO;
import com.hummersoft.eira.model.Site;
import com.hummersoft.eira.model.UserReportMap;
import com.hummersoft.eira.repository.SchedulingReportRepository;
import com.hummersoft.eira.repository.SiteRepository;
import com.hummersoft.eira.service.DailyGenerationService;
import com.hummersoft.eira.service.EnergyPerformanceService;
import com.hummersoft.eira.service.ParameterComparisionService;
import com.hummersoft.eira.service.SiteService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@EnableScheduling
public class PdfGenerator {

	@Autowired
	private DailyGenerationService dailyGenerationService;
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	private EnergyPerformanceService energyPerformanceService;

	@Autowired
	private ParameterComparisionService paramcompare;
	@Autowired
	private SchedulingReportRepository schedulingReportRepository;
	@Autowired
	private SiteService siteService;

	@Autowired
	private SiteRepository siteRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${report.siteTable}")
	private List<String> columnNames;

	// Set font size for table content
	Font tableFont = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);

	// Set background color for table headers
	BaseColor headerBackgroundColor = new BaseColor(135, 206, 250); // Light Blue color

	// Set total table border color to AliceBlue (#F0F8FF)
	BaseColor totalTableBorderColor = new BaseColor(240, 248, 255); // AliceBlue color

	// Set font color for header text
	Font headerFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(64, 64, 64)); // Dark Gray
																									// color for
	Font pageHeaderFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK); // header text

	Map<Integer, String> EquipMap = new HashMap<Integer, String>();
	private int executionCount = 0;

	// @Scheduled(cron = "0 0 * * * ?")
	 @Scheduled(cron = "0 */2 * * * ?")
	public void sendEmailsWithPDFAttachments() {
		executionCount++;

		// Get the list of UserReportMap objects by time period for different sites
		List<UserReportMap> reportMaps = getReportByTimePeriod("this month");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH_mm"); 

		for (UserReportMap reportMap : reportMaps) {
			Integer siteId = reportMap.getSiteId();
			String sitename = reportMap.getSiteName();
			String recipientEmail = reportMap.getMailId();

			try {
				// Generate PDF report for the current site
				ByteArrayOutputStream pdfOutputStream = generatePdfReport(siteId, sitename);

				// Save the PDF report locally (optional)
				String fileName = sitename + "_Monthly_Report_" + df.format(new Date()) + ".pdf";
				savePdfLocally(pdfOutputStream, fileName);

				// Send the email with the PDF report attached
				sendEmailWithAttachment(recipientEmail, pdfOutputStream.toByteArray(), fileName, sitename);

			} catch (Exception e) {
				e.printStackTrace();
				// Handle the exception, log it, or perform necessary actions
			}
		}
	}

	private void addSitenameToFirstPage(Document document, String siteName) throws Exception {
		Font sitenameFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLDITALIC, BaseColor.DARK_GRAY);
		Paragraph sitenameParagraph = new Paragraph(siteName, sitenameFont);
		sitenameParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(sitenameParagraph);
	}

	private void addSiteDetails(Document document, Optional<Site> site) {

		try {
			
			PdfPTable table = new PdfPTable(columnNames.size());
			table.setWidthPercentage(100);

			for (int i = 0; i < columnNames.size(); i++) {
				addTableHeader(table, columnNames.get(i), headerFont, headerBackgroundColor, headerBackgroundColor);
			}

			addTableRow(table, site.get().getSiteName(), headerFont, totalTableBorderColor);
			addTableRow(table, site.get().getSiteTypeName(), headerFont, totalTableBorderColor);
			addTableRow(table, String.valueOf(site.get().getInstallationCapacity()), headerFont, totalTableBorderColor);

			document.add(table);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//private String pdfDir = "D:\\PdfReportRepo";
	private String pdfDir = "S:\\Gradle";
	private String reportFileName = "Monthly_Report";
	private String localDateFormat = "dd MMMM yyyy HH:mm:ss";
	private String logoImgPath = "S:\\\\Images\\\\webdyb_logo.png";
	//private String logoImgPath = "D:\\PdfReportRepo\\Webdyn colour Logo.png";
	private Float[] logoImgScale = new Float[] { (float) 50, (float) 50 };

	public ByteArrayOutputStream generatePdfReport(Integer siteId, String sitename) throws Exception {
		float leftMargin = 72;
		float rightMargin = 72;
		float topMargin = 72;
		float bottomMargin = 72;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Document document = new Document();
		document.setMargins(leftMargin, rightMargin, topMargin, bottomMargin);

		try {

			Optional<Site> site = siteRepo.findById(siteId);

			// get all equipments
			List<EquipmentDTO> lstAllEquipments = siteService.listAllEquipment(siteId);
			List<Integer> inv_id = new ArrayList<>();
			Double SumCapacity = 0.0;
			
			for (int i = 0; i < lstAllEquipments.size(); i++) {

				if (lstAllEquipments.get(i).getCategory().equals("CENTRLINVRTR")
						|| lstAllEquipments.get(i).getCategory().equals("STRINGINVRTR")) {
					// lstEquipmentinv.add(lstAllEquipments.get(i));
					inv_id.add(lstAllEquipments.get(i).getEquipmentId());
					EquipMap.put(lstAllEquipments.get(i).getEquipmentId(), lstAllEquipments.get(i).getCustomerNaming());
					Double capacity = lstAllEquipments.get(i).getCapacity();
					SumCapacity = SumCapacity + capacity;
				}
			}
						
			List<SpecificYieldDTO> specificYieldInv = new ArrayList<>();
			DecimalFormat df = new DecimalFormat("#.00");
			
			//get energy details
			Date[] dateRange = dateUtil.setDateRange("last month");
			Timestamp[] timestamps = dateUtil.formatTimestamps(dateRange[0], dateRange[1]);

			List<DailyGenerationTodayEnergyDTO> dailyGenValue = dailyGenerationService.getDgrValue(siteId, "custom",
					timestamps[0], timestamps[1]);
			
			for (DailyGenerationTodayEnergyDTO generation : dailyGenValue) {
				SpecificYieldDTO specificDTO = new SpecificYieldDTO();
				if (generation.getTodayEnergy() != null && generation.getTodayEnergy() != 0) {
					Double SpecificYield = ((generation.getTodayEnergy()) / SumCapacity);

					specificDTO.setTodayEnergy(generation.getTodayEnergy());
					specificDTO.setTimeStamp(generation.getTimestamp());
					String formatted = df.format(SpecificYield);
					double SpecificYieldRounded = Double.parseDouble(formatted);
					specificDTO.setSpecificYield(SpecificYieldRounded);

				}
				specificYieldInv.add(specificDTO);
			}

			  List<EnergyPerformanceDTO> energyGenValue = energyPerformanceService.getEnergyPerformanceValue(siteId,
		                "custom", timestamps[0], timestamps[1], inv_id);

			List<Document> documents = new ArrayList<>();
			HeaderFooter event = new HeaderFooter(logoImgPath);
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);
			document.open();
			addLogo(document);
			addDocTitle(document);
			addSitenameToFirstPage(document, site.get().getSiteName()); // Call a new method to add sitename to the
																		// first page
			int headingCount=1;
			
			// new to page to add site details
			document.newPage();
			writer.setPageEvent(event);
			Paragraph emptyLinesParagraph = new Paragraph();
			leaveEmptyLine(emptyLinesParagraph, 2); // Add 1 empty line
			document.add(emptyLinesParagraph);
			addSectionHeading(document, "Site Details",headingCount++);
			addSiteDetails(document, site);

			// Create a new page for the table of contents
			//document.newPage();
			//writer.setPageEvent(event);
			// Paragraph emptyLinesParagraph = new Paragraph();
			//leaveEmptyLine(emptyLinesParagraph, 2); // Add 2 empty lines
			//document.add(emptyLinesParagraph);

			// Generate Table of Contents
			//PdfPTable tocTable = generateTableOfContents();
			//document.add(tocTable);

			// First Section: Bar Chart
			document.newPage();
			writer.setPageEvent(event);
			leaveEmptyLine(emptyLinesParagraph, 1); // Add 1 empty line
			document.add(emptyLinesParagraph);
			addSectionHeading(document, "Energy Performance",headingCount++);
			createBarChartPage(document, dailyGenValue);

			// Second Section: MultiLine Chart
			document.newPage();
			writer.setPageEvent(event);
			leaveEmptyLine(emptyLinesParagraph, 1); // Add 1 empty line
			document.add(emptyLinesParagraph);
			addSectionHeading(document, "Inverter Performance",headingCount++);
			createMultiLineChartPage(document, energyGenValue);
			leaveEmptyLine(emptyLinesParagraph, 3); // Add 1 empty line
			document.add(emptyLinesParagraph);
			//invAcEnergyTable(document, energyGenValue);
			//leaveEmptyLine(emptyLinesParagraph, 2); // Add 1 empty line
			//document.add(emptyLinesParagraph);
			addCo2Avoided(document, dailyGenValue);

			// Third Section: Line Chart
			document.newPage();
			writer.setPageEvent(event);
			leaveEmptyLine(emptyLinesParagraph, 1); // Add 1 empty line
			document.add(emptyLinesParagraph);
			addSectionHeading(document, "Specific Yield",headingCount++);
			createLineChartPage(document,specificYieldInv);

			
			addSectionHeading(document, "Multi-Bar Chart", headingCount++); // Section heading for multi-bar chart
			createMultiBarChartPage(document, energyGenValue); // Add multi-bar chart to the page

			// Fourth Section: Equipment List (New Page)
			/*
			 * document.newPage(); writer.setPageEvent(event);
			 * leaveEmptyLine(emptyLinesParagraph, 1); // Add 1 empty line
			 * document.add(emptyLinesParagraph); addSectionHeading(document,
			 * "Equipment List"); createTableFromApi(document);
			 */

			document.close();

			System.out.println("------------------Your PDF Report is ready!-------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return outputStream;
	}

	private void createMultiLineChartPage(Document document, List<EnergyPerformanceDTO> energyGenValue)
			throws Exception {
		JFreeChart multiLineChart = generateMultiLineChart(energyGenValue);
		Image chartImage = getImageFromChart(multiLineChart);
		chartImage.setSpacingBefore(-30);
		addChartToDocument(document, chartImage);
	}

	private void createLineChartPage(Document document,List<SpecificYieldDTO>  specificYieldInv) throws Exception {
		JFreeChart lineChart = generateLineChart(specificYieldInv);
		Image chartImage = getImageFromChart(lineChart);
		chartImage.setSpacingBefore(-150);
		addChartToDocument(document, chartImage);
	}

	private void createBarChartPage(Document document, List<DailyGenerationTodayEnergyDTO> dailyGenValue ) throws Exception {
		JFreeChart barChart = generateBarChart(dailyGenValue );
		CategoryPlot plot = barChart.getCategoryPlot();
		plot.setRenderer(new CustomRenderer()); // Set the custom renderer
		Image chartImage = getImageFromChart(barChart);
		addChartToDocument(document, chartImage);
	}

	private Image getImageFromChart(JFreeChart chart) throws Exception {
		int width = 900;
		int height = 400;
		java.awt.Image awtImage = chart.createBufferedImage(width, height);
		com.itextpdf.text.Image chartImage = com.itextpdf.text.Image.getInstance(awtImage, null);
		chartImage.scalePercent(50); // Adjust scale as needed
		return chartImage;
	}

	private void addChartToDocument(Document document, Image chartImage) throws Exception {
		float leftPadding = 5; // Adjust this value based on your requirement
		Paragraph paragraph = new Paragraph();
		paragraph.setIndentationLeft(leftPadding);
		paragraph.add(chartImage);
		paragraph.setSpacingAfter(100);
		document.add(paragraph);
	}

	private void addLogo(Document document) {
		try {
			Image img = Image.getInstance(logoImgPath);
			img.scalePercent(logoImgScale[0], logoImgScale[1]);

			// Set the desired coordinates for the logo
			float xPosition = 200; // specify the x-coordinate
			float yPosition = 700; // specify the y-coordinate

			img.setSpacingAfter(150);
			img.setAbsolutePosition(xPosition, yPosition);
			document.add(img);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addDocTitle(Document document) throws Exception {
		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(localDateFormat));
		String lastMonthAndYear = getLastMonthAndYear();

		// Create a new paragraph for the title with adjusted spacing and alignment
		Font titleFont = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
		Paragraph titleParagraph = new Paragraph(reportFileName, titleFont);
		titleParagraph.setAlignment(Element.ALIGN_CENTER);
		titleParagraph.setSpacingBefore(80); // Adjust spacing before the title

		// Create a new paragraph for the subtitle with adjusted spacing and alignment
		Font subtitleFont = new Font(Font.FontFamily.COURIER, 14, Font.BOLD);
		Paragraph subtitleParagraph = new Paragraph(lastMonthAndYear, subtitleFont);
		subtitleParagraph.setAlignment(Element.ALIGN_CENTER);
		subtitleParagraph.setSpacingAfter(50); // Adjust spacing after the subtitle

		// Add title and subtitle paragraphs to the document
		document.add(titleParagraph);
		document.add(subtitleParagraph);
	}

	private String getLastMonthAndYear() {
		LocalDateTime lastMonth = LocalDateTime.now().minusMonths(1);
		return lastMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + lastMonth.getYear();
	}

	public JFreeChart generateBarChart(List<DailyGenerationTodayEnergyDTO> dailyGenValue ) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (int i = 0; i < dailyGenValue.size(); i++) {
			dataSet.setValue(dailyGenValue.get(i).getTodayEnergy(), "Energy Gen", Integer.toString(i));
		}

		// Create a custom renderer to set bar color and display values inside bars
		CategoryItemRenderer renderer = new CustomRenderer();

		JFreeChart chart = ChartFactory.createBarChart(null, "Time", "Energy Gen (KWh)", dataSet,
				PlotOrientation.VERTICAL, false, true, false);

		// Set the custom renderer as the renderer for the chart's plot
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(null);
		plot.setOutlineStroke(null); // Remove the outline (border)
		plot.setRangeGridlinePaint(Color.gray); // Set grid line color to white (same as background)

		return chart;
	}

	private JFreeChart generateLineChart(List<SpecificYieldDTO>  specificYieldInv) {
		DefaultCategoryDataset lineChartDataSet = new DefaultCategoryDataset();
		/*
		 * Date[] dateRange = dateUtil.setDateRange(reportMaps); Timestamp[] timestamps
		 * = dateUtil.formatTimestamps(dateRange[0], dateRange[1]);
		 * List<DailyGenerationTodayEnergyDTO> dailyGenValue =
		 * dailyGenerationService.getDgrValue(siteId, "custom", timestamps[0],
		 * timestamps[1]);
		 */

		for (int i = 0; i < specificYieldInv.size(); i++) {
			 String formattedDay = specificYieldInv.get(i).getTimeStamp().substring(8, 10); 
			lineChartDataSet.setValue(specificYieldInv.get(i).getSpecificYield(), "Specific Yield", formattedDay);
		}

		JFreeChart lineChart = ChartFactory.createLineChart(null, "Time", "Sp. Yield(kWh/kWp)", lineChartDataSet,
				PlotOrientation.VERTICAL, true, true, false);

		CategoryPlot lineChartPlot = lineChart.getCategoryPlot();
		LineAndShapeRenderer lineChartRenderer = new LineAndShapeRenderer();
		lineChartPlot.setRenderer(lineChartRenderer);
		// Set the line thickness (stroke) for all series
		float lineWidth = 3.0f; // Adjust the line thickness as needed
		
		  for (int i = 0; i < lineChartDataSet.getRowCount(); i++) {
		  lineChartRenderer.setSeriesStroke(i, new BasicStroke(lineWidth)); }
		 
		lineChartPlot.setBackgroundPaint(null);
		lineChartPlot.setRangeGridlinePaint(Color.gray);
		lineChartPlot.setOutlineStroke(null); // Remove the outline (border)

		return lineChart;
	}


	public JFreeChart generateMultiLineChart(List<EnergyPerformanceDTO> energyGenValue) {
	    DefaultCategoryDataset lineChartDataSet = new DefaultCategoryDataset();
	   
	    try {
				    	
	        for (EnergyPerformanceDTO energyPerformance : energyGenValue) {
	            String category = EquipMap.get(energyPerformance.getEquipmentId());
	            String timestamp = energyPerformance.getTimestamp(); // Assuming getTimestamp() returns a String in "yyyy-MM-dd" format
	            String formattedDay = timestamp.substring(8, 10); // Extract day from "yyyy-MM-dd"
	            lineChartDataSet.addValue(energyPerformance.getTodayEnergy(), category, formattedDay);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Handle the exception according to your application's error handling strategy.
	    }

	    JFreeChart lineChart = ChartFactory.createLineChart("Inverter Performance", "Time (Day)", "Energy Value",
	            lineChartDataSet, PlotOrientation.VERTICAL, true, true, false);

	    CategoryPlot lineChartPlot = lineChart.getCategoryPlot();
	    LineAndShapeRenderer lineChartRenderer = new LineAndShapeRenderer();
	    lineChartPlot.setRenderer(lineChartRenderer);
	    lineChartPlot.setBackgroundPaint(null);

	    lineChartPlot.setOutlineStroke(null); // Remove the outline (border)
	    lineChartPlot.setRangeGridlinePaint(Color.gray);
	    // Set category label positions to avoid overlapping on the X-axis
	    CategoryAxis domainAxis = lineChartPlot.getDomainAxis();
	    domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

	    return lineChart;
	}
	public JFreeChart generateMultiBarChart(List<EnergyPerformanceDTO> energyGenValue) {
	    DefaultCategoryDataset barChartDataSet = new DefaultCategoryDataset();

	    try {
	        for (EnergyPerformanceDTO energyPerformance : energyGenValue) {
	            String category = EquipMap.get(energyPerformance.getEquipmentId());
	            String timestamp = energyPerformance.getTimestamp(); // Assuming getTimestamp() returns a String in "yyyy-MM-dd" format
	            String formattedDay = timestamp.substring(8, 10); // Extract day from "yyyy-MM-dd"
	            barChartDataSet.addValue(energyPerformance.getTodayEnergy(), category, formattedDay);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Handle the exception according to your application's error handling strategy.
	    }

	    JFreeChart barChart = ChartFactory.createBarChart("Inverter Performance", "Time (Day)", "Energy Value",
	            barChartDataSet, PlotOrientation.VERTICAL, true, true, false);

	    CategoryPlot barChartPlot = barChart.getCategoryPlot();
	    BarRenderer barRenderer = new BarRenderer();
	    barChartPlot.setRenderer(barRenderer);
	    barChartPlot.setBackgroundPaint(null);

	    barChartPlot.setOutlineStroke(null); // Remove the outline (border)
	    barChartPlot.setRangeGridlinePaint(Color.gray);
	    // Set category label positions to avoid overlapping on the X-axis
	    CategoryAxis domainAxis = barChartPlot.getDomainAxis();
	    domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

	    return barChart;
	}

	private void createMultiBarChartPage(Document document, List<EnergyPerformanceDTO> energyGenValue)
	        throws Exception {
	    JFreeChart multiBarChart = generateMultiBarChart(energyGenValue);
	    Image chartImage = getImageFromChart(multiBarChart);
	    chartImage.setSpacingBefore(-30);
	    addChartToDocument(document, chartImage);
	}
	
	private void addCo2Avoided(Document document, List<DailyGenerationTodayEnergyDTO> dailyGenValue) {
	    try {
	        Paragraph sectionHeading = new Paragraph("Co2 Avoided");
	        sectionHeading.setSpacingBefore(-220); // Adjust the negative spacing value as needed
	        sectionHeading.setSpacingAfter(10);
	        sectionHeading.setAlignment(Element.ALIGN_LEFT); // Set the alignment as needed
	        document.add(sectionHeading);

	        // Define the column widths (adjust these values as needed)
	        float[] columnWidths = {-30f, -90f}; // First column: 70%, Second column: 30%

	       
	        PdfPTable table = new PdfPTable(columnWidths);
	        table.setWidthPercentage(100);
	        
	        addTableHeader(table, "Date", headerFont, headerBackgroundColor, headerBackgroundColor);
	        addTableHeader(table, "CO2 Avoided (in T)", headerFont, headerBackgroundColor, headerBackgroundColor);

	        for (int i = 0; i < dailyGenValue.size(); i++) {
	            addTableRow(table, dailyGenValue.get(i).getTimestamp(), headerFont, totalTableBorderColor);
	            addTableRow(table, String.valueOf(0.0067 * dailyGenValue.get(i).getTodayEnergy()), headerFont, totalTableBorderColor);
	        }

	        document.add(table);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	private void invAcEnergyTable(Document document,List<EnergyPerformanceDTO> energyGenValue) {

		try {
			addSectionHeading(document, "Inverter AC Energy Table",7);

			PdfPTable table = new PdfPTable(EquipMap.size()+1);
			table.setWidthPercentage(100);
			//table.
			//table.set

				addTableHeader(table,"Date", headerFont, headerBackgroundColor,
					  headerBackgroundColor); 
			  for (int i=0;i<EquipMap.size(); i++) 
			  { 
				  addTableHeader(table,EquipMap.get(energyGenValue.get(i).getEquipmentId()), headerFont, headerBackgroundColor,
			  headerBackgroundColor); 
			  }
			  for (int i=0;i<energyGenValue.size(); i++) 
			  { 
				  addTableRow(table,energyGenValue.get(i).getTodayEnergy().toString(), headerFont, totalTableBorderColor); 
			  }
			  
			 
			document.add(table);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void leaveEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

//	private String getPdfNameWithDate() {
//	    String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//	    return pdfDir + "\\" + reportFileName + "_" + localDateString + ".pdf";
//	}
	private void savePdfLocally(ByteArrayOutputStream outputStream, String fileName) {
		try (FileOutputStream fos = new FileOutputStream(pdfDir + "\\" + fileName)) {
			fos.write(outputStream.toByteArray());
			System.out.println("PDF Report saved locally: " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
			// Handle the exception, log it, or perform necessary actions
		}
	}

	class CustomRenderer extends BarRenderer {
		private Paint barColor; // The single color for all bars

		public CustomRenderer() {
			// Use specified color code
			this.barColor = hexToColor("#87CEEB"); // Blue
			setShadowVisible(false); // Disable bar chart background shadows
			setBarPainter(new StandardBarPainter()); // Disable gradient effect
		}

		public Paint getItemPaint(final int row, final int column) {
			return this.barColor; // Return the same color for all bars
		}

		@Override
		public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot,
				CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
			// Retrieve the value for the current bar
			Number value = dataset.getValue(row, column);

			// Call the parent method to draw the bar
			super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column, pass);

			// Display bar value centered on the bar
			if (value != null) {
				double x = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
				double y = rangeAxis.valueToJava2D(value.doubleValue(), dataArea, plot.getRangeAxisEdge());

				// Convert the value to a string
				String label = value.toString();

				// Draw the label on the bar centered
				g2.setColor(Color.BLACK); // Set color for the bar values
				g2.rotate(-Math.PI / 2, x, y); // Rotate the graphics context for vertical text
				FontMetrics metrics = g2.getFontMetrics(); // Get font metrics to calculate text width
				int labelWidth = metrics.stringWidth(label); // Calculate text width
				int xPos = (int) (x - labelWidth / 0.7); // Adjust x-coordinate for centering
				int yPos = (int) y + metrics.getHeight() / 2; // Adjust y-coordinate for centering
				g2.drawString(label, xPos, yPos); // Draw the text
				g2.rotate(Math.PI / 2, x, y); // Restore the original rotation
			}
		}

		private Color hexToColor(String hexCode) {
			// Convert hexadecimal color code to Color object
			int red = Integer.valueOf(hexCode.substring(1, 3), 16);
			int green = Integer.valueOf(hexCode.substring(3, 5), 16);
			int blue = Integer.valueOf(hexCode.substring(5, 7), 16);
			return new Color(red, green, blue);
		}
	}

	private void createTableFromApi(Document document) throws Exception {
		// Make an HTTP request to the API URL
		String apiUrl = "http://3.109.2.47:8085/Dashboardsrv/Site/getSiteDetails=1117";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
		String responseBody = responseEntity.getBody();

		// Parse the API response (assuming it is a JSON object)
		JSONObject jsonObject = new JSONObject(responseBody);

		// Get the equipmentList array from the JSON response
		JSONArray equipmentList = jsonObject.getJSONArray("equipmentList");

		// Create a table with fixed number of columns based on your JSON object fields
		PdfPTable table = new PdfPTable(7); // Assuming 7 columns: customerNaming, performanceRatio, totalEnergy,
											// todayEnergy, equipmentCode, equipmentID, networkStatus
		table.setWidthPercentage(100);

		// Add table headers with AliceBlue border color
		addTableHeader(table, "Customer Naming", headerFont, headerBackgroundColor, totalTableBorderColor);
		addTableHeader(table, "Performance Ratio", headerFont, headerBackgroundColor, totalTableBorderColor);
		addTableHeader(table, "Total Energy", headerFont, headerBackgroundColor, totalTableBorderColor);
		addTableHeader(table, "Today Energy", headerFont, headerBackgroundColor, totalTableBorderColor);
		addTableHeader(table, "Equipment Code", headerFont, headerBackgroundColor, totalTableBorderColor);
		addTableHeader(table, "Equipment ID", headerFont, headerBackgroundColor, totalTableBorderColor);
		addTableHeader(table, "Network Status", headerFont, headerBackgroundColor, totalTableBorderColor);

		// Add table rows from the equipmentList with the same border color
		for (int i = 0; i < equipmentList.length(); i++) {
			JSONObject equipment = equipmentList.getJSONObject(i);
			addTableRow(table, equipment.optString("customerNaming", ""), tableFont, totalTableBorderColor);
			addTableRow(table, equipment.optString("performanceRatio", ""), tableFont, totalTableBorderColor);
			addTableRow(table, equipment.optString("totalEnergy", ""), tableFont, totalTableBorderColor);
			addTableRow(table, equipment.optString("todayEnergy", ""), tableFont, totalTableBorderColor);
			addTableRow(table, equipment.optString("equipmentCode", ""), tableFont, totalTableBorderColor);
			addTableRow(table, String.valueOf(equipment.opt("equipmentID")), tableFont, totalTableBorderColor);
			addTableRow(table, equipment.optString("networkStatus", ""), tableFont, totalTableBorderColor);
		}

		document.add(table); // Add the table to the PDF document
	}

	private void addTableHeader(PdfPTable table, String header, Font font, BaseColor backgroundColor,
			BaseColor borderColor) {
		PdfPCell cell = new PdfPCell(new Phrase(header, font));
		cell.setBackgroundColor(backgroundColor);
		cell.setBorderColor(borderColor); // Set border color
		table.addCell(cell);
	}

	private void addTableRow(PdfPTable table, String content, Font font, BaseColor borderColor) {
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorderColor(borderColor); // Set border color for table row cell
		table.addCell(cell);
	}

	private void addSectionHeading(Document document, String heading,int count) throws Exception {
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC,BaseColor.DARK_GRAY);
		Paragraph paragraph = new Paragraph(count+"."+heading, font);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setSpacingAfter(10);
		paragraph.setSpacingBefore(-40);
		document.add(paragraph);
	}

	private PdfPTable generateTableOfContents() {
		PdfPTable tocTable = new PdfPTable(3);
		tocTable.setWidthPercentage(100);
		try {
			tocTable.setWidths(new int[] { 1, 6, 2 });
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tocTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		tocTable.setHorizontalAlignment(Element.ALIGN_LEFT);

		// Counter for serial numbers
		int serialNumber = 1;
		addTableOfContentsEntry(tocTable, serialNumber++, "Bar Chart", 3);
		addTableOfContentsEntry(tocTable, serialNumber++, "MultiLine Chart ", 3);
		addTableOfContentsEntry(tocTable, serialNumber++, "Line Chart ", 4);
		// Add entries for each section with corresponding page number
		addTableOfContentsEntry(tocTable, serialNumber++, "Equipment List", 5);
		// Add more entries as needed

		return tocTable;
	}

	private void addTableOfContentsEntry(PdfPTable tocTable, int serialNumber, String sectionName, int pageNumber) {
		Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

		// Serial Number Column
		PdfPCell serialNumberCell = new PdfPCell(new Phrase(String.valueOf(serialNumber), font));
		serialNumberCell.setBorder(PdfPCell.NO_BORDER);
		serialNumberCell.setPadding(5);
		serialNumberCell.setHorizontalAlignment(Element.ALIGN_LEFT);

		// Section Name Column
		PdfPCell sectionNameCell = new PdfPCell(new Phrase(sectionName, font));
		sectionNameCell.setBorder(PdfPCell.NO_BORDER);
		sectionNameCell.setPadding(5);
		sectionNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		sectionNameCell.setIndent(10);

		// Page Number Column
		PdfPCell pageNumberCell = new PdfPCell(new Phrase(String.valueOf(pageNumber), font));
		pageNumberCell.setBorder(PdfPCell.NO_BORDER);
		pageNumberCell.setPadding(5);
		pageNumberCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

		tocTable.addCell(serialNumberCell);
		tocTable.addCell(sectionNameCell);
		tocTable.addCell(pageNumberCell);
	}

	private void sendEmailWithAttachment(String recipientEmail, byte[] pdfData, String fileName, String sitename) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(recipientEmail);
			helper.setSubject("Monthly Report");
			helper.setText("Please find the monthly report attached.");

			// Attach the PDF report
			helper.addAttachment(fileName, new ByteArrayResource(pdfData));

			// Set site name on the first page
			helper.setText("<h2>" + sitename + "</h2>", true); // HTML content for the email body

			// Send the email
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			// Handle the exception, log it, or perform necessary actions
		}
	}
	public List<UserReportMap> getReportByTimePeriod(String timeperiod) {
		return schedulingReportRepository.findByTimePeriod(timeperiod);
}
}