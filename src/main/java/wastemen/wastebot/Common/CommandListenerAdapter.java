package wastemen.wastebot.Common;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import wastemen.wastebot.Core.WastebotApi;

public abstract class CommandListenerAdapter extends ListenerAdapter {

    protected final String commandName;
    protected final String commandDescription;

    protected CommandListenerAdapter(BotCommand botCommand) {
        this.commandName = botCommand.getName();
        this.commandDescription = botCommand.getDescription();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Message message = event.getMessage();
        User author = event.getAuthor();
        String content = message.getContentRaw();

        if (author.isBot() || !content.equals(WastebotApi.COMMAND_PREFIX + commandName)){
            return;
        }

        onMessageReceived(event, author, channel, content);
    }

    public abstract void onMessageReceived(MessageReceivedEvent event,
                                           User author,
                                           MessageChannel channel,
                                           String content);
}
