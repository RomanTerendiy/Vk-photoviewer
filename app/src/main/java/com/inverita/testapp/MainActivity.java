package com.inverita.testapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
		if (event.isToken) {
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new FriendsList(), "").commit();
			Toast.makeText(this, "friends list", Toast.LENGTH_LONG).show();
		} else {
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new WebViewFragment(), "").commit();
			setContentView(R.layout.web_view);
			Toast.makeText(this, "authorisation", Toast.LENGTH_SHORT).show();
		}
	}

//	private List<Person> initializeData(){
//		persons = new ArrayList<>();
//		for (int i = 0; i < 30; i++) {
//			if (i%2 == 0) {
//				persons.add(new Person("Bonnie Parker", "25 years old", R.mipmap.female));
//			} else {
//				persons.add(new Person("Clyde Barrow", "30 years old", R.mipmap.male));
//			}
//		}
//		return persons;
//	}

//	private void initializeAdapter(List<Person> persons){
//		RecycleViewAdapter adapter = new RecycleViewAdapter(persons);
//		recyclerView.setAdapter(adapter);
//	}
}
