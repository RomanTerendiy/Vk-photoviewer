package com.inverita.testapp.retrofitInterface;

import com.inverita.testapp.model.AlbumsList;
import com.inverita.testapp.model.FriendsList;
import com.inverita.testapp.model.PhotosList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VkServiceInterface {

	@GET("method/friends.get")
	Call<FriendsList> getFriends(@Query("user_id") int id, @Query("count") int count, @Query("fields") String photo);

	//https://api.vk.com/method/photos.getAlbums?owner_id=14025113&need_covers=1
	@GET("method/photos.getAlbums?need_covers=1")
	Call<AlbumsList> getAlbums(@Query("owner_id") int friendId, @Query("access_token") String accessToken);

	//https://api.vk.com/method/photos.get?owner_id=14025113&album_id=164284109
	@GET("method/photos.get")
	Call<PhotosList> getPhotos(@Query("owner_id") int friendId, @Query("album_id") int albumId, @Query("access_token") String accessToken);

}
