package com.haeahn.project_vanilla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

//@RestController
//@SpringBootApplication
// default ds 생성하지 않음 > 요청으로 동적 생성
//@EnableCaching
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProjectVanillaApplication {
	public static void main(String[] args) {
//		SpringApplication.run(SvrWorkApplication.class, args);
		try {
//			SpringApplication.run(ProjectVanillaApplication.class, args);
			ConfigurableApplicationContext context = SpringApplication.run(ProjectVanillaApplication.class, args);
			String env = context.getEnvironment().getProperty("env");
			String port = context.getEnvironment().getProperty("server.port");
			System.out.println("env : " + env + ", port : " + port);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}



