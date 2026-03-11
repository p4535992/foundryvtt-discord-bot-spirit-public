package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.mapper;

import jakarta.enterprise.context.ApplicationScoped;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.SystemCommandEnvelope;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemCommand;

/**
 * Maps the public provider command envelope to the internal router command model.
 *
 * <p>
 * The external envelope contains provider-facing concerns such as client id and optional API key
 * override. The internal {@link SystemCommand} keeps only the command name and payload because
 * routing/execution receives connection metadata separately.
 */
@ApplicationScoped
public class SystemCommandEnvelopeMapper {

    /**
     * Converts a public provider envelope to the minimal internal command shape.
     *
     * @param envelope public provider command envelope
     * @return internal command model
     */
    public SystemCommand toSystemCommand(SystemCommandEnvelope envelope) {
        if (envelope == null) {
            throw new IllegalArgumentException("envelope must not be null");
        }
        return new SystemCommand(envelope.getCommandName(), envelope.getPayload());
    }
}
