package com.github.joponie.flyer.portal.controller;

import com.github.joponie.flyer.common.base.BaseController;
import com.github.joponie.flyer.common.base.Response;
import com.github.joponie.flyer.portal.dal.model.User;
import com.github.joponie.flyer.portal.domain.IUserService;
import com.github.joponie.flyer.rocketmq.service.IRocketmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author 刘杰鹏
 * @since 2019-11-04
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IRocketmqService rocketmqService;

    @PostMapping
    public Response add(@RequestBody User user) {
        log.info("add");
        return Response.of(userService.addUser(user));
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable("id") Integer id) {
        userService.findById(id);
        User byId = userService.findById(id);
        return Response.of(byId);
    }

    @GetMapping("/def")
    public Response getDef() {
        return Response.of(userService.getDef());
    }

    @GetMapping("/deflist")
    public Response deflist() {
        return Response.of(userService.getDefList());
    }

    @GetMapping("/delete")
    public Response delete() {
        return Response.of(userService.deleteDeUser());
    }

    @GetMapping("/update")
    public Response update() {
        return Response.of(userService.updateMobile("123123"));
    }

    @GetMapping(value = "/cache/{id}")
    public Response getCache(@PathVariable Integer id) {
        Long increment = stringRedisTemplate.opsForValue().increment(id.toString());
        log.info("increment:{}", increment);
        return Response.of(increment);
    }
}
