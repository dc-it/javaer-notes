package cache;

import net.spy.memcached.MemcachedClient;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Memcached
 *
 * @author duchao
 */
public class MemcacheDemo {

    @Test
    public void test() {
        try {
            MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress(MemcacheProperties.IP, MemcacheProperties.PORT));
            memcachedClient.set("username", 1000, "duchao");
            System.out.println(memcachedClient.get("username"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
