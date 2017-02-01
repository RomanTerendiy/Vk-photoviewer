package com.inverita.testapp.event;

public class CustomMessageEvent {

	private boolean isToken;

	public CustomMessageEvent() {
	}

	public boolean hasToken() {
		return isToken;
	}

	public void setToken(boolean token) {
		this.isToken = token;
	}
}
