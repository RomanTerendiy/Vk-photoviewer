package com.inverita.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumsList {

	@SerializedName("response")
	@Expose
	private List<Album> response = null;

	public List<Album> getResponse() {
		return response;
	}

	public void setResponse(List<Album> response) {
		this.response = response;
	}

}