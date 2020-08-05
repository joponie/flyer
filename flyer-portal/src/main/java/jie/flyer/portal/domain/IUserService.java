package jie.flyer.portal.domain;

import jie.flyer.common.base.IBaseService;
import jie.flyer.portal.dal.model.User;

import java.util.List;

/**
 * @author kain
 * @since 2019-11-04
 */
public interface IUserService extends IBaseService<User> {

    Integer addUser(User user);

    User getDef();

    List<User> getDefList();

    Integer updateMobile(String mobile);

    Integer deleteDeUser();
}