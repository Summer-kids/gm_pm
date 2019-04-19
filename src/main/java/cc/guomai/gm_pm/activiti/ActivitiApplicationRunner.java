package cc.guomai.gm_pm.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 自定义ApplicationRunner,用于springboot容器启动之后执行特定的方法
 *implements ApplicationRunner
 * @author ZhangQI
 * @date 2019.04.18
 */
@Component
public class ActivitiApplicationRunner {

    /*Logger logger = LoggerFactory.getLogger(ActivitiApplicationRunner.class);

    @Resource
    RepositoryService repositoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        logger.info("--------开始部署流程--------");
        //部署流程引擎
        repositoryService.createDeployment()
                .name("pm")
                .addClasspathResource("processes/leave_bill.bpmn").deploy();


        logger.info("--------流程已部署--------");
    }*/
}
