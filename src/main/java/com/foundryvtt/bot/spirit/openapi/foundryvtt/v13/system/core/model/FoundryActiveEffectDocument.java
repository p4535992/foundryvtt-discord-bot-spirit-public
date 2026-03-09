package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Foundry v13 ActiveEffect core payload.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoundryActiveEffectDocument extends FoundryDocument {

    private List<FoundryActiveEffectChange> changes = new ArrayList<FoundryActiveEffectChange>();
    private Boolean disabled;
    private FoundryActiveEffectDuration duration;
    private String description;
    private String origin;
    private String tint;
    private Boolean transfer;
    private Set<String> statuses = new LinkedHashSet<String>();

    public List<FoundryActiveEffectChange> getChanges() {
        return this.changes;
    }

    public void setChanges(List<FoundryActiveEffectChange> changes) {
        this.changes = changes;
    }

    public Boolean getDisabled() {
        return this.disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public FoundryActiveEffectDuration getDuration() {
        return this.duration;
    }

    public void setDuration(FoundryActiveEffectDuration duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTint() {
        return this.tint;
    }

    public void setTint(String tint) {
        this.tint = tint;
    }

    public Boolean getTransfer() {
        return this.transfer;
    }

    public void setTransfer(Boolean transfer) {
        this.transfer = transfer;
    }

    public Set<String> getStatuses() {
        return this.statuses;
    }

    public void setStatuses(Set<String> statuses) {
        this.statuses = statuses;
    }

    /**
     * ActiveEffect change entry.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryActiveEffectChange {

        private String key;
        private String value;
        private Integer mode;
        private Integer priority;

        public String getKey() {
            return this.key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getMode() {
            return this.mode;
        }

        public void setMode(Integer mode) {
            this.mode = mode;
        }

        public Integer getPriority() {
            return this.priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }
    }

    /**
     * ActiveEffect duration descriptor.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryActiveEffectDuration {

        private Double startTime;
        private Integer seconds;
        private String combat;
        private Integer rounds;
        private Integer turns;
        private Integer startRound;
        private Integer startTurn;

        public Double getStartTime() {
            return this.startTime;
        }

        public void setStartTime(Double startTime) {
            this.startTime = startTime;
        }

        public Integer getSeconds() {
            return this.seconds;
        }

        public void setSeconds(Integer seconds) {
            this.seconds = seconds;
        }

        public String getCombat() {
            return this.combat;
        }

        public void setCombat(String combat) {
            this.combat = combat;
        }

        public Integer getRounds() {
            return this.rounds;
        }

        public void setRounds(Integer rounds) {
            this.rounds = rounds;
        }

        public Integer getTurns() {
            return this.turns;
        }

        public void setTurns(Integer turns) {
            this.turns = turns;
        }

        public Integer getStartRound() {
            return this.startRound;
        }

        public void setStartRound(Integer startRound) {
            this.startRound = startRound;
        }

        public Integer getStartTurn() {
            return this.startTurn;
        }

        public void setStartTurn(Integer startTurn) {
            this.startTurn = startTurn;
        }
    }
}
