package com.example.tilt.searchproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;

/**
 * Created by Tilt on 1/17/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResults {
    @JsonProperty("metadata")
    private MetaData metadata;

    @JsonProperty("search_results")
    private ArrayList<ListingResult> listing_results;

    public ArrayList<ListingResult> getListingResults() {
        return listing_results;
    }

    public SearchResults (){
        listing_results = new ArrayList<>();
    }

    public void setMetadata(MetaData metadata) {
        this.metadata = metadata;
    }

    public MetaData getMetaData() {
        return metadata;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonRootName("metadata")
    public class MetaData {

        @JsonProperty("listings_count")
        private int listingsCount;

        public MetaData (){
        }

        public int getListingsCount() {
            return listingsCount;
        }

        public void setListingsCount(int listingsCount) {
            this.listingsCount = listingsCount;
        }
    }
}
