package jie.flyer.common.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jie.flyer.common.base.wrapper.ClassWrapper;
import jie.flyer.common.base.wrapper.DaoConstants;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * mapper
 *
 * @author kain
 * @since 2019-11-04
 */
public interface IMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入元素
     *
     * @param list list
     */
    @InsertProvider(type = SQLTemplate.class, method = "insertAll")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    Integer insertAll(@Param("list") List<T> list);

    /**
     * 批量插入数据，忽略有些字段
     *
     * @param list         要插入的实体集合
     * @param classWrapper 里面包含数据库实体Class和忽略的字段IgnoreFields
     */
    @InsertProvider(type = SQLTemplate.class, method = "insertAll")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    <O extends T> Integer insertAllIgnoreFields(@Param("list") List<O> list, @Param(DaoConstants.CLASS_WRAPPER) ClassWrapper classWrapper);

    /**
     * 更新所有字段（包含为NULL的字段）
     *
     * @param param
     * @return
     */
    @UpdateProvider(type = SQLTemplate.class, method = "updateAllFields")
    Integer updateAllFields(@Param("cm") T param);

    /**
     * 插入或更新（当唯一索引冲突时）数据
     *
     * @param records      要插入或更新的数据集合
     * @param classWrapper 里面包含数据库实体Class和忽略的字段IgnoreFields
     * @param <O>
     * @return
     */
    @InsertProvider(type = SQLTemplate.class, method = "insertOrUpdateAll")
    <O extends T> Integer insertOrUpdateAll(@Param("list") List<O> records, @Param(DaoConstants.CLASS_WRAPPER) ClassWrapper classWrapper);

}
