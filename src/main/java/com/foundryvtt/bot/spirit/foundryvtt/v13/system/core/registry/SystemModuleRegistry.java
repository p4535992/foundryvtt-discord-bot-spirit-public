package com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.registry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.model.SystemId;
import com.foundryvtt.bot.spirit.foundryvtt.v13.system.core.spi.SystemModule;

/**
 * Runtime registry of available {@link SystemModule} beans.
 */
@ApplicationScoped
public class SystemModuleRegistry {

    /**
     * Module map by resolved {@link SystemId}.
     */
    private final Map<SystemId, SystemModule> modulesBySystemId;

    /**
     * Builds the module registry from CDI-discovered beans.
     *
     * @param systemModules discovered module beans
     */
    @Inject
    public SystemModuleRegistry(Instance<SystemModule> systemModules) {
        Map<SystemId, SystemModule> localMap = new HashMap<SystemId, SystemModule>();
        for (SystemModule module : systemModules) {
            SystemId systemId = module.systemId();
            if (localMap.containsKey(systemId)) {
                throw new IllegalStateException(
                        "Duplicate system module for system id: " + systemId.value());
            }
            localMap.put(systemId, module);
        }
        this.modulesBySystemId = Collections.unmodifiableMap(localMap);
    }

    /**
     * Resolves a required module by system id.
     *
     * @param systemId target system id
     * @return matching module
     */
    public SystemModule getRequiredModule(SystemId systemId) {
        Optional<SystemModule> optionalModule = this.findModule(systemId);
        if (optionalModule.isPresent()) {
            return optionalModule.get();
        }
        throw new IllegalStateException(
                "No system module registered for system id: " + systemId.value());
    }

    /**
     * Resolves an optional module by system id.
     *
     * @param systemId target system id
     * @return optional system module
     */
    public Optional<SystemModule> findModule(SystemId systemId) {
        if (systemId == null) {
            return Optional.empty();
        }
        if (this.modulesBySystemId.containsKey(systemId)) {
            return Optional.of(this.modulesBySystemId.get(systemId));
        }
        return Optional.empty();
    }

    /**
     * Returns registered system ids.
     *
     * @return registered system ids
     */
    public Set<SystemId> registeredSystemIds() {
        return this.modulesBySystemId.keySet();
    }
}
