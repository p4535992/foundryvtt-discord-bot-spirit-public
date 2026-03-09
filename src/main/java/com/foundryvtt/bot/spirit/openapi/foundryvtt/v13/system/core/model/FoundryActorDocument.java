package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Foundry v13 Actor core payload.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoundryActorDocument extends FoundryDocument {

    private FoundryPrototypeToken prototypeToken;
    private List<FoundryItemDocument> items = new ArrayList<FoundryItemDocument>();
    private List<FoundryActiveEffectDocument> effects = new ArrayList<FoundryActiveEffectDocument>();

    public FoundryPrototypeToken getPrototypeToken() {
        return this.prototypeToken;
    }

    public void setPrototypeToken(FoundryPrototypeToken prototypeToken) {
        this.prototypeToken = prototypeToken;
    }

    public List<FoundryItemDocument> getItems() {
        return this.items;
    }

    public void setItems(List<FoundryItemDocument> items) {
        this.items = items;
    }

    public List<FoundryActiveEffectDocument> getEffects() {
        return this.effects;
    }

    public void setEffects(List<FoundryActiveEffectDocument> effects) {
        this.effects = effects;
    }
}
