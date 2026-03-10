package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class RelayStructureNode extends AbstractFoundryModel {

    private String id;
    private String name;
    private String type;
    private String path;
    private Integer sorting;
    private List<RelayStructureNode> children = new ArrayList<RelayStructureNode>();
    private List<JsonNode> contents = new ArrayList<JsonNode>();

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

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSorting() {
        return this.sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public List<RelayStructureNode> getChildren() {
        return this.children;
    }

    public void setChildren(List<RelayStructureNode> children) {
        this.children = children;
    }

    public List<JsonNode> getContents() {
        return this.contents;
    }

    public void setContents(List<JsonNode> contents) {
        this.contents = contents;
    }
}
