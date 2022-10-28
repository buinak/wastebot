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
        StringBuilder message = new StringBuilder("All the available commands are:" + System.lineSeparator());
        for (BotCommand botCommand: BotCommand.values()) {
            message.append(String.format("%s%s: %s", WastebotApi.COMMAND_PREFIX, botCommand.getName(), botCommand.getDescription()));
            message.append(System.lineSeparator());
        }

        message.append(System.lineSeparator());
        message.append(String.format("The command prefix is \"%s\"", WastebotApi.COMMAND_PREFIX));

        channel.sendMessage(message.toString()).queue();
    }
}
