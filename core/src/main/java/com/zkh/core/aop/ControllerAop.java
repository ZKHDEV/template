package com.zkh.core.aop;

import com.zkh.core.bean.ResultBean;
import com.zkh.core.exception.UnloginException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(-99)
@Slf4j
public class ControllerAop {
    
    @Pointcut("execution(public com.zkh.core.bean.ResultBean *(..))")
    private void controllerMethod() {
    }
    
    @Around("controllerMethod()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        ResultBean<?> result;

        try {
            result = (ResultBean<?>) pjp.proceed();
            log.info(pjp.getSignature() + " elapsed time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handerException(pjp, e);
        }

        return result;
    }

    private ResultBean<?> handerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean(e);

        // 已知异常
        if (e instanceof UnloginException) {
            result.setMsg(e.getLocalizedMessage());
        }

        log.error(pjp.getSignature() + " error ", e);
        return result;
    }
}
