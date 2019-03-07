package cn.mauth.crm;

import cn.mauth.crm.common.properties.WxAuth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value={WxAuth.class})
public class CrmLeaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmLeaveApplication.class, args);
	}

}
