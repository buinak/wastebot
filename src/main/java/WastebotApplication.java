import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class WastebotApplication {

    private static final String TOKEN_VARIABLE_NAME = "WASTEBOT_TOKEN";

    public static void main(String[] args) {
        String token = System.getenv(TOKEN_VARIABLE_NAME);

        JDA botApi = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();

        botApi.addEventListener(new TestListener());
    }
}
