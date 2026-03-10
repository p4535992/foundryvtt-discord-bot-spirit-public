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
}
