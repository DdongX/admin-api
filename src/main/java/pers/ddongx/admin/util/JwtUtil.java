package pers.ddongx.admin.util;

import cn.hutool.core.codec.Base64;
import io.jsonwebtoken.*;
import pers.ddongx.admin.common.constant.JwtConstant;
import pers.ddongx.admin.domain.CheckResult;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * Description: JWT工具类
 *
 * @author DdongX
 * @since 2022/12/4 20:39
 */
public class JwtUtil {
    /**
     * 签发JWT
     *
     * @param id      用户ID
     * @param subject JSON明文数据 尽可能少
     * @param ttl     过期时间
     * @return Token
     */
    public static String createJwt(String id, String subject, long ttl) {
        long nowMillis = System.currentTimeMillis();
        SecretKey secretKey = generateSecretKey();
        JwtBuilder builder = Jwts.builder().setId(id).setSubject(subject).setIssuer("DdongX").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, secretKey);
        if (ttl >= 0) {
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
     * 生成JWTToken
     *
     * @param username 用户名
     * @return token
     */
    public static String generateJwtToken(String username) {
        return createJwt(username, username, 60 * 60 * 1000);
    }

    /**
     * 验证JWT
     *
     * @param token Token
     * @return 验证结果
     */
    public static CheckResult validateJwt(String token) {
        CheckResult result = new CheckResult();
        Claims claims;
        try {
            claims = parseJwt(token);
            result.setSuccess(true);
            result.setClaims(claims);
        } catch (ExpiredJwtException e) {
            result.setErrorCode(JwtConstant.JWT_ERROR_CODE_EXPIRE);
            result.setSuccess(false);
        } catch (Exception e) {
            result.setErrorCode(JwtConstant.JWT_ERROR_CODE_FAIL);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 生成加密Key
     *
     * @return 加密key
     */
    public static SecretKey generateSecretKey() {
        byte[] encoded = Base64.decode(JwtConstant.JWT_SECRET);
        return new SecretKeySpec(encoded, 0, encoded.length, "AES");
    }

    /**
     * 解析JWT
     *
     * @param token token
     * @return 实体
     */
    public static Claims parseJwt(String token) {
        SecretKey secretKey = generateSecretKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody();
    }
}
