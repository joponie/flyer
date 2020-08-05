package jie.flyer.portal.dal.model;

import lombok.Data;

import java.util.Date;

/**
 * @author kain
 * @since 2020-03-03
 */
@Data
public class Order {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 买家id
     */
    private Integer buyerId;

    /**
     * 支付状态 0 已支付 1 未支付 2 已超时
     */
    private Integer payStatus;

    /**
     * 下单日期
     */
    private Date createDate;

    /**
     * 金额
     */
    private Long amount;

}
