package com.github.joponie.flyer.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 刘杰鹏
 * @since 2019-11-04
 */
@Data
public abstract class BaseModel implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

}
