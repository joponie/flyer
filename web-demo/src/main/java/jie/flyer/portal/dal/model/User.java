package jie.flyer.portal.dal.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import jie.flyer.common.base.UpdatableModel;
import lombok.Data;

/**
 * @author kain
 * @since 2019-11-04
 */
@Data
@TableName("user")
public class User extends UpdatableModel {
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public String getHaha() {
        return "1231";
    }
}