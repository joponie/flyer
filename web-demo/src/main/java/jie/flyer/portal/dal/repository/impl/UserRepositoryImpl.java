package jie.flyer.portal.dal.repository.impl;

import jie.flyer.common.base.RepositoryImpl;
import jie.flyer.portal.dal.dao.UserMapper;
import jie.flyer.portal.dal.model.User;
import jie.flyer.portal.dal.repository.IUserRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author kain
 * @Date 2020/9/16
 **/
@Repository
public class UserRepositoryImpl extends RepositoryImpl<UserMapper, User> implements IUserRepository {
}
