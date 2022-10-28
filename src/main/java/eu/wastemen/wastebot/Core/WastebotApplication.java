package eu.wastemen.wastebot.Core;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WastebotApplication {

    private static final String TOKEN_VARIABLE_NAME = "WASTEBOT_TOKEN";

    public static void main(String[] args) {
        String token = System.getenv(TOKEN_VARIABLE_NAME);

        WastebotApi.startBot(token);
    }
}
