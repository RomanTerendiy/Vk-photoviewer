package com.inverita.testapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inverita.testapp.R;
import com.inverita.testapp.model.Photo;
import com.inverita.testapp.model.PhotosList;
import com.inverita.testapp.retrofitInterface.VkServiceInterface;
import com.inverita.testapp.util.ApiClient;
import com.inverita.testapp.view.adapter.PhotoAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoFragment extends Fragment {

	private VkServiceInterface vkServiceInterface;
	private ViewPager viewPager;
	private List<Photo> photos;
	private Integer friendId;
	private Integer albumId;
	private Integer photoId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.photo_view, container, false);
		Bundle bundle = getArguments();
		friendId = bundle.getInt("friendId");
		albumId = bundle.getInt("albumId");
		photoId = bundle.getInt("photoId");
		viewPager = (ViewPager) layout.findViewById(R.id.pager);
		getFriendsPhoto();
		return layout;
	}

	private void getFriendsPhoto() {
		vkServiceInterface = ApiClient.getClient().create(VkServiceInterface.class);
		final Call<PhotosList> callPhotos = vkServiceInterface.getPhoto(friendId, albumId, photoId);
		callPhotos.enqueue(new Callback<PhotosList>() {
			@Override
			public void onResponse(Call<PhotosList> call, Response<PhotosList> friendsListResponse) {
				PhotosList photosList = new PhotosList();
				photos = friendsListResponse.body().getResponse();
				photosList.setResponse(photos);
				PhotoAdapter photoAdapter = new PhotoAdapter(getActivity(), photos);
				photoAdapter.notifyDataSetChanged();
				viewPager.setAdapter(photoAdapter);
			}
			@Override
			public void onFailure(Call<PhotosList> call, Throwable t) {
				Log.d("Log", "failed to get friendsListId");
			}
		});
	}
}


