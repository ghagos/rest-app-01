package com.ghagos.wsviajersey.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DvdItem {

	private String title;
	
	private String genre;
	
	private String year;

	public DvdItem() {
	}
	
	public DvdItem(String title, String year, String genre) {
		this.title = title;
		this.genre = genre;
		this.year = year;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "DvdItem [title=" + title + ", genre=" + genre + ", year=" + year + "]";
	}
	
}
