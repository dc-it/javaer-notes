package zk;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * zookeeper = 文件系统 + 监听通知机制
 * Zab协议   数据一致性
 * <p>
 * 存在问题：
 * 1、watcher注册是一次性的，每次处理完状态变化事件都要重新注册watcher
 * 2、经常遇见session expire会话过期异常，异常发生后需要重新连接，zookeeper api实现繁琐
 * 3、zookeeper api节点数据是二进制byte数组，需要进行序列化和反序列化工作
 * 解决上述问题，第三方客户端zkClient，封装了Zookeeper api
 *
 * @author duchao
 */
public class ZkDemo {

    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();
    private static String zNode="/username";
    private static String userNode="/users";

    /**
     * hello world 原生api
     *
     * @throws Exception
     */
    @Test
    public void testZk() throws Exception {
        zk = new ZooKeeper("182.92.202.68:8022", 50000, watchedEvent -> {
            if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                if (Watcher.Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                    latch.countDown();
                } else if (watchedEvent.getType() == Watcher.Event.EventType.NodeDataChanged) {  //zk目录节点数据变化通知事件
                    try {
                        System.out.println("配置已修改，新值为：" + new String(zk.getData(watchedEvent.getPath(), true, stat)));
                    } catch (Exception e) {
                    }
                }
            }
        });
        latch.await();

        //持久节点
        System.out.println(new String(zk.getData(zNode, true, stat)));

        //临时节点
        String tmpNode = zk.create("/tmp", "临时节点数据".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("创建临时节点：" + tmpNode);
        Thread.sleep(10000);
        Stat tmpNodeStat = zk.exists(tmpNode, false);
        System.out.println("临时节点" + tmpNode + (tmpNodeStat != null ? "被删除" : "存在"));
    }

    @Test
    public void testZkClient() throws Exception {
        ZkClient zkClient = new ZkClient("182.92.202.68:8022",5000);
        String childNode = userNode + "/child";
        if(!zkClient.exists(userNode)) {
            zkClient.createPersistent(userNode);
        }
        if(!zkClient.exists(childNode)){
            zkClient.create(childNode, "child znode", CreateMode.EPHEMERAL);
        }
        zkClient.subscribeChildChanges(userNode, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("子节点变化："+parentPath+"  "+currentChilds);
            }

        });
        List<String> children = zkClient.getChildren(userNode);
        System.out.println(children);
        zkClient.writeData(childNode,"hello zkClient");
        System.out.println(zkClient.readData(childNode).toString());
    }
}
