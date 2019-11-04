package com.github.joponie.flyer.common.base;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author 刘杰鹏
 * @since 2019-11-04
 */
public abstract class UpdatableServiceImpl<M extends BaseMapper<T>, T extends UpdatableModel> extends BaseServiceImpl<M, T> {

    @Autowired
    protected M mapper;

    @Override
    public void logicDel(Integer id) {
        T record = findById(id);
        if (record == null) {
            return;
        }
        Date now = new Date();
        record.setUpdateTime(now);
        record.setDelFlg(1);
        update(record);
    }

    @Override
    public Integer add(T record) {
        Date now = new Date();
        record.setCreateTime(now);
        record.setUpdateTime(now);
        record.setDelFlg(0);
        return super.add(record);
    }
}
