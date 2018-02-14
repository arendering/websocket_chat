package settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    public int APP_PORT;
    public String DB_NAME;
    public String DB_USER;
    public String DB_PASSWORD;
    public int DB_PORT;
    public String DB_HOST;

    private static Settings settings;

    public static Settings instance() {
        if (settings == null)
            settings = new Settings();
        return settings;
    }

    private Settings() {
        Properties properties = new Properties();
        try {
            InputStream is = new FileInputStream("config/config.properties");
            properties.load(is);
            APP_PORT = Integer.parseInt(properties.getProperty("APP_PORT"));
            DB_NAME = properties.getProperty("DB_NAME");
            DB_USER = properties.getProperty("DB_USER");
            DB_PASSWORD = properties.getProperty("DB_PASSWORD");
            DB_PORT = Integer.parseInt(properties.getProperty("DB_PORT"));
            DB_HOST = properties.getProperty("DB_HOST");
            setDefaultValuesIfNull();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDefaultValuesIfNull() {
        if (APP_PORT == 0)
            APP_PORT = 8000;

        if (DB_NAME == null)
            DB_NAME = "webchat_users_db";

        if (DB_USER == null)
            DB_USER = "root";

        if (DB_PASSWORD == null)
            DB_PASSWORD = "root";

        if (DB_PORT == 0)
            DB_PORT = 3306;

        if (DB_HOST == null)
            DB_HOST="localhost";
    }
}
