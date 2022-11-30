package eu.wastemen.wastebot.Common;

import eu.wastemen.wastebot.ModCommands.PicPermsCommandListener;
import eu.wastemen.wastebot.ModCommands.SpedCommandListener;
import eu.wastemen.wastebot.UserCommands.HelpCommandListener;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BotCommand  {
    HELP_COMMAND("help", "this command lists all available commands for the bot",
            HelpCommandListener.class, CommandPermission.REGULAR),
    SPED_COMMAND("sped", "this command puts the user in prison",
            SpedCommandListener.class, CommandPermission.MODERATOR),
    PIC_PERMS_COMMAND("picperms", "this command gives the user pic perms",
            PicPermsCommandListener.class, CommandPermission.MODERATOR);

    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private Class<? extends WastebotCommandListener> commandListenerAdapter;
    @Getter
    private CommandPermission minimumCommandPermissions;
}
