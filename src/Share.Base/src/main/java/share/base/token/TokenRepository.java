package share.base.token;

import com.mongodb.MongoClientURI;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import share.base.SystemHelper;
import share.base.exceptions.BaseException;
import share.base.extensions.StringExtension;
import share.base.jwt.LoginInfoPayLoad;
import share.base.jwt.TokenPayLoad;

import java.util.Date;

public class TokenRepository {

    private static String TOKEN_URL = "mongodb://192.168.4.26:27017/FormDesign?authSource=admin";

    private static final String LIVELIHOOD_TOKEN_URL = "livelihood.token.url";

    private static MongoTemplate tokenMongoTemplate;

    static {
        String tokenUrl = SystemHelper.getProperty(LIVELIHOOD_TOKEN_URL);
        if (!StringExtension.isNullOrWhiteSpace(tokenUrl)) {
            TOKEN_URL = tokenUrl;
        }
        /** 初始化MongoTemplate对象 */
        MongoClientURI mongoClientURI = new MongoClientURI(TOKEN_URL);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClientURI);
        tokenMongoTemplate = new MongoTemplate(mongoDbFactory);
    }

    public static void addToken(TokenPayLoad tokenPayLoad) {
        tokenMongoTemplate.save(tokenPayLoad);
    }

    public static void deleteToken(String id) {
        tokenMongoTemplate.remove(new Query(Criteria.where("_id").is(id)), TokenPayLoad.class);
    }

    public static void deleteAllTimeOut() {
        tokenMongoTemplate.findAllAndRemove(new Query(Criteria.where("deadTime").lt(new Date())), TokenPayLoad.class);
    }

    public static LoginInfoPayLoad getToken(String id) {
        Date current = new Date();
        TokenPayLoad tokenPayLoads = tokenMongoTemplate.findOne(new Query(Criteria.where("_id").is(id).and("deadTime").gt(current)), TokenPayLoad.class);
        if (tokenPayLoads == null) {
            deleteToken(id);
            throw new BaseException("用户未登录或已超时！");
        } else {
            LoginInfoPayLoad loginInfo = tokenPayLoads.getLoginInfo();
            tokenMongoTemplate.findAndModify(
                    new Query(Criteria.where("_id").is(id)),
                    new Update().set("lastTime", current).set("deadTime", new Date(current.getTime() + tokenPayLoads.getType().getTimeSpan())),
                    TokenPayLoad.class);
            return loginInfo;
        }
    }

}
