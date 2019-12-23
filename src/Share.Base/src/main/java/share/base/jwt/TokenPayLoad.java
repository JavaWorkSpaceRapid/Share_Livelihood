package share.base.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TokenPayLoad {

    private String _id;
    private LoginInfoPayLoad loginInfo;
    private AccountTypeEnum type;
    private Date firstTime;
    private Date lastTime;
    private Date deadTime;

    public TokenPayLoad(String _id, LoginInfoPayLoad loginInfo, AccountTypeEnum type) {
        this._id = _id;
        this.loginInfo = loginInfo;
        this.type = type;
        this.firstTime = new Date();
        this.lastTime = firstTime;
        this.deadTime = new Date(this.firstTime.getTime() + this.type.getTimeSpan());
    }

}
