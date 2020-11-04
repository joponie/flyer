package jie.flyer.common.base.wrapper;

import jie.flyer.common.util.StringUtils;
import java.util.Collection;

/**
 * @author kain
 * @since 2018-09-17
 */
public interface NormalDbColMapper {

    String COMPLETE_STR = "`%s`";

    /**
     * 转化成数据库字段
     *
     * @param normalCol
     * @return
     */
    String dbCol(String normalCol);

    /**
     * 默认字段转换
     *
     * @param normalCol
     * @return
     */
    default String defaultDbCol(String normalCol) {
        return String.format(COMPLETE_STR, StringUtils.underLine(normalCol));
    }

    /**
     * 转化成普通字段
     *
     * @param dbCol
     * @return
     */
    String normalCol(String dbCol);

    /**
     * 所有字段
     *
     * @return
     */
    Collection<String> allNormal();

    /**
     * 待更新的数据库字段
     *
     * @return
     */
    Collection<String> updateCols();

    /**
     * 所有数据库字段
     *
     * @return
     */
    Collection<String> allDb();

    /**
     * 需更新的数据库字段
     *
     * @return
     */
    Collection<String> updateDbs();
}
