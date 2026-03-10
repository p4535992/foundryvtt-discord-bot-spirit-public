package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

public class RelayRollHistoryEntry extends AbstractFoundryModel {

    private String id;
    private String messageId;
    private RelayRollUser user;
    private RelayRollSpeaker speaker;
    private String flavor;
    private Integer rollTotal;
    private String formula;
    private Boolean isCritical;
    private Boolean isFumble;
    private List<RelayRollDie> dice = new ArrayList<RelayRollDie>();
    private Long timestamp;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public RelayRollUser getUser() {
        return this.user;
    }

    public void setUser(RelayRollUser user) {
        this.user = user;
    }

    public RelayRollSpeaker getSpeaker() {
        return this.speaker;
    }

    public void setSpeaker(RelayRollSpeaker speaker) {
        this.speaker = speaker;
    }

    public String getFlavor() {
        return this.flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public Integer getRollTotal() {
        return this.rollTotal;
    }

    public void setRollTotal(Integer rollTotal) {
        this.rollTotal = rollTotal;
    }

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
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
