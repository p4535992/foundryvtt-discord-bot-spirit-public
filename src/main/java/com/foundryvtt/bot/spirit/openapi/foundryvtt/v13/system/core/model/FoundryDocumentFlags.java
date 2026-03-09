package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Mapping of Foundry module/system ids to document flags payloads.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoundryDocumentFlags extends LinkedHashMap<String, JsonNode> {

    private static final long serialVersionUID = 1L;
}
