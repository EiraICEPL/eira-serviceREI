package com.hummersoft.eira.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class DateUtil {
	
	
	public Timestamp[] formatTimestamps(Date fromDate, Date toDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String fromDateStr = dateFormat.format(fromDate);
		String toDateStr = dateFormat.format(toDate);

		String fromDateTimeStr = fromDateStr + " 00:05:00";
		String toDateTimeStr = toDateStr + " 13:30:00";

		try {
			SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date fromDateWithTime = dateTimeFormat.parse(fromDateTimeStr);
			Date toDateWithTime = dateTimeFormat.parse(toDateTimeStr);

			Timestamp fDate = new Timestamp(fromDateWithTime.getTime());
			Timestamp tDate = new Timestamp(toDateWithTime.getTime());

			return new Timestamp[] { fDate, tDate };
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	public Date[] setDateRange(String timePeriod) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date fromDate = null;
		Date toDate = null;

		if (timePeriod != null) {
			switch (timePeriod.toLowerCase()) {
			case "today":
				fromDate = new Date();
				toDate = new Date();
				break;
			case "yesterday":
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				fromDate = calendar.getTime();
				toDate = calendar.getTime();
				break;
			case "this month":
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				fromDate = calendar.getTime();
				toDate = new Date();
				break;
			case "last month":
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				Date lastMonth = calendar.getTime();
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				fromDate = calendar.getTime();
				toDate = lastMonth;
				break;
			case "last 30 days":
				calendar.add(Calendar.DAY_OF_MONTH, -30);
				fromDate = calendar.getTime();
				toDate = new Date();
				break;
			case "last 7 days":
				calendar.add(Calendar.DAY_OF_MONTH, -7);
				fromDate = calendar.getTime();
				toDate = new Date();
				break;
			case "last week":
				int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
				int daysToSunday = Calendar.SATURDAY - currentDayOfWeek + 1;
				calendar.add(Calendar.DAY_OF_MONTH, -daysToSunday - 6);
				fromDate = calendar.getTime();
				calendar.setTime(fromDate);
				calendar.add(Calendar.DAY_OF_MONTH, 6);
				toDate = calendar.getTime();
				break;
			case "this week":
				int currentDayOfWeekThisWeek = calendar.get(Calendar.DAY_OF_WEEK);
				int daysToSaturday = Calendar.SATURDAY - currentDayOfWeekThisWeek;
				calendar.add(Calendar.DAY_OF_MONTH, -daysToSaturday);
				fromDate = calendar.getTime();
				calendar.setTime(fromDate);
				calendar.add(Calendar.DAY_OF_MONTH, 6);
				toDate = calendar.getTime();
				break;
			default:
				fromDate = new Date();
				toDate = new Date();
				break;
			}
		} else {
			fromDate = new Date();
			toDate = new Date();
		}

		return new Date[] { fromDate, toDate };
	}
	public static class HeaderFooter extends PdfPageEventHelper {
	    private Image logo;

	    public HeaderFooter(String logoPath) throws Exception {
	        this.logo = Image.getInstance(logoPath);
	        this.logo.scalePercent(30); // Adjust the logo size as needed
	    }

	    public void onStartPage(PdfWriter pdfWriter, Document document) {
	        System.out.println("onStartPage() method > Writing header in file");

	        // Check if the page number is greater than 1 before adding the logo
	        if (pdfWriter.getPageNumber() > 1) {
	            Rectangle rect = new Rectangle(document.left(), document.bottom(), document.right(), document.top());
	            rect.setBorder(Rectangle.BOX); // Set the border for debugging purposes, you can remove this line in production

	            try {
	                // Add Image from your system
	                // Set absolute position for the logo image in PDF (top left corner)
	                logo.setAbsolutePosition(rect.getLeft() - 10, rect.getTop(-15) - logo.getScaledHeight());

	                // Add image to the document
	                PdfContentByte content = pdfWriter.getDirectContent();
	                content.addImage(logo);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    public void onEndPage(PdfWriter writer, Document document) {
	        Rectangle rect = new Rectangle(document.left(), document.bottom(), document.right(), document.top());
	        rect.setBorder(Rectangle.BOX); // Set the border for debugging purposes, you can remove this line in production

	        // Set absolute position for the logo image in PDF (top left corner)
	        logo.setAbsolutePosition(rect.getLeft() - 10, rect.getTop(-15) - logo.getScaledHeight());

	        // Add image to the document
	        PdfContentByte content = writer.getDirectContent();
	        try {
	            content.addImage(logo);
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }

	        // BOTTOM RIGHT
	        Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.GRAY);
	        Phrase pageNumberPhrase = new Phrase(String.valueOf(writer.getPageNumber()), font);
	        Phrase websitePhrase = new Phrase("www.webdyn.eira.io", font);

	        float pageNumberX = (document.left() + document.right()) / 2; // Center of the page for the page number
	        float pageNumberY = document.bottom() - 20;

	        float websiteX = document.right() - 30; // Right side of the page for the website text
	        float websiteY = document.bottom() - 20;

	        // Add the page number and website text to the footer, except for the first page
	        if (writer.getPageNumber() > 1) {
	            ColumnText.showTextAligned(content, Element.ALIGN_CENTER, pageNumberPhrase, pageNumberX, pageNumberY, 0);
	            ColumnText.showTextAligned(content, Element.ALIGN_RIGHT, websitePhrase, websiteX, websiteY, 0);
	        }
	    }
	}

}
