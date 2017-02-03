package com.inverita.testapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.inverita.testapp.R;
import com.inverita.testapp.fragment.PhotosListFragment;
import com.inverita.testapp.model.Photo;

import java.util.List;

public class PhotosListRecycleViewAdapter extends RecyclerView.Adapter<PhotosListRecycleViewAdapter.PhotosViewHolder> {

	private Context context;
	private List<Photo> photosList;

	public PhotosListRecycleViewAdapter(Context context, List<Photo> photosList) {
		this.photosList = photosList;
		this.context = context;
	}

	public static class PhotosViewHolder extends RecyclerView.ViewHolder {
		GridLayout gridLayout;
		private ImageView albumPhoto;

		PhotosViewHolder(View itemView) {
			super(itemView);
			gridLayout = (GridLayout) itemView.findViewById(R.id.photos_list_id);
			albumPhoto = (ImageView) itemView.findViewById(R.id.album_photo_id);
		}
	}

	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}

	@Override
	public PhotosListRecycleViewAdapter.PhotosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photos_list_view, viewGroup, false);
		PhotosListRecycleViewAdapter.PhotosViewHolder pvh = new PhotosListRecycleViewAdapter.PhotosViewHolder(v);
		return pvh;
	}

	@Override
	public void onBindViewHolder(PhotosListRecycleViewAdapter.PhotosViewHolder photosViewHolder, final int i) {
		Log.d("Log", "photosList = " + photosList.size());
		Glide.with(context)
				.load(photosList.get(i).getSrc())
				.into(photosViewHolder.albumPhoto);
	}

	@Override
	public int getItemCount() {
		Log.d("Log", "photosList.size() = " + photosList.size());
		return photosList.size();
	}
}