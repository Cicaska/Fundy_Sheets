package com.fundy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Utility class to handle the parsing of excel 
 * files into useful information.
 * 
 * @author Mitchell Roberts
 * Date Created: Sep 13, 2018
 */
public class ExcelParser {

	private static final String CATEGORY_DESCRIPTION_CELL_STRING = "Category/Description";
	private static final String DATE_OF_REPORT_CELL_STRING = "FOR PERIOD";
	private static final String INCOME_CELL_STRING = "Income";
	
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	
	private static SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
	
	public static void parse(File inputFile, File outputDir) throws Exception{
		Workbook workbook = WorkbookFactory.create(inputFile);
		
		if(workbook.getNumberOfSheets() > 0) {
			CondensedSheetWrapper condensedSheet = new CondensedSheetWrapper();
			
			Sheet dataSheet = workbook.getSheetAt(0);
			//Assume that the first sheet is the correct one.
			
			Iterator<Row> rowIterator = dataSheet.rowIterator();
			
			//Date object for the first day of the month represented by this report.
			Date reportPeriod = null;
			
			Cell categoryDescriptionStartCell = null;
			Cell dateOfReportCell = null;
			Cell incomeStartCell = null;
			
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if(row.getPhysicalNumberOfCells() > 0) {
					//Check for the date cell
					if(dateOfReportCell == null && 
							row.getCell(0).getStringCellValue() != null &&
							row.getCell(0).getStringCellValue().toUpperCase().contains((DATE_OF_REPORT_CELL_STRING))) {
						dateOfReportCell = row.getCell(1);
						
						//The date range string is formatted like (ex) 01/08/2018 - 31/08/2018
						String dateStr = row.getCell(1).getStringCellValue();
						if(dateStr != null) {
							try {
								dateStr = dateStr.substring(0, dateStr.indexOf(" "));
								reportPeriod = df.parse(dateStr);
								
								condensedSheet.setReportPeriod(reportPeriod);
							} catch (Throwable e) {
								String message = "Unable to read date from report.";
								throw new Exception(message, e);
							}
						}
					}
					
					//Check for the Category/Description cell.
					if(categoryDescriptionStartCell == null && 
							row.getCell(0).getStringCellValue() != null &&
							row.getCell(0).getStringCellValue().trim().equalsIgnoreCase(CATEGORY_DESCRIPTION_CELL_STRING)) {
						categoryDescriptionStartCell = row.getCell(0);
						
						Iterator<Cell> cellIterator = dataSheet.getRow(categoryDescriptionStartCell.getRowIndex()).cellIterator();
						
						while(cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							if(cell.getStringCellValue().trim().equalsIgnoreCase(INCOME_CELL_STRING)) {
								incomeStartCell = cell;
								break;
							}
						}
						
						if(incomeStartCell == null) {
							//We have parsed all cells in the starting row, and have not 
							//found the Income column. Aborting with error.
							String message = "Unable to find the \"Income\" cell in correct location in sheet. Aborting.";
							throw new Exception(message);
						} else {
							//Can now move on, as the marker cells have been identified.
							continue;
						}
					}
					if(incomeStartCell != null) {
						if(row.getPhysicalNumberOfCells() > 0 && row.getCell(0) != null 
								&& row.getCell(0).getStringCellValue() != null && !row.getCell(0).getStringCellValue().trim().equals("")) {
							//All cells have been initialized correctly, and the current row has a valid detailed title cell.
							String detailedFieldTitle = row.getCell(0).getStringCellValue();
							if(row.getPhysicalNumberOfCells() > incomeStartCell.getColumnIndex()) {
								String detailedFieldIncomeStr = row.getCell(incomeStartCell.getColumnIndex()).getStringCellValue();
								double detailedFieldIncome = Double.parseDouble(detailedFieldIncomeStr);
								
								//Now that we have the title and the income, we can try to make a DetailedField from it, and add it to the condensed sheet.
								try {
									DetailedField detailedField = DetailedField.getFieldByTitle(detailedFieldTitle);
									condensedSheet.addDetailedField(detailedField, detailedFieldIncome);
									condensedSheet.addToCondensedFieldTotal(detailedField.getCondensedField(), detailedFieldIncome);
								} catch(Throwable e) {
									condensedSheet.addError(detailedFieldTitle, detailedFieldIncome);
								}
							} else {
								//This is an error case. There is a title, but there is no income associated with it.
								condensedSheet.addError(detailedFieldTitle, null);
							}
						}
					}
				}
			}
			
			if(categoryDescriptionStartCell == null) {
				//We have parsed all first cells of all rows in the document 
				//and have not found the starting cell. Aborting with error.
				String message = "Unable to find \"Category/Description\" cell in sheet. Aborting.";
				throw new Exception(message);
			}
			
			condensedSheet.buildOutput(outputDir);
		} else {
			String message = "Excel file does not contain any sheets!";
			throw new Exception(message);
		}
	}
	
}
