package com.inverita.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Friend {
	public Friend() {
	}

	public Friend(Integer userId, String photo50, String firstName, String lastName) {
		this.userId = userId;
		this.photo50 = photo50;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@SerializedName("uid")
	@Expose
	private Integer uid;
	@SerializedName("first_name")
	@Expose
	private String firstName;
	@SerializedName("last_name")
	@Expose
	private String lastName;
	@SerializedName("photo_50")
	@Expose
	private String photo50;
	@SerializedName("user_id")
	@Expose
	private Integer userId;
	@SerializedName("hidden")
	@Expose
	private Integer hidden;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoto50() {
		return photo50;
	}

	public void setPhoto50(String photo50) {
		this.photo50 = photo50;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getHidden() {
		return hidden;
	}

	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}

	@Override
	public String toString() {
		return "Friend{" +
				"uid=" + uid +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", photo50='" + photo50 + '\'' +
				", userId=" + userId +
				", hidden=" + hidden +
				'}';
	}
}