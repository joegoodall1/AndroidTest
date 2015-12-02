package com.candyspace.androidtest;

import android.support.test.runner.AndroidJUnit4;

import com.candyspace.androidtest.api.Article;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class SampleRecyclerViewAdapterTest {

    private final SampleRecyclerViewAdapter adapter = new SampleRecyclerViewAdapter();

    @Test
    public void getItemCount() throws Exception {

        final ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article());
        articles.add(new Article());

        adapter.updateArticles(articles);

        assertThat(adapter.getItemCount(), equalTo(2));
    }

    @Test
    public void testGetViewType() throws Exception {
        assertThat(adapter.getItemViewType(0), equalTo(SampleRecyclerViewAdapter.HERO_VIEW));
        assertThat(adapter.getItemViewType(1), equalTo(SampleRecyclerViewAdapter.GRID_VIEW));
        assertThat(adapter.getItemViewType(4), equalTo(SampleRecyclerViewAdapter.GRID_VIEW));
        assertThat(adapter.getItemViewType(5), equalTo(SampleRecyclerViewAdapter.HERO_VIEW));
        assertThat(adapter.getItemViewType(10), equalTo(SampleRecyclerViewAdapter.HERO_VIEW));
        assertThat(adapter.getItemViewType(11), equalTo(SampleRecyclerViewAdapter.GRID_VIEW));
        assertThat(adapter.getItemViewType(25), equalTo(SampleRecyclerViewAdapter.HERO_VIEW));
    }
}