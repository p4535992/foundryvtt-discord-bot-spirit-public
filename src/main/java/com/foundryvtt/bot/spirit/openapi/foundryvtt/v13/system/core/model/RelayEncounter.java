package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

public class RelayEncounter extends AbstractFoundryModel {

    private String id;
    private Integer round;
    private Integer turn;
    private Boolean current;
    private List<RelayEncounterCombatant> combatants = new ArrayList<RelayEncounterCombatant>();

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRound() {
        return this.round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getTurn() {
        return this.turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public Boolean getCurrent() {
        return this.current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }

    public List<RelayEncounterCombatant> getCombatants() {
        return this.combatants;
    }

    public void setCombatants(List<RelayEncounterCombatant> combatants) {
        this.combatants = combatants;
    }
}
