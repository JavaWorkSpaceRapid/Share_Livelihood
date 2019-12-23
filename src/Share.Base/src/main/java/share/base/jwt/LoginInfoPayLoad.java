package share.base.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginInfoPayLoad {
    private String user_id;
    private String user_name;
    private String  user_role_id;
    private String role_id;

}
