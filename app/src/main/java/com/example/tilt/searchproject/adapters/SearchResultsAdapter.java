package com.example.tilt.searchproject.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tilt.searchproject.model.SearchResults;
import com.example.tilt.searchproject.views.SearchListCellView;
import com.example.tilt.searchproject.views.SearchListCellView_;

/**
 * Created by Tilt on 1/17/17.
 */

public class SearchResultsAdapter extends BaseAdapter {

    protected SearchResults searchResults;
    protected Context context;

    public SearchResultsAdapter(SearchResults searchResults, Context context) {
        this.searchResults = searchResults;
        this.context = context;
    }

    @Override
    public int getCount() {
        return searchResults.getListingResults().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setSearchResults(SearchResults searchResults) {
        this.searchResults = searchResults;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = SearchListCellView_.build(context);
        }
        ((SearchListCellView) view).bind(searchResults.getListingResults().get(i));
        return view;
    }
}
