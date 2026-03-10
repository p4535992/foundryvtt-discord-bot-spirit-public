package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eCharacterActorSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eItemDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eModifyExperienceResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eSpellItemSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eUseAbilityResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eWeaponItemSystemData;

class FoundryDnd5eMapperImplTest {

    private final FoundryDnd5eMapper mapper = new FoundryDnd5eMapperImpl(
            new ObjectMapper());

    @Test
    void shouldConvertCharacterActorPayloadToTypedDnd5eModel() {
        Map<String, Object> payload = Map.of(
                "_id", "actor-1",
                "name", "Aelar",
                "type", "character",
                "system", Map.of(
                        "abilities", Map.of("str", Map.of("value", 12), "dex", Map.of("value", 16)),
                        "attributes", Map.of(
                                "hp", Map.of("value", 27, "max", 27),
                                "spellcasting", "wis"),
                        "details", Map.of(
                                "alignment", "neutral good",
                                "xp", Map.of("value", 900)),
                        "traits", Map.of(
                                "languages", Map.of("value", List.of("common", "elvish")))),
                "items", List.of(
                        Map.of(
                                "_id", "item-1",
                                "name", "Magic Missile",
                                "type", "spell",
                                "system", Map.of(
                                        "level", 1,
                                        "school", "evo",
                                        "method", "prepared",
                                        "properties", List.of("vocal", "somatic"))),
                        Map.of(
                                "_id", "item-2",
                                "name", "Longbow",
                                "type", "weapon",
                                "system", Map.of(
                                        "mastery", "vex",
                                        "magicalBonus", 1,
                                        "properties", List.of("amm", "hvy")))));

        Dnd5eActorDocument actor = this.mapper.toActorDocument(payload);

        assertThat(actor.getSystemData()).isInstanceOf(Dnd5eCharacterActorSystemData.class);
        assertThat(actor.getSystemData().getAttributes().getHp().getValue()).isEqualTo(27);
        assertThat(actor.getSystemData().getDetails().getAlignment()).isEqualTo("neutral good");
        assertThat(actor.getSystemData().getTraits().getLanguages().getValue()).contains("common",
                "elvish");
        assertThat(actor.getTypedItems()).hasSize(2);
        assertThat(actor.getTypedItems().get(0).getSystemData())
                .isInstanceOf(Dnd5eSpellItemSystemData.class);
        assertThat(((Dnd5eSpellItemSystemData) actor.getTypedItems().get(0).getSystemData())
                .getSchool()).isEqualTo("evo");
        assertThat(actor.getTypedItems().get(1).getSystemData())
                .isInstanceOf(Dnd5eWeaponItemSystemData.class);
    }

    @Test
    void shouldFallbackToGenericItemSystemForUnsupportedType() {
        Map<String, Object> payload = Map.of(
                "_id", "item-3",
                "name", "Sneak Attack",
                "type", "feat",
                "system", Map.of(
                        "identifier", "rogue.sneak-attack",
                        "uses", Map.of("spent", 0)));

        Dnd5eItemDocument item = this.mapper.toItemDocument(payload);

        assertThat(item.getSystemData()).isNotInstanceOf(Dnd5eSpellItemSystemData.class);
        assertThat(item.getSystemData().getIdentifier()).isEqualTo("rogue.sneak-attack");
        assertThat(this.mapper.supportedActorTypes()).contains("character", "npc");
        assertThat(this.mapper.supportedItemTypes()).contains("spell", "weapon");
    }

    @Test
    void shouldConvertOperationResultsToTypedWrappers() {
        Map<String, Object> modifyExperiencePayload = Map.of(
                "requestId", "req-1",
                "clientId", "client-1",
                "success", Boolean.TRUE,
                "amount", 300,
                "actor", Map.of(
                        "_id", "actor-1",
                        "name", "Aelar",
                        "type", "character",
                        "system", Map.of(
                                "details", Map.of("xp", Map.of("value", 1200)))));

        Dnd5eModifyExperienceResult modifyExperienceResult = this.mapper.toModifyExperienceResult(
                modifyExperiencePayload);

        assertThat(modifyExperienceResult.getRequestId()).isEqualTo("req-1");
        assertThat(modifyExperienceResult.getClientId()).isEqualTo("client-1");
        assertThat(modifyExperienceResult.getSuccess()).isTrue();
        assertThat(modifyExperienceResult.getActor()).isNotNull();
        assertThat(modifyExperienceResult.getActor().getSystemData())
                .isInstanceOf(Dnd5eCharacterActorSystemData.class);
        assertThat(modifyExperienceResult.getActor().getSystemData().getDetails().getXp()
                .path("value").asInt())
                .isEqualTo(1200);

        Map<String, Object> useAbilityPayload = Map.of(
                "requestId", "req-2",
                "clientId", "client-1",
                "abilityName", "Magic Missile",
                "actor", Map.of(
                        "_id", "actor-1",
                        "name", "Aelar",
                        "type", "character",
                        "system", Map.of()),
                "item", Map.of(
                        "_id", "item-1",
                        "name", "Magic Missile",
                        "type", "spell",
                        "system", Map.of(
                                "level", 1,
                                "school", "evo")));

        Dnd5eUseAbilityResult useAbilityResult = this.mapper.toUseAbilityResult(useAbilityPayload);

        assertThat(useAbilityResult.getAbilityName()).isEqualTo("Magic Missile");
        assertThat(useAbilityResult.getActor()).isNotNull();
        assertThat(useAbilityResult.getAbility()).isNotNull();
        assertThat(useAbilityResult.getAbility().getSystemData())
                .isInstanceOf(Dnd5eSpellItemSystemData.class);
    }
}
