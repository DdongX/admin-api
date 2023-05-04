package pers.ddongx.admin.util;

import cn.hutool.crypto.SecureUtil;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * Description: AES堆成加密工具类
 *
 * @author Ddong
 * @since 2023/5/3 18:57
 */
@Component
public class AESUtil {

    private AESUtil() {
    }

    public static final String KEY = "dskjhfosiy8oewinlslnklsd";

    public static String encrypt(String data) {
        return SecureUtil.aes(KEY.getBytes(StandardCharsets.UTF_8)).encryptBase64(data);
    }

    public static String decrypt(String data) {
        return SecureUtil.aes(KEY.getBytes(StandardCharsets.UTF_8)).decryptStr(data);
    }
}
