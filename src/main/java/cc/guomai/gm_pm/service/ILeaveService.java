package cc.guomai.gm_pm.service;

import cc.guomai.gm_pm.bean.LeaveInfo;

import java.util.List;

public interface ILeaveService {

	/**
	 * 新增一条请假单记录
	 * @param
	 */
	void addLeaveAInfo(LeaveInfo leaveInfo);
	/**
	 * 查询待办流程
	 * @param userId
	 * @return
	 */
	List<LeaveInfo> getByUserId(String userId);

	/**
	 * 完成任务
	 * @param taskId
	 * @param userId
	 * @param audit
	 */
	void completeTaskByUser(String taskId, String userId, String audit);
}
