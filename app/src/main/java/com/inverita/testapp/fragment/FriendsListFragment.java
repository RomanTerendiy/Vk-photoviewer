package com.inverita.testapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inverita.testapp.ApiClient;
import com.inverita.testapp.model.Friend;
import com.inverita.testapp.model.FriendsList;
import com.inverita.testapp.R;
import com.inverita.testapp.retrofitInterface.VkServiceInterface;
import com.inverita.testapp.view.adapter.RecycleViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsListFragment extends Fragment {

	RecyclerView recyclerView;
	List<Friend> friends;
	private VkServiceInterface vkServiceInterface;

	public FriendsListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.recycler_view, container, false);
		recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_id);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setHasFixedSize(true);
		getMyFriends();
		return layout;
	}

	private void getMyFriends() {
		vkServiceInterface = ApiClient.getClient().create(VkServiceInterface.class);
		Call<FriendsList> callFriendsId = vkServiceInterface.getFriends();
		callFriendsId.enqueue(new Callback<FriendsList>() {
			@Override
			public void onResponse(Call<FriendsList> call, Response<FriendsList> friendsListResponse) {
				FriendsList friendsList = new FriendsList();
				friends = friendsListResponse.body().getResponse();
				Log.d("Log", "vkService = " + friends);
				friendsList.setResponse(friends);
				//temp
				for (Friend friend : friendsList.getResponse()) {
					Log.d("Log", "vkFriendId = " + friend);
				}
				RecycleViewAdapter adapter = new RecycleViewAdapter(getActivity(), friends);
				adapter.notifyDataSetChanged();
				recyclerView.setAdapter(adapter);
			}

			@Override
			public void onFailure(Call<FriendsList> call, Throwable t) {
				Log.d("Log", "failed to get friendsListId");
			}
		});
	}

}
