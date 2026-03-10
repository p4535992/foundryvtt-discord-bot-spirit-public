package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.routing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.command.CoreCommandNames;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.Capability;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.SystemId;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.WorldContext;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.registry.SystemModuleRegistry;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.service.CoreCommandExecutorService;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemCommand;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemModule;

class SystemCommandRouterServiceTest {

    @Test
    void shouldDelegateCoreCommandsToCoreExecutorBeforeSystemResolution() {
        FoundrySystemResolverService resolverService = mock(FoundrySystemResolverService.class);
        CoreCommandExecutorService coreExecutorService = mock(CoreCommandExecutorService.class);
        SystemModuleRegistry moduleRegistry = mock(SystemModuleRegistry.class);
        SystemCommandRouterService routerService = new SystemCommandRouterService(
                resolverService,
                coreExecutorService,
                moduleRegistry);

        SystemCommand command = new SystemCommand(CoreCommandNames.GET_RELAY_STATUS);
        when(coreExecutorService.supportsCommand(CoreCommandNames.GET_RELAY_STATUS))
                .thenReturn(true);
        when(coreExecutorService.execute("client-1", "api-key", command)).thenReturn("ok");

        Object result = routerService.route("client-1", "api-key", command);

        assertThat(result).isEqualTo("ok");
        verify(coreExecutorService).execute("client-1", "api-key", command);
        verify(resolverService, never()).resolveSystemIdForClient("client-1", "api-key");
        verify(moduleRegistry, never()).getRequiredModule(SystemId.DND5E);
    }

    @Test
    void shouldResolveWorldContextAndDispatchNonCoreCommandsToSystemModule() {
        FoundrySystemResolverService resolverService = mock(FoundrySystemResolverService.class);
        CoreCommandExecutorService coreExecutorService = mock(CoreCommandExecutorService.class);
        SystemModuleRegistry moduleRegistry = mock(SystemModuleRegistry.class);
        SystemModule systemModule = new StubSystemModule();
        SystemCommandRouterService routerService = new SystemCommandRouterService(
                resolverService,
                coreExecutorService,
                moduleRegistry);

        SystemCommand command = new SystemCommand("dnd5e.actor.getDetails",
                Map.of("actorId", "a1"));
        when(coreExecutorService.supportsCommand(command.getCommandName())).thenReturn(false);
        when(resolverService.resolveSystemIdForClient("client-1", "api-key"))
                .thenReturn(SystemId.DND5E);
        when(moduleRegistry.getRequiredModule(SystemId.DND5E)).thenReturn(systemModule);

        Object result = routerService.route("client-1", "api-key", command);

        assertThat(result).isEqualTo("handled:client-1:dnd5e.actor.getDetails");
        verify(resolverService).resolveSystemIdForClient("client-1", "api-key");
        verify(moduleRegistry).getRequiredModule(SystemId.DND5E);
    }

    @Test
    void shouldDelegateCoreCommandsFromResolvedWorldContext() {
        FoundrySystemResolverService resolverService = mock(FoundrySystemResolverService.class);
        CoreCommandExecutorService coreExecutorService = mock(CoreCommandExecutorService.class);
        SystemModuleRegistry moduleRegistry = mock(SystemModuleRegistry.class);
        SystemCommandRouterService routerService = new SystemCommandRouterService(
                resolverService,
                coreExecutorService,
                moduleRegistry);
        WorldContext worldContext = new WorldContext("client-2", "api-key", SystemId.DND5E);
        SystemCommand command = new SystemCommand(CoreCommandNames.GET_CURRENT_SESSIONS);

        when(coreExecutorService.supportsCommand(command.getCommandName())).thenReturn(true);
        when(coreExecutorService.execute(eq(worldContext), eq(command))).thenReturn("sessions");

        Object result = routerService.route(worldContext, command);

        assertThat(result).isEqualTo("sessions");
        verify(coreExecutorService).execute(worldContext, command);
        verify(moduleRegistry, never()).getRequiredModule(SystemId.DND5E);
    }

    private static final class StubSystemModule implements SystemModule {

        @Override
        public SystemId systemId() {
            return SystemId.DND5E;
        }

        @Override
        public Set<Capability> capabilities() {
            return Set.of();
        }

        @Override
        public boolean supportsCommand(String commandName) {
            return "dnd5e.actor.getDetails".equals(commandName);
        }

        @Override
        public Object execute(SystemCommand command, WorldContext worldContext) {
            return "handled:" + worldContext.getClientId() + ":" + command.getCommandName();
        }
    }
}
