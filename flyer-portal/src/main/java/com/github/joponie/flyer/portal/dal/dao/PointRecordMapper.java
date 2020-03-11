package com.github.joponie.flyer.portal.dal.dao;

import com.github.joponie.flyer.common.base.BaseMapper;
import com.github.joponie.flyer.portal.dal.model.PointRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 刘杰鹏
 * @since 2020-03-03
 */
@Mapper
public interface PointRecordMapper extends BaseMapper<PointRecord> {
    Object getPointRecordByOrderNo(String orderNo);
}
