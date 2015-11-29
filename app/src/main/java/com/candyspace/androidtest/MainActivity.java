package com.candyspace.androidtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.candyspace.androidtest.api.Article;
import com.candyspace.androidtest.api.MostPopularApi;
import com.candyspace.androidtest.api.RetrofitMostPopularApi;
import com.candyspace.androidtest.model.MySpanSizeLookup;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	public static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

		GridLayoutManager manager = new GridLayoutManager(this, 2);

		manager.setSpanSizeLookup(new MySpanSizeLookup(5, 1, 2));

		recyclerView.setLayoutManager(manager);

		final SampleRecyclerViewAdapter adapter = new SampleRecyclerViewAdapter();
		recyclerView.setAdapter(adapter);

		MostPopularApi api = new RetrofitMostPopularApi();
		api.fetchArticles(new MostPopularApi.Callback() {

			@Override
			public void onSuccess(List<Article> articles) {
				Log.d(TAG, "Got articles");
				adapter.setArticles(articles);
			}

			@Override
			public void onFailure(String error) {
				Log.d(TAG, "Failed to get articles");
			}

		});
	}
}
