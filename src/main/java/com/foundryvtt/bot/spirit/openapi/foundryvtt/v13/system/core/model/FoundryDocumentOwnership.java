package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Mapping of Foundry user ids to ownership levels.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoundryDocumentOwnership extends LinkedHashMap<String, Integer> {

    private static final long serialVersionUID = 1L;
}
