package core.pageobject.Utils;

import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties PROPS = new Properties();

    static {
        try {
            PROPS.load(Config.class.getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static final String URL = get("gmail.url");
    public static final String SENDER_EMAIL = get("sender.email");
    public static final String SENDER_PASSWORD = get("sender.password");
    public static final String RECEIVER_EMAIL = get("receiver.email");
    public static final String RECEIVER_PASSWORD = get("receiver.password");

    public static String get(String key) {
        if (!PROPS.containsKey(key)) {
            throw new IllegalStateException("Required configuration property " + key + " is not found.");
        }

        return PROPS.getProperty(key);
    }

}
