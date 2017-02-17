package com.inverita.testapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PhotosList {

	@SerializedName("response")
	private List<Photo> response = new ArrayList<>(0);

	public List<Photo> getResponse() {
		return response;
	}

	public void setResponse(List<Photo> response) {
		this.response = response;
	}

}