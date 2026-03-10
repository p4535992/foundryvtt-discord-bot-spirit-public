package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.service;

import java.lang.reflect.Type;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.service.AbstractRelayClientService;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.service.RestRelayService;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.mapper.FoundryDnd5eMapper;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eModifyExperienceResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eModifyItemChargesResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eUseAbilityResult;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.dnd5e.api.DnD5eApi;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.dnd5e.invoker.ApiClient;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.dnd5e.invoker.ApiException;
import com.google.gson.reflect.TypeToken;

/**
 * Default DnD5e relay service implementation.
 */
@ApplicationScoped
public class Dnd5eServiceImpl extends AbstractRelayClientService implements Dnd5eService {

    /**
     * Logger for DnD5e operations.
     */
    private static final Logger LOG = Logger.getLogger(Dnd5eServiceImpl.class);

    /**
     * Generated DnD5e API client facade.
     */
    private final DnD5eApi dnD5eApi;
    private final FoundryDnd5eMapper foundryDnd5eMapper;

    /**
     * Builds the DnD5e service.
     *
     * @param restRelayService   relay service used for base URL discovery
     * @param relayDefaultApiKey default API key from config
     */
    @Inject
    public Dnd5eServiceImpl(
            RestRelayService restRelayService,
            FoundryDnd5eMapper foundryDnd5eMapper,
            @ConfigProperty(name = "spirit.relay.api-key", defaultValue = "") String relayDefaultApiKey) {
        super(restRelayService.getRelayBaseUrl(), relayDefaultApiKey);
        this.foundryDnd5eMapper = foundryDnd5eMapper;

        ApiClient apiClient = new ApiClient()
                .setBasePath(this.getRelayBaseUrl())
                .setConnectTimeout(10_000)
                .setReadTimeout(30_000)
                .setWriteTimeout(30_000);
        this.dnD5eApi = new DnD5eApi(apiClient);
    }

    @Override
    public Dnd5eActorDocument getActorDetails(
            String apiKeyOverride,
            String clientId,
            String actorUuid,
            String details) {
        try {
            Object payload = this.executeForObject(this.dnD5eApi.dnd5eGetActorDetailsGetCall(
                    this.resolveApiKey(apiKeyOverride),
                    actorUuid,
                    details,
                    clientId,
                    actorUuid,
                    details,
                    null));
            return this.foundryDnd5eMapper.toActorDocument(payload);
        } catch (ApiException exception) {
            throw this.dnd5eCallFailed("get actor details", exception);
        }
    }

    @Override
    public Dnd5eModifyExperienceResult modifyExperience(
            String apiKeyOverride,
            String clientId,
            String actorUuid,
            Integer amount,
            Map<String, Object> requestBody) {
        try {
            Object payload = this.executeForObject(this.dnD5eApi.dnd5eModifyExperiencePostCall(
                    this.resolveApiKey(apiKeyOverride),
                    clientId,
                    actorUuid,
                    amount,
                    requestBody,
                    null));
            return this.foundryDnd5eMapper.toModifyExperienceResult(payload);
        } catch (ApiException exception) {
            throw this.dnd5eCallFailed("modify experience", exception);
        }
    }

    @Override
    public Dnd5eUseAbilityResult useAbility(
            String apiKeyOverride,
            String clientId,
            String actorUuid,
            String abilityName,
            Map<String, Object> requestBody) {
        try {
            Object payload = this.executeForObject(this.dnD5eApi.dnd5eUseAbilityPostCall(
                    this.resolveApiKey(apiKeyOverride),
                    actorUuid,
                    null,
                    clientId,
                    actorUuid,
                    abilityName,
                    requestBody,
                    null));
            return this.foundryDnd5eMapper.toUseAbilityResult(payload);
        } catch (ApiException exception) {
            throw this.dnd5eCallFailed("use ability", exception);
        }
    }

    @Override
    public Dnd5eModifyItemChargesResult modifyItemCharges(
            String apiKeyOverride,
            String clientId,
            String actorUuid,
            String itemName,
            String amount,
            Map<String, Object> requestBody) {
        try {
            Object payload = this.executeForObject(this.dnD5eApi.dnd5eModifyItemChargesPostCall(
                    this.resolveApiKey(apiKeyOverride),
                    clientId,
                    actorUuid,
                    itemName,
                    amount,
                    requestBody,
                    null));
            return this.foundryDnd5eMapper.toModifyItemChargesResult(payload);
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
     * Creates a runtime exception for DnD5e relay client failures.
     *
     * @param actionName operation name
     * @param exception  root generated client exception
     * @return runtime exception to throw
     */
    private RuntimeException dnd5eCallFailed(String actionName, ApiException exception) {
        return this.relayCallFailed(LOG, "DnD5e relay", actionName, exception.getCode(), exception);
    }
}
