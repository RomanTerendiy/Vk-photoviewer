package com.inverita.testapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

public class FriendsList extends Fragment{

	RecyclerView recyclerView;
	List<Person> persons;


	public FriendsList() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.recycle_view, container, false);
		recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setHasFixedSize(true);
		persons = new ArrayList<>();
		initializeData();
		RVAdapter adapter = new RVAdapter(persons);
		recyclerView.setAdapter(adapter);

		return layout;
	}

	private List<Person> initializeData(){
		persons = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			if (i%2 == 0) {
				persons.add(new Person("Bonnie Parker", "25 years old", R.mipmap.female));
			} else {
				persons.add(new Person("Clyde Barrow", "30 years old", R.mipmap.male));
			}
		}
		return persons;
	}

//	private void initializeAdapter(List<Person> persons){
//		RVAdapter adapter = new RVAdapter(persons);
//		recyclerView.setAdapter(adapter);
//	}

}
