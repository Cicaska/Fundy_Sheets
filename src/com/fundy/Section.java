package com.fundy;

/**
 * Each sheet is divided into sections for readability. 
 * For Shubie, the sections are Small Animal, Large Animal, 
 * and Equine.
 * 
 * @author Mitchell Roberts
 * Date Created: Sep 22, 2018
 */
public enum Section {
	SMALL_ANIMAL_SHUBIE("Small Animal Shubie"),
	LARGE_ANIMAL_SHUBIE("Large Animal Shubie"),
	EQUINE_SHUBIE("Equine Shubie"),
	;
	
	private String title;
	
	private Section(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}
