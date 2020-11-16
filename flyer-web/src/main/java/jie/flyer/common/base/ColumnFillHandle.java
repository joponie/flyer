package jie.flyer.common.base;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.handlers.StrictFill;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.reflection.MetaObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Author kain
 * @Date 2020/11/13
 **/
public class ColumnFillHandle implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date current = new Date();
        List<StrictFill> strictFills = new ArrayList<>();
        strictFills.add(StrictFill.of("createTime", Date.class, current));
        strictFills.add(StrictFill.of("updateTime", Date.class, current));
        this.strictInsertFill(findTableInfo(metaObject), metaObject, strictFills);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date current = new Date();
        List<StrictFill> strictFills = new ArrayList<>();
        strictFills.add(StrictFill.of("updateTime", Date.class, current));
        updateLooseFill(findTableInfo(metaObject), metaObject, strictFills);
    }

    /**
     * 填充策略,覆盖原有的值
     */
    private void updateLooseFill(TableInfo tableInfo, MetaObject metaObject, List<StrictFill> strictFills) {
        if ((tableInfo.isWithUpdateFill())) {
            strictFills.forEach(i -> {
                final String fieldName = i.getFieldName();
                tableInfo.getFieldList().stream()
                        .filter(j -> j.getProperty().equals(fieldName) && i.getFieldType().equals(j.getPropertyType()) &&
                                ((j.isWithUpdateFill()))).findFirst()
                        .ifPresent(j -> LooseFillStrategy(metaObject, fieldName, i.getFieldVal()));
            });
        }
    }

    public void LooseFillStrategy(MetaObject metaObject, String fieldName, Supplier<Object> fieldVal) {
        setFieldValByName(fieldName, fieldVal.get(), metaObject);
    }
}

