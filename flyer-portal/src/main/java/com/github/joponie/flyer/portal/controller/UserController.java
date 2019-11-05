package com.github.joponie.flyer.portal.controller;

import com.github.joponie.flyer.common.base.BaseController;
import com.github.joponie.flyer.common.base.Response;
import com.github.joponie.flyer.portal.dal.model.User;
import com.github.joponie.flyer.portal.domain.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘杰鹏
 * @since 2019-11-04
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public Response add(@RequestBody User user) {
        log.info("add");
        return Response.of(userService.addUser(user));
    }

}
