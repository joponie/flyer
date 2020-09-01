package jie.flyer.portal.dal.dao;

import jie.flyer.common.base.BaseMapper;
import jie.flyer.portal.dal.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
