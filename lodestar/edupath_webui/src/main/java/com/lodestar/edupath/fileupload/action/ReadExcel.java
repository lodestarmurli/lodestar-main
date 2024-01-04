package com.lodestar.edupath.fileupload.action;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel
{

	public static void main(String args[]){
		Workbook wb = null;
		try{

			wb = WorkbookFactory.create(new File("D:/jamocha/loadstar/OccupationImageUpload.xls"));
			Sheet xlSheet = wb.getSheet("occupation");
			
			int firstRow = xlSheet.getFirstRowNum();
			int lastRow = xlSheet.getLastRowNum();

			for (int rowIndex = firstRow; rowIndex <= lastRow; rowIndex++){
				Row row = xlSheet.getRow(rowIndex);
				
				System.out.println(row.getCell(0)+"--"+row.getCell(1));
				for (Cell cell : row)
				{
					Object value = getCellValue(cell);
					System.out.println("Row : "+rowIndex+", cell : "+cell.getColumnIndex()+", value : "+value);
				}
			}

		}catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			if(wb != null)
				try
				{
					wb.close();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public static Object getCellValue(Cell cell)
	{
		Object value = "";
		switch (cell.getCellType())
		{
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell))
				{
					value = cell.getDateCellValue();
				}
				else
				{
					value = cell.getNumericCellValue();
				}
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value =  cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_ERROR:
				value = cell.getErrorCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				value = cell.getCellFormula();
				break;
	
			default:
				break;
		}
		return value;
	}

}
