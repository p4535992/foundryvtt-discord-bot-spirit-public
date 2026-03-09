package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eCharacterActorSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eItemDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eSpellItemSystemData;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model.Dnd5eWeaponItemSystemData;

class FoundryDnd5eModelServiceImplTest {

    private final FoundryDnd5eModelService service = new FoundryDnd5eModelServiceImpl(
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

        Dnd5eActorDocument actor = this.service.toActorDocument(payload);

        assertThat(actor.getSystemData()).isInstanceOf(Dnd5eCharacterActorSystemData.class);
        assertThat(actor.getSystemData().getAttributes().getHp().getValue()).isEqualTo(27);
        assertThat(actor.getSystemData().getDetails().getAlignment()).isEqualTo("neutral good");
        assertThat(actor.getSystemData().getTraits().getLanguages().getValue()).contains("common",
                "elvish");
        assertThat(actor.getItems()).hasSize(2);
        assertThat(actor.getItems().get(0)).isInstanceOf(Dnd5eItemDocument.class);
        assertThat(((Dnd5eItemDocument) actor.getItems().get(0)).getSystemData())
                .isInstanceOf(Dnd5eSpellItemSystemData.class);
        assertThat(((Dnd5eSpellItemSystemData) ((Dnd5eItemDocument) actor.getItems().get(0))
                .getSystemData())
                .getSchool()).isEqualTo("evo");
        assertThat(((Dnd5eItemDocument) actor.getItems().get(1)).getSystemData())
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

        Dnd5eItemDocument item = this.service.toItemDocument(payload);

        assertThat(item.getSystemData()).isNotInstanceOf(Dnd5eSpellItemSystemData.class);
        assertThat(item.getSystemData().getIdentifier()).isEqualTo("rogue.sneak-attack");
        assertThat(this.service.supportedActorTypes()).contains("character", "npc");
        assertThat(this.service.supportedItemTypes()).contains("spell", "weapon");
    }
}
