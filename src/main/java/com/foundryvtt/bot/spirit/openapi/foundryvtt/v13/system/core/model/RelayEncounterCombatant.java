package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

import java.math.BigDecimal;

public class RelayEncounterCombatant extends AbstractFoundryModel {

    private String id;
    private String name;
    private String tokenUuid;
    private String actorUuid;
    private String img;
    private BigDecimal initiative;
    private Boolean hidden;
    private Boolean defeated;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTokenUuid() {
        return this.tokenUuid;
    }

    public void setTokenUuid(String tokenUuid) {
        this.tokenUuid = tokenUuid;
    }

    public String getActorUuid() {
        return this.actorUuid;
    }

    public void setActorUuid(String actorUuid) {
        this.actorUuid = actorUuid;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BigDecimal getInitiative() {
        return this.initiative;
    }

    public void setInitiative(BigDecimal initiative) {
        this.initiative = initiative;
    }

    public Boolean getHidden() {
        return this.hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getDefeated() {
        return this.defeated;
    }

    public void setDefeated(Boolean defeated) {
        this.defeated = defeated;
    }
}
