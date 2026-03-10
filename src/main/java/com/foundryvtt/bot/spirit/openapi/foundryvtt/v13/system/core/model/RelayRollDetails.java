package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

public class RelayRollDetails extends AbstractFoundryModel {

    private String formula;
    private Integer total;
    private Boolean isCritical;
    private Boolean isFumble;
    private List<RelayRollDie> dice = new ArrayList<RelayRollDie>();
    private Long timestamp;

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Boolean getIsCritical() {
        return this.isCritical;
    }

    public void setIsCritical(Boolean isCritical) {
        this.isCritical = isCritical;
    }

    public Boolean getIsFumble() {
        return this.isFumble;
    }

    public void setIsFumble(Boolean isFumble) {
        this.isFumble = isFumble;
    }

    public List<RelayRollDie> getDice() {
        return this.dice;
    }

    public void setDice(List<RelayRollDie> dice) {
        this.dice = dice;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
