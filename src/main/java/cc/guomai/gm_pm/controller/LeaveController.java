package cc.guomai.gm_pm.controller;

import cc.guomai.gm_pm.bean.LeaveInfo;
import cc.guomai.gm_pm.service.ILeaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@RestController
@RequestMapping("/leave")
public class LeaveController {

    Logger logger = LoggerFactory.getLogger(LeaveController.class);

    @Resource
    private ILeaveService leaveService;

    /**
     * 发起申请，新增信息
     *
     * @param leaveInfo
     * @return
     */
    @PostMapping("/addLeaveInfo")
    public String addLeaveInfo(@RequestBody LeaveInfo leaveInfo) {
        leaveService.addLeaveAInfo(leaveInfo);
        return "新增成功...";
    }

    /**
     * 查询当前用户的任务列表
     *
     * @param userId
     * @param request
     * @return
     */
    @GetMapping("/getTaskByUserId")
    public Object getTaskByUserId(String userId, HttpServletRequest request) {
        return leaveService.getByUserId(userId);
    }

    /**
     * 处理完成任务
     *
     * @param taskId
     * @param userId
     * @param audit
     * @param request
     * @return
     */
    @PutMapping("/completeTask")
    public String completeTask(String taskId, String userId, String audit, HttpServletRequest request) {
        leaveService.completeTaskByUser(taskId, userId, audit);
        return "审批完成...";
    }


    /*@RequestMapping("/showImg")
    public void showImg(String processDefId, HttpServletRequest request, HttpServletResponse response) {

        try {
            InputStream inputStream = TestLeaveService.findProcessPic(processDefId);
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (IOException e) {
            logger.error("读取流程图片出错", e);
        }

    }*/

}
