package wastemen.wastebot.ModCommands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import wastemen.wastebot.Common.BotCommand;
import wastemen.wastebot.Common.WastebotCommandListener;

public class SpedCommandListener extends WastebotCommandListener {
    public SpedCommandListener(BotCommand botCommand) {
        super(botCommand);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event, User author, MessageChannel channel, String content) {
        channel.sendMessage("passes role").queue();
    }
}
