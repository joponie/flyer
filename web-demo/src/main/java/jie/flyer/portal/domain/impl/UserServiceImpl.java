package jie.flyer.portal.domain.impl;

import jie.flyer.common.base.ServiceImpl;
import jie.flyer.portal.dal.dao.UserMapper;
import jie.flyer.portal.dal.model.User;
import jie.flyer.portal.dal.repository.IUserRepository;
import jie.flyer.portal.domain.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kain
 * @since 2019-11-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<IUserRepository> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Integer addUser(User user) {
        log.info("add user");
        return repository.add(user);
    }

    @Override
    public void enable(Integer id) {
        userMapper.enable(id);
    }
}
