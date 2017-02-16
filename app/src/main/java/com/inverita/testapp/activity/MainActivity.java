package com.inverita.testapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.inverita.testapp.R;
import com.inverita.testapp.event.CustomMessageEvent;
import com.inverita.testapp.fragment.FriendsListFragment;
import com.inverita.testapp.fragment.RegistrationFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient));
		EventBus.getDefault().register(this);
		RegistrationFragment registrationFragment = new RegistrationFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.activity_main, registrationFragment, "")
				.addToBackStack("RegistrationFragment").commit();
	}

	@Subscribe
	public void onEvent(CustomMessageEvent event) {
		if (event.hasToken()) {
			FriendsListFragment friendsListFragment = new FriendsListFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, friendsListFragment, "")
					.addToBackStack("FriendsListFragment").commit();
		}
	}
}