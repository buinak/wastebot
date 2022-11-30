package eu.wastemen.wastebot.ModCommands;

import eu.wastemen.wastebot.Common.BotCommand;
import eu.wastemen.wastebot.Common.WastebotCommandListener;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PicPermsCommandListener extends WastebotCommandListener {
    public PicPermsCommandListener(BotCommand botCommand) {
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
        Role spedRole = guild.getRolesByName("temporary pic perms", false).get(0);
        Member member = guild.getMemberById(userId);
        toggleSpedRole(channel, messageParts, member, guild, spedRole);
    }

    private void toggleSpedRole(MessageChannel channel, String[] messageParts, Member member, Guild guild, Role picPermsRole) {
        String memberName = member.getUser().getName();
        UserSnowflake userSnowflake = UserSnowflake.fromId(member.getId());
        if (messageParts.length == 2){
            if (member.getRoles().contains(picPermsRole)){
                channel.sendMessage(String.format("User %s already has pic perms role", memberName)).queue();
                return;
            }
            guild.addRoleToMember(userSnowflake, picPermsRole).queue();
            channel.sendMessage(String.format("Put %s into sped", memberName)).queue();
        } else if (messageParts[1].equals("remove")) {
            if (!member.getRoles().contains(picPermsRole)){
                channel.sendMessage(String.format("User %s does not have pic perms role", memberName)).queue();
                return;
            }
            guild.removeRoleFromMember(userSnowflake, picPermsRole).queue();
            channel.sendMessage(String.format("Removed pic perms role from %s", memberName)).queue();
        } else {
            channel.sendMessage("Invalid format").queue();
        }
    }
}
