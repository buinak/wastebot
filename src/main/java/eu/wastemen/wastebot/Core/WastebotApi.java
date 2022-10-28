package eu.wastemen.wastebot.Core;

import eu.wastemen.wastebot.Common.BotCommand;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import eu.wastemen.wastebot.Common.WastebotCommandListener;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@Slf4j
public class WastebotApi {

    public final static String COMMAND_PREFIX = "!!";

    public static void startBot(String token){
        JDA botApi = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setChunkingFilter(ChunkingFilter.ALL)
                .build();

        Arrays.asList(BotCommand.values()).forEach(botCommand -> {
            log.info("Registering {} as a listener", botCommand.getCommandListenerAdapter().getName());
            try {
                WastebotCommandListener wastebotCommandListener = (WastebotCommandListener) botCommand.getCommandListenerAdapter()
                        .getDeclaredConstructors()[0]
                        .newInstance(botCommand);

                botApi.addEventListener(wastebotCommandListener);

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
