package com.github.joponie.flyer.pay.domain;

import com.github.joponie.flyer.portal.dal.dao.OrderMapper;
import com.github.joponie.flyer.portal.dal.dao.PointRecordMapper;
import com.github.joponie.flyer.portal.dal.model.Order;
import com.github.joponie.flyer.portal.dal.model.PointRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author kain
 * @since 2020-03-03
 */
@Service("payService")
@Slf4j
public class PayService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PointRecordMapper pointRecordMapper;

    /**
     * 支付功能：
     *  如果支付成功 则下游业务 也就是积分服务对应的账号需要增加积分
     *  如果支付失败，则下游业务无感知
     */
    @Transactional(rollbackFor = Exception.class)
    public void pay(String orderNo, Integer buyerId) {
        // 1、构造积分添加记录表
        PointRecord record = new PointRecord();
        record.setOrderNo(orderNo);
        record.setUserId(buyerId);
        // 2、存入数据库
        pointRecordMapper.insert(record);
        // 3、修改订单状态 为已支付
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setBuyerId(buyerId);
        //4、 更新订单信息
        orderMapper.updateOrder(order);

        log.info("执行本地事务，pay() ");
    }

    public Boolean checkPayStatus(String orderNo) {
        // 根据判断是否有PointRecord这个记录来 确实是否支付成成功 用于事务回查判断本地事务是否执行成功
        return Objects.nonNull(pointRecordMapper.getPointRecordByOrderNo(orderNo));
    }
}
