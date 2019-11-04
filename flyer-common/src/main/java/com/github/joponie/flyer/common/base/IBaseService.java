package com.github.joponie.flyer.common.base;

import java.util.List;

/**
 * @author 刘杰鹏
 * @since 2019-11-04
 */
public interface IBaseService<T> {
    /**
     * 逻辑删除
     */
    void logicDel(Integer id);

    /**
     * 真实删除
     */
    Integer delete(Integer id);

    /**
     * 更新数据
     */
    Integer update(T record);

    /**
     * 增加数据
     */
    Integer add(T record);

    /**
     * 批量添加元素
     */
    void addAll(List<T> records);

    /**
     * 单条数据
     */
    T findById(Integer id);
}
