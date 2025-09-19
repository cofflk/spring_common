plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.haeahn"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-aop")
//	implementation("org.springframework.boot:spring-boot-starter-graphql")
//	implementation("org.springframework.boot:spring-boot-starter-validation")
// Web Client
	implementation("org.springframework.boot:spring-boot-starter-webflux")

//  Local Cache
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("com.github.ben-manes.caffeine:caffeine") // 3.2.1

//	Redis - Global Cache
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
//	implementation("org.springframework.data:spring-data-redis")

// db connection
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")

// swagger > http://localhost:8080/swagger-ui/index.html
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")

// jackson
	implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")


// ================= local project dependencies

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
