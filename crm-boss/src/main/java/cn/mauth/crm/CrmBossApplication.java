package cn.mauth.crm;

import cn.mauth.crm.common.properties.WxAuth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(value={WxAuth.class})
public class CrmBossApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmBossApplication.class, args);
	}

}
