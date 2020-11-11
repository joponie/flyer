package jie.flyer.common.base.wrapper;

import java.util.List;

/**
 * @author kain
 * @since 2018-09-17
 */
public interface IClassWrapper {
    /**
     * 获取class
     *
     * @return Class
     */
    Class<?> getClazz();

    /**
     * 忽略字段列表
     *
     * @return ignoreFields
     */
    List<String> getIgnoreFields();

    /**
     * 更新忽略字段列表
     *
     * @return ignoreUpdateFields
     */
    List<String> getIgnoreUpdateFields();
}
