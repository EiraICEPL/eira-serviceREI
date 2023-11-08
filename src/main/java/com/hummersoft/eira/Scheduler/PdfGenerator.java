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
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hummersoft.eira.common.DateUtil;
import com.hummersoft.eira.common.DateUtil.HeaderFooter;
import com.hummersoft.eira.dto.DailyGenerationTodayEnergyDTO;
import com.hummersoft.eira.dto.EnergyPerformanceDTO;
import com.hummersoft.eira.dto.EquipmentDTO;
import com.hummersoft.eira.dto.EventDTO;
import com.hummersoft.eira.dto.SpecificYieldDTO;
import com.hummersoft.eira.model.Site;
import com.hummersoft.eira.model.UserReportMap;
import com.hummersoft.eira.repository.SchedulingReportRepository;
import com.hummersoft.eira.service.DailyGenerationService;
import com.hummersoft.eira.service.EnergyPerformanceService;
import com.hummersoft.eira.service.EquipmentService;
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

@Component
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
	private EquipmentService equipService;

	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${report.siteTable}")
	private List<String> columnNames;
	
	@Value("${report.eventTable}")
	private List<String> eventColumns;
	
	@Value("${report.energyTable}")
	private List<String> energyColumns;
	
	@Value("${report.equipmentTable}")
	private List<String> equipmentTable;
		
	@Value("${report.siteStatistics}")
	private List<String> sitestatsTable;
		
	private String pdfDir = "D:\\PdfReportRepo";
	private String reportFileName = "Asset Management Report";
	private String localDateFormat = "dd MMMM yyyy HH:mm:ss";
	private String logoImgPath = "https://eira-logo.s3.ap-south-1.amazonaws.com/Webdyn+colour+Logo.png";
	
	private Float[] logoImgScale = new Float[] { (float) 50, (float) 50 };

	// Set font size for table content
	Font tableFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);

	// Set background color for table headers
	BaseColor headerBackgroundColor = new BaseColor(135, 206, 250); // Light Blue color
	BaseColor headerBorderColor = new BaseColor(0, 0, 0); // Light Blue color

	// Set total table border color to AliceBlue (#F0F8FF)
	BaseColor totalTableBorderColor = new BaseColor(102, 102, 102); // AliceBlue color

	// Set font color for header text
	Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, new BaseColor(64, 64, 64));
	Font rowFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, new BaseColor(64, 64, 64));// Dark Gray color
	
	Font invheaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD, new BaseColor(64, 64, 64));
	Font invrowFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, new BaseColor(64, 64, 64));
																									
	Font pageHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK); // header text

	Map<Integer, String> EquipMap;
	
	//@Scheduled(cron = "0 */1 * * * ?")
	@Scheduled(cron = "0 0 0 8 * *")
	public void sendEmailsWithPDFAttachments() {
	
		// Get the list of UserReportMap objects by time period for different sites
		List<UserReportMap> reportMaps = getReportByTimePeriod("Monthly");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH_mm");

		for (UserReportMap reportMap : reportMaps) {
			Integer siteId = reportMap.getSiteId();
			String sitename = reportMap.getSiteName();
			String recipientEmail = reportMap.getMailId();

			try {
				// Generate PDF report for the current site
				Date[] dateRange = dateUtil.setDateRange("last month");
				Timestamp[] timestamps = dateUtil.formatTimestamps(dateRange[0], dateRange[1]);
				ByteArrayOutputStream pdfOutputStream = generatePdfReport(siteId, timestamps[0], timestamps[1]);

				// Save the PDF report locally (optional)
				String fileName = sitename + "_Monthly_Report_" + df.format(new Date()) + ".pdf";
				//savePdfLocally(pdfOutputStream, fileName);

				// Send the email with the PDF report attached
				sendEmailWithAttachment(recipientEmail, pdfOutputStream.toByteArray(), fileName, sitename);

			} catch (Exception e) {
				e.printStackTrace();
				// Handle the exception, log it, or perform necessary actions
			}
		}
	}

	
	private void addSiteDetails(Document document, Optional<Site> site, Double totalEnergy, int eventDetails) {

		try {
			
			PdfPTable table = new PdfPTable(columnNames.size());
			table.setWidthPercentage(100);
			table.setSpacingBefore(5f);
			table.setSpacingAfter(15f);
			table.getDefaultCell().setBorder(3);
			table.getDefaultCell().setMinimumHeight(15f);
			//table.getDefaultCell().ser

			for (int i = 0; i < columnNames.size(); i++) {
				addTableHeader(table, columnNames.get(i), headerFont, headerBackgroundColor, headerBorderColor);
			}

			addTableRow(table, site.get().getSiteName(), rowFont, totalTableBorderColor);
			addTableRowCenter(table,(site.get().getSiteTypeID()==1?"Roof Top":"Utility"), rowFont, totalTableBorderColor);
			addTableRowCenter(table, String.valueOf(site.get().getInstallationCapacity())+" kWp", rowFont, totalTableBorderColor);
			
			document.add(table);
			
			
			addSubSectionHeading(document,site.get().getSiteName()+" - Equipment Details", 1.1);
			List<Object[]> equipmentList = equipService.findEquipmentStatistics(BigInteger.valueOf(site.get().getSiteId()));
			
			PdfPTable table1 = new PdfPTable(equipmentTable.size());
			table1.setWidthPercentage(100);
			table.setSpacingBefore(5f);
			table1.setSpacingAfter(10f);
			table1.getDefaultCell().setBorder(3);
			table1.getDefaultCell().setMinimumHeight(20f);
			
			for (int i = 0; i < equipmentTable.size(); i++) {
				addTableHeader(table1, equipmentTable.get(i), headerFont, headerBackgroundColor, headerBorderColor);
			}
			for (Object object : equipmentList) {
				Object[] obj = (Object[]) object;
				
				addTableRow(table1,obj[2].toString(), rowFont, totalTableBorderColor);
				addTableRow(table1,obj[1].toString(), rowFont, totalTableBorderColor);
				addTableRowCenter(table1,obj[0].toString(), rowFont, totalTableBorderColor);
				
			}
			
			document.add(table1);
			
			
			addSubSectionHeading(document,site.get().getSiteName()+" - Performance", 1.2);
			PdfPTable table2 = new PdfPTable(sitestatsTable.size());
			table2.setWidthPercentage(100);
			table2.setSpacingAfter(10f);
			table2.getDefaultCell().setBorder(3);
			table2.getDefaultCell().setMinimumHeight(20f);
			
			for (int i = 0; i < sitestatsTable.size(); i++) {
				addTableHeader(table2, sitestatsTable.get(i), headerFont, headerBackgroundColor, headerBorderColor);
			}
			
			addTableRowCenter(table2,String.format("%.2f",totalEnergy)+" kWh", rowFont, totalTableBorderColor);
			addTableRowCenter(table2,String.format("%.2f",totalEnergy/(site.get().getInstallationCapacity() * 24 * 31) *100), rowFont, totalTableBorderColor);
			addTableRowCenter(table2,String.valueOf(eventDetails), rowFont, totalTableBorderColor);
			
			document.add(table2);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public ByteArrayOutputStream generatePdfReport(Integer siteId, Timestamp fromDate, Timestamp toDate ) throws Exception {
		
		float leftMargin = 72;
		float rightMargin = 72;
		float topMargin = 62;
		float bottomMargin = 62;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Document document = new Document();
		document.setMargins(leftMargin, rightMargin, topMargin, bottomMargin);
		
		try {

			Optional<Site> site = siteService.getSiteById(siteId);

			// get all equipments
			List<EquipmentDTO> lstAllEquipments = siteService.listAllEquipment(siteId);
			List<Integer> inv_id = new ArrayList<>();
			Double SumCapacity = 0.0;
			Double totalEnergy = 0.0;
			Double installationCap = site.get().getInstallationCapacity();
			EquipMap = new HashMap<Integer, String>();

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
			
			// get energy details
			

			List<DailyGenerationTodayEnergyDTO> dailyGenValue = dailyGenerationService.getDgrValue(siteId, "custom",
					fromDate, toDate);

			for (DailyGenerationTodayEnergyDTO generation : dailyGenValue) {
				SpecificYieldDTO specificDTO = new SpecificYieldDTO();
				if (generation.getTodayEnergy() != null && generation.getTodayEnergy() != 0) {
					Double SpecificYield = ((generation.getTodayEnergy()) /installationCap);
					totalEnergy = totalEnergy+generation.getTodayEnergy();
					specificDTO.setTodayEnergy(generation.getTodayEnergy());
					specificDTO.setTimeStamp(generation.getTimestamp());
					String formatted = df.format(SpecificYield);
					double SpecificYieldRounded = Double.parseDouble(formatted);
					specificDTO.setSpecificYield(SpecificYieldRounded);

				}
				specificYieldInv.add(specificDTO);
			}

			List<EnergyPerformanceDTO> energyGenValue = energyPerformanceService.getEnergyPerformanceValue(siteId,
					"custom", fromDate, toDate, inv_id);
			
			List<EventDTO> eventDetails = siteService.findTotalEventsBySiteIdforReports(siteId, fromDate, toDate);
			

			HeaderFooter event = new HeaderFooter(logoImgPath);
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);
			document.open();
			addLogo(document);
			addDocTitle(document,site.get().getSiteName());
			
			int headingCount = 1;
			Paragraph emptyLinesParagraph;
			// new to page to add site details
			document.newPage();
			writer.setPageEvent(event);
			
			emptyLinesParagraph = new Paragraph();
			Paragraph paragraph1 = new Paragraph();
			document.add(paragraph1);
			leaveEmptyLine(emptyLinesParagraph,5); // Add 1 empty line
			document.add(emptyLinesParagraph);
			addSectionHeading(document, "Site Details", headingCount++);
			addSiteDetails(document,site,totalEnergy,eventDetails.size());


			// First Section: Bar Chart
			document.newPage();
			writer.setPageEvent(event);
			Paragraph paragraph = new Paragraph();
			document.add(paragraph);
			leaveEmptyLine(emptyLinesParagraph, 1); // Add 1 empty line
			document.add(emptyLinesParagraph);
			addSectionHeading(document, "Energy Performance", headingCount++);
			createBarChartPage(document, dailyGenValue);
		
			Paragraph paragraph2 = new Paragraph();
			document.add(paragraph2);
			addSectionHeading(document, "Specific Yield", headingCount++);
			createLineChartPage(document, specificYieldInv);

			document.newPage();
			writer.setPageEvent(event);
			leaveEmptyLine(emptyLinesParagraph, 1); // Add 1 empty line
			document.add(emptyLinesParagraph);
			addSectionHeading(document, "Inverter Performance", headingCount++);
			createMultiLineChartPage(document, energyGenValue);
			
			document.newPage();
			writer.setPageEvent(event);
			leaveEmptyLine(emptyLinesParagraph, -2); 
			 document.add(emptyLinesParagraph);
			addSectionHeading(document, "Energy Generation - Details", headingCount++);
			addEnergyDetails(document, dailyGenValue,installationCap);
			
			// Second Section: MultiLine Chart
			//document.newPage();
			//writer.setPageEvent(event);
			
			document.newPage();
			writer.setPageEvent(event);
			leaveEmptyLine(emptyLinesParagraph, -4); // Add 1 empty line
			 document.add(emptyLinesParagraph);
			addSectionHeading(document, "Co2 Avoided", headingCount++);
			addCo2Avoided(document, dailyGenValue);
			
			document.newPage();
			writer.setPageEvent(event);
			leaveEmptyLine(emptyLinesParagraph, -3); // Add 1 empty line
			document.add(emptyLinesParagraph);
			addSectionHeading(document, "Inverter AC Energy Details", headingCount++);
			invAcEnergyTable(document, energyGenValue);
			
		
			document.newPage();
			writer.setPageEvent(event);
			leaveEmptyLine(emptyLinesParagraph, -4); // Add 1 empty line
			document.add(emptyLinesParagraph);
			addSectionHeading(document, "Top 10 events by occurence", headingCount++);
			addEvents(document, eventDetails);


			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return outputStream;
	}

	private void createMultiLineChartPage(Document document, List<EnergyPerformanceDTO> energyGenValue)
			throws Exception {
		JFreeChart multiLineChart = generateMultiLineChart(energyGenValue);
		int width = 1000;
		int height = 500;
		java.awt.Image awtImage = multiLineChart.createBufferedImage(width, height);
		com.itextpdf.text.Image chartImage = com.itextpdf.text.Image.getInstance(awtImage, null);
		chartImage.scalePercent(51); // Adjust scale as needed
		
		//Image chartImage = getImageFromChart(multiLineChart);
		chartImage.setSpacingBefore(-30);
		addChartToDocument(document, chartImage);
	}

	private void createLineChartPage(Document document, List<SpecificYieldDTO> specificYieldInv) throws Exception {
		JFreeChart lineChart = generateLineChart(specificYieldInv);
		Image chartImage = getImageFromChart(lineChart);
		chartImage.setSpacingBefore(-150);
		addChartToDocument(document, chartImage);
	}

	private void createBarChartPage(Document document, List<DailyGenerationTodayEnergyDTO> dailyGenValue)
			throws Exception {
		JFreeChart barChart = generateBarChart(dailyGenValue);
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

			img.setSpacingAfter(500);
			img.setAbsolutePosition(xPosition, yPosition);
			document.add(img);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addDocTitle(Document document, String siteName) throws Exception {
		//String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(localDateFormat));
		String lastMonthAndYear = getLastMonthAndYear();

		// Create a new paragraph for the title with adjusted spacing and alignment
		Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLDITALIC);
		Paragraph titleParagraph = new Paragraph(reportFileName, titleFont);
		titleParagraph.setAlignment(Element.ALIGN_CENTER);
		titleParagraph.setSpacingBefore(200); // Adjust spacing before the title
	

		// Create a new paragraph for the subtitle with adjusted spacing and alignment
		Font subtitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, new BaseColor(51, 102, 153));
		Paragraph subtitleParagraph = new Paragraph(siteName+" - "+lastMonthAndYear, subtitleFont);
		subtitleParagraph.setAlignment(Element.ALIGN_CENTER);
		subtitleParagraph.setSpacingAfter(50); // Adjust spacing after the subtitle

		// Add title and subtitle paragraphs to the document
		document.add(titleParagraph);
		document.add(subtitleParagraph);
	}

	private String getLastMonthAndYear() {
		LocalDateTime lastMonth = LocalDateTime.now().minusMonths(1);
		return lastMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + "_" + lastMonth.getYear();
	}



	public JFreeChart generateBarChart(List<DailyGenerationTodayEnergyDTO> dailyGenValue) {
	    DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

	    for (int i = 0; i < dailyGenValue.size(); i++) {
	    	
			// Assuming getTimestamp() returns a String in "yyyy-MM-dd" format
			String timestamp = dailyGenValue.get(i).getTimestamp(); 
			
			// Extract day from "yyyy-MM-dd"
			String formattedDay = timestamp.substring(8, 10); 
	        // Incrementing the index by 1 to start the axis values from 1
	        dataSet.setValue(dailyGenValue.get(i).getTodayEnergy(), "Energy Gen", formattedDay);
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

	    // Set custom category axis to start from 1
	    CategoryAxis domainAxis = (CategoryAxis) plot.getDomainAxis();
	    domainAxis.setLowerMargin(0.01); // Adjust the lower margin to fit the labels
	    domainAxis.setCategoryMargin(0.2); // Adjust the category margin for spacing between bars

	    return chart;
	}

	private JFreeChart generateLineChart(List<SpecificYieldDTO> specificYieldInv) {
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
		Color lineColor = Color.decode("#3AC9BA");

		lineChartRenderer.setSeriesPaint(0, lineColor); // Assuming you have only one series, change the series index if needed
		
		/*
		 * StatisticalLineAndShapeRenderer renderer = new
		 * StatisticalLineAndShapeRenderer(true, true); renderer.set
		 */
		// Set the line thickness (stroke) for all series
		float lineWidth = 3.0f; // Adjust the line thickness as needed

		for (int i = 0; i < lineChartDataSet.getRowCount(); i++) {
			lineChartRenderer.setSeriesStroke(i, new BasicStroke(lineWidth));
		}
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
				
				// Assuming getTimestamp() returns a String in "yyyy-MM-dd" format
				String timestamp = energyPerformance.getTimestamp(); 
				
				// Extract day from "yyyy-MM-dd"
				String formattedDay = timestamp.substring(8, 10); 
				
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
	
		
		/*
		 * LegendItemCollection legend = new LegendItemCollection(); for (int i = 0; i <
		 * seriecCount; ++i) { lineChart.getXYPlot().getRenderer().setSeriesPaint(i,
		 * colorPalette.get(i)); LegendItem li = new LegendItem(data.getSeriesName(i),
		 * "-", null, null, Plot.DEFAULT_LEGEND_ITEM_BOX, colorPalette.get(i));
		 * legend.add(li); } chart.getXYPlot().setFixedLegendItems(legend);
		 */

		return lineChart;
	}

	public JFreeChart generateMultiBarChart(List<EnergyPerformanceDTO> energyGenValue) {
		DefaultCategoryDataset barChartDataSet = new DefaultCategoryDataset();

		try {
			for (EnergyPerformanceDTO energyPerformance : energyGenValue) {
				String category = EquipMap.get(energyPerformance.getEquipmentId());
				String timestamp = energyPerformance.getTimestamp(); // Assuming getTimestamp() returns a String in
																		// "yyyy-MM-dd" format
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

	/*
	 * private void createMultiBarChartPage(Document document,
	 * List<EnergyPerformanceDTO> energyGenValue) throws Exception { JFreeChart
	 * multiBarChart = generateMultiBarChart(energyGenValue); Image chartImage =
	 * getImageFromChart(multiBarChart); chartImage.setSpacingBefore(-30);
	 * addChartToDocument(document, chartImage); }
	 */

	private void addCo2Avoided(Document document, List<DailyGenerationTodayEnergyDTO> dailyGenValue) {
		try {
			
			// Define the column widths (adjust these values as needed)
			float[] columnWidths = { -30f, -30f }; // First column: 70%, Second column: 30%
			Double co2Avoided = null;

			PdfPTable table = new PdfPTable(columnWidths);
			table.setWidthPercentage(40);

			addTableHeader(table, "Date", headerFont, headerBackgroundColor, headerBorderColor);
			addTableHeader(table, "CO2 Avoided (in T)", headerFont, headerBackgroundColor, headerBorderColor);

			for (int i = 0; i < dailyGenValue.size(); i++) {
				
				co2Avoided = .00067 * dailyGenValue.get(i).getTodayEnergy();
				addTableRowCenter(table, dailyGenValue.get(i).getTimestamp(), rowFont, totalTableBorderColor);
				addTableRowCenter(table,String.format("%.2f",co2Avoided), rowFont,
						totalTableBorderColor);
			}

			document.add(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addEnergyDetails(Document document, List<DailyGenerationTodayEnergyDTO> dailyGenValue, Double capacity ) {
		try {
						
			PdfPTable table = new PdfPTable(energyColumns.size());
			table.setWidthPercentage(100);
			

			for (int i = 0; i < energyColumns.size(); i++) {
				addTableHeader(table, energyColumns.get(i), headerFont, headerBackgroundColor, headerBorderColor);
			}

			for (int i = 0; i < dailyGenValue.size(); i++) {
				
				String yield = String.format("%.2f", dailyGenValue.get(i).getTodayEnergy()/capacity);
				addTableRowCenter(table, dailyGenValue.get(i).getTimestamp(), rowFont, totalTableBorderColor);
				addTableRowCenter(table, dailyGenValue.get(i).getTodayEnergy().toString() , rowFont, totalTableBorderColor);
				//addTableRow(table, dailyGenValue.get(i).getIrradiation().toString(), rowFont, totalTableBorderColor);
				addTableRowCenter(table,yield , rowFont, totalTableBorderColor);	

			}

			document.add(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void addEvents(Document document, List<EventDTO> eventDetails ) {
		try {
			Paragraph sectionHeading = new Paragraph();
			
			sectionHeading.setAlignment(Element.ALIGN_LEFT); // Set the alignment as needed
			document.add(sectionHeading);

			
			PdfPTable table = new PdfPTable(eventColumns.size());
			table.setWidthPercentage(100);

			for (int i = 0; i < eventColumns.size(); i++) {
				addTableHeader(table, eventColumns.get(i), headerFont, headerBackgroundColor, headerBorderColor);
			}

			for (int i = 0; i < eventDetails.size(); i++) {
				
				addTableRow(table, eventDetails.get(i).getEquipmentName(), rowFont, totalTableBorderColor);
				addTableRow(table, eventDetails.get(i).getErrorMessage(), rowFont, totalTableBorderColor);
				addTableRow(table, eventDetails.get(i).getEventTimestampText(), rowFont, totalTableBorderColor);
				addTableRowCenter(table, eventDetails.get(i).getEventOccurrence().toString(), rowFont, totalTableBorderColor);	
				
				if (i==10)
					break;
			}

			document.add(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void invAcEnergyTable(Document document, List<EnergyPerformanceDTO> energyGenValue) {

		try {
			
			Map<String, Map> datewiseEnergyMap = new HashMap<String, Map>();
			Map<String, String> inverterEnergyMap = new HashMap<String, String>();
			String date =null;
			String prevDate = null;
			
			PdfPTable table = new PdfPTable(EquipMap.size() + 1);
			table.setWidthPercentage(100);
			
			// table.
			// table.set

			addTableHeader(table, "Date", invheaderFont, headerBackgroundColor, headerBorderColor);
		
			for (Map.Entry<Integer, String> set :
				EquipMap.entrySet()) {
				
				addTableHeader(table, set.getValue(), invheaderFont,
						headerBackgroundColor, headerBorderColor);
			}
			
			for (int i = 0; i < energyGenValue.size(); i++) {
				
				date = energyGenValue.get(i).getTimestamp();
				
				inverterEnergyMap.put(EquipMap.get(energyGenValue.get(i).getEquipmentId()), 
						energyGenValue.get(i).getTodayEnergy().toString());
				
				if (null != prevDate && !date.equals(prevDate)) {
					datewiseEnergyMap.put(date, inverterEnergyMap);
					inverterEnergyMap = new HashMap<String, String>();
				}
				prevDate =date;

			}
			Iterator iterate1 = datewiseEnergyMap.keySet().iterator();
			while (iterate1.hasNext()) {
				String key = (String)iterate1.next();
				addTableRowCenter(table, key, invrowFont, totalTableBorderColor);
				Iterator iterate2 = datewiseEnergyMap.get(key).keySet().iterator();
				for (Map.Entry<Integer, String> set :
					EquipMap.entrySet()) {
				addTableRowCenter(table,(String) datewiseEnergyMap.get(key).get(set.getValue()), invrowFont, totalTableBorderColor);
				}
				
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

		        // Adjust the position for values less than 5000 on the y-axis
		        if (value.doubleValue() < 6500) {
		            y -= 40; // Adjust the y-coordinate by subtracting a value (e.g., 20 pixels)
		        }

		        // Convert the value toFline a string
		        String label = value.toString();

		        // Draw the label on the bar centered
		        g2.setColor(Color.BLACK); // Set color for the bar values
		        g2.rotate(-Math.PI / 2, x, y); // Rotate the graphics context for vertical text
		        FontMetrics metrics = g2.getFontMetrics(); // Get font metrics to calculate text width
		        int labelWidth = metrics.stringWidth(label); // Calculate text width
		        int xPos = (int) (x - labelWidth / 1.0); // Adjust x-coordinate for centering
		        int yPos = (int) y + metrics.getHeight() / 2; // Adjust y-coordinate for centering
		        g2.drawString(label, xPos, yPos); // Draw the text
		        g2.rotate(Math.PI / 2, x, y); // ResFtore the original rotation
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

	

	private void addTableHeader(PdfPTable table, String header, Font font, BaseColor backgroundColor,
			BaseColor borderColor) {
		PdfPCell cell = new PdfPCell(new Phrase(header, font));
		cell.setBackgroundColor(backgroundColor);
		cell.setBorderColor(borderColor);
		;// Set border color
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
	}

	private void addTableRow(PdfPTable table, String content, Font font, BaseColor borderColor) {
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorderColor(borderColor); // Set border color for table row cell
		table.addCell(cell);
	}
	
	private void addTableRowCenter(PdfPTable table, String content, Font font, BaseColor borderColor) {
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorderColor(borderColor); // Set border color for table row cell
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
	}

	private void addSectionHeading(Document document, String heading, int count) throws Exception {
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, BaseColor.DARK_GRAY);
		Paragraph paragraph = new Paragraph(count + "." + heading, font);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setSpacingBefore(-70);
		paragraph.setSpacingAfter(15);
		document.add(paragraph);
	}
	
	
	private void addSubSectionHeading(Document document, String heading, double count) throws Exception {
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, BaseColor.DARK_GRAY);
		Paragraph paragraph = new Paragraph(count + "." + heading, font);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		//paragraph.setSpacingBefore(-5);
		paragraph.setSpacingAfter(15);
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
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

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
			helper.setSubject("Asset Manangement Report for site:"+sitename);
			//helper.setText("Please find attached the report for the site - ");

			// Attach the PDF report
			helper.addAttachment(fileName, new ByteArrayResource(pdfData));

			// Set site name on the first page
			helper.setText("Please find attached the report for the site - <h2>" + sitename + "</h2>", true); // HTML content for the email body

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