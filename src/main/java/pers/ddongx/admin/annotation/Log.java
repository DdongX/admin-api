package pers.ddongx.admin.annotation;

import pers.ddongx.admin.enums.OperationType;

import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author DdongX
 */
@Target(ElementType.METHOD) // 注解放置的目标位置,PARAMETER: 可用在参数上  METHOD：可用在方法级别上
@Retention(RetentionPolicy.RUNTIME)    // 指明修饰的注解的生存周期  RUNTIME：运行级别保留
@Documented
public @interface Log {

    /**
     * 操作
     */
    String value() default "";

    /**
     * 功能
     */
    OperationType operationType() default OperationType.OTHER;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;
}