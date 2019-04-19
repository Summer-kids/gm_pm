package cc.guomai.gm_pm.bean.sys;

import java.io.Serializable;

/**
 * 角色表基本信息
 */
public class SysRole implements Serializable {

    private Integer roleId; //角色ID
    private String roleName;  //角色名称

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
