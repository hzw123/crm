package cn.mauth.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrmBossApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmBossApplication.class, args);
	}

}
