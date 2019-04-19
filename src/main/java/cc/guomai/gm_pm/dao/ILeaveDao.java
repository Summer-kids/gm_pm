package cc.guomai.gm_pm.dao;

import cc.guomai.gm_pm.bean.LeaveInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ILeaveDao {

	int getCount();
	
	void insert(LeaveInfo entity);
	
	void delete(String id);
	
	LeaveInfo getById(String id);
	
	List<LeaveInfo> getList();
	
	void update(LeaveInfo entity);
}
