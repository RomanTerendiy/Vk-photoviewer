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
import com.inverita.testapp.model.Friend;

import java.util.List;

public class FriendsListRecycleViewAdapter extends RecyclerView.Adapter<FriendsListRecycleViewAdapter.PersonViewHolder> {

	private Context context;
	private List<Friend> friendsList;
	private OnFriendClick onFriendClick;

	public FriendsListRecycleViewAdapter(Context context, List<Friend> friends, OnFriendClick onFriendClick) {
		this.friendsList = friends;
		this.context = context;
		this.onFriendClick = onFriendClick;
	}

	public interface OnFriendClick {
		void onFriendClick(int id);
	}

	public static class PersonViewHolder extends RecyclerView.ViewHolder {
		CardView cardView;
		private TextView friendsName;
		private TextView friendsLastName;
		private ImageView friendsPhoto;

		PersonViewHolder(View itemView) {
			super(itemView);
			cardView = (CardView) itemView.findViewById(R.id.friends_list_view_id);
			friendsName = (TextView) itemView.findViewById(R.id.friends_name);
			friendsLastName = (TextView) itemView.findViewById(R.id.friends_last_name);
			friendsPhoto = (ImageView) itemView.findViewById(R.id.friends_photo);
		}
	}

	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}

	@Override
	public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.friends_list_view, viewGroup, false);
		PersonViewHolder pvh = new PersonViewHolder(v);
		return pvh;
	}

	@Override
	public void onBindViewHolder(PersonViewHolder personViewHolder, final int i) {
		personViewHolder.friendsName.setText(friendsList.get(i).getFirstName());
		personViewHolder.friendsLastName.setText(friendsList.get(i).getLastName());
		Glide.with(context)
				.load(friendsList.get(i).getPhoto50())
				.into(personViewHolder.friendsPhoto);
		personViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onFriendClick.onFriendClick(friendsList.get(i).getUserId());
			}
		});
	}

	@Override
	public int getItemCount() {
		return friendsList.size();
	}
}