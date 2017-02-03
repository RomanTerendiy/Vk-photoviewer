package com.inverita.testapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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