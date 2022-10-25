package wastemen.wastebot.Commands;

import wastemen.wastebot.Common.BotCommand;
import wastemen.wastebot.Common.CommandListenerAdapter;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class TestCommandListener extends CommandListenerAdapter {

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
