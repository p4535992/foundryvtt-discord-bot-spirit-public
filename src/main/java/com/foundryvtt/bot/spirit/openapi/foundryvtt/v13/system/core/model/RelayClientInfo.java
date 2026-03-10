package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

public class RelayClientInfo extends AbstractFoundryModel {

    private String id;
    private String instanceId;
    private Long lastSeen;
    private Long connectedSince;
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

    public String getInstanceId() {
        return this.instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Long getLastSeen() {
        return this.lastSeen;
    }

    public void setLastSeen(Long lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Long getConnectedSince() {
        return this.connectedSince;
    }

    public void setConnectedSince(Long connectedSince) {
        this.connectedSince = connectedSince;
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
