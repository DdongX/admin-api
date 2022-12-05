package pers.ddongx.admin.common.constant;

/**
 * Description: Jwt系统级静态变量
 *
 * @author DdongX
 * @since 2022/12/4 20:34
 */
public class JwtConstant {
    /**
     * Token不存在
     */
    public static final String JWT_ERROR_CODE_NULL = "4000";
    /**
     * Token过期
     */
    public static final String JWT_ERROR_CODE_EXPIRE = "4001";
    /**
     * 验证不通过
     */
    public static final String JWT_ERROR_CODE_FAIL = "4002";

    /**
     * JWT秘钥
     */
    public static final String JWT_SECRET = "Xixd@1234";
    /**
     * 过期时间
     */
    public static final long JWT_TTL = 24 * 60 * 60 * 1000;
}
