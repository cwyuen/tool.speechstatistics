package com.primecredit.tool.speechstatistics.domain;

public class PinYin {
	private String name;
	private String initials;
	private String vowel;
	private String tone;
	
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	public String getVowel() {
		return vowel;
	}
	public void setVowel(String vowel) {
		this.vowel = vowel;
	}
	public String getTone() {
		return tone;
	}
	public void setTone(String tone) {
		this.tone = tone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
