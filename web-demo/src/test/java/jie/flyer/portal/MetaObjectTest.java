package jie.flyer.portal;

import jie.flyer.portal.dal.model.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Author kain
 * @Date 2020/9/15
 **/
public class MetaObjectTest {

    @Test
    public void t1() {
        User u1= new User();
        u1.setUsername("张三");

        User u2= new User();
        u2.setUsername("张三");

        UserHolder userHolder = new UserHolder();
        userHolder.setUsers(Arrays.asList(u1, u2));
        userHolder.setName("大家");

        MetaObject metaObject = SystemMetaObject.forObject(userHolder);
        String[] getterNames = metaObject.getGetterNames();

        System.out.println(Arrays.toString(getterNames));

        System.out.println(metaObject.getValue("users[1].username"));
    }

    @Getter
    @Setter
    public static class UserHolder {

        private List<User> users;

        private String name;

    }

}
