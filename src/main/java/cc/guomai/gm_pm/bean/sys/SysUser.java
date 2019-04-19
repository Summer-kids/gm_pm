package cc.guomai.gm_pm.bean.sys;

import java.io.Serializable;


/**
 * 用户表基本信息
 *
 * @author ZhangQI
 * @date 2019.04.18
 */
public class SysUser implements Serializable {
    private String userId; //用户ID
    private String userName;  //用户名
    private String password; //用户密码
    private String nickname; //用户昵称


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
