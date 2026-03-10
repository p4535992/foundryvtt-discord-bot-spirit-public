package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class RelayEntityResult extends AbstractFoundryModel {

    private String requestId;
    private String clientId;
    private String uuid;
    private String documentType;
    private List<FoundryDocument> documents = new ArrayList<FoundryDocument>();
    private List<JsonNode> rawData = new ArrayList<JsonNode>();

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public List<FoundryDocument> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<FoundryDocument> documents) {
        this.documents = documents;
    }

    public List<JsonNode> getRawData() {
        return this.rawData;
    }

    public void setRawData(List<JsonNode> rawData) {
        this.rawData = rawData;
    }
}
