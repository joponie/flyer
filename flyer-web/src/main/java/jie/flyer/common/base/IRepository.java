package jie.flyer.common.base;

/**
 * repository
 *
 * @Author kain
 * @Date 2020/9/16
 **/
public interface IRepository<T> {
    /**
     * 增加数据
     */
    Integer add(T record);

    /**
     * 真实删除
     */
    Integer delete(Integer id);

    /**
     * 更新数据
     */
    Integer update(T record);

    /**
     * 单条数据
     */
    T byId(Integer id);
}
