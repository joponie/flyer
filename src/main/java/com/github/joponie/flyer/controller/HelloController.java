package com.github.joponie.flyer.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘杰鹏
 * @since 2019-11-01
 */
@RestController
public class HelloController {

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return "hello " + name;
    }
}
