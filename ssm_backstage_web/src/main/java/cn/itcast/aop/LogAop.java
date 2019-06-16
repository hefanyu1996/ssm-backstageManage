package cn.itcast.aop;

import cn.itcast.domain.SysLog;
import cn.itcast.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 切面类
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LogService logService;

    @Around("execution(* cn.itcast.web.controller.*.*(..))")
    public Object addLog(ProceedingJoinPoint joinPoint){

        Object proceed = null;
        try {
            //访问时间
            Date date = new Date();
            // 登录用户用户名
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            // ip地址
            String ip = request.getRemoteAddr();
            //controller方法的url,
            String uri = request.getRequestURI();
            // 方法的全限定名
            //获取目标方法名
            String methodName = joinPoint.getSignature().getName();
            //获取目标Controller对象
            Object target = joinPoint.getTarget();
            //Controller类的全类名
            String className = target.getClass().getName();

            //调用切入点方法
            // controller方法执行时长
            long start = System.currentTimeMillis();
            proceed = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;

            //封装实体类
            SysLog sysLog = new SysLog();
            sysLog.setVisitTime(date);
            sysLog.setIp(ip);
            sysLog.setUrl(uri);
            sysLog.setUsername(username);
            sysLog.setMethod(className+"."+methodName);
            sysLog.setExecutionTime(executionTime);
            //调用业务层保存日志
            logService.save(sysLog);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return proceed;
    }


}
