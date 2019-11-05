package com.github.joponie.flyer.common.base.account;

import com.github.joponie.flyer.common.base.ThreadContext;

/**
 * @author 刘杰鹏
 * @since 2019-11-05
 */
public abstract class BaseAccountService implements IAccountService {

    private static final int DEFAULT_ACCOUNT_ID = 0;

    protected abstract Account _account();

    @Override
    public Account account() {
        Account account = _account();
        if (account == null) {
            account = ThreadContext.getAccount();
        }
        return account;
    }

    @Override
    public Integer accountId() {
        Account account = account();
        if (account == null) {
            return DEFAULT_ACCOUNT_ID;
        }
        return account.getAccountId();
    }
}
