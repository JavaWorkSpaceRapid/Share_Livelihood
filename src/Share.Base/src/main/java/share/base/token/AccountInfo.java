package share.base.token;

import lombok.Data;
import lombok.NoArgsConstructor;
import share.base.jwt.LoginInfoPayLoad;

@Data
@NoArgsConstructor
public class AccountInfo extends LoginInfoPayLoad
{

    private String type;

}
