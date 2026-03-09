package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.dnd5e.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Dnd5eDamageTrait extends Dnd5eSimpleTrait {

    private Set<String> bypasses = new LinkedHashSet<String>();

    public Set<String> getBypasses() {
        return this.bypasses;
    }

    public void setBypasses(Set<String> bypasses) {
        this.bypasses = bypasses;
    }
}
