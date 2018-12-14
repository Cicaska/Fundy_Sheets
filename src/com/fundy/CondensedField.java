package com.fundy;

public enum CondensedField {
	//Fields in Small Animal Shubie
	SA_OFFICE_VISITS("Office Visits", Section.SMALL_ANIMAL_SHUBIE),
	SA_PROFESSIONAL_SERVICES("Professional Services", Section.SMALL_ANIMAL_SHUBIE),
	SA_PRESCRIPTION_DRUGS("SA Prescription Drugs", Section.SMALL_ANIMAL_SHUBIE),
	SA_OVER_COUNTER("SA Over Counter", Section.SMALL_ANIMAL_SHUBIE),
	SA_LAB("Lab", Section.SMALL_ANIMAL_SHUBIE),
	SA_DIAGNOSTIC("Diagnostic", Section.SMALL_ANIMAL_SHUBIE),
	SA_DENTISTRY("Dentistry", Section.SMALL_ANIMAL_SHUBIE),
	SA_HOSPITAL("Hospital", Section.SMALL_ANIMAL_SHUBIE),
	SA_VACCINATIONS("Vaccinations", Section.SMALL_ANIMAL_SHUBIE),
	SA_FLEA_HEART("Flea/Heart", Section.SMALL_ANIMAL_SHUBIE),
	SA_PET_FOOD("Pet Food", Section.SMALL_ANIMAL_SHUBIE),
	SA_MISC("Misc", Section.SMALL_ANIMAL_SHUBIE),
	SA_TOTAL("Total", Section.SMALL_ANIMAL_SHUBIE),
	
	//Fields in Equine Shubie
	EQ_PROFESSIONAL_SERVICES("Professional Services", Section.EQUINE_SHUBIE),
	EQ_PRESCRIPTION_DRUGS("Prescription Drugs", Section.EQUINE_SHUBIE),
	EQ_LAB("Lab", Section.EQUINE_SHUBIE),
	EQ_DENTISTRY("Dentistry", Section.EQUINE_SHUBIE),
	EQ_DRUGS_OTHER("Drugs - Other", Section.EQUINE_SHUBIE),
	EQ_MISC("Misc", Section.EQUINE_SHUBIE),
	EQ_TOTAL("Total", Section.EQUINE_SHUBIE),
	
	//Fields in Large Animal Shubie
	LA_PROFESSIONAL_SERVICES("Professional Services", Section.LARGE_ANIMAL_SHUBIE),
	LA_PRESCRIPTION_DRUGS("Prescription Drugs", Section.LARGE_ANIMAL_SHUBIE),
	LA_LAB("Lab", Section.LARGE_ANIMAL_SHUBIE),
	LA_EMBRYO_TRANSFER("Embryo Transfer", Section.LARGE_ANIMAL_SHUBIE),
	LA_DRUGS_OTHER("Drugs - Other", Section.LARGE_ANIMAL_SHUBIE),
	LA_MISC("Misc", Section.LARGE_ANIMAL_SHUBIE),
	LA_MILEAGE("Mileage", Section.LARGE_ANIMAL_SHUBIE),
	LA_TOTAL("Total", Section.LARGE_ANIMAL_SHUBIE),
	;
	
	private String title;
	private Section section;
	
	private CondensedField(String title, Section section) {
		this.title = title;
		this.section = section;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Section getSection() {
		return section;
	}
}
