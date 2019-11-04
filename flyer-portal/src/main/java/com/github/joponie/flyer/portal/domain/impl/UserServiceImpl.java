package com.github.joponie.flyer.portal.domain.impl;

import com.github.joponie.flyer.portal.dal.dao.UserMapper;
import com.github.joponie.flyer.portal.dal.model.User;
import com.github.joponie.flyer.portal.domain.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 刘杰鹏
 * @since 2019-11-04
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public Integer add(User user) {
        return mapper.insert(user);
    }
}
