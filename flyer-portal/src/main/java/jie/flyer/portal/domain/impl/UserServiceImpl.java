package jie.flyer.portal.domain.impl;

import jie.flyer.common.base.UpdatableServiceImpl;
import jie.flyer.portal.dal.dao.UserMapper;
import jie.flyer.portal.dal.model.User;
import jie.flyer.portal.domain.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kain
 * @since 2019-11-04
 */
@Service
public class UserServiceImpl extends UpdatableServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public Integer addUser(User user) {
        log.info("add user");
        return add(user);
    }

    @Override
    public User getDef() {
        return mapper.getDefaultUser();
    }

    @Override
    public List<User> getDefList() {
        return mapper.getDefaultListUser();
    }

    @Override
    public Integer updateMobile(String mobile) {
        return mapper.updateMobile(mobile);
    }

    @Override
    public Integer deleteDeUser() {
        return mapper.deleteDeUser();
    }
}
