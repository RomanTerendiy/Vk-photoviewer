package com.inverita.testapp.retrofitInterface;

import com.inverita.testapp.model.FriendsList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VkServiceInterface {

	//https://api.vk.com/method/friends.get?user_id=14025113&count=20&fields=photo_50
	@GET("method/friends.get?user_id=14025113&count=30&fields=photo_50")
	Call<FriendsList> getFriends();


}
