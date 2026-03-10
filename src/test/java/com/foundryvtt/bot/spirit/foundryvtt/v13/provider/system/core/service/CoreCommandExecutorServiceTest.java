package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.CoreCommandNames;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.mapper.CoreCommandRequestMapper;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemCommand;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayActorSheetResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayConnectedClientsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayEncounterResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayEntityResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayExecuteJavaScriptResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayLastRollResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayRollResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayRollsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySearchResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionHandshakeResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionOperationResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayStatusResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayStructureResult;
import com.foundryvtt.bot.spirit.openapi.relay.v13.system.core.model.RollRequest;

class CoreCommandExecutorServiceTest {

    @Test
    void shouldExecuteCoreStatusCommand() {
        StubRestRelayService relayService = new StubRestRelayService();
        RelayStatusResult expected = new RelayStatusResult();
        expected.setStatus("ok");
        relayService.relayStatusResult = expected;
        CoreCommandExecutorService service = new CoreCommandExecutorService(relayService,
                new CoreCommandRequestMapper(new ObjectMapper()));

        Object result = service.execute(null, "api-key",
                new SystemCommand(CoreCommandNames.GET_RELAY_STATUS));

        assertThat(result).isSameAs(expected);
    }

    @Test
    void shouldExecuteEntityByUuidCommandUsingTypedRelayService() {
        StubRestRelayService relayService = new StubRestRelayService();
        RelayEntityResult expected = new RelayEntityResult();
        expected.setUuid("Actor.actor-1");
        relayService.entityResult = expected;
        CoreCommandExecutorService service = new CoreCommandExecutorService(relayService,
                new CoreCommandRequestMapper(new ObjectMapper()));

        Object result = service.execute("client-1", "api-key",
                new SystemCommand(CoreCommandNames.GET_ENTITY_BY_UUID, Map.of(
                        "uuid", "Actor.actor-1",
                        "actor", Boolean.TRUE)));

        assertThat(result).isSameAs(expected);
        assertThat(relayService.lastClientId).isEqualTo("client-1");
        assertThat(relayService.lastUuid).isEqualTo("Actor.actor-1");
        assertThat(relayService.lastActor).isTrue();
    }

    @Test
    void shouldConvertRollRequestPayloadBeforeExecutingRollCommand() {
        StubRestRelayService relayService = new StubRestRelayService();
        RelayRollResult expected = new RelayRollResult();
        expected.setSuccess(Boolean.TRUE);
        relayService.rollResult = expected;
        CoreCommandExecutorService service = new CoreCommandExecutorService(relayService,
                new CoreCommandRequestMapper(new ObjectMapper()));

        Object result = service.execute("client-1", "api-key",
                new SystemCommand(CoreCommandNames.ROLL, Map.of(
                        "rollRequest", Map.of(
                                "formula", "1d20 + 5",
                                "flavor", "Attack",
                                "whisper", List.of("gm")))));

        assertThat(result).isSameAs(expected);
        assertThat(relayService.lastRollRequest).isNotNull();
        assertThat(relayService.lastRollRequest.getFormula()).isEqualTo("1d20 + 5");
        assertThat(relayService.lastRollRequest.getFlavor()).isEqualTo("Attack");
        assertThat(relayService.lastRollRequest.getWhisper()).containsExactly("gm");
    }

    private static final class StubRestRelayService implements RestRelayService {

        private RelayStatusResult relayStatusResult;
        private RelayEntityResult entityResult;
        private RelayRollResult rollResult;
        private String lastClientId;
        private String lastUuid;
        private Boolean lastActor;
        private RollRequest lastRollRequest;

        @Override
        public RelayStatusResult getRelayStatus() {
            return this.relayStatusResult;
        }

        @Override
        public RelayConnectedClientsResult getConnectedClients(String apiKeyOverride) {
            throw new UnsupportedOperationException();
        }

        @Override
        public RelaySessionsResult getCurrentSessions(String apiKeyOverride) {
            throw new UnsupportedOperationException();
        }

        @Override
        public RelaySessionHandshakeResult createSessionHandshake(String apiKeyOverride,
                String foundryUrl, String username, String password, String worldName,
                Map<String, Object> requestBody) {
            throw new UnsupportedOperationException();
        }

        @Override
        public RelaySessionOperationResult startSession(String apiKeyOverride, Object requestBody) {
            throw new UnsupportedOperationException();
        }

        @Override
        public RelaySessionOperationResult endSession(String apiKeyOverride, String sessionId) {
            throw new UnsupportedOperationException();
        }

        @Override
        public RelayStructureResult getStructure(String apiKeyOverride, String clientId) {
            throw new UnsupportedOperationException();
        }

        @Override
        public RelayEncounterResult getEncounters(String apiKeyOverride, String clientId) {
            throw new UnsupportedOperationException();
        }

        @Override
        public RelayEntityResult getEntity(String apiKeyOverride, String clientId, Boolean selected,
                Boolean actor) {
            throw new UnsupportedOperationException();
        }

        @Override
        public RelayEntityResult getEntityByUuid(String apiKeyOverride, String clientId,
                String uuid, Boolean actor) {
            this.lastClientId = clientId;
            this.lastUuid = uuid;
            this.lastActor = actor;
            return this.entityResult;
        }

        @Override
        public RelayActorSheetResult getActorSheet(String apiKeyOverride, String clientId,
                String uuid, Boolean selected, Boolean actor, BigDecimal scale) {
            throw new UnsupportedOperationException();
        }

        @Override
        public RelayRollResult roll(String apiKeyOverride, String clientId,
                RollRequest rollRequest) {
            this.lastClientId = clientId;
            this.lastRollRequest = rollRequest;
            return this.rollResult;
        }

        @Override
        public RelayLastRollResult getLastRoll(String apiKeyOverride, String clientId) {
            throw new UnsupportedOperationException();
        }

        @Override
        public RelayRollsResult getRecentRolls(String apiKeyOverride, String clientId,
                Integer limit) {
            throw new UnsupportedOperationException();
        }

        @Override
        public RelaySearchResult search(String apiKeyOverride, String clientId, String query,
                String filter) {
            throw new UnsupportedOperationException();
        }

        @Override
        public RelayExecuteJavaScriptResult executeJavaScript(String apiKeyOverride,
                String clientId,
                Object requestBody) {
            throw new UnsupportedOperationException();
        }

        @Override
        public URI buildRelayWebSocketUri(String clientId, String token) {
            throw new UnsupportedOperationException();
        }

        @Override
        public String getRelayBaseUrl() {
            throw new UnsupportedOperationException();
        }

        @Override
        public String getRelayWebSocketUrl() {
            throw new UnsupportedOperationException();
        }
    }
}
