package share.livelihood.persongroup.domain.core;

import lombok.Data;

/**
 * 角色分类
 */
@Data
public class RoleInfoDomain
{
    /**
     * 角色Id
     */
    private String role_id;

    /**
     * 角色名称
     */
    private String role_name;

    /**
     * 角色描述
     */
    private String role_desc;
}
