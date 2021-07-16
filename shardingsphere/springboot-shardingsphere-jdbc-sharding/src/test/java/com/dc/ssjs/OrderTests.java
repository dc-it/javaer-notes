package com.dc.ssjs;

import com.dc.ssjs.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 订单测试
 *
 * @author duchao
 */
@SpringBootTest
public class OrderTests {

    @Autowired
    private OrderService orderService;

    /**
     * 测试数据分片-分库分表
     */
    @Test
    void testSharding() {
//        orderService.saveBatch(CollectionUtil.toList(
//                new Order() {{
//                    setUserId(3333L);
//                }},
//                new Order() {{
//                    setUserId(4444L);
//                }}
//        ));
        System.out.println(orderService.list());
    }
}
