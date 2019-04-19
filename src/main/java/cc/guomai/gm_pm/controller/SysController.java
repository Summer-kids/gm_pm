package cc.guomai.gm_pm.controller;

import cc.guomai.gm_pm.bean.sys.SysUser;
import cc.guomai.gm_pm.dto.Result;
import cc.guomai.gm_pm.service.SysService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 关于系统层面的controller
 *
 * @author ZhangQI
 * @date 2019.04.18
 */
@CrossOrigin
@RestController
@RequestMapping("/sys")
public class SysController {

    @Autowired
    SysService sysService;


    /**
     * 注册成为系统用户
     *
     * @param sysUser
     * @return Result
     */
    @PostMapping("/register")
    public Result register(@RequestBody SysUser sysUser){

        return sysService.register(sysUser);
    }

    /**
     * 系统用户登录
     *
     * @param userName
     * @param password
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestParam("userName") String userName, @RequestParam("password") String password){

        return sysService.login(userName,password);
    }

    /**
     * 系统用户登出
     *
     * @return Result
     */
    @RequiresAuthentication
    @DeleteMapping("/logout")
    public Result logout(){

        return sysService.logout();
    }
}
