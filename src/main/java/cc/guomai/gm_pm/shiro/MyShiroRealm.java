package cc.guomai.gm_pm.shiro;

import cc.guomai.gm_pm.bean.sys.SysUser;
import cc.guomai.gm_pm.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 自定义shiro realm
 *
 * @author ZhangQI
 * @date 2019.04.18
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        /*for (String role : user.getRoleList()) {
            authorizationInfo.addRole(role);

        }
        for (String p : user.getPermissionList()) {
            authorizationInfo.addStringPermission(p);
        }*/
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //从service中传来的AuthenticationToken中获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser user = userService.getSysUserInfoByProperty("USER_NAME",username);

        if (user == null) {
            return null;
        }
     /*   if (user.getState() == SysUserEntity.STATE.INVALID) { //账户冻结
            throw new LockedAccountException();
        }*/
        //将从数据库中获取的user信息作为SimpleAuthenticationInfo(传给shiro验证器)
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPassword(), //密码
//                ByteSource.Util.bytes(user.getSalt()),// md5(salt+password),采用明文访问时，不需要此句
                this.getName()  //realm name
        );
        return authenticationInfo;
    }
}
