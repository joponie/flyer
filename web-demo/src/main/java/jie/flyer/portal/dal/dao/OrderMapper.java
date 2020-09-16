package jie.flyer.portal.dal.dao;

import jie.flyer.common.base.IMapper;
import jie.flyer.portal.dal.model.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kain
 * @since 2020-03-03
 */
@Mapper
public interface OrderMapper extends IMapper<Order> {

    void updateOrder(Order order);

}
