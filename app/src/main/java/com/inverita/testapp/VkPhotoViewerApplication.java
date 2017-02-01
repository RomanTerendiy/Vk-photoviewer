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

	private VkServiceInterface vkServiceInterface;
//	public List<Friend> friends;

	@Override
	public void onCreate() {
		super.onCreate();
//		vkServiceInterface = ApiClient.getClient().create(VkServiceInterface.class);
//		Call<FriendsList> callFriendsId = vkServiceInterface.getFriends();
//		callFriendsId.enqueue(new Callback<FriendsList>() {
//			@Override
//			public void onResponse(Call<FriendsList> call, Response<FriendsList> friendsListResponse) {
//				FriendsList friendsList = new FriendsList();
//				friendsList.setResponse(friendsListResponse.body().getResponse());
////				friends = friendsListResponse.body().getResponse();
//				Log.d("Log", "vkService = " + friendsList.getResponse());
//				friendsList.setResponse(friendsList.getResponse());
//				//temp
//				for (Friend friend : friendsList.getResponse()) {
//					Log.d("Log", "vkFriendId = " + friend);
//				}
//			}
//
//			@Override
//			public void onFailure(Call<FriendsList> call, Throwable t) {
//				Log.d("Log", "failed to get friendsListId");
//			}
//		});



	}
}