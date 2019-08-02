package me.veir.oauth.common.util;

import java.util.UUID;

/**
 * @Author: Veir
 * @Despriction:
 * @Date: Created at 2018/8/9 10:42
 * @Modify by:
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static String getUUIDWithoutSymbol(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
