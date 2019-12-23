package share.base.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import share.base.jwt.LoginInfoPayLoad;


public class TokenContext {
    private static Logger log = LoggerFactory.getLogger(TokenContext.class);

    public static void setToken(LoginInfoPayLoad loginInfoPayLoad) {
        TokenContextLocal.getContext().setLoginInfoPayLoad(loginInfoPayLoad);
    }

    public LoginInfoPayLoad getToken() {
        LoginInfoPayLoad loginInfoPayLoad = TokenContextLocal.getContext().getLoginInfoPayLoad();
        return loginInfoPayLoad;
    }
}
