package cc.guomai.gm_pm.dao;

import cc.guomai.gm_pm.bean.sys.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 用户相关数据操作
 *
 * @author ZhangQI
 * @date 2019.04.18
 */
@Mapper
public interface ISysUserDao {

    /**
     * 添加用户
     *
     * @param user
     */
    public void insertUser(SysUser user);

    /**
     * 查看所有用户信息
     *
     * @return
     */
    public SysUser queryUserInfo();

    /**
     * 根据单个property查看用户信息
     *
     * @param property
     * @return
     */
    public SysUser queryUserInfoByProperty(@Param("property") String property, @Param("value") String value);
}
