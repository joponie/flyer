package jie.flyer.portal.domain;

import jie.flyer.common.base.IService;
import jie.flyer.portal.dal.model.User;
import jie.flyer.portal.dal.repository.IUserRepository;

import java.util.List;

/**
 * @author kain
 * @since 2019-11-04
 */
public interface IUserService extends IService<IUserRepository> {

    Integer addUser(User user);

    void deleteUser(Integer id);

    void updateUser(Integer id, User user);

    void enable(Integer id);

    User getUser(Integer id);
}
