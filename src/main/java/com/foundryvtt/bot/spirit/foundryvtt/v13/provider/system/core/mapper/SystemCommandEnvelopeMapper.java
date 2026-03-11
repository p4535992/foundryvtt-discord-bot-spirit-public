package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.mapper;

import jakarta.enterprise.context.ApplicationScoped;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.model.SystemCommandEnvelope;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemCommand;

/**
 * Maps an external command envelope to the internal system-command model.
 */
@ApplicationScoped
public class SystemCommandEnvelopeMapper {

    public SystemCommand toSystemCommand(SystemCommandEnvelope envelope) {
        if (envelope == null) {
            throw new IllegalArgumentException("envelope must not be null");
        }
        return new SystemCommand(envelope.getCommandName(), envelope.getPayload());
    }
}
