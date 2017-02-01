package com.inverita.testapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
