package eu.wastemen.wastebot.Common;

import eu.wastemen.wastebot.ModCommands.SpedCommandListener;
import eu.wastemen.wastebot.UserCommands.HelpCommandListener;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BotCommand  {
    HELP_COMMAND("help", "this command lists all available commands for the bot",
            HelpCommandListener.class, CommandPermission.REGULAR),
    SPED_COMMAND("sped", "this command puts the user in prison",
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
