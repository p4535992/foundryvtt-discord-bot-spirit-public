package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model;

import java.math.BigDecimal;
import java.util.Map;

import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.model.RollRequest;

/**
 * Typed provider request models for core commands.
 */
public final class CoreCommandRequests {

    private CoreCommandRequests() {
    }

    public record ClientCommandRequest(String clientId) {
    }

    public record BodyOnlyCommandRequest(Object requestBody) {
    }

    public record ClientBodyCommandRequest(String clientId, Object requestBody) {
    }

    public record SessionHandshakeCommandRequest(
            String foundryUrl,
            String username,
            String password,
            String worldName,
            Map<String, Object> requestBody) {
    }

    public record SessionIdCommandRequest(String sessionId) {
    }

    public record EntityCommandRequest(String clientId, Boolean selected, Boolean actor) {
    }

    public record EntityByUuidCommandRequest(String clientId, String uuid, Boolean actor) {
    }

    public record ActorSheetCommandRequest(
            String clientId,
            String uuid,
            Boolean selected,
            Boolean actor,
            BigDecimal scale) {
    }

    public record RollCommandRequest(String clientId, RollRequest rollRequest) {
    }

    public record RecentRollsCommandRequest(String clientId, Integer limit) {
    }

    public record SearchCommandRequest(String clientId, String query, String filter) {
    }
}
