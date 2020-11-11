package jie.flyer.common.base.wrapper;

import com.google.common.collect.Lists;
import jie.flyer.common.util.CollectionUtils;
import jie.flyer.common.util.StringUtils;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * 修改忽略字段（createTime、updateTime交与数据库自动更新，createBy只有创建时更新）
 *
 * @author kain
 * @since 2018-09-17
 */
@Data
public class SelectClassWrapper implements IClassWrapper {
    private Class<?> clazz;

    /**
     * 插入vs更新都需忽略的字段
     */
    private List<String> ignoreFields = Lists.newArrayList();


    public SelectClassWrapper(Class<?> clazz) {
        this.clazz = clazz;
    }

    public SelectClassWrapper ignoreFields(List<String> ignoreFields) {
        List<String> trans = CollectionUtils.map(ignoreFields, StringUtils::camelHump);
        this.ignoreFields.addAll(trans);
        return this;
    }

    @Override
    public List<String> getIgnoreUpdateFields() {
        return Collections.emptyList();
    }
}
