package com.inverita.testapp.retrofitInterface;

import com.inverita.testapp.model.AlbumsList;
import com.inverita.testapp.model.FriendsList;
import com.inverita.testapp.model.PhotosList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VkServiceInterface {

	//https://api.vk.com/method/friends.get?user_id=14025113&count=20&fields=photo_50
	@GET("method/friends.get?user_id=14025113&count=30&fields=photo_50")
	Call<FriendsList> getFriends();

	//https://api.vk.com/method/photos.getAlbums?owner_id=14025113&need_covers=1
	@GET("method/photos.getAlbums?&need_covers=1")
	Call<AlbumsList> getAlbums(@Query("owner_id") int friendId);

	//https://api.vk.com/method/photos.get?owner_id=14025113&album_id=164284109
	@GET("method/photos.get?")
	Call<PhotosList> getPhotos(@Query("owner_id") int friendId, @Query("album_id") int albumId);

	//https://api.vk.com/method/photos.get?owner_id=14025113&album_id=167457024&photo_ids=293666120&v=5.62
//	@GET("method/photos.get?")
//	Call<PhotosList> getPhoto(@Query("owner_id") int friendId, @Query("album_id") int albumId, @Query("photo_ids") int photoId);

}
