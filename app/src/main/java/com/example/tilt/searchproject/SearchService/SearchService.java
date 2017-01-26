package com.example.tilt.searchproject.SearchService;

import android.content.Context;

import com.example.tilt.searchproject.api.RestClient;
import com.example.tilt.searchproject.api.RestManager;
import com.example.tilt.searchproject.api.RestManager_;
import com.example.tilt.searchproject.model.SearchResults;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by Tilt on 1/17/17.
 */
@EBean(scope = EBean.Scope.Singleton)
public class SearchService {

    @RootContext
    Context context;

    private RestManager restManager;

    private RestManager getRestManager(){
        if (restManager == null){
            restManager = RestManager_.getInstance_(context);
        }
        return restManager;
    }

    public SearchResults searchListingsFor(String location, String checkIn, String checkOut){
        return getRestManager().getClient().getSearchResults(location, checkIn, checkOut);
    }
}
