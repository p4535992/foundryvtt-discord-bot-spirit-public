package com.foundryvtt.bot.spirit.foundryvtt.v13.system.dnd5e.service;

import java.lang.reflect.Type;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.foundryvtt.bot.spirit.openapi.relay.system.dnd5e.api.DnD5eApi;
import com.foundryvtt.bot.spirit.openapi.relay.system.dnd5e.invoker.ApiClient;
import com.foundryvtt.bot.spirit.openapi.relay.system.dnd5e.invoker.ApiException;
import com.foundryvtt.bot.spirit.service.RestRelayService;
import com.google.gson.reflect.TypeToken;

/**
 * Default DnD5e relay service implementation.
 */
@ApplicationScoped
public class Dnd5eServiceImpl implements Dnd5eService {

    /**
     * Logger for DnD5e operations.
     */
    private static final Logger LOG = Logger.getLogger(Dnd5eServiceImpl.class);

    /**
     * Relay base URL used by generated client.
     */
    private final String relayBaseUrl;

    /**
     * Default API key from configuration.
     */
    private final String relayDefaultApiKey;

    /**
     * Generated DnD5e API client facade.
     */
    private final DnD5eApi dnD5eApi;

    /**
     * Builds the DnD5e service.
     *
     * @param restRelayService   relay service used for base URL discovery
     * @param relayDefaultApiKey default API key from config
     */
    @Inject
    public Dnd5eServiceImpl(
            RestRelayService restRelayService,
            @ConfigProperty(name = "spirit.relay.api-key", defaultValue = "") String relayDefaultApiKey) {
        this.relayBaseUrl = restRelayService.getRelayBaseUrl();
        this.relayDefaultApiKey = relayDefaultApiKey;

        ApiClient apiClient = new ApiClient()
                .setBasePath(this.relayBaseUrl)
                .setConnectTimeout(10_000)
                .setReadTimeout(30_000)
                .setWriteTimeout(30_000);
        this.dnD5eApi = new DnD5eApi(apiClient);
    }

    @Override
    public Object getActorDetails(
            String apiKeyOverride,
            String clientId,
            String actorUuid,
            String details) {
        try {
            return this.executeForObject(this.dnD5eApi.dnd5eGetActorDetailsGetCall(
                    this.resolveApiKey(apiKeyOverride),
                    actorUuid,
                    details,
                    clientId,
                    actorUuid,
                    details,
                    null));
        } catch (ApiException exception) {
            throw this.dnd5eCallFailed("get actor details", exception);
        }
    }

    @Override
    public Object modifyExperience(
            String apiKeyOverride,
            String clientId,
            String actorUuid,
            Integer amount,
            Map<String, Object> requestBody) {
        try {
            return this.executeForObject(this.dnD5eApi.dnd5eModifyExperiencePostCall(
                    this.resolveApiKey(apiKeyOverride),
                    clientId,
                    actorUuid,
                    amount,
                    requestBody,
                    null));
        } catch (ApiException exception) {
            throw this.dnd5eCallFailed("modify experience", exception);
        }
    }

    @Override
    public Object useAbility(
            String apiKeyOverride,
            String clientId,
            String actorUuid,
            String abilityName,
            Map<String, Object> requestBody) {
        try {
            return this.executeForObject(this.dnD5eApi.dnd5eUseAbilityPostCall(
                    this.resolveApiKey(apiKeyOverride),
                    actorUuid,
                    null,
                    clientId,
                    actorUuid,
                    abilityName,
                    requestBody,
                    null));
        } catch (ApiException exception) {
            throw this.dnd5eCallFailed("use ability", exception);
        }
    }

    @Override
    public Object modifyItemCharges(
            String apiKeyOverride,
            String clientId,
            String actorUuid,
            String itemName,
            String amount,
            Map<String, Object> requestBody) {
        try {
            return this.executeForObject(this.dnD5eApi.dnd5eModifyItemChargesPostCall(
                    this.resolveApiKey(apiKeyOverride),
                    clientId,
                    actorUuid,
                    itemName,
                    amount,
                    requestBody,
                    null));
        } catch (ApiException exception) {
            throw this.dnd5eCallFailed("modify item charges", exception);
        }
    }

    private Object executeForObject(okhttp3.Call call) throws ApiException {
        Type responseType = new TypeToken<Object>() {
        }.getType();
        return this.dnD5eApi.getApiClient().execute(call, responseType).getData();
    }

    /**
     * Resolves API key by preferring method override over configured key.
     *
     * @param apiKeyOverride runtime API key override
     * @return effective API key, or {@code null} when both are empty
     */
    private String resolveApiKey(String apiKeyOverride) {
        if (this.hasText(apiKeyOverride)) {
            return apiKeyOverride;
        }
        if (this.hasText(this.relayDefaultApiKey)) {
            return this.relayDefaultApiKey;
        }
        return null;
    }

    /**
     * Checks if a value contains at least one non-whitespace character.
     *
     * @param value input value
     * @return {@code true} when value has text
     */
    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

    /**
     * Creates a runtime exception for DnD5e relay client failures.
     *
     * @param actionName operation name
     * @param exception  root generated client exception
     * @return runtime exception to throw
     */
    private RuntimeException dnd5eCallFailed(String actionName, ApiException exception) {
        int statusCode = exception.getCode();
        LOG.errorf(
                exception,
                "DnD5e relay call failed for action '%s' (status=%d, baseUrl=%s).",
                actionName,
                statusCode,
                this.relayBaseUrl);
        return new IllegalStateException(
                "DnD5e relay call failed while trying to "
                        + actionName
                        + " (status="
                        + statusCode
                        + ").",
                exception);
    }
}
