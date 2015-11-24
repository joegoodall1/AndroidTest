package com.candyspace.androidtest;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.candyspace.androidtest.api.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a sample RecyclerView adapter.
 */
public class SampleRecyclerViewAdapter extends RecyclerView.Adapter<SampleRecyclerViewAdapter.SampleViewHolder> {

	private List<Article> articles = new ArrayList<>();

	@Override
	public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
		return new SampleViewHolder(v);
	}

	@Override
	public void onBindViewHolder(SampleViewHolder holder, int position) {
		Article article = articles.get(position);
		holder.title.setText(article.getTitle());
		holder.body.setText(article.getBody());
		String imageUrl = getImageUrl(article);
		if (imageUrl != null) {
			Picasso.with(holder.itemView.getContext()).load(imageUrl).into(holder.image);
		}
	}

	@Override
	public int getItemCount() {
		return articles.size();
	}

	public void setArticles(List<Article> articles){
		this.articles = articles;
		notifyDataSetChanged();
	}

	static class SampleViewHolder extends RecyclerView.ViewHolder {

		ImageView image;
		TextView title;
		TextView body;

		public SampleViewHolder(View itemView) {
			super(itemView);
			image = (ImageView) itemView.findViewById(R.id.grid_item_image);
			title = (TextView) itemView.findViewById(R.id.grid_item_title);
			body = (TextView) itemView.findViewById(R.id.grid_item_body);
		}

	}

	@Nullable
	private String getImageUrl(Article article){
		for(Article.Media media : article.getMediaList()) {
			if(media.getType().equalsIgnoreCase("image")) {
				return getThumbUrl(media.getMediaMetadataList());
			}
		}
		return null;
	}

	@Nullable
	private String getThumbUrl(List<Article.Media.MediaMetadata> mediaMetadataList){
		for(Article.Media.MediaMetadata item : mediaMetadataList) {
			if (item.getFormat().equalsIgnoreCase("Standard Thumbnail")) {
				return item.getUrl();
			}
		}
		return null;
	}

	@Nullable
	private String getHeroUrl(List<Article.Media.MediaMetadata> mediaMetadataList){
		for(Article.Media.MediaMetadata item : mediaMetadataList) {
			if (item.getFormat().equalsIgnoreCase("square640")) {
				return item.getUrl();
			}
		}
		return null;
	}

}
