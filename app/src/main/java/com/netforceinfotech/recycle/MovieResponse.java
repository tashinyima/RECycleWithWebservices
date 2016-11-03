package com.netforceinfotech.recycle;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xyz on 11/1/2016.
 */

public class MovieResponse {

// Here List<<jsondatamovie>> takes values from the JsonDataMovie... Generic typecast...


    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<JsonDataMovie> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;


    public MovieResponse(int page, List<JsonDataMovie> results, int totalResults, int totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<JsonDataMovie> getResults() {
        return results;
    }

    public void setResults(List<JsonDataMovie> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
