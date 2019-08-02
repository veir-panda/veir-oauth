package me.veir.oauth.mapper;

import me.veir.oauth.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Veir, veir.xw@gmail.com
 * @create 2019/8/1 16:45
 */
@Component
public interface UserMapper {
    @Select("select * from sys_user limit #{limit} offset #{offset}")
    @Results(id = "user", value = {
            @Result(column = "user_id", property = "userId")
    })
    List<User> getPageList(int limit, int offset);

    @Select("select count(*) from sys_user")
    long getPageListCount();

    @Select("select * from sys_user where user_id=#{userId} ")
    @ResultMap("user")
    User selectByUserId(String userId);

    @Insert("insert into sys_user(user_id, username, password, salt, email, enabled, dept_id) " +
            "values (#{userId} ,#{username} , #{password} , #{salt} , #{email} , #{enabled} , #{deptId} )")
    int add(User user);

    @Delete("delete from sys_user where user_id = #{userId} ")
    int delete(String userId);
}
