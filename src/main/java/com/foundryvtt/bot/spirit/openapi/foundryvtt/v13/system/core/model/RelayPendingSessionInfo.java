package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

public class RelayPendingSessionInfo extends AbstractFoundryModel {

    private String id;
    private String status;
    private String expectedClientId;
    private String foundryUrl;
    private String worldName;
    private String username;
    private Integer waitingSeconds;
    private Long startTime;
    private String instanceId;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpectedClientId() {
        return this.expectedClientId;
    }

    public void setExpectedClientId(String expectedClientId) {
        this.expectedClientId = expectedClientId;
    }

    public String getFoundryUrl() {
        return this.foundryUrl;
    }

    public void setFoundryUrl(String foundryUrl) {
        this.foundryUrl = foundryUrl;
    }

    public String getWorldName() {
        return this.worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getWaitingSeconds() {
        return this.waitingSeconds;
    }

    public void setWaitingSeconds(Integer waitingSeconds) {
        this.waitingSeconds = waitingSeconds;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getInstanceId() {
        return this.instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
}
