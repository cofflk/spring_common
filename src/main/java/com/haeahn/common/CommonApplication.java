package com.haeahn.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

//@EnableCaching
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class, // DB 연결(DataSource) 자동 구성 제외
		DataSourceTransactionManagerAutoConfiguration.class, // JDBC 기반 트랜잭션 매니저 자동 구성을 제외.
		HibernateJpaAutoConfiguration.class, // JPA(Hibernate) 관련 자동 구성을 제외
})
@Slf4j
public class CommonApplication {
	public static void main(String[] args) {
//		SpringApplication.run(SvrWorkApplication.class, args);
		try {
			ConfigurableApplicationContext context = SpringApplication.run(CommonApplication.class, args);
			String env = context.getEnvironment().getProperty("env");
			String appName = context.getEnvironment().getProperty("spring.application.name");
			String port = context.getEnvironment().getProperty("server.port");
			System.out.println("hello Auth-SpringBoot: appName: " + appName + ", env : " + env + ", port : " + port);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Failed to start application", e);
			System.exit(1);
		}
	}
}



