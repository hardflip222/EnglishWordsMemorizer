package com.piotrek.web.model;

public class Word {
	
	private int id;
	private String englishWord;
	private String polishWord;
	
	
	
	public Word(String englishWord, String polishWord) {
		
		this.englishWord = englishWord;
		this.polishWord = polishWord;
	}



	public Word(int id, String englishWord, String polishWord) {
		
		this(englishWord,polishWord);
		this.id = id;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getEnglishWord() {
		return englishWord;
	}



	public void setEnglishWord(String englishWord) {
		this.englishWord = englishWord;
	}



	public String getPolishWord() {
		return polishWord;
	}



	public void setPolishWord(String polishWord) {
		this.polishWord = polishWord;
	}



	@Override
	public String toString() {
		return "Word [id=" + id + ", englishWord=" + englishWord + ", polishWord=" + polishWord + "]";
	}
	
	
	
	
	

}
