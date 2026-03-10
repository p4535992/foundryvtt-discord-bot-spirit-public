package com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.SystemId;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.model.WorldContext;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.core.spi.SystemCommand;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.command.Dnd5eCommandNames;
import com.foundryvtt.bot.spirit.foundryvtt.v13.provider.system.dnd5e.service.Dnd5eCommandExecutorService;

class Dnd5eSystemModuleTest {

    @Test
    void shouldDelegateExecutionToDnd5eCommandExecutorService() {
        Dnd5eCommandExecutorService executorService = mock(Dnd5eCommandExecutorService.class);
        Dnd5eSystemModule module = new Dnd5eSystemModule(executorService);
        WorldContext worldContext = new WorldContext("client-1", "api-key", SystemId.DND5E);
        SystemCommand command = new SystemCommand(Dnd5eCommandNames.GET_ACTOR_DETAILS);

        when(executorService.execute(worldContext, command)).thenReturn("actor");

        Object result = module.execute(command, worldContext);

        assertThat(result).isEqualTo("actor");
        verify(executorService).execute(worldContext, command);
    }
}
