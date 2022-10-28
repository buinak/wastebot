package eu.wastemen.wastebot.ModCommands;

import eu.wastemen.wastebot.Common.BotCommand;
import eu.wastemen.wastebot.Common.WastebotCommandListener;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class TestCommandListener extends WastebotCommandListener {

    public TestCommandListener(BotCommand botCommand) {
        super(botCommand);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event,
                                  User author,
                                  MessageChannel channel,
                                  String content) {
        channel.sendMessage("test").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
    }
}
