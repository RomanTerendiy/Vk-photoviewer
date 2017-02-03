package com.inverita.testapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inverita.testapp.ApiClient;
import com.inverita.testapp.R;
import com.inverita.testapp.model.Album;
import com.inverita.testapp.model.AlbumsList;
import com.inverita.testapp.retrofitInterface.VkServiceInterface;
import com.inverita.testapp.view.adapter.AlbumsListRecycleViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumListFragment extends Fragment {

	private int NUMBER_OF_COLUMNS = 2;
	private RecyclerView recyclerView;
	private VkServiceInterface vkServiceInterface;
	private List<Album> albums;
	private Integer friendId;
	Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.recycler_view, container, false);
		Bundle bundle = getArguments();
		Log.d("Log", "bundle = " + bundle.getInt("friendId"));
		friendId = bundle.getInt("friendId");
		recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_id);
		recyclerView.setLayoutManager(new GridLayoutManager(context, NUMBER_OF_COLUMNS));
		recyclerView.setHasFixedSize(true);
		getFriendsAlbums();
		return layout;
	}

	private void getFriendsAlbums() {
		vkServiceInterface = ApiClient.getClient().create(VkServiceInterface.class);
		Call<AlbumsList> callAlbums = vkServiceInterface.getAlbums(friendId); //14025113
		callAlbums.enqueue(new Callback<AlbumsList>() {
			@Override
			public void onResponse(Call<AlbumsList> call, Response<AlbumsList> albumsListResponse) {
				AlbumsList albumsList = new AlbumsList();
				albums = albumsListResponse.body().getResponse();
				Log.d("Log", "albumsListResponse = " + albums.size());
				albumsList.setResponse(albums);
				AlbumsListRecycleViewAdapter adapter = new AlbumsListRecycleViewAdapter(getActivity(), albums);
				adapter.notifyDataSetChanged();
				recyclerView.setAdapter(adapter);
			}
			@Override
			public void onFailure(Call<AlbumsList> call, Throwable t) {
				Log.d("Log", "failed to get friendsListId");
			}
		});
	}
}
