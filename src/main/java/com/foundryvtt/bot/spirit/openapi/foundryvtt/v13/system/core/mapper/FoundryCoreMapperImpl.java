package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryActiveEffectDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryFolderDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryItemDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryMacroDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayActorSheetResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayConnectedClientsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayEncounterResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayEntityResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayExecuteJavaScriptResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayLastRollResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayRollResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayRollsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySearchResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionHandshakeResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionOperationResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayStatusResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayStructureResult;

/**
 * Default conversion service for typed Foundry v13 core models.
 */
@ApplicationScoped
public class FoundryCoreMapperImpl extends AbstractFoundryMapper implements FoundryCoreMapper {

    private final Map<String, Class<? extends FoundryDocument>> modelClasses;

    /**
     * Builds the conversion service.
     *
     * @param objectMapper shared Jackson mapper
     */
    @Inject
    public FoundryCoreMapperImpl(ObjectMapper objectMapper) {
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

    @Override
    public RelayStructureResult toStructureResult(Object payload) {
        return this.convert(payload, RelayStructureResult.class);
    }

    @Override
    public RelayEncounterResult toEncounterResult(Object payload) {
        return this.convert(payload, RelayEncounterResult.class);
    }

    @Override
    public RelayEntityResult toEntityResult(Object payload) {
        if (payload == null) {
            return null;
        }
        RelayEntityResult result = this.convert(payload, RelayEntityResult.class);
        if (!(payload instanceof Map<?, ?> rawMap)) {
            return result;
        }

        Object uuidPayload = rawMap.get("uuid");
        if (uuidPayload instanceof String uuidValue) {
            result.setDocumentType(this.extractDocumentTypeFromUuid(uuidValue));
        }

        Object dataPayload = rawMap.get("data");
        if (dataPayload == null) {
            return result;
        }

        List<Object> rawEntries = this.asPayloadList(dataPayload);
        List<JsonNode> rawData = new ArrayList<JsonNode>(rawEntries.size());
        List<FoundryDocument> documents = new ArrayList<FoundryDocument>(rawEntries.size());
        for (Object rawEntry : rawEntries) {
            rawData.add(this.convert(rawEntry, JsonNode.class));
            FoundryDocument document = this.tryConvertDocument(result.getDocumentType(), rawEntry);
            if (document != null) {
                documents.add(document);
            }
        }
        result.setRawData(rawData);
        result.setDocuments(documents);
        return result;
    }

    @Override
    public RelayActorSheetResult toActorSheetResult(Object payload) {
        if (payload == null) {
            return null;
        }
        if (payload instanceof String htmlPayload) {
            RelayActorSheetResult result = new RelayActorSheetResult();
            result.setHtml(htmlPayload);
            return result;
        }
        return this.convert(payload, RelayActorSheetResult.class);
    }

    private List<Object> asPayloadList(Object payload) {
        if (payload instanceof List<?> listPayload) {
            return new ArrayList<Object>(listPayload);
        }
        return List.of(payload);
    }

    private FoundryDocument tryConvertDocument(String documentType, Object payload) {
        if (documentType == null || documentType.isBlank()) {
            return null;
        }
        if (this.findModelClass(documentType).isEmpty()) {
            return null;
        }
        return this.toDocument(documentType, payload);
    }

    private String extractDocumentTypeFromUuid(String uuid) {
        if (uuid == null || uuid.isBlank()) {
            return null;
        }
        int separatorIndex = uuid.indexOf('.');
        if (separatorIndex < 0) {
            return uuid;
        }
        return uuid.substring(0, separatorIndex);
    }

    private String normalizeDocumentType(String documentType) {
        return this.normalizeType(documentType);
    }
}
