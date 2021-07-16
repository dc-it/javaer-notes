package com.dc.ssj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.ssj.entity.Order;
import com.dc.ssj.mapper.OrderMapper;
import com.dc.ssj.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 订单服务，业务逻辑
 *
 * @author duchao
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
