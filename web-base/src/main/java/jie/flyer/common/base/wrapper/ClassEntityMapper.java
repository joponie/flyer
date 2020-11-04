package jie.flyer.common.base.wrapper;

import com.baomidou.mybatisplus.annotation.TableField;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import jie.flyer.common.util.ClassUtils;
import jie.flyer.common.util.CollectionUtils;
import jie.flyer.common.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

/**
 * @author kain
 * @since 2018-09-17
 */
public class ClassEntityMapper implements NormalDbColMapper {
    private final BiMap<String, String> fieldMap;
    private final BiMap<String, String> updateMap;
    private final Class<?> clazz;

    public ClassEntityMapper(Class<?> clazz) {
        this(new ClassWrapper(clazz));
    }

    public ClassEntityMapper(IClassWrapper wrapper) {
        this.clazz = wrapper.getClazz();
        List<String> ignoreFields = wrapper.getIgnoreFields();
        fieldMap = HashBiMap.create();
        List<Field> fields = ClassUtils.getAllFields(clazz);
        fields = CollectionUtils.filter(fields, field -> ignoreFields == null || !ignoreFields.contains(field.getName()));
        for (Field field : fields) {
            String fieldName = field.getName();
            TableField tableField = field.getAnnotation(TableField.class);
            if (tableField == null) {
                fieldMap.put(fieldName, completeColName(StringUtils.underLine(field.getName())));
            } else if (tableField.exist()) {
                fieldMap.put(fieldName, completeColName(tableField.value()));
            }
        }
        this.updateMap = HashBiMap.create(fieldMap);
        CollectionUtils.forEach(wrapper.getIgnoreUpdateFields(), this.updateMap::remove);
    }

    @Override
    public String dbCol(String normalCol) {
        String dbCol = fieldMap.get(normalCol);
        if (dbCol == null) {
            throw new IllegalArgumentException(String.format("不存在该字段[%s]的映射", normalCol));
        }
        return dbCol;
    }

    @Override
    public String normalCol(String dbCol) {
        String normalCol = fieldMap.inverse().get(dbCol);
        if (normalCol == null) {
            throw new IllegalArgumentException(String.format("不存在该数据库字段[%s]的映射", dbCol));
        }
        return normalCol;
    }

    @Override
    public Collection<String> allNormal() {
        return fieldMap.keySet();
    }

    @Override
    public Collection<String> updateCols() {
        return this.updateMap.keySet();
    }

    @Override
    public Collection<String> allDb() {
        return fieldMap.values();
    }

    @Override
    public Collection<String> updateDbs() {
        return this.updateMap.values();
    }

    public Class<?> getClazz() {
        return clazz;
    }

    private String completeColName(String colName) {
        return "`" + colName + "`";
    }
}
