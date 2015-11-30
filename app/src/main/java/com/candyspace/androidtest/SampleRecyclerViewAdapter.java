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

	private static final int GRID_VIEW = 0;
	private static final int HERO_VIEW = 1;

	@Override
	public int getItemViewType(int position) {
		int viewType;
		if (getItemCount() < 3) {
			viewType = GRID_VIEW;
		} else {
			viewType = HERO_VIEW;
		}

		return viewType;
	}

	@Override
	public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		switch (viewType) {
			case GRID_VIEW:
				View grid = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
				return new SampleViewHolder(grid);
			case HERO_VIEW:
				View hero = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_item, parent, false);
				return new SampleViewHolder(hero);
			default:
				View grid0 = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_item, parent, false);
				return new SampleViewHolder(grid0);
		}

	}

	@Override
	public void onBindViewHolder(SampleViewHolder holder, int position) {

		switch (holder.getItemViewType()) {

			case GRID_VIEW:

				Article article = articles.get(position);
				holder.title.setText(article.getTitle());
				holder.body.setText(article.getBody());

				ArticleWrapper articleWrapper = new ArticleWrapper(article);
				if (articleWrapper.getThumbImageUrl() != null) {
					Picasso.with(holder.itemView.getContext()).load(articleWrapper.getThumbImageUrl()).into(holder.image);
				} else {
					holder.image.setImageBitmap(null);
				}
				break;

			case HERO_VIEW:

				Article heroArticle = articles.get(position);
				holder.heroTitle.setText(heroArticle.getTitle());
				holder.heroBody.setText(heroArticle.getBody());

				ArticleWrapper heroArticleWrapper = new ArticleWrapper(heroArticle);
				if (heroArticleWrapper.getHeroImageUrl() != null) {
					Picasso.with(holder.itemView.getContext()).load(heroArticleWrapper.getHeroImageUrl()).into(holder.heroImage);
				} else {
					holder.heroImage.setImageBitmap(null);
				}

				break;
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

		ImageView heroImage;
		TextView heroTitle;
		TextView heroBody;

		public SampleViewHolder(View itemView) {
			super(itemView);
			image = (ImageView) itemView.findViewById(R.id.grid_item_image);
			title = (TextView) itemView.findViewById(R.id.grid_item_title);
			body = (TextView) itemView.findViewById(R.id.grid_item_body);

			heroImage = (ImageView) itemView.findViewById(R.id.hero_item_image);
			heroTitle = (TextView) itemView.findViewById(R.id.hero_item_title);
			heroBody = (TextView) itemView.findViewById(R.id.hero_item_body);
		}

	}

}
