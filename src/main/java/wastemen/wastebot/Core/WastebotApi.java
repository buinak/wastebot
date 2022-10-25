package wastemen.wastebot.Core;

import wastemen.wastebot.Common.BotCommand;
import wastemen.wastebot.Common.CommandListenerAdapter;
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
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();

        Arrays.asList(BotCommand.values()).forEach(botCommand -> {
            log.info("Registering {} as a listener", botCommand.getCommandListenerAdapter().getName());
            try {
                CommandListenerAdapter commandListenerAdapter = (CommandListenerAdapter) botCommand.getCommandListenerAdapter()
                        .getDeclaredConstructors()[0]
                        .newInstance(botCommand);

                botApi.addEventListener(commandListenerAdapter);

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
