package cc.guomai.gm_pm.service.impl;

import cc.guomai.gm_pm.bean.LeaveInfo;
import cc.guomai.gm_pm.dao.ILeaveDao;
import cc.guomai.gm_pm.service.ILeaveService;
import cc.guomai.gm_pm.service.TestLeaveService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LeaveServiceImpl implements ILeaveService {

	@Autowired
	private TestLeaveService testLeaveService;

	@Resource
	private ILeaveDao leaveDao;

	@Resource
	private RuntimeService runtimeService;

	@Override
	public void addLeaveAInfo(LeaveInfo leaveInfo) {

		String id = UUID.randomUUID().toString();

		leaveInfo.setLeaveId(id);
		//新增一条记录至数据库中
		leaveDao.insert(leaveInfo);

		String businessKey="LEAVE_INFO:"+id;
		//启动流程引擎
		testLeaveService.startProcess(businessKey);
	}

	@Override
	public List<LeaveInfo> getByUserId(String userId) {
		ArrayList<LeaveInfo> leaveInfoList = new ArrayList<LeaveInfo>();
		List<Task> list = testLeaveService.findTaskByUserId(userId);
		for (Task task : list) {
			ProcessInstance result = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			//获得业务流程的bussinessKey
			String businessKey = result.getBusinessKey();
			LeaveInfo leaveInfo = leaveDao.getById(businessKey);
			leaveInfo.setTaskId(task.getId());
			leaveInfoList.add(leaveInfo);
		}
		return leaveInfoList;
	}

	@Override
	public void completeTaskByUser(String taskId, String userId, String audit) {
		testLeaveService.completeTaskByUser(taskId, userId, audit);
	}

}
