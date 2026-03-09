package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Foundry v13 Item core payload.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoundryItemDocument extends FoundryDocument {

    private List<FoundryActiveEffectDocument> effects = new ArrayList<FoundryActiveEffectDocument>();

    public List<FoundryActiveEffectDocument> getEffects() {
        return this.effects;
    }

    public void setEffects(List<FoundryActiveEffectDocument> effects) {
        this.effects = effects;
    }
}
