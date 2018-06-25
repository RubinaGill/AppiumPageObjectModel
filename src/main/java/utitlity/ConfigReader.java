package utitlity;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class will read config.properties file stored under src/main/resources
 */
public class ConfigReader {
    private static Logger logger = Logger.getLogger(ConfigReader.class);

    /**
     * Use this method to parse properties file and get the desired property
     *
     * @param propertyName, name of property whose value is needed
     * @return value of property passed as propertyName
     */
    public static String getProperty(String propertyName) {
        Properties config = new Properties();
        try {
            InputStream iStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            config.load(iStream);
        } catch (IOException e) {
            logger.error("unable to read property " + propertyName + " from file");
        }
        return (config.getProperty(propertyName));
    }
}
