package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Runtime world context used while routing a system command.
 */
public class WorldContext {

    /**
     * Target relay client id.
     */
    private final String clientId;

    /**
     * Optional API key override scoped to this command execution.
     */
    private final String apiKeyOverride;

    /**
     * Resolved game-system id for the target world.
     */
    private final SystemId systemId;

    /**
     * Additional metadata captured during routing.
     */
    private final Map<String, Object> metadata;

    /**
     * Creates a context without additional metadata.
     *
     * @param clientId       target relay client id
     * @param apiKeyOverride optional API key override
     * @param systemId       resolved system id
     */
    public WorldContext(String clientId, String apiKeyOverride, SystemId systemId) {
        this(clientId, apiKeyOverride, systemId, null);
    }

    /**
     * Creates a context with additional metadata.
     *
     * @param clientId       target relay client id
     * @param apiKeyOverride optional API key override
     * @param systemId       resolved system id
     * @param metadata       additional metadata
     */
    public WorldContext(
            String clientId,
            String apiKeyOverride,
            SystemId systemId,
            Map<String, Object> metadata) {
        this.clientId = clientId;
        this.apiKeyOverride = apiKeyOverride;
        this.systemId = Objects.requireNonNull(systemId, "systemId must not be null");

        if (metadata == null) {
            this.metadata = Collections.emptyMap();
        } else {
            this.metadata = Collections.unmodifiableMap(new HashMap<String, Object>(metadata));
        }
    }

    /**
     * Returns the target relay client id.
     *
     * @return relay client id
     */
    public String getClientId() {
        return this.clientId;
    }

    /**
     * Returns an optional API key override.
     *
     * @return API key override, possibly {@code null}
     */
    public String getApiKeyOverride() {
        return this.apiKeyOverride;
    }

    /**
     * Returns the resolved system id.
     *
     * @return system id
     */
    public SystemId getSystemId() {
        return this.systemId;
    }

    /**
     * Returns immutable routing metadata.
     *
     * @return immutable metadata map
     */
    public Map<String, Object> getMetadata() {
        return this.metadata;
    }
}
