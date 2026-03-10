package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

public class RelaySearchResult extends AbstractFoundryModel {

    private String requestId;
    private String clientId;
    private String query;
    private String filter;
    private Integer totalResults;
    private List<RelaySearchResultItem> results = new ArrayList<RelaySearchResultItem>();

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getFilter() {
        return this.filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Integer getTotalResults() {
        return this.totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<RelaySearchResultItem> getResults() {
        return this.results;
    }

    public void setResults(List<RelaySearchResultItem> results) {
        this.results = results;
    }
}
