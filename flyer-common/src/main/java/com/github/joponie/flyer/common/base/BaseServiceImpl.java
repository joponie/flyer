package com.github.joponie.flyer.common.base;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author 刘杰鹏
 * @since 2019-11-04
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseModel> implements IBaseService<T> {

    @Autowired
    protected M mapper;

    @Override
    public void logicDel(Integer id) {
        T record = findById(id);
        if (record == null) {
            return;
        }
        if (record instanceof UpdatableModel) {
            Date now = new Date();
            UpdatableModel updatableModel = (UpdatableModel) record;
            updatableModel.setCreateTime(now);
            updatableModel.setUpdateTime(now);
            updatableModel.setDelFlg(1);
            update(record);
        }
    }

    @Override
    public Integer delete(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public Integer update(T record) {
        return mapper.updateById(record);
    }

    @Override
    public Integer add(T record) {
        return mapper.insert(record);
    }

    @Override
    public void addAll(List<T> records) {

    }

    @Override
    public T findById(Integer id) {
        return mapper.selectById(id);
    }
}
