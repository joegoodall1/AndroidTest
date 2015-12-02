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

import java.util.List;

/**
 * This is a sample RecyclerView adapter.
 */
public class SampleRecyclerViewAdapter extends RecyclerView.Adapter<SampleRecyclerViewAdapter.SampleViewHolder> {

    private List<Article> mostPopular;
    private List<Article> leastPopular;

    public static final int GRID_VIEW = 0;
    public static final int HERO_VIEW = 1;

    @Override
    public int getItemViewType(int position) {

        int viewType = 0;


        if (position % 5 == 0) {
            viewType = HERO_VIEW;
        } else if (position == MainActivity.SPAN_SIZE_LOOKUP.spanCnt2) {
            viewType = GRID_VIEW;
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
                final int index = position - (position / 5);
                if (index < leastPopular.size()) {

                    Article article = leastPopular.get(index);
                    holder.title.setText(article.getTitle());
                    holder.body.setText(article.getBody());

                    ArticleWrapper articleWrapper = new ArticleWrapper(article);
                    if (articleWrapper.getThumbImageUrl() != null) {
                        Picasso.with(holder.itemView.getContext()).load(articleWrapper.getThumbImageUrl()).into(holder.image);
                    } else {
                        holder.image.setImageBitmap(null);
                    }
                    break;
                }
                return;

            case HERO_VIEW:

                Article heroArticle = mostPopular.get(position / 5);
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
        if (mostPopular == null) {
            return 0;
        } else {
            return mostPopular.size() + leastPopular.size();
        }
    }

    public void updateArticles(List<Article> articles) {
        int result = articles.size() % 5;
        int temp = articles.size() - result;
        int newPopular = temp / 5;

        List<Article> mostPopular = articles.subList(0, newPopular);
        List<Article> leastPopular = articles.subList(newPopular, articles.size());

        updateArticles(mostPopular, leastPopular);
    }

    public void updateArticles(List<Article> mostPopular, List<Article> leastPopular) {
        this.mostPopular = mostPopular;
        this.leastPopular = leastPopular;
        notifyDataSetChanged();
    }

    public static class SampleViewHolder extends RecyclerView.ViewHolder {

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
