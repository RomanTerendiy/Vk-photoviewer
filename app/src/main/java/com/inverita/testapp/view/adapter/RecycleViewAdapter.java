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
import com.inverita.testapp.model.Friend;
import com.inverita.testapp.R;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.PersonViewHolder> {

	public static class PersonViewHolder extends RecyclerView.ViewHolder {

		CardView cardView;
		private TextView friendsName;
		private TextView friendsLastName;
		private ImageView friendsPhoto;

		PersonViewHolder(View itemView) {
			super(itemView);
			cardView = (CardView) itemView.findViewById(R.id.card_view_id);
			friendsName = (TextView) itemView.findViewById(R.id.friends_name);
			friendsLastName = (TextView) itemView.findViewById(R.id.friends_last_name);
			friendsPhoto = (ImageView) itemView.findViewById(R.id.friends_photo);
		}
	}

	private Context context;
	private List<Friend> friends;

	public RecycleViewAdapter(Context context, List<Friend> friends) {
		this.friends = friends;
		this.context = context;
	}

	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}

	@Override
	public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
		PersonViewHolder pvh = new PersonViewHolder(v);
		return pvh;
	}

	@Override
	public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
		personViewHolder.friendsName.setText(friends.get(i).getFirstName());
		personViewHolder.friendsLastName.setText(friends.get(i).getLastName());
		Glide.with(context)
				.load(friends.get(i).getPhoto50())
				.into(personViewHolder.friendsPhoto);
	}

	@Override
	public int getItemCount() {
		return friends.size();
	}
}