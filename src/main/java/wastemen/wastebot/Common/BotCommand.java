package wastemen.wastebot.Common;

import wastemen.wastebot.Commands.HelpCommandListener;
import wastemen.wastebot.Commands.TestCommandListener;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BotCommand  {
    TEST_COMMAND("test", "test command", TestCommandListener.class),
    HELP_COMMAND("help", "this command lists all available commands for the bot.", HelpCommandListener.class);

    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private Class<? extends CommandListenerAdapter> commandListenerAdapter;
}
