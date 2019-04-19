package cc.guomai.gm_pm.service;

import cc.guomai.gm_pm.bean.LeaveInfo;
import cc.guomai.gm_pm.dao.ILeaveDao;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestLeaveService {

	@Resource
	private ILeaveDao leaveDao;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	
	@Resource
	private HistoryService historyService;
	
	@Autowired
	private static RepositoryService repositoryService;
	/**
	 * 启动流程
	 * 
	 */
	public void startProcess(String bizKey) {
		//第一个参数是指定启动流程的id,即要启动哪个流程 ;第二个参数是指业务id
		System.out.println("启动前-----");
		ProcessInstance instance=runtimeService.startProcessInstanceByKey("leave_bill", bizKey);
		System.out.println("启动之后------当前流程实例ID为:"+instance.getId());
	}
	
	/**
	 * 根据审批人id查询需要审批的任务
	 * @param userId
	 * @return
	 */
	public List<Task> findTaskByUserId(String userId) {
		List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(userId).list();
		return list;
	}
	
	/**
	 * 审批
	 * @param taskId 审批的任务id
	 * @param userId 审批人的id
	 * @param audit  审批意见：通过（pass）or驳回（reject）
	 */
	 public void completeTaskByUser(String taskId,String userId,String audit) {
		 Map<String, Object> map = new HashMap<String, Object>();
		 //1、认领任务
		 taskService.claim(taskId, userId);
		//2.完成任务
		 map.put("audit",audit);
		 taskService.complete(taskId, map);
	 }
	
	/**
	 * 查询相关的项目经理
	 * @param execution 执行实例的代理对象 ,代表的是一个请假的具体实例
	 * @return
	 */
	public List<String> findProjectManager(DelegateExecution execution) {
		return Arrays.asList("project1","project2");
	}
	
	/**
	 * 查询相关的人事经理
	 * @param execution 执行实例的代理对象,代表的是一个请假的具体实例
	 * @return
	 */
	public List<String> findHrManager(DelegateExecution execution) {
		return Arrays.asList("hr1","hr2");
	}
	
	/**
	 * 修改请假单的状态
	 */
	public void changeStatus(DelegateExecution execution,String status) {
		
		String key = execution.getProcessBusinessKey();
		//LeaveInfo entity = new LeaveInfo();
		LeaveInfo entity = leaveDao.getById(key);
//		entity.setStatus(status);
		leaveDao.update(entity);
		
	//	System.out.println("修改请假单状态为：" + status);
		
	}
	
	/**
	 * 根据流程id查看该流程是否结束
	 * @param processInstanceId
	 * @return
	 */
	public boolean queryProcessIsEnd(String processInstanceId){
		
		HistoricProcessInstance result = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(result != null && result.getStartTime() != null && result.getEndTime() != null) {
			return true;
		}
		return false;
	}
	
	/*public InputStream getImgStream(String taskId) {
		BpmnModel bpmnModel = new BpmnModel();
		ProcessDefinitionEntity entity = findProcessDefinitionEntityByTaskId(taskId);
		//ProcessDiagramGenerator.generateDiagram
		InputStream imageStream = ProcessDiagramGenerator.generateDiagram(entity, "png", runtimeService.getActiveActivityIds(findProcessInstanceByTaskId(taskId).getId()));

		return null;
		
	}
	
	
	*//**
	 * 根据任务id获取流程实例
	 * @param taskId
	 * @return
	 *//*
	public ProcessInstance findProcessInstanceByTaskId(String taskId) {
		  ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(findTaskEntityById(taskId).getProcessInstanceId()).singleResult();
		  return processInstance;

	}
	@Autowired
	private RepositoryService repositoryService;
	
	
	*//**
	 * 根据任务id获取流程定义
	 * @param taskId
	 * @return
	 *//*
	public ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(String taskId) {
		
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(findTaskEntityById(taskId).getProcessDefinitionId());
		return processDefinition;
	}
	
	
	*//**
	 * 根据任务id获取任务实例
	 * @param taskId
	 * @return
	 *//*
	public TaskEntity findTaskEntityById(String taskId) {
		return (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();
	}*/
	
	
	
	
	/**
	 * 获取流程图
	 * @param processDefId
	 * @return
	 */
	public static InputStream findProcessPic(String processDefId) {
		ProcessDefinition result = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefId).singleResult();
		String name = result.getDiagramResourceName();
		InputStream inputStream = repositoryService.getResourceAsStream(result.getDeploymentId(), name);
		return inputStream;
	}
}
