package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.SystemId;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.WorldContext;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemCommand;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.Dnd5eCommandNames;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.mapper.Dnd5eCommandRequestMapper;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eModifyExperienceResult;

class Dnd5eCommandExecutorServiceTest {

    @Test
    void shouldExecuteGetActorDetailsCommand() {
        StubDnd5eService dnd5eService = new StubDnd5eService();
        Dnd5eActorDocument expected = new Dnd5eActorDocument();
        expected.setId("actor-1");
        dnd5eService.actorDocument = expected;
        Dnd5eCommandExecutorService service = new Dnd5eCommandExecutorService(
                dnd5eService,
                new Dnd5eCommandRequestMapper(new ObjectMapper()));
        WorldContext worldContext = new WorldContext("client-1", "api-key", SystemId.DND5E);

        Object result = service.execute(worldContext, new SystemCommand(
                Dnd5eCommandNames.GET_ACTOR_DETAILS,
                Map.of("actorUuid", "Actor.actor-1", "details", "inventory")));

        assertThat(result).isSameAs(expected);
        assertThat(dnd5eService.lastClientId).isEqualTo("client-1");
        assertThat(dnd5eService.lastActorUuid).isEqualTo("Actor.actor-1");
        assertThat(dnd5eService.lastDetails).isEqualTo("inventory");
    }

    @Test
    void shouldConvertModifyExperiencePayloadAndDelegateToService() {
        StubDnd5eService dnd5eService = new StubDnd5eService();
        Dnd5eModifyExperienceResult expected = new Dnd5eModifyExperienceResult();
        expected.setAmount(250);
        dnd5eService.modifyExperienceResult = expected;
        Dnd5eCommandExecutorService service = new Dnd5eCommandExecutorService(
                dnd5eService,
                new Dnd5eCommandRequestMapper(new ObjectMapper()));
        WorldContext worldContext = new WorldContext("client-1", "api-key", SystemId.DND5E);

        Object result = service.execute(worldContext, new SystemCommand(
                Dnd5eCommandNames.MODIFY_EXPERIENCE,
                Map.of(
                        "actorUuid", "Actor.actor-1",
                        "amount", "250",
                        "requestBody", Map.of("reason", "milestone"))));

        assertThat(result).isSameAs(expected);
        assertThat(dnd5eService.lastClientId).isEqualTo("client-1");
        assertThat(dnd5eService.lastActorUuid).isEqualTo("Actor.actor-1");
        assertThat(dnd5eService.lastAmount).isEqualTo(250);
        assertThat(dnd5eService.lastRequestBody).containsEntry("reason", "milestone");
    }

    private static final class StubDnd5eService implements Dnd5eService {

        private Dnd5eActorDocument actorDocument;
        private Dnd5eModifyExperienceResult modifyExperienceResult;
        private String lastClientId;
        private String lastActorUuid;
        private String lastDetails;
        private Integer lastAmount;
        private Map<String, Object> lastRequestBody;

        @Override
        public Dnd5eActorDocument getActorDetails(
                String apiKeyOverride,
                String clientId,
                String actorUuid,
                String details) {
            this.lastClientId = clientId;
            this.lastActorUuid = actorUuid;
            this.lastDetails = details;
            return this.actorDocument;
        }

        @Override
        public Dnd5eModifyExperienceResult modifyExperience(
                String apiKeyOverride,
                String clientId,
                String actorUuid,
                Integer amount,
                Map<String, Object> requestBody) {
            this.lastClientId = clientId;
            this.lastActorUuid = actorUuid;
            this.lastAmount = amount;
            this.lastRequestBody = requestBody;
            return this.modifyExperienceResult;
        }

        @Override
        public com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eUseAbilityResult useAbility(
                String apiKeyOverride,
                String clientId,
                String actorUuid,
                String abilityName,
                Map<String, Object> requestBody) {
            throw new UnsupportedOperationException();
        }

        @Override
        public com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eModifyItemChargesResult modifyItemCharges(
                String apiKeyOverride,
                String clientId,
                String actorUuid,
                String itemName,
                String amount,
                Map<String, Object> requestBody) {
            throw new UnsupportedOperationException();
        }
    }
}
