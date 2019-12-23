package share.base.token;

import lombok.Data;
import share.base.jwt.LoginInfoPayLoad;

@Data
public class TokenContextLocal {
    private TokenContextLocal() {
    }

    private static final ThreadLocal<TokenContextLocal> LOCAL = ThreadLocal.withInitial(TokenContextLocal::new);

    public static TokenContextLocal getContext() {
        return LOCAL.get();
    }

    private LoginInfoPayLoad loginInfoPayLoad;
}
