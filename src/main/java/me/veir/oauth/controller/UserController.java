package me.veir.oauth.controller;

import me.veir.oauth.common.ReturnT;
import me.veir.oauth.common.page.PageBean;
import me.veir.oauth.dto.UserRegisterDto;
import me.veir.oauth.entity.User;
import me.veir.oauth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Veir, veir.xw@gmail.com
 * @create 2019/8/1 16:45
 */
@RequestMapping("/sys")
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @GetMapping("/user")
    public ReturnT<PageBean> pageList(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int pageSize){
        logger.debug(".........");
        return userService.getPageList(page, pageSize);
    }

    @PostMapping("/user/register")
    public ReturnT<User> register(@Valid UserRegisterDto dto){
        User user = userService.register(dto);
        return new ReturnT(user);
    }
}
