package com.inverita.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.inverita.testapp.R;
import com.inverita.testapp.event.CustomMessageEvent;
import com.inverita.testapp.fragment.FriendsListFragment;
import com.inverita.testapp.fragment.WebViewFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;

public class MainActivity extends AppCompatActivity {

	SharedPreferences sharedPreferences;
	Button button;
	private ShareActionProvider mShareActionProvider;

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.sharing_menu, menu);
//		MenuItem facebookMenuItem = menu.findItem(R.id.facebook_menu_item_id);
//		mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(facebookMenuItem);

//		File f = new File("C:\\Android\\TestApp\\app\\src\\main\\res\\mipmap-hdpi\facebook_icon");
//		Uri uri = Uri.parse("file://" + f.getAbsolutePath());
//		Intent shareIntent = new Intent();
//		shareIntent.setAction(Intent.ACTION_SEND);
//		shareIntent.setPackage("com.instagram.android");
//		shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
//		shareIntent.setType("image/*");
//		shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//		this.startActivity(Intent.createChooser(shareIntent, "Share image File"));
//		setShareIntent(shareIntent);

		return true;
	}

	private void setShareIntent(Intent shareIntent) {
		if (mShareActionProvider != null) {
			mShareActionProvider.setShareIntent(shareIntent);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.facebook_menu_item_id) {
			Toast.makeText(this, "sharePhotoOnFacebook", Toast.LENGTH_SHORT).show();
		} else if (item.getItemId() == R.id.instagram_menu_item_id) {
			File f = new File("C:\\Android\\TestApp\\app\\src\\main\\res\\mipmap-hdpi\facebook_icon");
			Uri uri = Uri.parse("file://" + f.getAbsolutePath());
			Intent shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.setPackage("com.facebook.android"); //https://www.facebook.com/  com.instagram.android
			shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
			shareIntent.setType("image/*");
			shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			this.startActivity(Intent.createChooser(shareIntent, "Share image File"));
			setShareIntent(shareIntent);
			Toast.makeText(this, "sharePhotoOnInstagram", Toast.LENGTH_SHORT).show();
		} else if (item.getItemId() == R.id.twitter_menu_item_id) {
			Toast.makeText(this, "sharePhotoOnTwitter", Toast.LENGTH_SHORT).show();
		}
		return super.onOptionsItemSelected(item);
	}

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
			Toast.makeText(this, "friends list", Toast.LENGTH_SHORT).show();
		} else {
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new WebViewFragment(), "").commit();
			setContentView(R.layout.web_view);
			Toast.makeText(this, "authorisation", Toast.LENGTH_SHORT).show();
		}
	}
}
