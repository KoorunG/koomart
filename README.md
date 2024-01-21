# 코틀린 토이 프로젝트

### 0. 목적
공부 내용 복습 겸 개인 포트폴리오를 위한 토이 프로젝트
도서 API 연동하여 이것저것 붙여보는 것이 목표
<br/>
그 중 `Kotlin` 복습을 우선으로 한다.   

### 1. 사용 기술
#### back-end
* `Kotlin`
* `Springboot`
* `JPA`
* `postgresql`, `h2`(테스트용 메모리 DB로 사용)
#### front-end
* `TypeScript`
* `React`
#### 외부 라이브러리
* `kotlin-faker`
* `spring-security`
#### CI/CD
* `Github Action`
* `AWS EC2`
* `AWS S3`
* `AWS CodeDeploy`
#### DB
* `postgresql` (`AWS RDS`를 이용하여 띄움)


### 2. 목표

* 일단은 도서 검색 API를 이용한 도서 판매 프로젝트로 가자
* 회원가입 - 인증 - 네이버, 카카오 로그인 API 등 붙이기
* 물품구매 - 외부 API (ex - 아임포트) 붙이기
* 포인트 적립, 이벤트, 지역별 인기도서 등 기능도 염두에 두자
* 깃허브 액션이나 AWS 등을 이용하여 CI/CD 환경을 구축해보자
* `kotest` 코틀린 테스팅 라이브러리와 `kotlin-faker` mocking 라이브러리를 사용해서 꼼꼼한 테스트를 작성해보자.

### 3. 기타 고려사항
* 일단 `구현`에 초점을 맞춘다. 이후 리팩토링을 하는 방식으로..
* 프로젝트 진척사항을 `Obsidian`에 빠짐없이 기록한다.
* `commit message`는 최대한 신경써서 작성한다.
