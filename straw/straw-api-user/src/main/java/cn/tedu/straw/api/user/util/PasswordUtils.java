package cn.tedu.straw.api.user.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 处理密码加密的工具类
 */
public class PasswordUtils {
    /**
     * 密码加密
     */
    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 执行密码加密
     * @param rawPassword 原始密码
     * @return 加密后的密码
     */
    public static String encode(String rawPassword){
        String encodePassword = "{bcrypt}"+passwordEncoder.encode(rawPassword);
        return encodePassword;
    }
}
