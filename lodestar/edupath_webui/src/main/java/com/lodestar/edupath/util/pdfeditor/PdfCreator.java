package com.lodestar.edupath.util.pdfeditor;

import java.awt.Color;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.TextPosition;

/**
 * @author yogesh.s
 *
 */
public class PdfCreator
{
	private final PDDocument	document	= new PDDocument();
	private PDPageContentStream	contentStream;

	/**
	 * @param page
	 * @param text
	 * @param textfirstPos
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public void writePDF(PDPage page, String text, TextPosition textfirstPos) throws Exception
	{
		addPDFPage(page);
		contentStream = new PDPageContentStream(document, page, true, true);
		contentStream.beginText();
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
		contentStream.moveTextPositionByAmount(textfirstPos.getXDirAdj() + 15, textfirstPos.getTextMatrix().getYPosition());
		contentStream.showText(text);
		contentStream.endText();
		contentStream.close();
	}

	/**
	 * @param page
	 * @param txtPosMap
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public void writePDFPage(PDPage page, Map<TextPosition, String> txtPosMap) throws Exception
	{
		addPDFPage(page);
		contentStream = new PDPageContentStream(document, page, true, true);
		TextPosition textfirstPos;
		for (Map.Entry<TextPosition, String> posMap : txtPosMap.entrySet())
		{
			textfirstPos = posMap.getKey();
			contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
			contentStream.moveTextPositionByAmount(textfirstPos.getXDirAdj() + 20, textfirstPos.getTextMatrix().getYPosition() + 0);
			contentStream.showText(posMap.getValue());
			contentStream.endText();
		}
		contentStream.close();
	}

	/**
	 * @param page
	 * @param txtPosMap
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public void writePDFPageAsList(PDPage page, Map<TextPosition, Map<String, String>> txtPosMap) throws Exception
	{
		addPDFPage(page);
		contentStream = new PDPageContentStream(document, page, true, true);
		TextPosition textfirstPos;
		int txtY = 24;
		for (Map.Entry<TextPosition, Map<String, String>> posMap : txtPosMap.entrySet())
		{
			textfirstPos = posMap.getKey();
			contentStream.addRect(0, textfirstPos.getTextMatrix().getYPosition() - 60, page.getMediaBox().getWidth(), posMap.getValue().size() * 17);
			contentStream.fill();
			for (Map.Entry<String, String> textMap : posMap.getValue().entrySet())
			{
				contentStream.beginText();
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
				contentStream.moveTextPositionByAmount(textfirstPos.getXDirAdj(), textfirstPos.getTextMatrix().getYPosition() - txtY);
				contentStream.setNonStrokingColor(Color.black);
				contentStream.showText(textMap.getKey() + textMap.getValue());
				contentStream.endText();
				contentStream.setLineWidth(.5F);
				// contentStream.drawLine(textfirstPos.getXDirAdj() + textMap.getKey().length() + 15, textfirstPos.getTextMatrix().getYPosition() - txtY - 2,
				// textfirstPos.getXDirAdj() + 100, textfirstPos.getTextMatrix().getYPosition() - txtY - 2);
				txtY += 15;
			}

		}
		contentStream.close();
	}

	/**
	 * @param page
	 * @throws Exception
	 */
	public void addPDFPage(PDPage page) throws Exception
	{
		document.addPage(page);
	}

	/**
	 * @param path
	 * @throws Exception
	 */
	public void savePDFFile(String path) throws Exception
	{
		document.save(path);
		document.close();
	}
}
