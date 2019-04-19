package cc.guomai.gm_pm.service.impl;

import cc.guomai.gm_pm.bean.sys.SysUser;
import cc.guomai.gm_pm.dao.ISysUserDao;
import cc.guomai.gm_pm.service.IUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * 用户信息相关service实现类
 *
 * @author ZhangQI
 * @date 2019.04.18
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    ISysUserDao sysUserDao;


    /**
     * 获取所有用户基本信息
     *
     * @return SysUser
     */
    @Override
    public SysUser getSysUserInfo() {

        SysUser sysUser = sysUserDao.queryUserInfo();

        return sysUser;
    }

    /**
     * 根据单个property查看用户信息
     *
     * @param property
     * @return SysUser
     */
    @Override
    public SysUser getSysUserInfoByProperty(String property,String value) {

        SysUser sysUser = sysUserDao.queryUserInfoByProperty(property,value);

        return sysUser;
    }
}
