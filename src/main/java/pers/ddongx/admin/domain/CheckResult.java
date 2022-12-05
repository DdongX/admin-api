package pers.ddongx.admin.domain;

import io.jsonwebtoken.Claims;
import lombok.Data;

/**
 * Description: JWT验证信息
 *
 * @author DdongX
 * @since 2022/12/4 20:21
 */
@Data
public class CheckResult {
    private String errorCode;
    private Boolean success;
    private Claims claims;
}
