package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

public class RelayRollSpeaker extends AbstractFoundryModel {

    private String scene;
    private String actor;
    private String token;
    private String alias;

    public String getScene() {
        return this.scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getActor() {
        return this.actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
