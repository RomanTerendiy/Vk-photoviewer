package com.inverita.testapp;

import android.app.Application;
import android.util.Log;

import com.inverita.testapp.model.Friend;
import com.inverita.testapp.model.FriendsList;
import com.inverita.testapp.retrofitInterface.VkServiceInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VkPhotoViewerApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
	}
}