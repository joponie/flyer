package jie.flyer.common.base.vo.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jie.flyer.common.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @Author kain
 * @Date 2020/11/10
 **/
public class PageBuilder {

    public static <F, T> PageVO<T> build(Page<F> page, Function<F, T> function) {
        if (page == null) {
            return null;
        }
        return new PageVO<>(page.getCurrent(), page.getSize(), page.getTotal()
                , CollectionUtils.map(page.getRecords(), function));
    }

    public static <T> PageVO<T> build(Page<T> page) {
        if (page == null) {
            return null;
        }
        PageVO<T> result = new PageVO<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<T> resultList = page.getRecords();
        result.setList(CollectionUtils.isEmpty(resultList) ? Collections.emptyList() : resultList);
        return result;
    }
}
