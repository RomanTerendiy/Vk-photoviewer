package com.inverita.testapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inverita.testapp.R;
import com.inverita.testapp.model.Friend;
import com.inverita.testapp.model.FriendsList;
import com.inverita.testapp.retrofitInterface.VkServiceInterface;
import com.inverita.testapp.util.ApiClient;
import com.inverita.testapp.view.adapter.FriendsListRecycleViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.defaultValue;

public class FriendsListFragment extends Fragment implements FriendsListRecycleViewAdapter.OnFriendClick {

	private RecyclerView recyclerView;
	private List<Friend> friends;
	private VkServiceInterface vkServiceInterface;
	SharedPreferences sharedPreferences;
	int id;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.friends_list_fragment, container, false);

		sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
		id = sharedPreferences.getInt("UserIdKey", defaultValue);

		recyclerView = (RecyclerView) layout.findViewById(R.id.friend_list_recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setHasFixedSize(true);
		getMyFriends();
		return layout;
	}

	private void getMyFriends() {
		vkServiceInterface = ApiClient.getClient().create(VkServiceInterface.class);
		final Call<FriendsList> callFriendsId = vkServiceInterface.getFriends(id, 50, "photo_50"); //59034633
		callFriendsId.enqueue(new Callback<FriendsList>() {
			@Override
			public void onResponse(Call<FriendsList> call, Response<FriendsList> friendsListResponse) {
				FriendsList friendsList = new FriendsList();
				friends = friendsListResponse.body().getResponse();
				friendsList.setResponse(friends);
				FriendsListRecycleViewAdapter adapter = new FriendsListRecycleViewAdapter(getActivity(), friends, FriendsListFragment.this);
				adapter.notifyDataSetChanged();
				recyclerView.setAdapter(adapter);
			}

			@Override
			public void onFailure(Call<FriendsList> call, Throwable t) {
				Log.d("log", "failed to get friendsListId");
			}
		});
	}

	@Override
	public void onFriendClick(int id) {
		AlbumsListFragment albumListFragment = new AlbumsListFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("friendId", id);
		albumListFragment.setArguments(bundle);
		getFragmentManager().beginTransaction().replace(R.id.activity_main, albumListFragment, "")
				.addToBackStack("AlbumsListFragment").commit();
	}
}