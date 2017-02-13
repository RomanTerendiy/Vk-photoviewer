package com.inverita.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FriendsList {
	@SerializedName("response")
	@Expose
	private List<Friend> response;

	public List<Friend> getResponse() {
		return response;
	}

	public void setResponse(List<Friend> response) {
		this.response = response;
	}
}
