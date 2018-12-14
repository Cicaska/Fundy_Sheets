package com.fundy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for helpful methods for condensed fields.
 * 
 * @author Mitchell Roberts
 * Date Created: Sep 25, 2018
 */
public class CondensedFieldUtil {

	private static int numberOfSmallAnimalShubieFields = -1;
	private static int numberOfLargeAnimalShubieFields = -1;
	private static int numberOfEquineShubieFields = -1;
	
	private static List<CondensedField> smallAnimalFields = null;
	private static List<CondensedField> largeAnimalFields = null;
	private static List<CondensedField> equineFields = null;
	
	private static Map<CondensedField, Integer> fieldColumnMap;
	private static Map<Section, Integer> sectionTotalColumnMap;
	
	public static String getDefaultFileName() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		
		String fileName = "FundyVets_CondensedReport_" + dateFormat.format(date) + ".xlsx";
		return fileName;
	}
	
	public static int getNumberOfSmallAnimalShubieFields() {
		if(numberOfSmallAnimalShubieFields < 0) {
			numberOfSmallAnimalShubieFields = getSmallAnimalFields().size() + 1; //1 extra for the "Total" column.
		}
		return numberOfSmallAnimalShubieFields;
	}
	
	public static int getNumberOfLargeAnimalShubieFields() {
		if(numberOfLargeAnimalShubieFields < 0) {
			numberOfLargeAnimalShubieFields = getLargeAnimalFields().size() + 1; //1 extra for the "Total" column.
		}
		return numberOfLargeAnimalShubieFields;
	}
	
	public static int getNumberOfEquineShubieFields() {
		if(numberOfEquineShubieFields < 0) {
			numberOfEquineShubieFields = getEquineFields().size() + 1; //1 extra for the "Total" column.
		}
		return numberOfEquineShubieFields;
	}
	
	public static List<CondensedField> getSmallAnimalFields() {
		if(smallAnimalFields == null) {
			initializeLists();
		}
		return smallAnimalFields;
	}
	
	public static List<CondensedField> getLargeAnimalFields() {
		if(largeAnimalFields == null) {
			initializeLists();
		}
		return largeAnimalFields;
	}
	
	public static List<CondensedField> getEquineFields() {
		if(equineFields == null) {
			initializeLists();
		}
		return equineFields;
	}
	
	public static Map<Section, Integer> getSectionTotalColumnMap() {
		if(sectionTotalColumnMap == null) {
			getFieldColumnMap(); //Force initialization.
		}
		
		return sectionTotalColumnMap;
	}
	
	public static Map<CondensedField, Integer> getFieldColumnMap() {
		if(fieldColumnMap == null) {
			fieldColumnMap = new HashMap<CondensedField, Integer>();
			
			if(sectionTotalColumnMap == null) {
				sectionTotalColumnMap = new HashMap<Section, Integer>();
			}
			int counter = 1;
			
			for(CondensedField field : getSmallAnimalFields()) {
				fieldColumnMap.put(field, counter);
				if(field.getTitle().equalsIgnoreCase("Total")) {
					sectionTotalColumnMap.put(field.getSection(), counter);
				}
				counter++;
			}
			
			for(CondensedField field : getLargeAnimalFields()) {
				fieldColumnMap.put(field, counter);
				if(field.getTitle().equalsIgnoreCase("Total")) {
					sectionTotalColumnMap.put(field.getSection(), counter);
				}
				counter++;
			}
			
			for(CondensedField field : getEquineFields()) {
				fieldColumnMap.put(field, counter);
				if(field.getTitle().equalsIgnoreCase("Total")) {
					sectionTotalColumnMap.put(field.getSection(), counter);
				}
				counter++;
			}
		}
		return fieldColumnMap;
	}
	
	public static int getSaTotalIndex() {
		return getNumberOfSmallAnimalShubieFields();
	}
	
	public static int getLaTotalIndex() {
		return getSaTotalIndex() + getNumberOfLargeAnimalShubieFields();
	}
	
	public static int getEqTotalIndex() {
		return getLaTotalIndex() + getNumberOfEquineShubieFields();
	}
	
	public static List<String> getMonths(){
		return Arrays.asList(new String[]  {"January",
											"February",
											"March",
											"April",
											"May",
											"June",
											"July",
											"August",
											"September",
											"October",
											"November",
											"December"});
	}
	
	private static void initializeLists() {
		smallAnimalFields = new ArrayList<CondensedField>();
		largeAnimalFields = new ArrayList<CondensedField>();
		equineFields = new ArrayList<CondensedField>();
		
		for(CondensedField field : CondensedField.values()) {
			switch(field.getSection()) {
			case SMALL_ANIMAL_SHUBIE:{
				smallAnimalFields.add(field);
				break;
			}
			case LARGE_ANIMAL_SHUBIE:{
				largeAnimalFields.add(field);
				break;
			}
			case EQUINE_SHUBIE:{
				equineFields.add(field);
				break;
			}
			}
		}
	}
}
