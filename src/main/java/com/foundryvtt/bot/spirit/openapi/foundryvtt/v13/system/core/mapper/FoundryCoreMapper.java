package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.mapper;

import java.util.Optional;
import java.util.Set;

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
 * Conversion entry point between relay payloads and hand-written Foundry core models.
 *
 * <p>
 * This mapper is the seam that keeps generated relay transport models out of the rest of the
 * application. Provider services may receive raw/generated relay payloads, but they immediately
 * convert them through this contract to the manual core models rooted under
 * {@code openapi.foundryvtt.v13.system}.
 */
public interface FoundryCoreMapper {

    /**
     * Returns the Foundry core document types explicitly modeled by this mapper.
     *
     * @return supported Foundry document types
     */
    Set<String> supportedDocumentTypes();

    /**
     * Finds the manual Java model class associated with a Foundry document type.
     *
     * @param documentType Foundry document type
     * @return manual model class when known
     */
    Optional<Class<? extends FoundryDocument>> findModelClass(String documentType);

    /**
     * Converts a generic relay payload to the manual Foundry document model matching the type.
     *
     * @param documentType Foundry document type
     * @param payload      raw relay payload
     * @return typed manual document model
     */
    FoundryDocument toDocument(String documentType, Object payload);

    FoundryActorDocument toActorDocument(Object payload);

    FoundryItemDocument toItemDocument(Object payload);

    FoundryActiveEffectDocument toActiveEffectDocument(Object payload);

    FoundryFolderDocument toFolderDocument(Object payload);

    FoundryMacroDocument toMacroDocument(Object payload);

    RelayStatusResult toRelayStatusResult(Object payload);

    RelayConnectedClientsResult toConnectedClientsResult(Object payload);

    RelaySessionsResult toSessionsResult(Object payload);

    RelaySessionHandshakeResult toSessionHandshakeResult(Object payload);

    RelaySessionOperationResult toSessionOperationResult(Object payload);

    RelayRollResult toRollResult(Object payload);

    RelayLastRollResult toLastRollResult(Object payload);

    RelayRollsResult toRollsResult(Object payload);

    RelaySearchResult toSearchResult(Object payload);

    RelayExecuteJavaScriptResult toExecuteJavaScriptResult(Object payload);

    RelayStructureResult toStructureResult(Object payload);

    RelayEncounterResult toEncounterResult(Object payload);

    RelayEntityResult toEntityResult(Object payload);

    RelayActorSheetResult toActorSheetResult(Object payload);
}
