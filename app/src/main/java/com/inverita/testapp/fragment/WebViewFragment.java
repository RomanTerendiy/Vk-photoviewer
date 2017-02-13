package com.inverita.testapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.inverita.testapp.R;
import com.inverita.testapp.event.CustomMessageEvent;

import org.greenrobot.eventbus.EventBus;

public class WebViewFragment extends Fragment {

	private final String defaultUrl = "https://oauth.vk.com/authorize?client_id=5826840&display=page&redirect_uri=" +
			"https://oauth.vk.com/blank.html/callback&scope=friends&response_type=token&v=5.62&state=123456";
	private static final String ARG_REDIRECT_URL = "token";
	private static final String ARG_TOKEN = "token";
	private static final String ARG_EXPIRES_IN = "expiresIn";
	private static final String ARG_USER_ID = "userId";
	private String redirectUrl;
	private String token;
	private String expiresIn;
	private String userId;
	private SharedPreferences sPref;
	private WebView mWebView;
	private CustomMessageEvent event;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			redirectUrl = savedInstanceState.getString(ARG_REDIRECT_URL);
			token = savedInstanceState.getString(ARG_TOKEN);
			expiresIn = savedInstanceState.getString(ARG_EXPIRES_IN);
			userId = savedInstanceState.getString(ARG_USER_ID);

		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(ARG_REDIRECT_URL, redirectUrl);
		outState.putString(ARG_TOKEN, token);
		outState.putString(ARG_EXPIRES_IN, expiresIn);
		outState.putString(ARG_USER_ID, userId);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.web_view, container, false);
		mWebView = (WebView) view.findViewById(R.id.web_view_id);
		mWebView.loadUrl(defaultUrl);
		mWebView.setWebViewClient(new WebViewClient() {

			@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
				redirectUrl = request.toString();
				extractParameters(redirectUrl);
				return false;
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				redirectUrl = url;
				extractParameters(redirectUrl);
				return false;
			}

			private void extractParameters(String url) {
				//Extact parameters from redirect uri
				Uri uri = Uri.parse(url.replace('#', '?'));
				token = uri.getQueryParameter("access_token");
				if (token != null && !token.isEmpty()) {
					expiresIn = uri.getQueryParameter("expires_in");
					userId = uri.getQueryParameter("user_id");
					//Post the fragment event
					event = new CustomMessageEvent();
					event.setToken(token != null);
					saveParameters(token, expiresIn, userId);
					EventBus.getDefault().post(event);
				}
			}

			private void saveParameters(String token, String expiresIn, String userId) {
				//Save uri parameters in sharedPreferences
				if (token != null && expiresIn != null && userId != null) {
					sPref = getActivity().getPreferences(Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sPref.edit();
					editor.putString("TokenKey", token);
					Log.d("log", "my  token = " + token);
					editor.putString("ExpiresInKey", expiresIn);
					editor.putString("UserIdKey", userId);
					editor.apply();
				} else {
					Log.d("log", "token is not writen to editor");
				}
			}
		});
		return view;
	}
}