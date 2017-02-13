package com.inverita.testapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.inverita.testapp.R;
import com.inverita.testapp.event.CustomMessageEvent;
import com.inverita.testapp.fragment.FriendsListFragment;
import com.inverita.testapp.fragment.WebViewFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

	SharedPreferences sharedPreferences;
	Button button;
	Toolbar toolBar;
	WebViewFragment webViewFragment;
	FriendsListFragment friendsListFragment;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient));
		sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		EventBus.getDefault().register(this);
		button = (Button) findViewById(R.id.test_button);
		button.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						button.setVisibility(View.GONE);
						webViewFragment = new WebViewFragment();
						getSupportFragmentManager().beginTransaction().add(R.id.activity_main, webViewFragment, "")
								.addToBackStack("WebViewFragment").commit();
					}
				});
	}

	@Subscribe
	public void onEvent(CustomMessageEvent event) {
		Log.d("Log", "Event is on");
		if (event.hasToken()) {
			friendsListFragment = new FriendsListFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, friendsListFragment, "")
					.addToBackStack("FriendsListFragment").commit();
		} else {
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, webViewFragment, "")
					.addToBackStack("WebViewFragment").commit();
			setContentView(R.layout.web_view);
		}
	}
}
