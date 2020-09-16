package jie.flyer.portal.controller;

import jie.flyer.common.base.BaseController;
import jie.flyer.common.base.vo.Response;
import jie.flyer.portal.dal.model.User;
import jie.flyer.portal.domain.IUserService;
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

    @PostMapping
    public Response add(@RequestBody User user) {
        log.info("add");
        return Response.ok(userService.addUser(user));
    }

    @GetMapping(value = "/cache/{id}")
    public Response getCache(@PathVariable Integer id) {
        Long increment = stringRedisTemplate.opsForValue().increment(id.toString());
        log.info("increment:{}", increment);
        return Response.ok(increment);
    }
}
