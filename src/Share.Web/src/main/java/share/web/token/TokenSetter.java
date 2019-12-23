package share.web.token;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import share.base.extensions.JsonExtension;
import share.base.jwt.AccountTypeEnum;
import share.base.jwt.TokenPayLoad;
import share.base.token.AccountInfo;
import share.base.token.TokenRepository;

public class TokenSetter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenSetter.class);

    /**
     * 登录
     *
     * @param accoutnInfo
     * @return
     */
    public static String afterLogin(AccountInfo accoutnInfo) {
        String type = accoutnInfo.getType();
//        String _id = new StringBuilder(type).append("_").append(accoutnInfo.getUser_id()).toString();
        String _id = new StringBuilder(type).append("_").append(DigestUtils.md5Hex(JsonExtension.asJsonString(accoutnInfo))).toString();
        TokenPayLoad tokenPayLoad = new TokenPayLoad(
                _id,
                accoutnInfo,
                AccountTypeEnum.getType(type));
        TokenRepository.addToken(tokenPayLoad);
        return _id;
    }

    /**
     * 登退
     *
     * @param id
     * @return
     */
    public static boolean afterLogout(String id) {
        TokenRepository.deleteToken(id);
        return true;
    }

    public static void removeTimeOut() {
        TokenRepository.deleteAllTimeOut();

    }
}
