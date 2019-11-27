package com.github.joponie.flyer.portal.controller;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author 刘杰鹏
 * @since 2019-11-07
 */
public class UserControllerTest {

    @Test
    public void t1() {
        List<String> strings = Arrays.asList("11", "2", "17");
        strings.sort(String::compareTo);
        for (String string : strings) {
            System.out.println(string);
        }
    }


}