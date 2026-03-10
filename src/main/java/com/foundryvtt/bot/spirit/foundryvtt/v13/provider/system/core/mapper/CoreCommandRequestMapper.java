package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.mapper;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.ActorSheetCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.BodyOnlyCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.ClientBodyCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.ClientCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.EntityByUuidCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.EntityCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.RecentRollsCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.RollCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.SearchCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.SessionHandshakeCommandRequest;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.CoreCommandRequests.SessionIdCommandRequest;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.model.RollRequest;

/**
 * Maps raw core command payloads to typed provider request models.
 */
@ApplicationScoped
public class CoreCommandRequestMapper extends AbstractCommandRequestMapper {

    @Inject
    public CoreCommandRequestMapper(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    public SessionHandshakeCommandRequest toSessionHandshakeRequest(Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new SessionHandshakeCommandRequest(
                this.readRequiredString(requestPayload, "foundryUrl"),
                this.readRequiredString(requestPayload, "username"),
                this.readRequiredString(requestPayload, "password"),
                this.readOptionalString(requestPayload, "worldName"),
                this.readOptionalMap(requestPayload, "requestBody"));
    }

    public BodyOnlyCommandRequest toBodyOnlyRequest(Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new BodyOnlyCommandRequest(requestPayload.get("requestBody"));
    }

    public SessionIdCommandRequest toSessionIdRequest(Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new SessionIdCommandRequest(this.readRequiredString(requestPayload, "sessionId"));
    }

    public ClientCommandRequest toClientCommandRequest(String clientId,
            Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new ClientCommandRequest(this.requireClientId(clientId, requestPayload));
    }

    public EntityCommandRequest toEntityRequest(String clientId, Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new EntityCommandRequest(
                this.requireClientId(clientId, requestPayload),
                this.readOptionalBoolean(requestPayload, "selected"),
                this.readOptionalBoolean(requestPayload, "actor"));
    }

    public EntityByUuidCommandRequest toEntityByUuidRequest(
            String clientId,
            Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new EntityByUuidCommandRequest(
                this.requireClientId(clientId, requestPayload),
                this.readRequiredString(requestPayload, "uuid"),
                this.readOptionalBoolean(requestPayload, "actor"));
    }

    public ActorSheetCommandRequest toActorSheetRequest(String clientId,
            Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new ActorSheetCommandRequest(
                this.requireClientId(clientId, requestPayload),
                this.readOptionalString(requestPayload, "uuid"),
                this.readOptionalBoolean(requestPayload, "selected"),
                this.readOptionalBoolean(requestPayload, "actor"),
                this.readOptionalBigDecimal(requestPayload, "scale"));
    }

    public RollCommandRequest toRollRequest(String clientId, Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new RollCommandRequest(
                this.requireClientId(clientId, requestPayload),
                this.toGeneratedRollRequest(requestPayload.get("rollRequest")));
    }

    public RecentRollsCommandRequest toRecentRollsRequest(String clientId,
            Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new RecentRollsCommandRequest(
                this.requireClientId(clientId, requestPayload),
                this.readOptionalInteger(requestPayload, "limit"));
    }

    public SearchCommandRequest toSearchRequest(String clientId, Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new SearchCommandRequest(
                this.requireClientId(clientId, requestPayload),
                this.readRequiredString(requestPayload, "query"),
                this.readOptionalString(requestPayload, "filter"));
    }

    public ClientBodyCommandRequest toClientBodyRequest(String clientId,
            Map<String, Object> payload) {
        Map<String, Object> requestPayload = this.safePayload(payload);
        return new ClientBodyCommandRequest(
                this.requireClientId(clientId, requestPayload),
                requestPayload.get("requestBody"));
    }

    private RollRequest toGeneratedRollRequest(Object payload) {
        if (payload == null) {
            throw new IllegalArgumentException("Missing required payload field: rollRequest");
        }
        if (payload instanceof RollRequest rollRequest) {
            return rollRequest;
        }
        if (payload instanceof Map<?, ?> rawMap) {
            Map<String, Object> requestMap = this.toStringObjectMap(rawMap);
            RollRequest rollRequest = new RollRequest();
            rollRequest.setFormula(this.readRequiredString(requestMap, "formula"));
            rollRequest.setItemUuid(this.readOptionalString(requestMap, "itemUuid"));
            rollRequest.setFlavor(this.readOptionalString(requestMap, "flavor"));
            rollRequest.setCreateChatMessage(
                    this.readOptionalBoolean(requestMap, "createChatMessage"));
            rollRequest.setTarget(this.readOptionalString(requestMap, "target"));
            rollRequest.setSpeaker(this.readOptionalString(requestMap, "speaker"));
            Object whisperPayload = requestMap.get("whisper");
            if (whisperPayload instanceof Iterable<?> iterablePayload) {
                for (Object item : iterablePayload) {
                    if (item != null) {
                        rollRequest.addWhisperItem(String.valueOf(item));
                    }
                }
            }
            return rollRequest;
        }
        return this.objectMapper().convertValue(payload, RollRequest.class);
    }
}
