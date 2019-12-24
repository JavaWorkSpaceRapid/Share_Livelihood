package share.web;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import share.base.exceptions.BaseException;
import share.base.extensions.JsonExtension;
import share.base.extensions.StringExtension;
import share.base.jwt.LoginInfoPayLoad;
import share.base.token.AccountInfo;
import share.base.token.TokenContext;
import share.base.token.TokenRepository;
import share.web.token.TokenSetter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Lycanthrope_Cheng
 * @Date 2019/5/9 10:10
 */
@Aspect
@Order(2)
@Component
public class TokenAspectJ {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public TokenAspectJ() {

    }

//    @Pointcut("execution (@io.swagger.annotations.ApiOperation !@lycan.base.annotation.NoAuth  * *(..))")
    @Pointcut("(execution(@io.swagger.annotations.ApiOperation * *(..)) || this(share.web.ApiController)) && !execution (@share.base.annotation.NoAuth  * *(..))")
    public void cutController4Token() {
    }

    @Pointcut("execution (@share.base.annotation.InitAuth  * *(..))")
    public void cutControllerInitAuth() {
    }

    @Around("cutController4Token()")
    public Object controllerAspect(ProceedingJoinPoint point) throws Throwable {
        String tokenStr = this.request.getHeader("Token");
        if (StringExtension.isNullOrWhiteSpace(tokenStr)) {
            throw new BaseException("Token为空！");
        }
        LoginInfoPayLoad loginInfoPayLoad = TokenRepository.getToken(tokenStr);
        TokenContext.setToken(loginInfoPayLoad);
        return point.proceed();
    }

    @AfterReturning(pointcut = "cutControllerInitAuth()", returning = "accountInfoDomain")
    public void initAuth(JoinPoint joinPoint, Object accountInfoDomain) {
        Object[] args = joinPoint.getArgs();
        String type = (String) args[2];
        AccountInfo accountInfo = JsonExtension.asJson(JsonExtension.asJsonString(accountInfoDomain), AccountInfo.class);
        accountInfo.setType(type);
        String tokenStr = TokenSetter.afterLogin(accountInfo);
        this.response.setHeader("Token", tokenStr);
    }

}
