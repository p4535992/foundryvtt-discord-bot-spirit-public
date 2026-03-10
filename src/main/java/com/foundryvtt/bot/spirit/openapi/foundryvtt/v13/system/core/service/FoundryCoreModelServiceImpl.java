package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryActiveEffectDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryFolderDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryItemDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryMacroDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayConnectedClientsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayExecuteJavaScriptResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayLastRollResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayRollResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayRollsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySearchResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionHandshakeResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionOperationResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayStatusResult;

/**
 * Default conversion service for typed Foundry v13 core models.
 */
@ApplicationScoped
public class FoundryCoreModelServiceImpl extends AbstractFoundryModelService
        implements FoundryCoreModelService {

    private final Map<String, Class<? extends FoundryDocument>> modelClasses;

    /**
     * Builds the conversion service.
     *
     * @param objectMapper shared Jackson mapper
     */
    @Inject
    public FoundryCoreModelServiceImpl(ObjectMapper objectMapper) {
        super(objectMapper);
        Map<String, Class<? extends FoundryDocument>> localMap = new LinkedHashMap<String, Class<? extends FoundryDocument>>();
        localMap.put("actor", FoundryActorDocument.class);
        localMap.put("item", FoundryItemDocument.class);
        localMap.put("activeeffect", FoundryActiveEffectDocument.class);
        localMap.put("folder", FoundryFolderDocument.class);
        localMap.put("macro", FoundryMacroDocument.class);
        this.modelClasses = Collections.unmodifiableMap(localMap);
    }

    @Override
    public Set<String> supportedDocumentTypes() {
        return this.modelClasses.keySet();
    }

    @Override
    public Optional<Class<? extends FoundryDocument>> findModelClass(String documentType) {
        if (documentType == null || documentType.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(this.modelClasses.get(this.normalizeDocumentType(documentType)));
    }

    @Override
    public FoundryDocument toDocument(String documentType, Object payload) {
        if (payload == null) {
            return null;
        }
        Class<? extends FoundryDocument> modelClass = this.findModelClass(documentType)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unsupported Foundry core document type: " + documentType));
        if (modelClass.isInstance(payload)) {
            return modelClass.cast(payload);
        }
        return this.convert(payload, modelClass);
    }

    @Override
    public FoundryActorDocument toActorDocument(Object payload) {
        return this.convert(payload, FoundryActorDocument.class);
    }

    @Override
    public FoundryItemDocument toItemDocument(Object payload) {
        return this.convert(payload, FoundryItemDocument.class);
    }

    @Override
    public FoundryActiveEffectDocument toActiveEffectDocument(Object payload) {
        return this.convert(payload, FoundryActiveEffectDocument.class);
    }

    @Override
    public FoundryFolderDocument toFolderDocument(Object payload) {
        return this.convert(payload, FoundryFolderDocument.class);
    }

    @Override
    public FoundryMacroDocument toMacroDocument(Object payload) {
        return this.convert(payload, FoundryMacroDocument.class);
    }

    @Override
    public RelayStatusResult toRelayStatusResult(Object payload) {
        return this.convert(payload, RelayStatusResult.class);
    }

    @Override
    public RelayConnectedClientsResult toConnectedClientsResult(Object payload) {
        return this.convert(payload, RelayConnectedClientsResult.class);
    }

    @Override
    public RelaySessionsResult toSessionsResult(Object payload) {
        return this.convert(payload, RelaySessionsResult.class);
    }

    @Override
    public RelaySessionHandshakeResult toSessionHandshakeResult(Object payload) {
        return this.convert(payload, RelaySessionHandshakeResult.class);
    }

    @Override
    public RelaySessionOperationResult toSessionOperationResult(Object payload) {
        return this.convert(payload, RelaySessionOperationResult.class);
    }

    @Override
    public RelayRollResult toRollResult(Object payload) {
        return this.convert(payload, RelayRollResult.class);
    }

    @Override
    public RelayLastRollResult toLastRollResult(Object payload) {
        return this.convert(payload, RelayLastRollResult.class);
    }

    @Override
    public RelayRollsResult toRollsResult(Object payload) {
        return this.convert(payload, RelayRollsResult.class);
    }

    @Override
    public RelaySearchResult toSearchResult(Object payload) {
        return this.convert(payload, RelaySearchResult.class);
    }

    @Override
    public RelayExecuteJavaScriptResult toExecuteJavaScriptResult(Object payload) {
        return this.convert(payload, RelayExecuteJavaScriptResult.class);
    }

    private String normalizeDocumentType(String documentType) {
        return this.normalizeType(documentType);
    }
}
