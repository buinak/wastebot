package eu.wastemen.wastebot.ModCommands;

import eu.wastemen.wastebot.Common.BotCommand;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import eu.wastemen.wastebot.Common.WastebotCommandListener;

public class SpedCommandListener extends WastebotCommandListener {
    public SpedCommandListener(BotCommand botCommand) {
        super(botCommand);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event, User author, MessageChannel channel, String content) {
        String[] messageParts = content.split(" ");
        if (messageParts.length < 2){
            channel.sendMessage("invalid format").queue();
            return;
        }

        String userId = messageParts[messageParts.length - 1].replaceAll("[^\\d.]", "");
        Guild guild = event.getGuild();
        Role spedRole = guild.getRolesByName("paralympic champion", false).get(0);
        if (messageParts.length == 2){
            guild.addRoleToMember(UserSnowflake.fromId(userId), spedRole).queue();
            channel.sendMessage(String.format("put %s into sped", guild.getMemberById(userId).getNickname())).queue();
        } else if (messageParts[1].equals("remove")) {
            guild.removeRoleFromMember(UserSnowflake.fromId(userId), spedRole).queue();
            channel.sendMessage(String.format("removed %s from sped", guild.getMemberById(userId).getNickname())).queue();
        } else {
            channel.sendMessage("invalid format").queue();
            return;
        }
    }
}
