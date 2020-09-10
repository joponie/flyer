package jie.flyer.portal.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author kain
 * @Date 2020/9/10
 **/
@Order(-100)
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution (* jie.flyer.portal..*.*Controller.*(..))")
    public void controller() {
    }

    @Before("controller()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        List<Object> requestParams = Arrays.stream(args).filter(a -> !(a instanceof MultipartFile)
                && !(a instanceof HttpServletResponse)
                && !(a instanceof HttpServletRequest)).collect(Collectors.toList());
        log.info("请求地址：{}，请求参数：{}", getRequestURL(), JSON.toJSONString(requestParams));
    }

    @AfterReturning(value = "controller()", returning = "result")
    public void doAfter(Object result) {
        log.info("请求地址：{}，返回参数：{}", getRequestURL(), JSON.toJSONString(result));
    }

    private String getRequestURL() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return "";
        }
        return requestAttributes.getRequest().getRequestURL().toString();
    }
}
