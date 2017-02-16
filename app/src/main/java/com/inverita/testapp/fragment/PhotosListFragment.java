package com.inverita.testapp.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inverita.testapp.R;
import com.inverita.testapp.model.Photo;
import com.inverita.testapp.model.PhotosList;
import com.inverita.testapp.retrofitInterface.VkServiceInterface;
import com.inverita.testapp.util.ApiClient;
import com.inverita.testapp.view.adapter.PhotosListRecycleViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosListFragment extends Fragment implements PhotosListRecycleViewAdapter.OnPhotoClick {

	private int NUMBER_OF_COLUMNS = 3;
	private RecyclerView recyclerView;
	private List<Photo> photos;
	private VkServiceInterface vkServiceInterface;
	private int albumId;
	private int friendId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.photos_list_fragment, container, false);
		int currentOrientation = getActivity().getResources().getConfiguration().orientation;
		int landscapeOrientation = Configuration.ORIENTATION_LANDSCAPE;
		if (currentOrientation == landscapeOrientation) {
			FriendsListFragment friendsListFragment = new FriendsListFragment();
			getFragmentManager().beginTransaction().replace(R.id.albums_navigation_friend_list, friendsListFragment, "").commit();
		}
		Bundle bundle = getArguments();
		albumId = bundle.getInt("albumId");
		friendId = bundle.getInt("friendId");
		recyclerView = (RecyclerView) layout.findViewById(R.id.photos_recycler_view);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), NUMBER_OF_COLUMNS));
		recyclerView.setHasFixedSize(true);
		getFriendsPhotos();
		return layout;
	}

	private void getFriendsPhotos() {
		vkServiceInterface = ApiClient.getClient().create(VkServiceInterface.class);
		final Call<PhotosList> callAlbumsId = vkServiceInterface.getPhotos(friendId, albumId);
		callAlbumsId.enqueue(new Callback<PhotosList>() {
			@Override
			public void onResponse(Call<PhotosList> call, Response<PhotosList> photosListResponse) {
				PhotosList photosList = new PhotosList();
				photos = photosListResponse.body().getResponse();
				photosList.setResponse(photos);
				PhotosListRecycleViewAdapter adapter = new PhotosListRecycleViewAdapter(getActivity(), photos, PhotosListFragment.this);
				adapter.notifyDataSetChanged();
				recyclerView.setAdapter(adapter);
			}

			@Override
			public void onFailure(Call<PhotosList> call, Throwable t) {
				Log.d("error", "failed to get friendsListId");
			}
		});
	}

	@Override
	public void onPhotoClick(int friendId, int albumId, int photoId, int position) {
		PhotoFragment photoFragment = new PhotoFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("friendId", friendId);
		bundle.putInt("albumId", albumId);
		bundle.putInt("photoId", photoId);
		bundle.putInt("position", position);
		photoFragment.setArguments(bundle);
		getFragmentManager().beginTransaction().replace(R.id.activity_main, photoFragment, "")
				.addToBackStack("PhotoFragment").commit();
	}


}