package cc.guomai.gm_pm;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GmPmApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmPmApplication.class, args);
    }
}
