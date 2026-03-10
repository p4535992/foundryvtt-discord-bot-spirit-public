package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryItemDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayConnectedClientsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayExecuteJavaScriptResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayLastRollResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayRollResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayRollsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySearchResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionHandshakeResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionOperationResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayStatusResult;

class FoundryCoreModelServiceImplTest {

    private final FoundryCoreModelService service = new FoundryCoreModelServiceImpl(
            new ObjectMapper());

    @Test
    void shouldConvertActorPayloadToTypedModel() {
        Map<String, Object> payload = Map.of(
                "_id", "actor-1",
                "name", "Big Ape",
                "type", "npc",
                "system", Map.of("attributes", Map.of("hp", Map.of("value", 19))),
                "prototypeToken", Map.of(
                        "name", "Big Ape",
                        "actorLink", Boolean.TRUE,
                        "texture", Map.of("src", "tokens/big-ape.webp")),
                "items", List.of(Map.of("_id", "item-1", "name", "Claws", "type", "weapon")));

        FoundryActorDocument actor = this.service.toActorDocument(payload);

        assertThat(actor.getId()).isEqualTo("actor-1");
        assertThat(actor.getType()).isEqualTo("npc");
        assertThat(actor.getPrototypeToken()).isNotNull();
        assertThat(actor.getPrototypeToken().getActorLink()).isTrue();
        assertThat(actor.getPrototypeToken().getTexture().getSrc())
                .isEqualTo("tokens/big-ape.webp");
        assertThat(actor.getItems()).hasSize(1);
        assertThat(actor.getItems().get(0).getName()).isEqualTo("Claws");
        assertThat(actor.getSystem().path("attributes").path("hp").path("value").asInt())
                .isEqualTo(19);
    }

    @Test
    void shouldResolveDocumentTypeUsingGenericEntryPoint() {
        Map<String, Object> payload = Map.of(
                "_id", "item-1",
                "name", "Longsword",
                "type", "weapon",
                "img", "icons/longsword.webp");

        FoundryDocument item = this.service.toDocument("Item", payload);

        assertThat(item).isInstanceOf(FoundryItemDocument.class);
        assertThat(((FoundryItemDocument) item).getName()).isEqualTo("Longsword");
        assertThat(this.service.supportedDocumentTypes()).contains("actor", "item", "activeeffect");
    }

    @Test
    void shouldConvertStableRelayCoreResponses() {
        RelayStatusResult status = this.service.toRelayStatusResult(Map.of(
                "status", "ok",
                "version", "2.1.2",
                "websocket", "/relay"));
        assertThat(status.getStatus()).isEqualTo("ok");
        assertThat(status.getVersion()).isEqualTo("2.1.2");

        RelayConnectedClientsResult clients = this.service.toConnectedClientsResult(Map.of(
                "total", 1,
                "clients", List.of(Map.of(
                        "id", "client-1",
                        "systemId", "dnd5e",
                        "worldTitle", "Example World"))));
        assertThat(clients.getTotal()).isEqualTo(1);
        assertThat(clients.getClients()).hasSize(1);
        assertThat(clients.getClients().get(0).getSystemId()).isEqualTo("dnd5e");

        RelaySessionsResult sessions = this.service.toSessionsResult(Map.of(
                "activeSessions", List.of(Map.of(
                        "id", "session-1",
                        "clientId", "client-1",
                        "systemId", "dnd5e")),
                "pendingSessions", List.of(Map.of(
                        "id", "pending-1",
                        "status", "pending",
                        "expectedClientId", "headless-user"))));
        assertThat(sessions.getActiveSessions()).hasSize(1);
        assertThat(sessions.getPendingSessions()).hasSize(1);
        assertThat(sessions.getActiveSessions().get(0).getSystemId()).isEqualTo("dnd5e");

        RelaySessionHandshakeResult handshake = this.service.toSessionHandshakeResult(Map.of(
                "token", "token-1",
                "publicKey", "public-key",
                "nonce", "nonce-1",
                "expires", 123456789L));
        assertThat(handshake.getToken()).isEqualTo("token-1");
        assertThat(handshake.getExpires()).isEqualTo(123456789L);

        RelaySessionOperationResult operation = this.service.toSessionOperationResult(Map.of(
                "success", Boolean.TRUE,
                "message", "Foundry session started successfully",
                "sessionId", "session-1",
                "clientId", "client-1"));
        assertThat(operation.getSuccess()).isTrue();
        assertThat(operation.getSessionId()).isEqualTo("session-1");
    }

    @Test
    void shouldConvertRelayRollSearchAndUtilityResponses() {
        RelayRollResult roll = this.service.toRollResult(Map.of(
                "clientId", "client-1",
                "success", Boolean.TRUE,
                "roll", Map.of(
                        "id", "manual-1",
                        "chatMessageCreated", Boolean.TRUE,
                        "roll", Map.of(
                                "formula", "2d20kh + 5",
                                "total", 19,
                                "isCritical", Boolean.FALSE,
                                "isFumble", Boolean.FALSE,
                                "dice", List.of(Map.of(
                                        "faces", 20,
                                        "results", List.of(
                                                Map.of("result", 19, "active", Boolean.TRUE),
                                                Map.of("result", 3, "active", Boolean.FALSE)))),
                                "timestamp", 1741128675737L))));
        assertThat(roll.getSuccess()).isTrue();
        assertThat(roll.getRoll().getId()).isEqualTo("manual-1");
        assertThat(roll.getRoll().getRoll().getTotal()).isEqualTo(19);
        assertThat(roll.getRoll().getRoll().getDice().get(0).getResults()).hasSize(2);

        RelayLastRollResult lastRoll = this.service.toLastRollResult(Map.of(
                "clientId", "client-1",
                "roll", Map.of(
                        "id", "history-1",
                        "messageId", "message-1",
                        "user", Map.of("id", "user-1", "name", "Gamemaster"),
                        "speaker", Map.of("alias", "Gamemaster"),
                        "formula", "1d20",
                        "rollTotal", 14,
                        "dice", List.of(Map.of(
                                "faces", 20,
                                "results", List.of(Map.of("result", 14, "active", Boolean.TRUE)))),
                        "timestamp", 1741128440641L)));
        assertThat(lastRoll.getRoll().getUser().getName()).isEqualTo("Gamemaster");
        assertThat(lastRoll.getRoll().getRollTotal()).isEqualTo(14);

        RelayRollsResult rolls = this.service.toRollsResult(Map.of(
                "clientId", "client-1",
                "rolls", List.of(
                        Map.of("id", "history-1", "formula", "1d20", "rollTotal", 14),
                        Map.of("id", "history-2", "formula", "2d6", "rollTotal", 7))));
        assertThat(rolls.getRolls()).hasSize(2);
        assertThat(rolls.getRolls().get(1).getFormula()).isEqualTo("2d6");

        RelaySearchResult search = this.service.toSearchResult(Map.of(
                "requestId", "search-1",
                "clientId", "client-1",
                "query", "abo",
                "filter", "actor",
                "totalResults", 1,
                "results", List.of(Map.of(
                        "documentType", "Actor",
                        "id", "actor-1",
                        "name", "Aboleth",
                        "package", "dnd5e.monsters",
                        "packageName", "Monsters (SRD)",
                        "subType", "npc",
                        "uuid", "Compendium.dnd5e.monsters.actor-1",
                        "resultType", "CompendiumSearchItem"))));
        assertThat(search.getRequestId()).isEqualTo("search-1");
        assertThat(search.getResults()).hasSize(1);
        assertThat(search.getResults().get(0).getPackageId()).isEqualTo("dnd5e.monsters");

        RelayExecuteJavaScriptResult executeJavaScript = this.service
                .toExecuteJavaScriptResult(Map.of(
                        "requestId", "execute-js-1",
                        "clientId", "client-1",
                        "success", Boolean.TRUE,
                        "result", "ws://localhost:3010/"));
        assertThat(executeJavaScript.getSuccess()).isTrue();
        assertThat(executeJavaScript.getResult().asText()).isEqualTo("ws://localhost:3010/");
    }
}
