package share.web.result;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import share.base.exceptions.BaseException;
import share.base.extensions.JsonExtension;
import share.base.result.LiveResult;
import share.base.result.RtnMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Order(1)
@Component
public class ControllerAspectJ {
    private Logger log = LoggerFactory.getLogger(ControllerAspectJ.class);

    @Pointcut("within(@io.swagger.annotations.Api *) || this(share.web.ApiController)")
    public void cutController() {
    }

    @Around("cutController()")
    public Object controllerAspect(ProceedingJoinPoint point) {
        Object gaeaResult;
        try {
            log.info("api：" + point.getSignature().getDeclaringTypeName() + " " + point.getSignature().getName());
            Object[] args = point.getArgs();
            List<Object> collect = Arrays.stream(args).filter(x -> !(x instanceof HttpServletRequest)
                    && !(x instanceof HttpServletResponse)
                    && !(x instanceof File)
                    && !(x instanceof MultipartFile)).collect(Collectors.toList());
            log.debug("args：" + JsonExtension.asJsonString(collect));
            gaeaResult = point.proceed();
        } catch (Throwable throwable) {
            MethodSignature signature = (MethodSignature) point.getSignature();
            List<RtnMessage> rtnMessages = new ArrayList<>();
            if (throwable instanceof BaseException) {
                log.error(throwable.getMessage());
                rtnMessages.add(((BaseException) throwable).asMessage());
            } else {
                log.error(throwable.getMessage(), throwable);
                rtnMessages.add(new RtnMessage("后台服务忙，请稍后重试！", 0));
            }
            LiveResult<Object> objectGaeaResult = new LiveResult(0, rtnMessages, null);
            if (signature.getReturnType() == LiveResult.class) {
                gaeaResult = objectGaeaResult;
            } else {
                gaeaResult = JsonExtension.asJsonString(objectGaeaResult);
            }
        }
        return gaeaResult;
    }
}
