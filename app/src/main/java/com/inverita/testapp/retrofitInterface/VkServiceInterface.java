package com.inverita.testapp.retrofitInterface;

import com.inverita.testapp.model.AlbumsList;
import com.inverita.testapp.model.FriendsList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VkServiceInterface {

	//https://api.vk.com/method/friends.get?user_id=14025113&count=20&fields=photo_50
	@GET("method/friends.get?user_id=14025113&count=30&fields=photo_50")
	Call<FriendsList> getFriends();

	//https://api.vk.com/method/photos.getAlbums?owner_id=14025113&album_ids=167457024,167911130,167457528,167613013&need_covers=1
	@GET("method/photos.getAlbums?owner_id=14025113&album_ids=167457024,167911130,167457528,167613013&need_covers=1")
	Call<AlbumsList> getAlbums();
}
