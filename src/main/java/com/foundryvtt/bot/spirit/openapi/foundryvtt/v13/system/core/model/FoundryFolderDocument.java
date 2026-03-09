package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Foundry v13 Folder core payload.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoundryFolderDocument extends FoundryDocument {

    private String description;
    private String sorting;
    private String color;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSorting() {
        return this.sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
