# SpringSlackApiDemo
SpringBoot 2.0 에서 spring-boot-starter-webflux : WebClient를 통해서 Slack API 호출하는 샘플 코드

## Slack API Doc Site
* https://api.slack.com/docs/

## Getting Slack Token Site
* https://api.slack.com/custom-integrations/legacy-tokens

## Maven
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

## 첨부파일
* 위치: attachment
* 파일: english.txt, 한글테스트.txt

## 구현된 서비스
* 서비스 위치 : src/main/java/spring.slack.api.demo.service
* 클래스 : SlackApiServiceImpl
* 구현 내용 : 특정 채널에 파일 업로드

## Slack token 및 업로드 체널 설정
* 위치 : src/main/resources
* 파일 : application.yml

