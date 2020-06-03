/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.github.joponie.flyer.portal.dal.dao;

import com.github.joponie.flyer.common.base.BaseMapper;
import com.github.joponie.flyer.portal.dal.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * @author kain
 * @since 2019-11-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User getDefaultUser();

    List<User> getDefaultListUser();

    Integer updateMobile(@Param("mobile") String mobile);

    Integer deleteDeUser();

}
