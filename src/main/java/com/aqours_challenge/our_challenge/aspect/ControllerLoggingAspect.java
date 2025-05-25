package com.aqours_challenge.our_challenge.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ControllerLoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(ControllerLoggingAspect.class);


    // 컨트롤러 내의 모든 메서드
    @Pointcut("within(@org.springframework.stereotype.Controller *) || within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerMethods() {}

    // 요청 시작 로그
    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("[Controller: {}.{}] 호출됨, 인자: {}",
                signature.getDeclaringType().getSimpleName(),
                signature.getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    // 정상 응답 반환 시
    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("[Controller: {}.{}] 정상 완료, 반환값: {}",
                signature.getDeclaringType().getSimpleName(),
                signature.getName(),
                result);
    }

    // 예외 발생 시
    @AfterThrowing(pointcut = "controllerMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.error("[Controller: {}.{}] 예외 발생: {}",
                signature.getDeclaringType().getSimpleName(),
                signature.getName(),
                ex.getMessage(), ex);
    }
}
