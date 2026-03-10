package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

public class RelayActiveSessionInfo extends AbstractFoundryModel {

    private String id;
    private String clientId;
    private Long lastActivity;
    private Integer idleMinutes;
    private String instanceId;
    private String worldId;
    private String worldTitle;
    private String foundryVersion;
    private String systemId;
    private String systemTitle;
    private String systemVersion;
    private String customName;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getLastActivity() {
        return this.lastActivity;
    }

    public void setLastActivity(Long lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Integer getIdleMinutes() {
        return this.idleMinutes;
    }

    public void setIdleMinutes(Integer idleMinutes) {
        this.idleMinutes = idleMinutes;
    }

    public String getInstanceId() {
        return this.instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getWorldId() {
        return this.worldId;
    }

    public void setWorldId(String worldId) {
        this.worldId = worldId;
    }

    public String getWorldTitle() {
        return this.worldTitle;
    }

    public void setWorldTitle(String worldTitle) {
        this.worldTitle = worldTitle;
    }

    public String getFoundryVersion() {
        return this.foundryVersion;
    }

    public void setFoundryVersion(String foundryVersion) {
        this.foundryVersion = foundryVersion;
    }

    public String getSystemId() {
        return this.systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemTitle() {
        return this.systemTitle;
    }

    public void setSystemTitle(String systemTitle) {
        this.systemTitle = systemTitle;
    }

    public String getSystemVersion() {
        return this.systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
}
