package jie.flyer.common.base.vo.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author 刘杰鹏
 * @Date 2020/11/10
 **/
public class PageBuilder {

    public static <F, T> PageVO<T> build(Page<F> page, Function<F, T> function) {
        if (page == null) {
            return null;
        }
        PageVO<T> result = new PageVO<>();
        result.setPageSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setPageNum(page.getCurrent());
        List<F> resultList = page.getRecords();
        if (CollectionUtils.isEmpty(resultList)) {
            result.setList(Collections.emptyList());
            return result;
        }
        result.setList(resultList.stream().map(function).collect(Collectors.toList()));
        return result;
    }

    public static <T> PageVO<T> build(Page<T> page) {
        if (page == null) {
            return null;
        }
        PageVO<T> result = new PageVO<>();
        result.setPageSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setPageNum(page.getCurrent());
        List<T> resultList = page.getRecords();
        if (CollectionUtils.isEmpty(resultList)) {
            result.setList(Collections.emptyList());
            return result;
        }
        result.setList(resultList);
        return result;
    }
}
