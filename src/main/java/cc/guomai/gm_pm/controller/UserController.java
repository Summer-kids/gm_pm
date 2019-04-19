package cc.guomai.gm_pm.controller;

import cc.guomai.gm_pm.bean.sys.SysUser;
import cc.guomai.gm_pm.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户信息相关controller
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 获取全部用户的基本信息
     *
     * @return SysUser
     */
    @RequiresAuthentication
    @GetMapping("/userInfo")
    public SysUser getSysUserInfo() {

        SysUser sysUser = userService.getSysUserInfo();

        return sysUser;
    }
}
