package jie.flyer.common.base.wrapper;

import com.google.common.collect.Lists;
import jie.flyer.common.util.CollectionUtils;
import jie.flyer.common.util.StringUtils;
import lombok.Data;

import java.util.List;

/**
 * 修改忽略字段（createTime、updateTime交与数据库自动更新，createBy只有创建时更新）
 *
 * @author kain
 * @since 2018-09-17
 */
@Data
public class ClassWrapper implements IClassWrapper {

    private Class<?> clazz;

    /**
     * 插入vs更新都需忽略的字段
     */
    private List<String> ignoreFields = Lists.newArrayList("createTime", "updateTime");

    /**
     * 更新需要额外忽略的字段，如createTime或createBy等字段
     */
    private List<String> ignoreUpdateFields = Lists.newArrayList("createBy");

    public ClassWrapper(Class<?> clazz) {
        this.clazz = clazz;
    }

    public ClassWrapper ignoreFields(List<String> ignoreFields) {
        List<String> trans = CollectionUtils.map(ignoreFields, StringUtils::camelHump);
        this.ignoreFields.addAll(trans);
        return this;
    }

    public ClassWrapper ignoreUpdateFields(List<String> ignoreUpdate) {
        List<String> trans = CollectionUtils.map(ignoreUpdate, StringUtils::camelHump);
        this.ignoreUpdateFields.addAll(trans);
        return this;
    }
}
