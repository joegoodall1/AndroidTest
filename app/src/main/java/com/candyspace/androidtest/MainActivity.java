package com.candyspace.androidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.candyspace.androidtest.api.Article;
import com.candyspace.androidtest.api.MostPopularApi;
import com.candyspace.androidtest.api.RetrofitMostPopularApi;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	public static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MostPopularApi api = new RetrofitMostPopularApi();
		api.fetchArticles(new MostPopularApi.Callback() {

			@Override
			public void onSuccess(List<Article> articles) {
				Log.d(TAG, "Got articles");
			}

			@Override
			public void onFailure(String error) {
				Log.d(TAG, "Failed to get articles");
			}

		});
	}
}
