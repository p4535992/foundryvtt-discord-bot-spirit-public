package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelaySearchResultItem extends AbstractFoundryModel {

    private String documentType;
    private String id;
    private String name;
    private String packageId;
    private String packageName;
    private String subType;
    private String uuid;
    private String icon;
    private String journalLink;
    private String tagline;
    private String formattedMatch;
    private String resultType;

    public String getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

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

    @JsonProperty("package")
    public String getPackageId() {
        return this.packageId;
    }

    @JsonProperty("package")
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSubType() {
        return this.subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getJournalLink() {
        return this.journalLink;
    }

    public void setJournalLink(String journalLink) {
        this.journalLink = journalLink;
    }

    public String getTagline() {
        return this.tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFormattedMatch() {
        return this.formattedMatch;
    }

    public void setFormattedMatch(String formattedMatch) {
        this.formattedMatch = formattedMatch;
    }

    public String getResultType() {
        return this.resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
