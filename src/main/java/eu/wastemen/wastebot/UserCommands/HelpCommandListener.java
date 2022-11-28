package eu.wastemen.wastebot.UserCommands;

import eu.wastemen.wastebot.Common.BotCommand;
import eu.wastemen.wastebot.Core.WastebotApi;
import eu.wastemen.wastebot.Common.WastebotCommandListener;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommandListener extends WastebotCommandListener {

    public HelpCommandListener(BotCommand botCommand) {
        super(botCommand);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event, User author, MessageChannel channel, String content) {
        StringBuilder message = new StringBuilder();
        message.append(String.format("To run help on a specific command, use \"%s%s COMMAND_NAME\", for example \"%s%s sped\""
        ,       WastebotApi.COMMAND_PREFIX, commandName,
                WastebotApi.COMMAND_PREFIX, commandName)).append(System.lineSeparator());
        message.append("All the available commands are:").append(System.lineSeparator());
        for (BotCommand botCommand: BotCommand.values()) {
            message
                    .append(String.format("%s%s: %s", WastebotApi.COMMAND_PREFIX, botCommand.getName(), botCommand.getDescription()))
                    .append(System.lineSeparator());
        }

        message.append(System.lineSeparator())
               .append(String.format("The command prefix is \"%s\"", WastebotApi.COMMAND_PREFIX));

        channel.sendMessage(message.toString()).queue();
    }
}
