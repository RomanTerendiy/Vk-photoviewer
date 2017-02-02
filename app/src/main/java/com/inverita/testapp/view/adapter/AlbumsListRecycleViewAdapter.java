package com.inverita.testapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inverita.testapp.R;
import com.inverita.testapp.model.Album;

import java.util.List;

public class AlbumsListRecycleViewAdapter extends RecyclerView.Adapter<AlbumsListRecycleViewAdapter.AlbumsViewHolder> {

	public static class AlbumsViewHolder extends RecyclerView.ViewHolder {

		CardView cardView;
		TextView albumName;
		ImageView albumCover;

		AlbumsViewHolder(View itemView) {
			super(itemView);
			cardView = (CardView) itemView.findViewById(R.id.card_view_id);
			albumName = (TextView) itemView.findViewById(R.id.friends_last_name);
			albumCover = (ImageView) itemView.findViewById(R.id.friends_photo);
		}
	}

	private Context context;
	private List<Album> albumList;

	public AlbumsListRecycleViewAdapter(Context context, List<Album> albumList) {
		this.albumList = albumList;
		this.context = context;
	}

	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}

	@Override
	public AlbumsListRecycleViewAdapter.AlbumsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
		AlbumsListRecycleViewAdapter.AlbumsViewHolder pvh = new AlbumsListRecycleViewAdapter.AlbumsViewHolder(v);
		return pvh;
	}

	@Override
	public void onBindViewHolder(AlbumsListRecycleViewAdapter.AlbumsViewHolder albumsViewHolder, int i) {
		albumsViewHolder.albumName.setText(albumList.get(i).getTitle());
		Glide.with(context)
				.load(albumList.get(i).getThumbSrc())
				.into(albumsViewHolder.albumCover);
	}

	@Override
	public int getItemCount() {
		return albumList.size();
	}
}
