package com.inverita.testapp.util;

import com.inverita.testapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

	public static final String BASE_URL = "https://api.vk.com/";
	private static Retrofit retrofit = null;

	public static Retrofit getClient() {
		if (retrofit == null) {
			final OkHttpClient.Builder builder = new OkHttpClient.Builder();
			builder.connectTimeout(15, TimeUnit.SECONDS);
			builder.readTimeout(15, TimeUnit.SECONDS);

			if (BuildConfig.DEBUG) {
				final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
				logging.setLevel(HttpLoggingInterceptor.Level.BODY);
				builder.addInterceptor(logging);
			}

			final OkHttpClient httpClient = builder.build();
			retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.client(httpClient)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return retrofit;
	}
}
