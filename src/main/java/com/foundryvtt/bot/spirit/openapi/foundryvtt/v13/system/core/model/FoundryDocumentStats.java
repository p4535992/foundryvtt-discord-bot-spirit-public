package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Foundry document metadata stored in the {@code _stats} field.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoundryDocumentStats {

    private String coreVersion;
    private String systemId;
    private String systemVersion;
    private Long createdTime;
    private Long modifiedTime;
    private String lastModifiedBy;
    private String compendiumSource;
    private String duplicateSource;
    private FoundryDocumentExportSource exportSource;

    public String getCoreVersion() {
        return this.coreVersion;
    }

    public void setCoreVersion(String coreVersion) {
        this.coreVersion = coreVersion;
    }

    public String getSystemId() {
        return this.systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemVersion() {
        return this.systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public Long getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getModifiedTime() {
        return this.modifiedTime;
    }

    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getCompendiumSource() {
        return this.compendiumSource;
    }

    public void setCompendiumSource(String compendiumSource) {
        this.compendiumSource = compendiumSource;
    }

    public String getDuplicateSource() {
        return this.duplicateSource;
    }

    public void setDuplicateSource(String duplicateSource) {
        this.duplicateSource = duplicateSource;
    }

    public FoundryDocumentExportSource getExportSource() {
        return this.exportSource;
    }

    public void setExportSource(FoundryDocumentExportSource exportSource) {
        this.exportSource = exportSource;
    }

    /**
     * Nested export source descriptor from Foundry document stats.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoundryDocumentExportSource {

        private String worldId;
        private String uuid;
        private String coreVersion;
        private String systemId;
        private String systemVersion;

        public String getWorldId() {
            return this.worldId;
        }

        public void setWorldId(String worldId) {
            this.worldId = worldId;
        }

        public String getUuid() {
            return this.uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getCoreVersion() {
            return this.coreVersion;
        }

        public void setCoreVersion(String coreVersion) {
            this.coreVersion = coreVersion;
        }

        public String getSystemId() {
            return this.systemId;
        }

        public void setSystemId(String systemId) {
            this.systemId = systemId;
        }

        public String getSystemVersion() {
            return this.systemVersion;
        }

        public void setSystemVersion(String systemVersion) {
            this.systemVersion = systemVersion;
        }
    }
}
