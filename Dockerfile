# docker build -f Dockerfile --progress=plain -t <이미지 이름> .
# docker run -d --name <컨테이너 이름> -p 9000:8080 <이미지 이름>
# docker build -f Dockerfile --progress=plain -t vanilla_springboot .
# docker run -d --name vanilla_springboot -p 9000:8080 vanilla_springboot

# 1단계: 빌드 단계
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# 빌드 도구 복사 (Gradle 프로젝트 기준)
# COPY --chown=<user>:<group> . .
# COPY --chown=ekpuser:ekpgroup . .

# COPY . . # 권장하지 않음
# case1 - 명시적 설정
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src
# case2 - .dockerignore 사용
# .git
# build/
# *.iml
# *.log
# .gradle/

# 타임존 설정
ENV TZ=Asia/Seoul
RUN apk add --no-cache tzdata && \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 빌드 수행
# 실행권한 부여
RUN chmod +x gradlew
# RUN ./gradlew clean build --no-daemon
RUN ./gradlew clean build -x test --no-daemon
# 빌드 오류 시 내용 출력
# RUN ./gradlew clean build --no-daemon --stacktrace --info
# RUN ./gradlew clean build -x test --no-daemon --stacktrace --info

# 2단계: 런타임 이미지 jre 경량화 이미지 사용
FROM eclipse-temurin:21-jre-alpine

# alpine 기반 컨테이너에 curl 명령어 설치
RUN apk add --no-cache curl

WORKDIR /app

ENV TZ=Asia/Seoul
RUN apk add --no-cache tzdata && \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 빌드된 JAR 복사
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# 보안설정 START
# 일반 사용자 생성
RUN addgroup -S spring && adduser -S spring -G spring

# 소유권 변경 (권한 이슈 방지용)
RUN chown -R spring:spring /app

# 👇 일반 사용자로 실행
USER spring:spring
# 보안설정 END

# 포트 노출 (Spring Boot 기본 포트 = 8080)
EXPOSE 8080

# 실행 명령
ENTRYPOINT ["java", "-jar", "app.jar"]

