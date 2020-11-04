package jie.flyer.common.base;

import jie.flyer.common.base.wrapper.ClassEntityMapper;
import jie.flyer.common.base.wrapper.ClassWrapper;
import jie.flyer.common.base.wrapper.DaoConstants;
import jie.flyer.common.util.CollectionUtils;
import jie.flyer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 语句模板类
 *
 * @author kain
 * @since 2018-09-17
 */
@Slf4j
public class SQLTemplate<T extends BaseModel> {
    final static String LIST_VALUE_PATTERN = "#{%s.%s}";

    private String tableName(Class<?> tClass) {
        return StringUtils.underLine(tClass.getSimpleName());
    }

    /**
     * 批量插入语句，没有用batch
     */
    @SuppressWarnings("unchecked")
    public String insertAll(Map<String, Object> params) {
        List<T> list = (List<T>) params.get("list");
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("list can't be empty");
        }
        ClassEntityMapper mapper;
        if (params.containsKey(DaoConstants.CLASS_WRAPPER)) {
            ClassWrapper classWrapper = (ClassWrapper) params.get(DaoConstants.CLASS_WRAPPER);
            mapper = new ClassEntityMapper(classWrapper);
        } else {
            mapper = new ClassEntityMapper(list.get(0).getClass());
        }

        List<String> valuesSql = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final String listIndexStr = String.format("list[%s]", i);
            List<String> attributes = CollectionUtils.map(mapper.allNormal(), from ->
                    String.format(LIST_VALUE_PATTERN, listIndexStr, from));
            valuesSql.add(String.format("(%s)", StringUtils.join(attributes, ",")));
        }

        String sql = String.format("INSERT INTO %s(%s) VALUES%s", tableName(mapper.getClazz()),
                StringUtils.join(mapper.allDb(), ","), StringUtils.join(valuesSql, ","));
        log.debug("sql:{}", sql);
        return sql;
    }

    @SuppressWarnings("unchecked")
    public String updateAllFields(Map<String, Object> param) {
        T entity = (T) param.get("cm");
        ClassEntityMapper mapper = new ClassEntityMapper(entity.getClass());
        String updateBody = mapper.updateCols().stream().map(normalCol ->
                String.format("%s=#{cm.%s}", mapper.dbCol(normalCol), normalCol))
                .collect(Collectors.joining(","));
        String sql = String.format("UPDATE %s SET %s WHERE id = #{cm.id}",
                tableName(mapper.getClazz()), updateBody);
        log.debug("sql:{}", sql);
        return sql;
    }

    @SuppressWarnings("unchecked")
    public String insertOrUpdateAll(Map<String, Object> params) {
        List<T> list = (List<T>) params.get("list");
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("list can't be empty");
        }
        ClassEntityMapper mapper = new ClassEntityMapper((ClassWrapper) params.get(DaoConstants.CLASS_WRAPPER));

        List<String> valuesSql = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final String listIndexStr = String.format("list[%s]", i);
            List<String> attributes = CollectionUtils.map(mapper.allNormal(), from ->
                    String.format(LIST_VALUE_PATTERN, listIndexStr, from));
            valuesSql.add(String.format("(%s)", StringUtils.join(attributes, ",")));
        }
        String sql = String.format("INSERT INTO %s(%s) VALUES%s ON DUPLICATE KEY UPDATE %s", tableName(mapper.getClazz()),
                StringUtils.join(mapper.allDb(), ","), StringUtils.join(valuesSql, ","),
                StringUtils.join(CollectionUtils.map(mapper.updateDbs(), key -> key + "= VALUES(" + key + ")"), ","));
        log.debug("sql:{}", sql);
        return sql;
    }
}
