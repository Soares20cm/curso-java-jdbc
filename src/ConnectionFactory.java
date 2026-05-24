import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {

    private static final String DEFAULT_URL = "jdbc:postgresql://localhost:5432/cadastro";
    private static final String DEFAULT_USER = "postgres";
    private static final String DEFAULT_PASSWORD = "postgres";

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        String url = getConfig("DB_URL", "db.url", DEFAULT_URL);
        String user = getConfig("DB_USER", "db.user", DEFAULT_USER);
        String password = getConfig("DB_PASSWORD", "db.password", DEFAULT_PASSWORD);
        return DriverManager.getConnection(url, user, password);
    }

    private static String getConfig(String envName, String propertyName, String defaultValue) {
        String propertyValue = System.getProperty(propertyName);
        if (propertyValue != null && !propertyValue.isBlank()) {
            return propertyValue;
        }

        String envValue = System.getenv(envName);
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }

        return defaultValue;
    }
}
