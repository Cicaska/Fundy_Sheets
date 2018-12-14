package com.fundy;

public enum DetailedField {

	ADJUSTMENTS("Adjustments", CondensedField.LA_MISC),
	ANESTHESIA("Anesthesia", CondensedField.SA_HOSPITAL),
	BO_ADM_MEDICATION_AND_INJ("Bo-Adm Medication & Inj.", CondensedField.LA_PRESCRIPTION_DRUGS),
	BO_IMMUNIZATION_REG_PROCESS("Bo-Immunization/Reg/Process", CondensedField.LA_PRESCRIPTION_DRUGS),
	BO_LAB_RADIOLOGY("Bo-Lab/Radiology", CondensedField.LA_LAB),
	BO_PROF_SERVICE_CALL_FEES("Bo-Prof Service/Call Fees", CondensedField.LA_PROFESSIONAL_SERVICES),
	BO_SURGERY_ANESTHESIA_DENT("Bo-Surgery/Anesthesia/Dent", CondensedField.LA_PROFESSIONAL_SERVICES),
	BOARD_PAID_MILEAGE("Board Paid Mileage", CondensedField.LA_MILEAGE),
	BOVINE_ET("Bovine ET", CondensedField.LA_EMBRYO_TRANSFER),
	DENTISTRY_ROUTINE("Dentistry - Routine", CondensedField.SA_DENTISTRY),
	DENTISTRY_SURGERY("Dentistry - Surgery", CondensedField.SA_DENTISTRY),
	DISCOUNTS("Discounts", CondensedField.LA_MISC),
	EQ_ADM_MEDICATION_AND_INJ("Eq- Adm Medication & Inj.", CondensedField.EQ_PRESCRIPTION_DRUGS),
	EQ_IMMUNIZATION_REGULATORY("Eq-Immunization/Regulatory", CondensedField.EQ_PRESCRIPTION_DRUGS),
	EQ_LAB_RADIOLOGY("Eq-Lab/Radiology", CondensedField.EQ_LAB),
	LABORATORY_ALL_SPECIES("Laboratory - all species", CondensedField.EQ_LAB),
	EQ_OBSTETRICS_REPRODUCTION("Eq-Obstetrics/Reproduction", CondensedField.EQ_PROFESSIONAL_SERVICES),
	EQ_PROF_SERVICE_CALL_FEES("Eq-Prof Service/Call Fees", CondensedField.EQ_PROFESSIONAL_SERVICES),
	EQ_SURGERY_ANESTHESIA_DENT("Eq-Surgery/Anesthesia/Dentistry", CondensedField.EQ_PROFESSIONAL_SERVICES),
	EQUINE_DENTISTRY("Equine Dentistry", CondensedField.EQ_DENTISTRY),
	HERD_HEALTH_ASSISTANCE_NSDA("Herd Health Assistance Nsda", CondensedField.LA_PROFESSIONAL_SERVICES),
	HOSPITAL_PROCEDURES("Hospital Procedures", CondensedField.SA_HOSPITAL),
	HOSPITALIZATION("Hospitalization", CondensedField.SA_HOSPITAL),
	IMMUNIZATIONS_CANINE("Immunizations - Canine", CondensedField.SA_VACCINATIONS),
	IMMUNIZATIONS_FELINE("Immunizations - Feline", CondensedField.SA_VACCINATIONS),
	LAB_IN_HOUSE("Lab - In House", CondensedField.SA_LAB),
	LAB_REFERRED("Lab - Referred", CondensedField.SA_LAB),
	MISCELLANEOUS("Miscellaneous", CondensedField.SA_MISC),
	MISCELLANEOUS_PROCEDURES("Miscellaneous Procedures", CondensedField.SA_HOSPITAL),
	NO_CHARGE_SERVICE_CODES("No Charge Service Codes", CondensedField.SA_MISC),
	OFFICE_VISITS_EXAMS("Office Visits/Exams", CondensedField.SA_OFFICE_VISITS),
	OS_PROF_SERVICE_CALL_FEES("Os-Prof Service/Call Fees", CondensedField.LA_PROFESSIONAL_SERVICES),
	OTHER_SERVICES("Other Services", CondensedField.SA_PROFESSIONAL_SERVICES),
	OTHER_SPECIES_SERVICES("Other Species Services", CondensedField.LA_MISC),
	OVER_THE_COUNTER_SALES("Over The Counter Sales", CondensedField.SA_OVER_COUNTER),
	PROFESSIONAL_SERVICES("Professional Services", CondensedField.SA_PROFESSIONAL_SERVICES),
	RADIOLOGY("Radiology", CondensedField.SA_DIAGNOSTIC),
	SURGERY_MINOR("Surgery - Minor", CondensedField.SA_PROFESSIONAL_SERVICES),
	TAGS_LICENSE("Tags/License", CondensedField.SA_MISC),
	ULTRASOUND("Ultrasound", CondensedField.SA_DIAGNOSTIC),
	ANTIBIOTICS_INJECTABLE("Antibiotics-Injectable", CondensedField.LA_PRESCRIPTION_DRUGS),
	ANTIBIOTICS_ORAL("Antibiotics-Oral", CondensedField.LA_PRESCRIPTION_DRUGS),
	BANDAGE_TAPE_GUAZE_DRESS("Bandage/Tape/Guaze/Dress", CondensedField.SA_OVER_COUNTER),
	BIOLOGICALS("Biologicals", CondensedField.LA_PRESCRIPTION_DRUGS),
	BO_DISPENSING_MEDICATIONS("Bo-Dispensing Medications", CondensedField.LA_PRESCRIPTION_DRUGS),
	CALCIUM_ORAL("Calcium-Oral", CondensedField.LA_DRUGS_OTHER),
	CLINIC_SUPPLIES("Clinic Supplies", CondensedField.SA_HOSPITAL),
	EQUINE_ANTINFLAMMATORIES("Equine Antinflammatories", CondensedField.EQ_PRESCRIPTION_DRUGS),
	EQUINE_MISC_DRUG("Equine Misc. Drug", CondensedField.EQ_PRESCRIPTION_DRUGS),
	EQUINE_SEDATIVE_PAIN_RELIEVE("Equine Sedative/Pain Relieve", CondensedField.EQ_PRESCRIPTION_DRUGS),
	EQUINE_DISPENSED_MEDICATION("Equine Dispensed Medication", CondensedField.EQ_PRESCRIPTION_DRUGS),
	EQUINE_BIOLOGICALS("Equine Biologicals", CondensedField.EQ_PRESCRIPTION_DRUGS),
	EQUINE_VITS_AND_COAT_PRODUCTS("Equine Vits & Coat Products", CondensedField.EQ_DRUGS_OTHER),
	EQUINE_WORMERS_AND_FLY_CONT("Equine Wormers And Fly Cont.", CondensedField.EQ_DRUGS_OTHER),
	FLEA_CONTROL_PRODUCTS("Flea Control Products", CondensedField.SA_FLEA_HEART),
	FLUIDS_ORAL("Fluids-Oral", CondensedField.LA_DRUGS_OTHER),
	FLUIDS_PARENTERAL("Fluids-Parenteral", CondensedField.LA_DRUGS_OTHER),
	HEARTWORM_PRODUCTS("Heartworm Products", CondensedField.SA_FLEA_HEART),
	HILLS_PRESCRIPTION_DIETS("Hills Prescription Diets", CondensedField.SA_PET_FOOD),
	HORMONES("Hormones", CondensedField.LA_PRESCRIPTION_DRUGS),
	INJECTION_MEDS_ADMINISTERED("Injection- Meds Administered", CondensedField.SA_PRESCRIPTION_DRUGS),
	LARGE_ANIMAL_SUPPLIES("Large Animal Supplies", CondensedField.LA_MISC),
	MASTITIS_DRY_COW("Mastitis-Dry Cow", CondensedField.LA_PRESCRIPTION_DRUGS),
	MASTITIS_LACTATING("Mastitis-Lactating", CondensedField.LA_PRESCRIPTION_DRUGS),
	MISC_INJECTABLES_LA("Misc Injectables La", CondensedField.LA_PRESCRIPTION_DRUGS),
	MISC_ORAL_PRODUCTS_LA("Misc Oral Products La", CondensedField.LA_DRUGS_OTHER),
	PARASITICIDES_ORAL("Parasiticides-Oral", CondensedField.LA_DRUGS_OTHER),
	PARASITICIDES_TOPICAL("Parasiticides-Topical", CondensedField.LA_DRUGS_OTHER),
	SA_MISC_NEUTRICEUTICALS_AND_OTHER("SA Misc Neutriceuticals & Other", CondensedField.SA_OVER_COUNTER),
	SA_SUTURE_MATERIALS("SA Suture Materials", CondensedField.SA_HOSPITAL),
	SMALL_ANIMAL_SUPPLIES_SHONNA("SMALL ANIMAL SUPPLIES - SHONNA", CondensedField.SA_HOSPITAL),
	SA_CARDIAC_DIUR_BRONCHODIL("Sa Cardiac/Diur/Bronchodil", CondensedField.SA_PRESCRIPTION_DRUGS),
	SA_EXT_PREMISE_PARASITICIDES("Sa Ext/Premise Parasiticides", CondensedField.SA_OVER_COUNTER),
	SA_FLUIDS_PARENTERAL("Sa Fluids(parenteral)", CondensedField.SA_HOSPITAL),
	SA_FOOD("Sa Food", CondensedField.SA_PET_FOOD),
	SA_INJECTABLE_ANTIBIOTICS("Sa Injectable Antibiotics", CondensedField.SA_PRESCRIPTION_DRUGS),
	SA_INJECTABLE_STEROIDS("Sa Injectable Steroids", CondensedField.SA_PRESCRIPTION_DRUGS),
	SA_MISC_INJECTABLES("Sa Misc Injectables", CondensedField.SA_PRESCRIPTION_DRUGS),
	SA_MISC_ORAL_DRUGS("Sa Misc Oral Drugs", CondensedField.SA_PRESCRIPTION_DRUGS),
	SA_OPHTHALMIC_PRODUCTS("Sa Ophthalmic Products", CondensedField.SA_PRESCRIPTION_DRUGS),
	SA_ORAL_ANTIBIOTICS("Sa Oral Antibiotics", CondensedField.SA_PRESCRIPTION_DRUGS),
	SA_ORAL_PARASITICIDES("Sa Oral Parasiticides", CondensedField.SA_PRESCRIPTION_DRUGS),
	SA_ORAL_STEROIDS_AIS("Sa Oral Steroids/Ai's", CondensedField.SA_PRESCRIPTION_DRUGS),
	SA_OTIC_PRODUCTS("Sa Otic Products", CondensedField.SA_PRESCRIPTION_DRUGS),
	SA_PET_SUPPLIES("Sa Pet Supplies", CondensedField.SA_OVER_COUNTER),
	SA_SHAMPOOS_AND_COAT_SKIN_CARE("Sa Shampoos & Coat/Skin Care", CondensedField.SA_OVER_COUNTER),
	SA_TOPICAL_AB_AB_STEROID("Sa Topical Ab/Ab+steroid", CondensedField.SA_OVER_COUNTER),
	DISINFECTANTS("Disinfectants", CondensedField.SA_OVER_COUNTER),
	SA_LAB("Sa lab", CondensedField.SA_LAB),
	STEROIDS_AND_ANTINFLAMMATORIES("Steroids & Antinflammatories", CondensedField.LA_PRESCRIPTION_DRUGS),
	TOPICAL_SPRAYS_AND_OINTMENTS("Topical Sprays And Ointments", CondensedField.LA_DRUGS_OTHER),
	VITAMINS_INJECTABLE("Vitamins-Injectable", CondensedField.LA_DRUGS_OTHER),
	PARASITICIDES_INJECTABLE("Parasiticides - Injectable", CondensedField.LA_DRUGS_OTHER),
	;
	
	private String title;
	private CondensedField condensedField;
	
	private DetailedField(String title, CondensedField condensedField) {
		this.title = title;
		this.condensedField = condensedField;
	}
	
	public String getTitle() {
		return title;
	}
	
	public CondensedField getCondensedField() {
		return condensedField;
	}
	
	public static DetailedField getFieldByTitle(String title) throws Exception{
		for(DetailedField field : DetailedField.values()) {
			if(field.getTitle().equals(title)) {
				return field;
			}
		}
		
		throw new Exception("No such DetailedField with title " + title);
	}
}
