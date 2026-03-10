package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

public class RelayRollDie extends AbstractFoundryModel {

    private Integer faces;
    private List<RelayRollDieResult> results = new ArrayList<RelayRollDieResult>();

    public Integer getFaces() {
        return this.faces;
    }

    public void setFaces(Integer faces) {
        this.faces = faces;
    }

    public List<RelayRollDieResult> getResults() {
        return this.results;
    }

    public void setResults(List<RelayRollDieResult> results) {
        this.results = results;
    }
}
