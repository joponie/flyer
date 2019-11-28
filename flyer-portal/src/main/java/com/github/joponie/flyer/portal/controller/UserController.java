package com.github.joponie.flyer.portal.controller;

import com.github.joponie.flyer.common.base.BaseController;
import com.github.joponie.flyer.common.base.Response;
import com.github.joponie.flyer.portal.dal.model.User;
import com.github.joponie.flyer.portal.domain.IUserService;
import com.github.joponie.flyer.rocketmq.MessageBuilder;
import com.github.joponie.flyer.rocketmq.service.IRocketmqService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IRocketmqService rocketmqService;

    @PostMapping
    public Response add(@RequestBody User user) {
        log.info("add");
        return Response.of(userService.addUser(user));
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable("id") Integer id) {
        rocketmqService.send(MessageBuilder.topic("flytest").body(id.toString()).build());
        log.info("get user, userId:{}", id);
        return Response.of(id);
    }
}
