package com.inverita.testapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inverita.testapp.util.ApiClient;
import com.inverita.testapp.R;
import com.inverita.testapp.model.Photo;
import com.inverita.testapp.model.PhotosList;
import com.inverita.testapp.retrofitInterface.VkServiceInterface;
import com.inverita.testapp.view.adapter.PhotosListRecycleViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosListFragment extends Fragment {

	private int NUMBER_OF_COLUMNS = 2;
	private RecyclerView recyclerView;
	private List<Photo> photos;
	private VkServiceInterface vkServiceInterface;
	private Integer albumId;
	private Integer friendId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.recycler_view, container, false);
		Bundle bundle = getArguments();
		Log.d("Log", "bundle = " + bundle.getInt("albumId"));
		albumId = bundle.getInt("albumId");
		friendId = bundle.getInt("friendId");
		recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_id);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), NUMBER_OF_COLUMNS));
		recyclerView.setHasFixedSize(true);
		getFriendsPhotos();
		return layout;
	}

	private void getFriendsPhotos() {
		vkServiceInterface = ApiClient.getClient().create(VkServiceInterface.class);
		Log.d("Log", "vkService = " + friendId + "   album = " + albumId);
		final Call<PhotosList> callAlbumsId = vkServiceInterface.getPhotos(friendId, albumId);
		callAlbumsId.enqueue(new Callback<PhotosList>() {
			@Override
			public void onResponse(Call<PhotosList> call, Response<PhotosList> friendsListResponse) {
				PhotosList friendsList = new PhotosList();
				photos = friendsListResponse.body().getResponse();
				Log.d("Log", "PHOTOS = " + photos.size());
				friendsList.setResponse(photos);
				PhotosListRecycleViewAdapter adapter = new PhotosListRecycleViewAdapter(getActivity(), photos);
				adapter.notifyDataSetChanged();
				recyclerView.setAdapter(adapter);
			}
			@Override
			public void onFailure(Call<PhotosList> call, Throwable t) {
				Log.d("Log", "failed to get friendsListId");
			}
		});
	}

}
