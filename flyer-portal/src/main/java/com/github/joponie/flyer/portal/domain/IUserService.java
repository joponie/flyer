package com.github.joponie.flyer.portal.domain;

import com.github.joponie.flyer.common.base.IBaseService;
import com.github.joponie.flyer.portal.dal.model.User;

/**
 * @author 刘杰鹏
 * @since 2019-11-04
 */
public interface IUserService extends IBaseService<User> {

    Integer addUser(User user);

}
