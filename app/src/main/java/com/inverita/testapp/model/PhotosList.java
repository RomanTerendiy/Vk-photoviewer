package com.inverita.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotosList {

	@SerializedName("response")
	@Expose
	private List<Photo> response = null;

	public List<Photo> getResponse() {
		return response;
	}

	public void setResponse(List<Photo> response) {
		this.response = response;
	}

}