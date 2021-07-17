package org.utility.classes;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelData 
{
	public static String getExcelData(String filePath,String sheetName,int row,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream file = new FileInputStream(filePath);
		Sheet sheet = WorkbookFactory.create(file).getSheet(sheetName);
		String strValue;
		try
		{
			strValue = sheet.getRow(row).getCell(cell).getStringCellValue();
			return strValue;
		}
		catch (Exception e) 
		{
			double numValue = sheet.getRow(row).getCell(cell).getNumericCellValue();
			strValue = String.valueOf(numValue);
			return strValue;
		}
	}
}