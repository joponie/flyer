package jie.flyer.common.base.vo.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author kain
 * @Date 2020/11/10
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageVO<T> implements Serializable {

    private Long pageNum;

    private Long pageSize;

    private Long total;

    private List<T> list;

    public PageVO(Long pageNum, Long pageSize, Long total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }
}
