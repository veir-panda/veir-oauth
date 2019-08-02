package me.veir.oauth.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import me.veir.oauth.dto.UserRegisterDto;

/**
 * @author Veir, veir.xw@gmail.com
 * @create 2019/8/1 16:43
 */
@Accessors(chain = true)
@Data
public class User {
    private String userId;
    private String username;
    private String password;
    private String salt;
    private String email;
    private byte enabled;
    private String deptId;

    public static User of(UserRegisterDto dto){
        return new User()
                .setUsername(dto.getUsername())
                .setPassword(dto.getPassword())
                .setEmail(dto.getEmail());
    }
}
