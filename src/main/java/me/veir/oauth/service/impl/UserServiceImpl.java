package me.veir.oauth.service.impl;

import me.veir.oauth.common.ReturnT;
import me.veir.oauth.common.page.PageBean;
import me.veir.oauth.common.page.PageBeanUtil;
import me.veir.oauth.common.util.UUIDUtil;
import me.veir.oauth.dto.UserRegisterDto;
import me.veir.oauth.entity.User;
import me.veir.oauth.mapper.UserMapper;
import me.veir.oauth.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Veir, veir.xw@gmail.com
 * @create 2019/8/1 17:03
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public ReturnT<User> add(User user) {
        return null;
    }

    @Override
    public int delete(String userId) {
        return 0;
    }

    @Override
    public ReturnT<PageBean> getPageList(int page, int pageSize) {
        List<User> users = userMapper.getPageList(pageSize, PageBeanUtil.getStartIndex(page, pageSize));
        long total = userMapper.getPageListCount();
        return new ReturnT<>(PageBeanUtil.generateGridData(users, null, total, pageSize, Collections.emptyList()));
    }

    @Override
    public User register(UserRegisterDto dto) {
        User user = User.of(dto);
        String userId = UUIDUtil.getUUIDWithoutSymbol();
        user.setUserId(userId);
        userMapper.add(user);
        return user;
    }


}
