package eu.wastemen.wastebot.Common;

import eu.wastemen.wastebot.ModCommands.SpedCommandListener;
import eu.wastemen.wastebot.UserCommands.HelpCommandListener;
import eu.wastemen.wastebot.ModCommands.TestCommandListener;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BotCommand  {
    TEST_COMMAND("test", "test command",
            TestCommandListener.class, CommandPermission.REGULAR),
    HELP_COMMAND("help", "this command lists all available commands for the bot.",
            HelpCommandListener.class, CommandPermission.REGULAR),
    SPED_COMMAND("sped", "this command puts the user in the paralympic division of wastemen (prison)",
            SpedCommandListener.class, CommandPermission.MODERATOR);

    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private Class<? extends WastebotCommandListener> commandListenerAdapter;
    @Getter
    private CommandPermission minimumCommandPermissions;
}
