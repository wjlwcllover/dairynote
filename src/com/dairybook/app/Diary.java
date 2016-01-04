package com.dairybook.app;

import android.R.integer;

public class Diary {

	public String titleString;
	public String date;
	public String contentString;
	public int dairyId;
	public int imageId;

	public Diary()
	{
		
	}
	
	public Diary(String titleString, String date, String contentString,
			int dairyId, int imageId) {
		super();
		this.titleString = titleString;
		this.date = date;
		this.contentString = contentString;
		this.dairyId = dairyId;
		this.imageId = imageId;
	}

	public String getTitleString() {
		return titleString;
	}

	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContentString() {
		return contentString;
	}

	public void setContentString(String contentString) {
		this.contentString = contentString;
	}

	public int getDairyId() {
		return dairyId;
	}

	public void setDairyId(int dairyId) {
		this.dairyId = dairyId;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

}
