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
        Member member = guild.getMemberById(userId);
        toggleSpedRole(channel, messageParts, member, guild, spedRole);
    }

    private void toggleSpedRole(MessageChannel channel, String[] messageParts, Member member, Guild guild, Role spedRole) {
        String memberName = member.getUser().getName();
        UserSnowflake userSnowflake = UserSnowflake.fromId(member.getId());
        if (messageParts.length == 2){
            if (member.getRoles().contains(spedRole)){
                channel.sendMessage(String.format("User %s already has sped role", memberName)).queue();
                return;
            }
            guild.addRoleToMember(userSnowflake, spedRole).queue();
            channel.sendMessage(String.format("Put %s into sped", memberName)).queue();
        } else if (messageParts[1].equals("remove")) {
            if (!member.getRoles().contains(spedRole)){
                channel.sendMessage(String.format("User %s does not have sped role", memberName)).queue();
                return;
            }
            guild.removeRoleFromMember(userSnowflake, spedRole).queue();
            channel.sendMessage(String.format("Removed %s from sped", memberName)).queue();
        } else {
            channel.sendMessage("Invalid format").queue();
        }
    }
}
