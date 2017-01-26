package com.example.tilt.searchproject.api;

import com.example.tilt.searchproject.model.SearchResults;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.RequiresAuthentication;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.androidannotations.rest.spring.api.RestClientRootUrl;
import org.androidannotations.rest.spring.api.RestClientSupport;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by Tilt on 1/17/17.
 */

@Rest(rootUrl = "https://api.airbnb.com/v2", converters = { MappingJackson2HttpMessageConverter.class })
public interface RestClient extends RestClientHeaders, RestClientRootUrl, RestClientSupport {
    // Missing the auth key. sorry it wont work without it. 
    @Get("/search_results?location={location}&checkin={checkin}&checkout={checkout}&key=&_format=for_search_results_with_minimal_pricing")
    SearchResults getSearchResults(@Path String location, @Path String checkin, @Path String checkout);

}
