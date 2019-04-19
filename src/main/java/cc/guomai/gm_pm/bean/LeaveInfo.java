package cc.guomai.gm_pm.bean;


/**
 * 请假单信息
 *
 * @author ZhangQI
 * @date 2019.04.18
 */
public class LeaveInfo {

	private String leaveId;  //请假单ID
	private String applicantID;  //申请人ID
	private String applicantName;  //申请人姓名

	private String taskId;  //activiti任务ID


	public String getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}

	public String getApplicantID() {
		return applicantID;
	}

	public void setApplicantID(String applicantID) {
		this.applicantID = applicantID;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
}
