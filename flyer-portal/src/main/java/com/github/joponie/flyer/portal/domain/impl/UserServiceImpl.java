package com.github.joponie.flyer.portal.domain.impl;

import com.github.joponie.flyer.common.base.UpdatableServiceImpl;
import com.github.joponie.flyer.portal.dal.dao.UserMapper;
import com.github.joponie.flyer.portal.dal.model.User;
import com.github.joponie.flyer.portal.domain.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author 刘杰鹏
 * @since 2019-11-04
 */
@Service
public class UserServiceImpl extends UpdatableServiceImpl<UserMapper, User> implements IUserService {
}
