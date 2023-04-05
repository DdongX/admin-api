package pers.ddongx.admin.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 统一响应
 *
 * @author DdongX
 * @since 2023/3/11 16:58
 */
public class R extends HashMap<String, Object> {

    public R() {
        put("code", "200");
    }

    public static R error() {
        return error("500", "服务异常，请联系管理员！");
    }

    public static R error(String msg) {
        return error("500", msg);
    }

    public static R error(String code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
