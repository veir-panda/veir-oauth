package me.veir.oauth.service;

import me.veir.oauth.common.ReturnT;
import me.veir.oauth.common.page.PageBean;
import me.veir.oauth.dto.UserRegisterDto;
import me.veir.oauth.entity.User;

/**
 * @author Veir, veir.xw@gmail.com
 * @create 2019/8/1 17:02
 */
public interface UserService {
    ReturnT<User> add(User user);

    int delete(String userId);

    ReturnT<PageBean> getPageList(int page, int pageSize);

    User register(UserRegisterDto dto);
}
