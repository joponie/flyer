package com.github.joponie.flyer.portal.mybatis;

import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;

/**
 * @author kain
 * @since 2020-03-12
 */
public class MybatisTest {

    @Test
    public void sqlTest() {
        System.out.println(new SQL().SELECT("*").FROM("project").WHERE("id = 1").toString());
    }

}
