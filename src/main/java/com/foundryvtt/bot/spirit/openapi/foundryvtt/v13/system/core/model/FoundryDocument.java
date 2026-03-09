package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Base Foundry primary document payload aligned with common document fields in v13.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class FoundryDocument {

    @JsonProperty("_id")
    private String id;
    private String name;
    private String type;
    private String img;
    private String folder;
    private Integer sort;
    private JsonNode system;
    private FoundryDocumentOwnership ownership;
    private FoundryDocumentFlags flags;
    @JsonProperty("_stats")
    private FoundryDocumentStats stats;
    private final Map<String, JsonNode> additionalProperties = new LinkedHashMap<String, JsonNode>();

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFolder() {
        return this.folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public JsonNode getSystem() {
        return this.system;
    }

    public void setSystem(JsonNode system) {
        this.system = system;
    }

    public FoundryDocumentOwnership getOwnership() {
        return this.ownership;
    }

    public void setOwnership(FoundryDocumentOwnership ownership) {
        this.ownership = ownership;
    }

    public FoundryDocumentFlags getFlags() {
        return this.flags;
    }

    public void setFlags(FoundryDocumentFlags flags) {
        this.flags = flags;
    }

    public FoundryDocumentStats getStats() {
        return this.stats;
    }

    public void setStats(FoundryDocumentStats stats) {
        this.stats = stats;
    }

    @JsonAnyGetter
    public Map<String, JsonNode> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void putAdditionalProperty(String name, JsonNode value) {
        this.additionalProperties.put(name, value);
    }
}
