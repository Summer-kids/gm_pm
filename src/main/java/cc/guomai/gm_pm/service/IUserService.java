package cc.guomai.gm_pm.service;

import cc.guomai.gm_pm.bean.sys.SysUser;


/**
 * 用户信息相关service接口
 *
 * @author ZhangQI
 * @date 2019.04.18
 */
public interface IUserService {

    /**
     * 获取所有用户基本信息
     *
     * @return SysUser
     */
    public SysUser getSysUserInfo();


    /**
     * 根据单个property查看用户信息
     *
     * @param property
     * @return SysUser
     */
    public SysUser getSysUserInfoByProperty(String property,String value);
}
