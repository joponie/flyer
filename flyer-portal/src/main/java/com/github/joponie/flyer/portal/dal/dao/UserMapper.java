/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.github.joponie.flyer.portal.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.joponie.flyer.portal.dal.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 刘杰鹏
 * @since 2019-11-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
