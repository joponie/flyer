package jie.flyer.portal.domain.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jie.flyer.common.base.ServiceImpl;
import jie.flyer.common.base.vo.page.PageBuilder;
import jie.flyer.common.base.vo.page.PageVO;
import jie.flyer.portal.dal.dao.UserMapper;
import jie.flyer.portal.dal.model.User;
import jie.flyer.portal.dal.repository.IUserRepository;
import jie.flyer.portal.domain.IUserService;
import jie.flyer.portal.vo.req.UserPageReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kain
 * @since 2019-11-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<IUserRepository> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUser(Integer id) {
        return repository.byId(id);
    }

    @Override
    public PageVO<User> page(UserPageReq req) {
        Page<User> page = new Page<>(req.getPageNum(), req.getPageSize());
        Page<User> userPage = userMapper.selectPage(page, Wrappers.<User>lambdaQuery().eq(User::getDelFlg, 1));
        return PageBuilder.build(userPage);
    }

    @Override
    public Integer addUser(User user) {
        log.info("add user");
        return repository.add(user);
    }

    @Override
    public void deleteUser(Integer id) {
        repository.delete(id);
    }

    @Override
    public void updateUser(Integer id, User user) {
        user.setId(id);
        repository.update(user);
    }

    @Override
    public void enable(Integer id) {
        userMapper.enable(id);
    }
}
