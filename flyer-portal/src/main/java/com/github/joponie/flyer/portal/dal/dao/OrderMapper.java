package com.github.joponie.flyer.portal.dal.dao;

import com.github.joponie.flyer.common.base.BaseMapper;
import com.github.joponie.flyer.portal.dal.model.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kain
 * @since 2020-03-03
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    void updateOrder(Order order);

}
