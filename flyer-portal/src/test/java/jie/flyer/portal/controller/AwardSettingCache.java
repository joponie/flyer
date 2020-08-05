package jie.flyer.portal.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author kain
 * @since 2019-11-22
 */
@Getter
@Setter
@NoArgsConstructor
public class AwardSettingCache {

    /**
     * 奖项设置
     */
    private Integer id;

    /**
     * 周期内投放数量
     */
    private Integer num;

    /**
     * 奖项概率
     */
    private BigDecimal awardRate;

    /**
     * 奖品名称
     */
    private String awardName;
}
