package jie.flyer.portal.controller;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kain
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

    @Test
    public void t2() {
        Pattern pattern = Pattern.compile("^(\\w{10})([1-2])([\\s\\S]*)$");
        Matcher matcher = pattern.matcher("CD000011153翻乐颂杏林店10至12月物业费");
        if (matcher.find()) {
            System.out.println("match");
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
    }
}