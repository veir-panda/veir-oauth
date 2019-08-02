package me.veir.oauth.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

/**
 * @author Veir, veir.xw@gmail.com
 * @create 2019/8/2 8:54
 */
@Accessors(chain = true)
@Data
public class UserRegisterDto {
    @Length(min = 3, max = 16, message = "用户名介于3-16位之间")
    private String username;
    @Length(min = 3, max = 16, message = "密码介于3-16位之间")
    private String password;
    @Email(message = "邮箱格式不正确")
    private String email;
}
