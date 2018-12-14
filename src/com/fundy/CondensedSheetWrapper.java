package com.fundy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Class for containing all necessary information for 
 * generating the condensed form of the Fundy spreadsheet.
 * 
 * @author Mitchell Roberts
 * Date Created: Sep 24, 2018
 */
public class CondensedSheetWrapper {

	private Map<CondensedField, Double> condensedFieldTotals;
	private Map<String, Double> errorMap;
	private Map<DetailedField, Double> detailedFieldIncomes;
	private Date reportPeriod;
	
	private CellStyle headingCellStyle;
	private CellStyle regularCellStyle;
	private CellStyle yearHeadingStyle;
	
	public CondensedSheetWrapper() {
		errorMap = new HashMap<String, Double>();
		detailedFieldIncomes = new HashMap<DetailedField, Double>();
		condensedFieldTotals = new HashMap<CondensedField, Double>();
		initializeMap();
	}
	
	private void initializeMap() {
		for(CondensedField field : CondensedField.values()) {
			condensedFieldTotals.put(field, 0.0);
		}
	}
	
	public void setReportPeriod(Date reportPeriod) {
		this.reportPeriod = reportPeriod;
	}
	
	/**
	 * Adds to the running total income value for the given condensed field.
	 * Also adds the value to the Total column for the section of this field.
	 * 
	 * @param condensedField The field for which to add to the running total.
	 * @param value The amount to add to the total.
	 */
	public void addToCondensedFieldTotal(CondensedField condensedField, Double value) {
		double currentValue = condensedFieldTotals.get(condensedField);
		currentValue += value;
		condensedFieldTotals.put(condensedField, currentValue);
		
		CondensedField totalField = null;
		
		switch(condensedField.getSection()) {
		case SMALL_ANIMAL_SHUBIE:{
			totalField = CondensedField.SA_TOTAL;
			break;
		}
		case LARGE_ANIMAL_SHUBIE:{
			totalField = CondensedField.LA_TOTAL;
			break;
		}
		case EQUINE_SHUBIE:{
			totalField = CondensedField.EQ_TOTAL;
			break;
		}
		}
		
		double currentSectionTotal = condensedFieldTotals.get(totalField);
		currentSectionTotal += value;
		condensedFieldTotals.put(totalField, currentSectionTotal);
	}
	
	public void addDetailedField(DetailedField detailedField, Double value) {
		detailedFieldIncomes.put(detailedField, value);
	}
	
	public void addError(String title, Double value) {
		errorMap.put(title, value);
	}
	
	public void buildOutput(File outputFile) {
		if(outputFile.isDirectory()) {
			generateNewExcelFileInDirectory(outputFile);
		} else {
			updateExcelFile(outputFile);
		}
	}
	
	private void generateNewExcelFileInDirectory(File outputDir) {
		Workbook workbook = null;
		FileOutputStream fos = null;
		try {
			File newCondensedFile = new File(outputDir, CondensedFieldUtil.getDefaultFileName());
			newCondensedFile.createNewFile();
			
			workbook = generateTemplateWorkbook();
			workbook = addValuesToWorkbook(workbook);
			fos = new FileOutputStream(newCondensedFile);
			workbook.write(fos);
		} catch (Throwable e) {
			String message = "Failed to generate new Excel file in directory " + outputDir;
			System.err.println(message);
			e.printStackTrace();
		} finally {
			if(workbook != null) {
				try {
					workbook.close();
				} catch (Throwable e) {
					System.err.println("Failed closing workbook.");
				}
			}
			
			if(fos != null) {
				try {
					fos.close();
				} catch (Throwable e) {
					System.err.println("Failed closing File output stream.");
				}
			}
		}
	}
	
	private void updateExcelFile(File inputFile) {
		Workbook workbook = null;
		FileOutputStream fos = null;
		try {
			workbook = getWorkbookFromFile(inputFile);
			//workbook = handleYearRows(workbook);
			workbook = addValuesToWorkbook(workbook);
			
			File tmp = new File(inputFile.getParentFile() + File.separator + "NEW_" + inputFile.getName());
			tmp.createNewFile();
			
			fos = new FileOutputStream(tmp);
			workbook.write(fos);
		} catch(Throwable e) {
			String message = "Failed to update workbook in file " + inputFile.getAbsolutePath();
			System.err.println(message);
			e.printStackTrace();
		} finally {
			if(workbook != null) {
				try {
					workbook.close();
				} catch (Throwable e) {
					System.err.println("Failed closing workbook.");
				}
			}
			
			if(fos != null) {
				try {
					fos.close();
				} catch (Throwable e) {
					System.err.println("Failed closing File output stream.");
				}
			}
		}
	}
	
	/**
	 * Takes an existing file and creates a workbook from it. 
	 * Assumes that the existing file has already been initialized with the template.
	 * 
	 * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	private Workbook getWorkbookFromFile(File inputFile) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(inputFile);
		
		Workbook workbook = WorkbookFactory.create(fis); 
		initializeWorkbook(workbook);
		
		return workbook; 
	}
	
	private void initializeWorkbook(Workbook workbook) {
		Font headingFont = workbook.createFont();
		headingFont.setBold(true);
		headingFont.setFontHeightInPoints((short)11);
		headingCellStyle = workbook.createCellStyle();
		headingCellStyle.setFont(headingFont);
		
		yearHeadingStyle = workbook.createCellStyle();
		yearHeadingStyle.setAlignment(HorizontalAlignment.RIGHT);
		
		Font regularFont = workbook.createFont();
		regularFont.setFontHeightInPoints((short)11);
		regularCellStyle = workbook.createCellStyle();
		regularCellStyle.setFont(regularFont);
	}
	
	/**
	 * Generates a new template workbook for the condensed output.
	 * 
	 * @return A new workbook with the template for the condensed output already populated.
	 */
	private Workbook generateTemplateWorkbook() {
		Workbook condensedBook = new XSSFWorkbook();
		Sheet condensedSheet = condensedBook.createSheet();
		
		initializeWorkbook(condensedBook);
		
		//The template will, by default, create a new sheet with data for all 12 months of each the last 3 years.
		//The first row: 
		//[   ] [Small Animal Shubie] [   ]x#SmallAnimalShubieFields [Large Animal Shubie] [   ]x#LargeAnimalShubieFields [Equine Shubie] [   ]x#EquineShubieFields
		Row firstRow = condensedSheet.createRow(0);
		
		Cell smallAnimalShubieHeader = firstRow.createCell(1);
		smallAnimalShubieHeader.setCellValue(Section.SMALL_ANIMAL_SHUBIE.getTitle());
		
		int indexOfLargeAnimalShubieHeader = smallAnimalShubieHeader.getColumnIndex()
				+ CondensedFieldUtil.getNumberOfSmallAnimalShubieFields();
		Cell largeAnimalShubieHeader = firstRow.createCell(indexOfLargeAnimalShubieHeader);
		largeAnimalShubieHeader.setCellValue(Section.LARGE_ANIMAL_SHUBIE.getTitle());
		
		int indexOfEquineShubieHeader = largeAnimalShubieHeader.getColumnIndex()
				+ CondensedFieldUtil.getNumberOfLargeAnimalShubieFields();
		Cell equineShubieHeader = firstRow.createCell(indexOfEquineShubieHeader);
		equineShubieHeader.setCellValue(Section.EQUINE_SHUBIE.getTitle());
		
		
		
		//Create the row of condensed field headers
		Row condensedHeadersRow = condensedSheet.createRow(2);
		
		for(Entry<CondensedField, Integer> fieldColumnEntry : CondensedFieldUtil.getFieldColumnMap().entrySet()) {
			int columnIndex = fieldColumnEntry.getValue();
			String title = fieldColumnEntry.getKey().getTitle();
			
			Cell cell = condensedHeadersRow.createCell(columnIndex);
			cell.setCellStyle(headingCellStyle);
			cell.setCellValue(title);
		}
		
		
		//Now all of the headers have been created.
		//The next thing to do is to generate all of the Month/Year rows.
		//By default, there will be two year rows for each month: the current year and the following.
		int nextRow = 3;
		
		Calendar reportCalendar = Calendar.getInstance();
		reportCalendar.setTime(reportPeriod);
		int year = reportCalendar.get(Calendar.YEAR);
		
		int saTotalColumnIndex = CondensedFieldUtil.getSectionTotalColumnMap().get(Section.SMALL_ANIMAL_SHUBIE);
		int laTotalColumnIndex = CondensedFieldUtil.getSectionTotalColumnMap().get(Section.LARGE_ANIMAL_SHUBIE);
		int eqTotalColumnIndex = CondensedFieldUtil.getSectionTotalColumnMap().get(Section.EQUINE_SHUBIE);
		
		for(String month : CondensedFieldUtil.getMonths()) {
			Row monthHeaderRow = condensedSheet.createRow(nextRow);
			Cell monthHeaderCell = monthHeaderRow.createCell(0);
			monthHeaderCell.setCellValue(month);
			monthHeaderCell.setCellStyle(headingCellStyle);
			
			nextRow++;
			
			Row monthFirstYearRow = condensedSheet.createRow(nextRow);
			Cell firstYearHeadingCell = monthFirstYearRow.createCell(0);
			firstYearHeadingCell.setCellValue(Integer.toString(year));
			firstYearHeadingCell.setCellStyle(yearHeadingStyle);
			monthFirstYearRow.createCell(saTotalColumnIndex).setCellValue(0.0);
			monthFirstYearRow.createCell(laTotalColumnIndex).setCellValue(0.0);
			monthFirstYearRow.createCell(eqTotalColumnIndex).setCellValue(0.0);
			
			nextRow++;
			
			Row monthSecondYearRow = condensedSheet.createRow(nextRow);
			Cell secondYearHeadingCell = monthSecondYearRow.createCell(0);
			secondYearHeadingCell.setCellValue(Integer.toString(year+1));
			secondYearHeadingCell.setCellStyle(yearHeadingStyle);
			
			monthSecondYearRow.createCell(saTotalColumnIndex).setCellValue(0.0);
			monthSecondYearRow.createCell(laTotalColumnIndex).setCellValue(0.0);
			monthSecondYearRow.createCell(eqTotalColumnIndex).setCellValue(0.0);
			
			nextRow++;
		}
		
		return condensedBook;
	}
	
	/**
	 * Takes a workbook generated with the default template, 
	 * and adds the values from the condensedFieldTotals.
	 * 
	 * @param workbook The workbook to update.
	 * @return
	 */
	private Workbook addValuesToWorkbook(Workbook workbook) throws Exception{
		//First thing to do is determine whether the current workbook has rows in it for this year.
		if(!hasCorrectYear(workbook)) {
			String message = "The workbook does not contain any rows for the report year. Cannot update rows.";
			System.err.println(message);
			throw new Exception(message);
		}
		
		Row rowToUpdate = getReportMonthYearRow(workbook);
		
		//Now, need to iterate over cells in the row and add values under the correct headings.
		for(Entry<CondensedField, Double> fieldTotalEntry : condensedFieldTotals.entrySet()) {
			int fieldColumn = CondensedFieldUtil.getFieldColumnMap().get(fieldTotalEntry.getKey());
			
			Cell fieldCell = rowToUpdate.createCell(fieldColumn);
			fieldCell.setCellValue(fieldTotalEntry.getValue());
		}
		
		return workbook;
	}
	
	/**
	 * Retrieves the row for the month and year in the workbook for the report period in question. 
	 * 
	 * @param workbook The workbook from which to retrieve the correct month/year row.
	 * @return The row for the month and year of the report period.
	 */
	private Row getReportMonthYearRow(Workbook workbook) throws Exception{
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		
		List<String> months = CondensedFieldUtil.getMonths();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(reportPeriod);
		
		Month reportMonth = Month.of(cal.get(Calendar.MONTH) + 1);
		String month = reportMonth.getDisplayName(TextStyle.FULL, Locale.CANADA);
		
		int year = cal.get(Calendar.YEAR);
		
		Row monthTitleRow = null;
		
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			Cell firstCell = row.getCell(0);
			
			//If we have already found the month, can move on to finding the year.
			if(monthTitleRow != null) {
				if(firstCell != null && firstCell.getStringCellValue() != null) {
					//There is a string value in the cell.
					
					//If the value in the cell is another month string, we've gone too far without finding the right
					//year. Abort.
					if(months.contains(firstCell.getStringCellValue())) {
						String message = "Unable to find row for year " + year + " in month " + month + " in workbook.";
						System.err.println(message);
						throw new Exception(message);
					}
					
					String cellValue = firstCell.getStringCellValue();
					
					try {
						int cellYear = Integer.parseInt(cellValue);
						if(year == cellYear) {
							return row;
						}
					} catch (Throwable e) {
						//Cannot parse as a number. Move on...
					}
				}
			} else {
				if(firstCell != null && firstCell.getStringCellValue() != null 
						
						&& firstCell.getStringCellValue().equalsIgnoreCase(month)) {
					//Found the correct month row. Now need to keep iterating until we find the correct year beneath it.
					monthTitleRow = row;
				}
			}
		}
		
		//If we have made it out of the loops without returning a row, then there is no row matching. Abort.
		String message = "Unable to find row for year " + year + " in month " + month + " in workbook.";
		System.err.println(message);
		throw new Exception(message);
	}
	
	private boolean hasCorrectYear(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		
		List<String> months = CondensedFieldUtil.getMonths();
		
		Row firstMonthRow = null;
		
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			Cell firstCell = row.getCell(0);
			if(firstCell != null && firstCell.getStringCellValue() != null 
					&& months.contains(firstCell.getStringCellValue())) {
				//First occurrence of a month.
				firstMonthRow = row;
				break;
			}
		}
		
		if(firstMonthRow != null) {
			List<Integer> yearsOnSheet = new ArrayList<Integer>();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				String firstCellVal = row.getCell(0).getStringCellValue();
				
				try {
					int year = Integer.parseInt(firstCellVal);
					yearsOnSheet.add(year);
				} catch (NumberFormatException e) {
					//Non-year value. This means that we have reached the end of the years/
					break;
				}
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(reportPeriod);
			
			int reportYear = cal.get(Calendar.YEAR);
			
			return yearsOnSheet.contains(reportYear);
		}
		
		return false;
	}
	
	private Workbook handleYearRows(Workbook workbook) throws Exception{
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		
		List<String> months = CondensedFieldUtil.getMonths();
		
		Row firstMonthRow = null;
		
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			Cell firstCell = row.getCell(0);
			if(firstCell != null && firstCell.getStringCellValue() != null 
					&& months.contains(firstCell.getStringCellValue())) {
				//First occurrence of a month.
				firstMonthRow = row;
				break;
			}
		}
		
		if(firstMonthRow != null) {
			List<Integer> yearsOnSheet = new ArrayList<Integer>();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				String firstCellVal = row.getCell(0).getStringCellValue();
				
				try {
					int year = Integer.parseInt(firstCellVal);
					yearsOnSheet.add(year);
				} catch (NumberFormatException e) {
					//Non-year value. This means that we have reached the end of the years/
					break;
				}
			}
			
			if(yearsOnSheet.size() > 0) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(reportPeriod);
				
				int reportYear = cal.get(Calendar.YEAR);
				
				if(!yearsOnSheet.contains(reportYear)) {
					rowIterator = sheet.rowIterator();
					
					boolean monthIsDone = false;
					
					List<Integer> yearRowInserts = new ArrayList<Integer>();
					
					while(rowIterator.hasNext()) {
						Row row = rowIterator.next();
						
						//TODO: This causes a problem with concurrent modification, since we have potentially inserted a
						//		new row before the current row.
						
						//Skip rows until we get past the row with the first month name in it.
						if(row.getRowNum() <= firstMonthRow.getRowNum()) {
							continue;
						}
						
						String rowTitle = row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "";
						try {
							int rowYear = Integer.parseInt(rowTitle);
							
							if(!monthIsDone && reportYear < rowYear) {
								yearRowInserts.add(row.getRowNum());
								
								monthIsDone = true;
							}
						} catch(NumberFormatException e) {
							//If we get to here, we have reached a new month.
							//If we reach here and monthIsDone = false, then we need to add the new year before this row.
							if(!monthIsDone) {
								yearRowInserts.add(row.getRowNum());
							}
							
							
							monthIsDone = false;
						}
					}
					
					if(yearRowInserts.size() > 0) {
						int offset = 0;
						for(Integer yearRowInsertIndex : yearRowInserts) {
							Row newYearRow = insertRowBefore(sheet, yearRowInsertIndex + offset);
							
							Cell newYearHeadingCell = newYearRow.createCell(0);
							newYearHeadingCell.setCellValue(Integer.toString(reportYear));
							newYearHeadingCell.setCellStyle(yearHeadingStyle);
							newYearRow.createCell(CondensedFieldUtil.getSaTotalIndex()).setCellValue(0.0);
							newYearRow.createCell(CondensedFieldUtil.getLaTotalIndex()).setCellValue(0.0);
							newYearRow.createCell(CondensedFieldUtil.getEqTotalIndex()).setCellValue(0.0);
							
							offset++;
						}
					}
					
					System.out.println("workbook is done. This is just so I can breakpoint here.");
				}
				//Otherwise, no need to do anything.
				
			} else {
				throw new Exception("Unable to read any years out of sheet.");
			}
		} else {
			throw new Exception("Unable to parse months in rows. Could not determine whether year needs to be added.");
		}
		
		return workbook;
	}
	
	/**
	 * Creates a new empty row before the given row index in the sheet.
	 * 
	 * @param sheet The sheet to shift rows in.
	 * @param rowIndex The index at which the new row should go.
	 * @return The newly squeezed in row.
	 */
	private Row insertRowBefore(Sheet sheet, int rowIndex) {
		sheet.shiftRows(rowIndex, sheet.getPhysicalNumberOfRows(), 1);
		return sheet.createRow(rowIndex);
	}
}
