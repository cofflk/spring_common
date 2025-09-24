### common project

> - spring boot : 3.4.4
> - last update : 2025-08-08
> - SDK : Java21 / eclipse-temurin

### 프로젝트 네이밍 룰
- [프로젝트 네이밍 룰](https://haeahn.sharepoint.com/:fl:/g/contentstorage/CSP_6ec15de9-2fe9-4a2d-813a-0fbac99469d8/ES_v_bhiGhJAs0PwpyiD40AB9iYEpPWNoTL47B5uIJsK6A?e=oLhMNg&nav=cz0lMkZjb250ZW50c3RvcmFnZSUyRkNTUF82ZWMxNWRlOS0yZmU5LTRhMmQtODEzYS0wZmJhYzk5NDY5ZDgmZD1iJTIxNlYzQmJ1a3ZMVXFCT2ctNnlaUnAyUFFkWTRaUUljVkJtX00xcWlDbERzcEdvZUNhM09aR1JMd1VDeEs4UFBsNSZmPTAxSFM3WFFSQlA1NzYzUVlRMkNKQUxHUTdRVTRVSUhZMkEmYz0lMkYmYT1Mb29wQXBwJnA9JTQwZmx1aWR4JTJGbG9vcC1wYWdlLWNvbnRhaW5lciZ4PSU3QiUyMnclMjIlM0ElMjJUMFJUVUh4b1lXVmhhRzR1YzJoaGNtVndiMmx1ZEM1amIyMThZaUUyVmpOQ1luVnJka3hWY1VKUFp5MDJlVnBTY0RKUVVXUlpORnBSU1dOV1FtMWZUVEZ4YVVOc1JITndSMjlsUTJFelQxcEhVa3gzVlVONFN6aFFVR3cxZkRBeFNGTTNXRkZTUmxSWlZGZElXa3hXVFROR1IwbFpURlJaUmtKQ1JrOUxUbFUlM0QlMjIlMkMlMjJpJTIyJTNBJTIyNmY1MDczZjktYmE1NS00NmM2LWJiNTEtNDJhMWI0Mzc3ZDgxJTIyJTdE)
 

### 프로젝트 변경
> - /settings.gradle.kts => rootProject.name = "common"
> - /src/main/resources/application.properties => spring.application.name=project-vanilla
> - /src/main/com/haeahn/{project-vanilla}
> - /src/main/com/haeahn/{ProjectVanilla}Application
> - SwaggerConfig

### Configuration
- application.yml
> - application name, version
- application-(dev | prod).yml
> - domain 설정
> - logging 설정
> - jwt, ldap 설정 (auth)
- application-database-(dev | prod).yml
- application-secret-(dev | prod).yml

### Gradle Dependencies
* storage
> - local cache: caffeine
> - global cache: redis
> - database: mssql - jdbc
     [//]: # ( >> - mongo, qdrant)
* utils
> - jackson

### Deploy - (Linux)
> - Project: /codeH/{Project-Name}
> - Configurations: /codeH/config/{lang}-{framework}/
>> - e.g. /codeH/config/java-springboot/resources/*.yml
>> - e.g. /codeH/config/java-springboot/libs/*.jar
> - Logging: /logs

### Deploy
- [Jenkins (Linux)](https://haeahn.sharepoint.com/:fl:/g/contentstorage/CSP_6ec15de9-2fe9-4a2d-813a-0fbac99469d8/EXmZAS0Be01GoJoT7Vfu3GkBfk8gZ6K7ZUBf_DRjIW21SQ?e=syMC0X&nav=cz0lMkZjb250ZW50c3RvcmFnZSUyRkNTUF82ZWMxNWRlOS0yZmU5LTRhMmQtODEzYS0wZmJhYzk5NDY5ZDgmZD1iJTIxNlYzQmJ1a3ZMVXFCT2ctNnlaUnAyUFFkWTRaUUljVkJtX00xcWlDbERzcEdvZUNhM09aR1JMd1VDeEs4UFBsNSZmPTAxSFM3WFFSRFpURUFTMkFMM0pWREtCR1FUNVZMNjVYREomYz0lMkYmYT1Mb29wQXBwJnA9JTQwZmx1aWR4JTJGbG9vcC1wYWdlLWNvbnRhaW5lciZ4PSU3QiUyMnclMjIlM0ElMjJUMFJUVUh4b1lXVmhhRzR1YzJoaGNtVndiMmx1ZEM1amIyMThZaUUyVmpOQ1luVnJka3hWY1VKUFp5MDJlVnBTY0RKUVVXUlpORnBSU1dOV1FtMWZUVEZ4YVVOc1JITndSMjlsUTJFelQxcEhVa3gzVlVONFN6aFFVR3cxZkRBeFNGTTNXRkZTUmxSWlZGZElXa3hXVFROR1IwbFpURlJaUmtKQ1JrOUxUbFUlM0QlMjIlMkMlMjJpJTIyJTNBJTIyY2M4ZDlmZTEtZTI0Yi00NzJjLWE4NmYtNmVhNWUyYmVlNmZlJTIyJTdE) 

>> - Dockerfile : docker container
>> - Jenkins/Jenkinsfile : CI/CD pipeline (Jenkinsfile, Jenkinsfile-dev, ...)

### project structure
```
.
└── src/
    ├── [project]
    └── global/
        ├── annotation : aop, scheduler 어노테이션 클래스
        ├── filter : 공통 비즈니스 로직 (보안설정, 로깅 등)
        ├── interceptor : 공통 비즈니스 로직 (인증, 인가 처리)
        ├── aop : 공통 비즈니스 로직
        ├── config : 설정 클래스
        ├── provider : filter 로직 구현
        ├── token : filter <> provider 데이터 공유
        ├── context : SecurityContext, ThreadLocal
        ├── controller : URL 경로 맵핑
        ├── service : 비즈니스 로직
        ├── repository : storage 요청 부분
        ├── dto : data class, 비즈니스 로직 내부
        ├── payload : data class, controller <> cline 데이터 포맷/
        │   ├── request : API 요청 클래스
        │   └── response : API 응답 클래스
        └── storage /
            ├── cache/
            │   ├── local : JVM 내부 캐시 e.g. Caffeine
            │   └── redis : 서버 간 캐시 공유 
            ├── mssql 
            ├── mongodb
            └── etc.
```

==========

Global Cache
- Redis : API 서버간 데이터 공유

[1단계] Caffeine (JVM Local)
- ↓ Miss

[2단계] Redis (Global)
- ↓ Miss

[3단계] DB or API
- ↑ Set Redis
- ↑ Set Caffeine

1. caffeine : 1분 | 30분
2. redis : data 별 TTL 설정
3. db : 영속성
