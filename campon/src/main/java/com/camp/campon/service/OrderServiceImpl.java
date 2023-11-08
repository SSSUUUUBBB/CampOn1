package com.camp.campon.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.campon.dto.Order;
import com.camp.campon.mapper.OrderMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order selectOrder(String orderNumber) throws Exception {
        Order order = orderMapper.selectOrder(orderNumber);
        Date orderDate = order.getOrderDate();
        Calendar cal = Calendar.getInstance(); 
		cal.setTime(orderDate);
        cal.add(Calendar.DATE, 3);
        Date depositDeadLine = new Date(cal.getTimeInMillis());
        log.info("입금기한 : "+depositDeadLine);
        order.setDepositDeadLine(depositDeadLine);
        return order;
    }

    @Override
    public Order paymentsByOrNo(String orderNumber) throws Exception {
        Order order = orderMapper.paymentsByOrNo(orderNumber);
        return order;
    }
    
}
