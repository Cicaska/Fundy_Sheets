package com.fundy;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Test {

	public static void main(String[] args) throws ParseException {
//		String[] d = {"Adjustments",
//				"Anesthesia",
//				"Bo-Adm Medication & Inj.",
//				"Bo-Immunization/Reg/Process",
//				"Bo-Lab/Radiology",
//				"Bo-Prof Service/Call Fees",
//				"Bo-Surgery/Anesthesia/Dent",
//				"Board Paid Mileage",
//				"Bovine ET",
//				"Dentistry - Routine",
//				"Dentistry - Surgery",
//				"Discounts",
//				"Eq- Adm Medication & Inj.",
//				"Eq-Immunization/Regulatory",
//				"Eq-Lab/Radiology",
//				"Eq-Obstetrics/Reproduction",
//				"Eq-Prof Service/Call Fees",
//				"Equine Dentistry",
//				"Herd Health Assistance Nsda",
//				"Hospital Procedures",
//				"Hospitalization",
//				"Immunizations - Canine",
//				"Immunizations - Feline",
//				"Lab - In House",
//				"Lab - Referred",
//				"Miscellaneous",
//				"Miscellaneous Procedures",
//				"No Charge Service Codes",
//				"Office Visits/Exams",
//				"Os-Prof Service/Call Fees",
//				"Other Services",
//				"Other Species Services",
//				"Over The Counter Sales",
//				"Professional Services",
//				"Radiology",
//				"Surgery - Minor",
//				"Tags/License",
//				"Ultrasound",
//				"Antibiotics-Injectable",
//				"Antibiotics-Oral",
//				"Bandage/Tape/Guaze/Dress",
//				"Biologicals",
//				"Calcium-Oral",
//				"Clinic Supplies",
//				"Equine Antinflammatories",
//				"Equine Misc. Drug",
//				"Equine Sedative/Pain Relieve",
//				"Equine Vits & Coat Products",
//				"Equine Wormers And Fly Cont.",
//				"Flea Control Products",
//				"Fluids-Oral",
//				"Fluids-Parenteral",
//				"Heartworm Products",
//				"Hills Prescription Diets",
//				"Hormones",
//				"Injection- Meds Administered",
//				"Large Animal Supplies",
//				"Mastitis-Dry Cow",
//				"Mastitis-Lactating",
//				"Misc Injectables La",
//				"Misc Oral Products La",
//				"Parasiticides-Oral",
//				"Parasiticides-Topical",
//				"SA Misc Neutriceuticals & Other",
//				"SA Suture Materials",
//				"SMALL ANIMAL SUPPLIES - SHONNA",
//				"Sa Cardiac/Diur/Bronchodil",
//				"Sa Ext/Premise Parasiticides",
//				"Sa Fluids(parenteral)",
//				"Sa Food",
//				"Sa Injectable Antibiotics",
//				"Sa Injectable Steroids",
//				"Sa Misc Injectables",
//				"Sa Misc Oral Drugs",
//				"Sa Ophthalmic Products",
//				"Sa Oral Antibiotics",
//				"Sa Oral Parasiticides",
//				"Sa Oral Steroids/Ai's",
//				"Sa Otic Products",
//				"Sa Pet Supplies",
//				"Sa Shampoos & Coat/Skin Care",
//				"Sa Topical Ab/Ab+steroid",
//				"Sa lab",
//				"Steroids & Antinflammatories",
//				"Topical Sprays And Ointments",
//				"Vitamins-Injectable"};
//		
//		for(String entry : d) {
//			String out = entry.toUpperCase();
//			out = out.replace("&", "AND");
//			out = out.replace("+", "_");
//			out = out.replace(".", "");
//			out = out.replace("(", "_");
//			out = out.replace(")", "_");
//			out = out.replace("/", "_");
//			out = out.replace("'", "");
//			out = out.replace(" - ", "_");
//			out = out.replace(" ", "_");
//			out = out.replaceAll("-", "_");
//			
//			if(out.endsWith("_")) {
//				out = out.substring(0, out.length()-1);
//			}
//			
//			System.out.println(out + "(\"" + entry + "\", ),");
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		
		Date today = new Date();
		Date future = sdf.parse("10-02-2020");
		Date past = sdf.parse("10-02-2017");
		
		File outputFile = new File("C:\\Users\\cicat\\Desktop");
//		File outputFile = new File("C:\\Users\\cicat\\Desktop\\FundyVets_CondensedReport_10-03-2018.xlsx");
		
		CondensedSheetWrapper wrapper = new CondensedSheetWrapper();
		wrapper.setReportPeriod(today);
		wrapper.buildOutput(outputFile);
//	
//		
//		Date today = new Date();
//		
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(today);
//		
//		System.out.println(Month.of(cal.get(Calendar.MONTH) + 1).getDisplayName(TextStyle.FULL, Locale.CANADA));
		
		//TODO: TEST INSERTING NEW YEAR ROWS AS NECESSARY.
	}
	
}
