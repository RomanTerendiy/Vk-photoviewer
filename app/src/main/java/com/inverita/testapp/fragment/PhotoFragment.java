package com.inverita.testapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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

public class PhotoFragment extends Fragment implements PhotoAdapter.SharePhoto {

	private VkServiceInterface vkServiceInterface;
	private ViewPager viewPager;
	private List<Photo> photos;
	private int friendId;
	private int albumId;
	private int photoId;
	private int position;
	private int photoPosition;

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.sharing_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		sharePhoto();
		return super.onOptionsItemSelected(item);
	}

	public void sharePhoto() {
		Uri uri = Uri.parse(photos.get(photoPosition).getSrc());
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
		shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		shareIntent.setType("image/*");
		startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.share_to)));
		getActivity().getSupportFragmentManager().beginTransaction()
				.addToBackStack("PhotoFragment")
				.commit();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.photo_fragment, container, false);
		setHasOptionsMenu(true);
		Bundle bundle = getArguments();
		friendId = bundle.getInt("friendId");
		albumId = bundle.getInt("albumId");
		photoId = bundle.getInt("photoId");
		position = bundle.getInt("position");
		viewPager = (ViewPager) layout.findViewById(R.id.photo_view_pager);
		getFriendsPhoto();
		return layout;
	}

	private void getFriendsPhoto() {
		vkServiceInterface = ApiClient.getClient().create(VkServiceInterface.class);
		final Call<PhotosList> callPhotos = vkServiceInterface.getPhotos(friendId, albumId);
		callPhotos.enqueue(new Callback<PhotosList>() {
			@Override
			public void onResponse(Call<PhotosList> call, Response<PhotosList> friendsListResponse) {
				PhotosList photosList = new PhotosList();
				photos = friendsListResponse.body().getResponse();
				photosList.setResponse(photos);
				PhotoAdapter photoAdapter = new PhotoAdapter(getActivity(), photos, PhotoFragment.this);
				viewPager.setAdapter(photoAdapter);
				viewPager.setCurrentItem(position);
				photoAdapter.notifyDataSetChanged();
			}

			@Override
			public void onFailure(Call<PhotosList> call, Throwable t) {
				Log.d("error", "failed to get friendsListId");
			}
		});
	}

	@Override
	public void sharePhoto(int position) {
		photoPosition = position - 1; //fix magic number
	}

	@Override
	public void displayToolbar() {
		ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
		if (actionBar.isShowing()) {
			actionBar.hide();
		} else {
			actionBar.show();
		}
	}
}