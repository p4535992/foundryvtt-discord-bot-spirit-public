package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Shared base type for hand-written Foundry payload fragments that expose arbitrary extra fields.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractFoundryModel {

    private final Map<String, JsonNode> additionalProperties = new LinkedHashMap<String, JsonNode>();

    @JsonAnyGetter
    public Map<String, JsonNode> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void putAdditionalProperty(String name, JsonNode value) {
        this.additionalProperties.put(name, value);
    }
}
