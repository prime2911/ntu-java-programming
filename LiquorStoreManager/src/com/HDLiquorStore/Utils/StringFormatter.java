package com.HDLiquorStore.Utils;

public class StringFormatter {
	
	// Converts Java.SQL's yyyy-mm-dd string format to dd/mm/yyyy format and vice-versa
	public String reformatDateString(String date, boolean toDisplay) {
		String[] parts;
		if (toDisplay) {
			parts = date.split("-");
			return String.valueOf(parts[2] + "/" + parts[1] + "/" + parts[0]);
		} else {
			parts = date.split("/");
			return String.valueOf(parts[2] + "-" + parts[1] + "-" + parts[0]);
		}
	}
	
	// Returns either the index or value string from "<index> - <value>" strings (0 for index and 1 for value)
//	public String reformatIdValueString(String str, boolean isId) {
	public String reformatIdValueString(String str, int value) {
		String[] parts;
		parts = str.split(" - ");
//		if (isId)		
//			return String.valueOf(parts[0]);
//		else 
//			return String.valueOf(parts[1]);
		return String.valueOf(parts[value]);
	}
}
