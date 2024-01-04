package com.lodestar.edupath.util.pdfeditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.datatransferobject.enumtype.PDFContentSearchEnum;

/**
 * @author yogesh.s
 *
 */
public class PDFManager
{

	private static final Logger								OUT	= LoggerFactory.getLogger(PDFManager.class);
	private PDFParser										parser;
	private PDDocument										pdDoc;
	private COSDocument										cosDoc;

	private String											text;
	private String											filePath;
	private String											saveFilePath;
	private File											file;
	private int												fromPage;
	private int												toPage;
	private Map<PDFContentSearchEnum, String>				replaceEnumMap;

	private Map<PDFContentSearchEnum, Map<String, String>>	replaceListEnumMap;

	/**
	 * @param filePath
	 */
	public PDFManager(String filePath)
	{
		this.filePath = filePath;
	}

	/**
	 * @param filePath
	 * @param saveFilePath
	 */
	public PDFManager(String filePath, String saveFilePath)
	{
		this.filePath = filePath;
		this.saveFilePath = saveFilePath;
	}

	public PDFManager(File file, String saveFilePath)
	{
		this.file = file;
		this.saveFilePath = saveFilePath;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String getText() throws Exception
	{
		initDefalutPDF();
		int pageCount = pdDoc.getNumberOfPages();
		// PDDocument document = new PDDocument();
		// final PDFont font = PDType1Font.HELVETICA_BOLD;
		PDFTextStripper stripper = new PDFTextStripper();
		if (toPage <= 0)
		{
			toPage = pageCount;
		}
		if (fromPage <= 0)
		{
			fromPage = 1;
		}
		stripper.setStartPage(fromPage);
		stripper.setEndPage(toPage);
		text = stripper.getText(pdDoc);
		return text;
	}

	/**
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void initDefalutPDF() throws IOException, FileNotFoundException
	{
		this.pdDoc = null;
		this.cosDoc = null;
		if (null == file)
		{
			file = new File(filePath);
		}
		this.parser = new PDFParser(new RandomAccessFile(file, "r")); // update for PDFBox V 2.0
		this.parser.parse();
		this.cosDoc = parser.getDocument();
		this.pdDoc = new PDDocument(cosDoc);
	}

	/**
	 * @param fromPage
	 * @param toPage
	 * @param replaceMap
	 * @throws Exception
	 */
	public void editAndSavePDF(int fromPage, int toPage, Map<PDFContentSearchEnum, String> replaceMap) throws Exception
	{
		initDefalutPDF();
		this.replaceEnumMap = replaceMap;
		int pageCount = pdDoc.getNumberOfPages();
		final Map<String, PDFContentSearchEnum> enumMap = getEnumMap();
		final Map<TextPosition, String> txtPosMap = new HashMap<TextPosition, String>();

		PDFTextStripper stripper = new PDFTextStripper()
		{
			@Override
			protected void startPage(PDPage page) throws IOException
			{
				startOfLine = true;
				super.startPage(page);
			}

			@Override
			protected void writeLineSeparator() throws IOException
			{
				startOfLine = true;
				super.writeLineSeparator();
			}

			@Override
			protected void writeString(String text, List<TextPosition> textPositions) throws IOException
			{
				if (startOfLine)
				{
					TextPosition postion = textPositions.get(textPositions.size() - 1);
					if (enumMap.containsKey(text))
					{
						try
						{
							txtPosMap.put(postion, replaceEnumMap.get(enumMap.get(text)));
						}
						catch (Exception e)
						{
							OUT.error("Exception", e);
						}
					}
					else
					{
						writeString(String.format("[%s]", postion.getXDirAdj()));
					}
					startOfLine = false;
				}
				super.writeString(text, textPositions);
			}

			boolean	startOfLine	= true;
		};
		stripper.setStartPage(fromPage);
		if (toPage <= 0)
		{
			toPage = pageCount;
		}
		stripper.setEndPage(toPage);
		stripper.getText(pdDoc);
		PdfCreator pdfCreator = new PdfCreator();
		for (int i = (fromPage - 1); i < toPage; i++)
		{
			pdfCreator.writePDFPage(pdDoc.getPage(i), txtPosMap);
		}

		if ((pageCount - toPage) > 0)
		{
			for (int i = toPage; i < pageCount; i++)
			{
				pdfCreator.addPDFPage(pdDoc.getPage(i));
			}
		}
		pdfCreator.savePDFFile(saveFilePath);
	}

	/**
	 * This Method edit PDF For Student
	 * 
	 * @param replaceListEnumMaps
	 * @param fromPage
	 * @param toPage
	 * @throws Exception
	 */
	public void editAndSavePDF(Map<PDFContentSearchEnum, Map<String, String>> replaceListEnumMaps, int fromPage, int toPage) throws Exception
	{
		initDefalutPDF();
		this.replaceListEnumMap = replaceListEnumMaps;
		int pageCount = pdDoc.getNumberOfPages();
		final Map<String, PDFContentSearchEnum> enumMap = getEnumMap();
		final Map<TextPosition, Map<String, String>> txtPosMap = new HashMap<TextPosition, Map<String, String>>();

		PDFTextStripper stripper = new PDFTextStripper()
		{
			@Override
			protected void startPage(PDPage page) throws IOException
			{
				startOfLine = true;
				super.startPage(page);
			}

			@Override
			protected void writeLineSeparator() throws IOException
			{
				startOfLine = true;
				super.writeLineSeparator();
			}

			@Override
			protected void writeString(String text, List<TextPosition> textPositions) throws IOException
			{
				if (startOfLine)
				{
					TextPosition postion = textPositions.get(0);
					if (enumMap.containsKey(text))
					{
						try
						{
							txtPosMap.put(postion, replaceListEnumMap.get(enumMap.get(text)));
						}
						catch (Exception e)
						{
							OUT.error("Exception", e);
						}
					}
					else
					{
						writeString(String.format("[%s]", postion.getXDirAdj()));
					}
					startOfLine = false;
				}
				super.writeString(text, textPositions);
			}

			boolean	startOfLine	= true;
		};
		stripper.setStartPage(fromPage);
		if (toPage <= 0)
		{
			toPage = pageCount;
		}
		stripper.setEndPage(toPage);
		stripper.getText(pdDoc);
		PdfCreator pdfCreator = new PdfCreator();
		for (int i = (fromPage - 1); i < toPage; i++)
		{
			pdfCreator.writePDFPageAsList(pdDoc.getPage(i), txtPosMap);
		}

		if ((pageCount - toPage) > 0)
		{
			for (int i = toPage; i < pageCount; i++)
			{
				pdfCreator.addPDFPage(pdDoc.getPage(i));
			}
		}
		pdfCreator.savePDFFile(saveFilePath);
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	/**
	 * @return
	 */
	public Map<String, PDFContentSearchEnum> getEnumMap()
	{
		Map<String, PDFContentSearchEnum> enumMap = new HashMap<String, PDFContentSearchEnum>();

		for (PDFContentSearchEnum enums : PDFContentSearchEnum.values())
		{
			enumMap.put(enums.getText(), enums);
		}
		return enumMap;
	}

	public int getFromPage()
	{
		return fromPage;
	}

	public void setFromPage(int fromPage)
	{
		this.fromPage = fromPage;
	}

	public int getToPage()
	{
		return toPage;
	}

	public void setToPage(int toPage)
	{
		this.toPage = toPage;
	}

	public static void main(String[] args) throws Exception
	{
		Map<PDFContentSearchEnum, Map<String, String>> replaceListEnumMaps = new HashMap<PDFContentSearchEnum, Map<String, String>>();
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		dataMap.put(PDFContentSearchEnum.PDFContentReplaceEnum.REPLACE_STUDENT_NAME.getText() + " ", "Yogesh");
		dataMap.put(PDFContentSearchEnum.PDFContentReplaceEnum.REPLACE_CLASS_AND_SECTION.getText() + " ", "11 (A)");
		dataMap.put(PDFContentSearchEnum.PDFContentReplaceEnum.REPLACE_SCHOOL.getText() + " ", "Test School");
		replaceListEnumMaps.put(PDFContentSearchEnum.STUDENT_DETAILS, dataMap);
		PDFManager manager = new PDFManager("E:\\ACS.pdf", "E:\\test.pdf");
		manager.editAndSavePDF(replaceListEnumMaps, 1, 1);
	}
}