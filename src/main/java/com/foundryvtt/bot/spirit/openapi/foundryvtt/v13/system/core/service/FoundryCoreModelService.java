package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.service;

import java.util.Optional;
import java.util.Set;

import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryActiveEffectDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryActorDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryFolderDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryItemDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.FoundryMacroDocument;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayConnectedClientsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionHandshakeResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionOperationResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelaySessionsResult;
import com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model.RelayStatusResult;

/**
 * Utility entry point for resolving and converting core Foundry document payloads.
 */
public interface FoundryCoreModelService {

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
}
