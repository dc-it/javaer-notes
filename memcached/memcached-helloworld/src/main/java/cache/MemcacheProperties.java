package cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取resources下memcache.properties配置
 *
 * @author duchao
 */
public class MemcacheProperties {

    public static String IP;
    public static int PORT;

    static {
        try {
            InputStream stream = MemcacheProperties.class.getClassLoader().getResourceAsStream("memcache.properties");

            Properties properties = new Properties();
            properties.load(stream);

            IP = properties.getProperty("ip");
            PORT = Integer.parseInt(properties.getProperty("port"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
