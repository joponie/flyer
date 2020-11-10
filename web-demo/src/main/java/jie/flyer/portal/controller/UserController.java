package jie.flyer.portal.controller;

import jie.flyer.common.base.BaseController;
import jie.flyer.common.base.vo.Response;
import jie.flyer.common.base.vo.page.PageVO;
import jie.flyer.portal.dal.model.User;
import jie.flyer.portal.domain.IUserService;
import jie.flyer.portal.vo.req.UserPageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author kain
 * @since 2019-11-04
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/{id}")
    public Response get(@PathVariable Integer id) {
        User user = userService.getUser(id);
        return Response.ok(user);
    }

    @GetMapping(value = "page")
    public Response get(@Valid @RequestBody UserPageReq req) {
        PageVO<User> user = userService.page(req);
        return Response.ok(user);
    }

    @PostMapping
    public Response add(@RequestBody User user) {
        return Response.ok(userService.addUser(user));
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return Response.ok();
    }

    @PutMapping(value = "/{id}")
    public Response update(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(id, user);
        return Response.ok();
    }
}
