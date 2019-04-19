package cc.guomai.gm_pm.service;

import cc.guomai.gm_pm.bean.sys.SysUser;
import cc.guomai.gm_pm.dao.ISysUserDao;
import cc.guomai.gm_pm.dto.Result;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * 关于系统层面使用到的service
 *
 * @author ZhangQI
 * @date 2019.04.18
 */
@Service
public class SysService {

//    Log log = LogFactory.getLog(SysService.class);
    Logger logger = LoggerFactory.getLogger(SysService.class);

    @Resource
    private SecurityManager securityManager;

    @Resource
    private ISysUserDao sysUserDao;

    @PostConstruct
    private void initStaticSecurityManager() {
        SecurityUtils.setSecurityManager(securityManager);
    }


    /**
     * 注册成为系统用户
     *
     * @param sysUser
     * @return Result
     */
    public Result register(SysUser sysUser){

        sysUserDao.insertUser(sysUser);

        Result result=new Result();
        result.message="注册成功~";

        return result;
    }
    /**
     * 系统用户登录
     *
     * @param userName
     * @param password
     * @return Result
     */
    public Result login(String userName, String password) {

        //构建一个shiro的登录主体
        Subject subject = SecurityUtils.getSubject();

        subject.login(new UsernamePasswordToken(userName, password));

        Result result = new Result();
        result.message = "登录成功~";
        result.body = subject.getSession().getId();

        return result;
    }

/*    @RequiresAuthentication
    public Result getUserInfo() {
        Subject subject = SecurityUtils.getSubject();

        JSONObject retJson = (JSONObject) JSON.toJSON(subject.getPrincipal());
        retJson.remove("password");
        retJson.remove("id");
        retJson.remove("salt");

        Result result = new Result();
        result.body = retJson;
        return result;
    }*/

    /**
     * 系统用户登出
     *
     * @return Result
     */
    public Result logout() {

        //获取当前登录的主体
        Subject subject = SecurityUtils.getSubject();

        subject.logout();

        Result result = new Result();
        result.message = "登出成功~";

        return result;
    }
}
