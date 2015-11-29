package com.candyspace.androidtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.candyspace.androidtest.api.Article;
import com.candyspace.androidtest.model.ArticleWrapper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a sample RecyclerView adapter.
 */
public class SampleRecyclerViewAdapter extends RecyclerView.Adapter<SampleRecyclerViewAdapter.SampleViewHolder> {

	private final List<Article> articles = new ArrayList<>();


	@Override
	public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_item, parent, false);
		View w = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);


		switch (viewType) {
			case 0:
				return new SampleViewHolder(v);
			case 1:
				return new SampleViewHolder(w);
		}
		return null;
	}

	@Override
	public void onBindViewHolder(SampleViewHolder holder, int position) {
		Article article = articles.get(position);
		holder.title.setText(article.getTitle());
		holder.body.setText(article.getBody());

		ArticleWrapper articleWrapper = new ArticleWrapper(article);
		if (articleWrapper.getThumbImageUrl() != null) {
			Picasso.with(holder.itemView.getContext()).load(articleWrapper.getThumbImageUrl()).into(holder.image);
		} else {
			holder.image.setImageBitmap(null);
		}
	}

	@Override
	public int getItemCount() {
		return articles.size();
	}


	public void setArticles(List<Article> articles){
		this.articles.clear();
		if(articles != null) {
			this.articles.addAll(articles);
		}
		notifyDataSetChanged();
	}

	static class SampleViewHolder extends RecyclerView.ViewHolder {

		ImageView image;
		TextView title;
		TextView body;

		/*ImageView heroImage;
		TextView heroTitle;
		TextView heroBody;*/

		public SampleViewHolder(View itemView) {
			super(itemView);
			/*image = (ImageView) itemView.findViewById(R.id.grid_item_image);
			title = (TextView) itemView.findViewById(R.id.grid_item_title);
			body = (TextView) itemView.findViewById(R.id.grid_item_body);*/

			image = (ImageView) itemView.findViewById(R.id.hero_item_image);
			title = (TextView) itemView.findViewById(R.id.hero_item_title);
			body = (TextView) itemView.findViewById(R.id.hero_item_body);
		}

	}

}
