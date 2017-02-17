package com.inverita.testapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inverita.testapp.R;
import com.inverita.testapp.model.Album;
import com.inverita.testapp.model.AlbumsList;
import com.inverita.testapp.retrofitInterface.VkServiceInterface;
import com.inverita.testapp.util.ApiClient;
import com.inverita.testapp.view.adapter.AlbumsListRecycleViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsListFragment extends Fragment implements AlbumsListRecycleViewAdapter.OnAlbumClick {

	private int NUMBER_OF_COLUMNS = 2;
	private RecyclerView recyclerView;
	private VkServiceInterface vkServiceInterface;
	private List<Album> albums;
	private int friendId;
	private String token;
	SharedPreferences sharedPreferences;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.albums_list_fragment, container, false);

		sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
		token = sharedPreferences.getString("TokenKey", "");

		int currentOrientation = getActivity().getResources().getConfiguration().orientation;
		int landscapeOrientation = Configuration.ORIENTATION_LANDSCAPE;
		if (currentOrientation == landscapeOrientation) {
			FriendsListFragment friendsListFragment = new FriendsListFragment();
			getFragmentManager().beginTransaction().replace(R.id.albums_navigation_friend_list, friendsListFragment, "").commit();
		}
		Bundle bundle = getArguments();
		friendId = bundle.getInt("friendId");
		recyclerView = (RecyclerView) layout.findViewById(R.id.albums_recycler_view);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), NUMBER_OF_COLUMNS));
		recyclerView.setHasFixedSize(true);
		getFriendsAlbums();
		return layout;
	}

	private void getFriendsAlbums() {
		vkServiceInterface = ApiClient.getClient().create(VkServiceInterface.class);
		Call<AlbumsList> callAlbums = vkServiceInterface.getAlbums(friendId, token);
		callAlbums.enqueue(new Callback<AlbumsList>() {
			@Override
			public void onResponse(Call<AlbumsList> call, Response<AlbumsList> albumsListResponse) {
				AlbumsList albumsList = new AlbumsList();
				albums = albumsListResponse.body().getResponse();
				albumsList.setResponse(albums);
				AlbumsListRecycleViewAdapter adapter = new AlbumsListRecycleViewAdapter(getActivity(), albums, AlbumsListFragment.this);
				adapter.notifyDataSetChanged();
				recyclerView.setAdapter(adapter);
				if (albums.size() == 0 && albums == null) {
					Toast.makeText(getActivity(), "albums list is empty", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(Call<AlbumsList> call, Throwable t) {
				Log.d("error", "failed to get friends albums");
			}
		});
	}

	@Override
	public void onAlbumClick(int id) {
		PhotosListFragment photosListFragment = new PhotosListFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("albumId", id);
		bundle.putInt("friendId", friendId);
		photosListFragment.setArguments(bundle);
		getFragmentManager().beginTransaction().replace(R.id.activity_main, photosListFragment, "")
				.addToBackStack("PhotosListFragment").commit();
	}
}
