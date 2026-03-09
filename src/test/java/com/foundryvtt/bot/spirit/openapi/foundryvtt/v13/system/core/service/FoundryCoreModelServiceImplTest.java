package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryItemDocument;

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
}
