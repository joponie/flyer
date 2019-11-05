package com.github.joponie.flyer.common.base.account;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刘杰鹏
 * @since 2019-11-05
 */
@NoArgsConstructor
@Data
public class Account {

    /**
     * 账号Id
     */
    private Integer accountId;

    public Account(Integer accountId) {
        this.accountId = accountId;
    }
}
