package jie.flyer.common.base;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * model
 *
 * @author kain
 * @since 2019-11-04
 */
@Data
public abstract class BaseModel implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 创建人ID
     */
    @JsonIgnore
    private Integer createBy;
    /**
     * 创建时间
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新人ID
     */
    @JsonIgnore
    private Integer updateBy;
    /**
     * 更新时间
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 删除标记
     */
    @TableLogic(value = "0", delval = "1")
    @JsonIgnore
    private Integer delFlg;

}
