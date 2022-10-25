package wastemen.wastebot.Common;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import wastemen.wastebot.Core.WastebotApi;

import java.util.*;
import java.util.stream.Collectors;

public abstract class WastebotCommandListener extends ListenerAdapter {

    protected final String commandName;
    protected final String commandDescription;
    protected final CommandPermission minimumCommandPermission;

    protected WastebotCommandListener(BotCommand botCommand) {
        this.commandName = botCommand.getName();
        this.commandDescription = botCommand.getDescription();
        this.minimumCommandPermission = botCommand.getMinimumCommandPermissions();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Message message = event.getMessage();
        User author = event.getAuthor();
        String content = message.getContentRaw();

        if (author.isBot() || !content.startsWith(WastebotApi.COMMAND_PREFIX + commandName)) {
            return;
        }

        if (!passesMinimumRole(event)) {
            channel.sendMessage(String.format("not enough permissions, permissions required are %s", minimumCommandPermission)).queue();
            return;
        }

        onMessageReceived(event, author, channel, content);
    }

    private boolean passesMinimumRole(MessageReceivedEvent event) {
        if (minimumCommandPermission == CommandPermission.REGULAR) {
            return true;
        }

        Guild guild = event.getGuild();
        List<Role> allowedRoles = minimumCommandPermission.getUserRoles().stream()
                .map(userRole -> guild.getRolesByName(userRole.getRoleName(), false))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        String tag = event.getAuthor().getAsTag();
        List<Role> memberRoles = guild.getMemberByTag(tag).getRoles();
        for (Role allowedRole : allowedRoles) {
            if (memberRoles.contains(allowedRole)){
                return true;
            }
        }

        return false;
    }

    public abstract void onMessageReceived(MessageReceivedEvent event,
                                           User author,
                                           MessageChannel channel,
                                           String content);
}
