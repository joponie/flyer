package com.github.joponie.flyer.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 刘杰鹏
 * @since 2019-11-04
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseModel> implements IBaseService<T> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected M mapper;

    @Override
    public void logicDel(Integer id) {
        throw new UnsupportedOperationException("unsupported operation for logicDel");
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
        mapper.insert(record);
        return record.getId();
    }

    @Override
    public void addAll(List<T> records) {

    }

    @Override
    public T findById(Integer id) {
        return mapper.selectById(id);
    }
}
