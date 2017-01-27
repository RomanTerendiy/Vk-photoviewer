package com.inverita.testapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FriendsList extends Fragment{

	RecyclerView recyclerView;
	List<Person> persons;

	public FriendsList() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.recycler_view, container, false);
		recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_id);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setHasFixedSize(true);
		persons = new ArrayList<>();
		initializeData();
		RecycleViewAdapter adapter = new RecycleViewAdapter(persons);
		recyclerView.setAdapter(adapter);
		return layout;
	}

	private List<Person> initializeData(){
		persons = new ArrayList<>();
		for (int i = 1; i <= 30; i++) {
			if (i%2 == 0) {
				persons.add(new Person("Bonnie Parker" + i, "25 years old", R.mipmap.female));
			} else {
				persons.add(new Person("Clyde Barrow" + i, "30 years old", R.mipmap.male));
			}
		}
		return persons;
	}

}
