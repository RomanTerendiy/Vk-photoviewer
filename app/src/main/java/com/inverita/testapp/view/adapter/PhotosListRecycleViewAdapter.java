package com.inverita.testapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.inverita.testapp.R;
import com.inverita.testapp.model.Photo;

import java.util.List;

public class PhotosListRecycleViewAdapter extends RecyclerView.Adapter<PhotosListRecycleViewAdapter.PhotosViewHolder> {

	private Context context;
	private List<Photo> photosList;
	private OnPhotoClick onPhotoClick;

	public PhotosListRecycleViewAdapter(Context context, List<Photo> photosList, OnPhotoClick onPhotoClick) {
		this.photosList = photosList;
		this.context = context;
		this.onPhotoClick = onPhotoClick;
	}

	public interface OnPhotoClick {
		void onPhotoClick(int friendId, int albumId, int photoId, int position);
	}

	public static class PhotosViewHolder extends RecyclerView.ViewHolder {
		GridLayout gridLayout;
		private ImageView albumPhoto;

		PhotosViewHolder(View itemView) {
			super(itemView);
			gridLayout = (GridLayout) itemView.findViewById(R.id.photos_list_id);
			albumPhoto = (ImageView) itemView.findViewById(R.id.album_photos);
		}
	}

	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}

	@Override
	public PhotosListRecycleViewAdapter.PhotosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photos_list_item, viewGroup, false);
		PhotosListRecycleViewAdapter.PhotosViewHolder pvh = new PhotosListRecycleViewAdapter.PhotosViewHolder(v);
		return pvh;
	}

	@Override
	public void onBindViewHolder(PhotosListRecycleViewAdapter.PhotosViewHolder photosViewHolder, final int i) {
		Glide.with(context)
				.load(photosList.get(i).getSrc())
				.placeholder(R.mipmap.instagram_icon)
				.into(photosViewHolder.albumPhoto);
		photosViewHolder.gridLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onPhotoClick.onPhotoClick(photosList.get(i).getOwnerId(), photosList.get(i).getAid(), photosList.get(i).getPid(), i);
			}
		});
	}

	@Override
	public int getItemCount() {
		return photosList.size();
	}
}