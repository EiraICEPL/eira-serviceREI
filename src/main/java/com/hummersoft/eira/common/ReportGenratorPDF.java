package com.hummersoft.eira.common;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hummersoft.eira.common.DateUtil.HeaderFooter;
import com.hummersoft.eira.dto.DailyGenerationTodayEnergyDTO;
import com.hummersoft.eira.dto.EnergyPerformanceDTO;
import com.hummersoft.eira.service.DailyGenerationService;
import com.hummersoft.eira.service.EnergyPerformanceService;
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

@Component
public class ReportGenratorPDF {

	@Autowired
	private DailyGenerationService dailyGenerationService;
	@Autowired
	private DateUtil dateUtil;
	private EnergyPerformanceService energyPerformanceService;

	private final RestTemplate restTemplate;

	@Autowired
	public ReportGenratorPDF(EnergyPerformanceService energyPerformanceService, RestTemplate restTemplate) {
		this.energyPerformanceService = energyPerformanceService;
		this.restTemplate = restTemplate;
	}

	private String pdfDir = "E:\\REI Eira\\Report";
	private String reportFileName = "Monthly_Report";
	private String reportFileNameDateFormat = "dd-MM-yyyy";
	private String localDateFormat = "dd MMMM yyyy HH:mm:ss";
	private String logoImgPath = "E:\\REI Eira\\Report\\Webdyn colour Logo.png";
	private Float[] logoImgScale = new Float[] { (float) 50, (float) 50 };

	public void generatePdfReport() {
		float leftMargin = 72;
		float rightMargin = 72;
		float topMargin = 72;
		float bottomMargin = 72;

		Document document = new Document();
		document.setMargins(leftMargin, rightMargin, topMargin, bottomMargin);

		try {
			HeaderFooter event = new HeaderFooter(logoImgPath);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(getPdfNameWithDate()));
			document.open();
			addLogo(document);
			addDocTitle(document);

			// Create a new page for the table of contents
			document.newPage();
			writer.setPageEvent(event);
			Paragraph emptyLinesParagraph = new Paragraph();
			leaveEmptyLine(emptyLinesParagraph, 2); // Add 2 empty lines
			document.add(emptyLinesParagraph);

			// Generate Table of Contents
			PdfPTable tocTable = generateTableOfContents();
			document.add(tocTable);

			// First Section: Bar Chart
			document.newPage();
			writer.setPageEvent(event);
			leaveEmptyLine(emptyLinesParagraph, 1); // Add 1 empty line
			document.add(emptyLinesParagraph);
			addSectionHeading(document, "Bar Chart");
			createBarChartPage(document);
			addSectionHeading(document, "MultiLine Chart");

			int siteId = 1117;
			createMultiLineChartPage(document, siteId);

			// Fourth Section: Line Chart (New Page)
			document.newPage();
			writer.setPageEvent(event);
			leaveEmptyLine(emptyLinesParagraph, 1); // Add 1 empty line
			document.add(emptyLinesParagraph);
			addSectionHeading(document, "Line Chart");
			createLineChartPage(document);

			// Fifth Section: Equipment List (New Page)
			document.newPage();
			writer.setPageEvent(event);
			leaveEmptyLine(emptyLinesParagraph, 1); // Add 1 empty line
			document.add(emptyLinesParagraph);
			addSectionHeading(document, "Equipment List");
			createTableFromApi(document);

			document.close();

			System.out.println("------------------Your PDF Report is ready!-------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createMultiLineChartPage(Document document, int siteId) throws Exception {
		JFreeChart multiLineChart = generateMultiLineChart();
		Image chartImage = getImageFromChart(multiLineChart);
		chartImage.setSpacingBefore(-20);
		addChartToDocument(document, chartImage);
	}

	private void createLineChartPage(Document document) throws Exception {
		JFreeChart lineChart = generateLineChart();
		Image chartImage = getImageFromChart(lineChart);
		addChartToDocument(document, chartImage);
	}

	private void createBarChartPage(Document document) throws Exception {
		JFreeChart barChart = generateBarChart();
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

	public JFreeChart generateBarChart() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		Date[] dateRange = dateUtil.setDateRange("last month");
		Timestamp[] timestamps = dateUtil.formatTimestamps(dateRange[0], dateRange[1]);
		List<DailyGenerationTodayEnergyDTO> dailyGenValue = dailyGenerationService.getDgrValue(1117, "custom",
				timestamps[0], timestamps[1]);

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
		plot.setRangeGridlinePaint(Color.WHITE); // Set grid line color to white (same as background)

		return chart;
	}

	private JFreeChart generateLineChart() {
		DefaultCategoryDataset lineChartDataSet = new DefaultCategoryDataset();
		Date[] dateRange = dateUtil.setDateRange("last month");
		Timestamp[] timestamps = dateUtil.formatTimestamps(dateRange[0], dateRange[1]);
		List<DailyGenerationTodayEnergyDTO> dailyGenValue = dailyGenerationService.getDgrValue(1117, "custom",
				timestamps[0], timestamps[1]);

		for (int i = 0; i < dailyGenValue.size(); i++) {
			lineChartDataSet.setValue(dailyGenValue.get(i).getTodayEnergy(), "Energy Gen", Integer.toString(i));
		}

		JFreeChart lineChart = ChartFactory.createLineChart(null, "Time", "Energy Gen (KWh)", lineChartDataSet,
				PlotOrientation.VERTICAL, true, true, false);

		CategoryPlot lineChartPlot = lineChart.getCategoryPlot();
		LineAndShapeRenderer lineChartRenderer = new LineAndShapeRenderer();
		lineChartPlot.setRenderer(lineChartRenderer);
		lineChartPlot.setBackgroundPaint(null);
		lineChartPlot.setOutlineStroke(null); // Remove the outline (border)

		return lineChart;
	}

	public JFreeChart generateMultiLineChart() throws IOException {
		DefaultCategoryDataset lineChartDataSet = new DefaultCategoryDataset();
		Date[] dateRange = dateUtil.setDateRange("last month");
		Timestamp[] timestamps = dateUtil.formatTimestamps(dateRange[0], dateRange[1]);
		Integer[] equipmentIds = { 3230, 3231, 3232, 3233, 3234, 3235, 3236, 3237, 3238, 3239, 3240, 3241, 3242 };
		List<Integer> intList = Arrays.asList(equipmentIds);

		try {
			List<EnergyPerformanceDTO> energyGenValue = energyPerformanceService.getEnergyPerformanceValue(1117,
					"custom", timestamps[0], timestamps[1], intList);

			for (EnergyPerformanceDTO energyPerformance : energyGenValue) {
				String category = String.valueOf(energyPerformance.getEquipmentId());
				String time = energyPerformance.getTimestamp().toString();
				double energyValue = energyPerformance.getTodayEnergy();

				lineChartDataSet.addValue(energyValue, category, time);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Handle the exception according to your application's error handling strategy.
		}

		JFreeChart lineChart = ChartFactory.createLineChart(null, "Time", "Energy Value", lineChartDataSet,
				PlotOrientation.VERTICAL, true, true, false);

		CategoryPlot lineChartPlot = lineChart.getCategoryPlot();
		LineAndShapeRenderer lineChartRenderer = new LineAndShapeRenderer();
		lineChartPlot.setRenderer(lineChartRenderer);
		lineChartPlot.setBackgroundPaint(null);
		lineChartPlot.setOutlineStroke(null); // Remove the outline (border)

		return lineChart;
	}

	private static void leaveEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private String getPdfNameWithDate() {
		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(reportFileNameDateFormat));
		return pdfDir + reportFileName + "-" + localDateString + ".pdf";
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
				int xPos = (int) (x - labelWidth / 0.5); // Adjust x-coordinate for centering
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

		// Set font size for table content
		Font tableFont = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);

		// Set background color for table headers
		BaseColor headerBackgroundColor = new BaseColor(135, 206, 250); // Light Blue color

		// Set font color for header text
		Font headerFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(64, 64, 64)); // Dark Gray
																										// color for
																										// header text

		// Set total table border color to AliceBlue (#F0F8FF)
		BaseColor totalTableBorderColor = new BaseColor(240, 248, 255); // AliceBlue color

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

	private void addSectionHeading(Document document, String heading) throws Exception {
		Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
		Paragraph paragraph = new Paragraph(heading, font);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setSpacingAfter(20);
		paragraph.setSpacingBefore(-30);
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
}