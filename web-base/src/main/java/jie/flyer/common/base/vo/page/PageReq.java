package jie.flyer.common.base.vo.page;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author kain
 * @Date 2020/11/10
 **/
@NoArgsConstructor
@Data
public class PageReq implements Serializable {

    @Max(value = 500, message = "分页不能大于500页")
    @Min(value = 1, message = "分页不能小于1页")
    @NotNull
    private Integer pageSize;

    @Max(value = 500, message = "分页不能大于500")
    @Min(value = 10, message = "分页不能小于10")
    @NotNull
    private Integer pageNum;

}
