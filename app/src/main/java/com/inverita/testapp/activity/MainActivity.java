package com.inverita.testapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.inverita.testapp.event.CustomMessageEvent;
import com.inverita.testapp.fragment.FriendsListFragment;
import com.inverita.testapp.R;
import com.inverita.testapp.fragment.WebViewFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

	SharedPreferences sharedPreferences;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		EventBus.getDefault().register(this);
		button = (Button) findViewById(R.id.test_button);
		button.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						button.setVisibility(View.GONE);
						getSupportFragmentManager().beginTransaction().add(R.id.activity_main, new WebViewFragment(), "").commit();
						Toast.makeText(MainActivity.this, "authorisation", Toast.LENGTH_SHORT).show();
					}
				});
	}

	@Subscribe
	public void onEvent(CustomMessageEvent event) {
		Log.d("Log", "Event is on");
		if (event.hasToken()) {
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new FriendsListFragment(), "").commit();
			Toast.makeText(this, "friends list", Toast.LENGTH_LONG).show();
		} else {
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new WebViewFragment(), "").commit();
			setContentView(R.layout.web_view);
			Toast.makeText(this, "authorisation", Toast.LENGTH_SHORT).show();
		}
	}
}
