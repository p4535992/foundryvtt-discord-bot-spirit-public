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
 */
public interface FoundryCoreMapper {

    Set<String> supportedDocumentTypes();

    Optional<Class<? extends FoundryDocument>> findModelClass(String documentType);

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
