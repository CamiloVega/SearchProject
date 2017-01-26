package com.example.tilt.searchproject.api;

import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by Tilt on 1/17/17.
 */

@EBean (scope = EBean.Scope.Singleton)
public class RestManager {

    @RestService
    RestClient restClient;

    private boolean isInitialized;

    public synchronized RestClient getClient(){
        return restClient;
    }
}
