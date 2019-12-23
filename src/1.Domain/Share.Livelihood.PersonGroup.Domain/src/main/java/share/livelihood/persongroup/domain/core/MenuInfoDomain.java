package share.livelihood.persongroup.domain.core;

import lombok.Data;
import share.domain.EntityBase;

/**
 * 菜单信息
 */
@Data
public class MenuInfoDomain extends EntityBase
{
    private String menu_id;
    private String user_id;
    private String role_id;
    private String menu_name;
    private String menu_url;
    private String menu_img;
    private String parent_id;
    private String isStatus;
}
