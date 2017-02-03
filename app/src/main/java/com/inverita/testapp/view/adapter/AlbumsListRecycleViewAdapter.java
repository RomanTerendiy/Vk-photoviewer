package com.inverita.testapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inverita.testapp.R;
import com.inverita.testapp.model.Album;

import java.util.List;

public class AlbumsListRecycleViewAdapter extends RecyclerView.Adapter<AlbumsListRecycleViewAdapter.AlbumsViewHolder> {

	private Context context;
	private List<Album> albumList;

	public AlbumsListRecycleViewAdapter(Context context, List<Album> albumList) {
		this.albumList = albumList;
		this.context = context;
	}

	public static class AlbumsViewHolder extends RecyclerView.ViewHolder {
		GridLayout gridLayout;
		TextView albumName;
		ImageView albumCover;
		AlbumsViewHolder(View itemView) {
			super(itemView);
			gridLayout = (GridLayout) itemView.findViewById(R.id.albums_list_id);
			albumName = (TextView) itemView.findViewById(R.id.album_name);
			albumCover = (ImageView) itemView.findViewById(R.id.album_cover);
		}
	}

	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}

	@Override
	public AlbumsListRecycleViewAdapter.AlbumsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.albums_list_view, viewGroup, false);
		AlbumsViewHolder pvh = new AlbumsViewHolder(v);
		return pvh;
	}

	@Override
	public void onBindViewHolder(AlbumsListRecycleViewAdapter.AlbumsViewHolder albumsViewHolder, int i) {
		albumsViewHolder.albumName.setText(albumList.get(i).getTitle());
		Log.d("Log", "albumList = " + albumList.get(i).getThumbSrc() );
		Glide.with(context)
				.load(albumList.get(i).getThumbSrc())
				.into(albumsViewHolder.albumCover);
	}

	@Override
	public int getItemCount() {
		return albumList.size();
	}
}
