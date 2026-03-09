package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
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

/**
 * Default conversion service for typed Foundry v13 core models.
 */
@ApplicationScoped
public class FoundryCoreModelServiceImpl implements FoundryCoreModelService {

    private final ObjectMapper objectMapper;
    private final Map<String, Class<? extends FoundryDocument>> modelClasses;

    /**
     * Builds the conversion service.
     *
     * @param objectMapper shared Jackson mapper
     */
    @Inject
    public FoundryCoreModelServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
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
        return this.objectMapper.convertValue(payload, modelClass);
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

    private <T> T convert(Object payload, Class<T> targetType) {
        if (payload == null) {
            return null;
        }
        if (targetType.isInstance(payload)) {
            return targetType.cast(payload);
        }
        return this.objectMapper.convertValue(payload, targetType);
    }

    private String normalizeDocumentType(String documentType) {
        return documentType
                .replace("-", "")
                .replace("_", "")
                .replace(" ", "")
                .toLowerCase(Locale.ROOT);
    }
}
