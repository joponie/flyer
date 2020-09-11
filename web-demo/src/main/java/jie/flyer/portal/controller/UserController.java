package jie.flyer.portal.controller;

import jie.flyer.common.base.BaseController;
import jie.flyer.common.base.Response;
import jie.flyer.portal.dal.model.User;
import jie.flyer.portal.domain.IUserService;
import jie.flyer.rocketmq.service.IRocketmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author kain
 * @since 2019-11-04
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired(required = false)
    private IRocketmqService rocketmqService;

    @PostMapping
    public Response add(@RequestBody User user) {
        log.info("add");
        return Response.ok(userService.addUser(user));
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable("id") Integer id) {
        userService.findById(id);
        User byId = userService.findById(id);
        return Response.ok(byId);
    }

    @GetMapping("/def")
    public Response getDef() {
        return Response.ok(userService.getDef());
    }

    @GetMapping("/deflist")
    public Response deflist() {
        return Response.ok(userService.getDefList());
    }

    @GetMapping("/delete")
    public Response delete() {
        return Response.ok(userService.deleteDeUser());
    }

    @GetMapping("/update")
    public Response update() {
        return Response.ok(userService.updateMobile("123123"));
    }

    @GetMapping(value = "/cache/{id}")
    public Response getCache(@PathVariable Integer id) {
        Long increment = stringRedisTemplate.opsForValue().increment(id.toString());
        log.info("increment:{}", increment);
        return Response.ok(increment);
    }
}
