# Koomart 토이 프로젝트

### 0. 목적
공부 내용 복습 겸 개인 포트폴리오를 위한 토이 프로젝트
<br/>
그 중 `Kotlin` 복습을 우선으로 한다.   

### 1. 무슨 기술을 쓸건데?
#### back-end
* `Kotlin`
* `Springboot`
* `JPA`
* `postgresql`, `h2`(테스트용 메모리 DB로 사용)
#### front-end
* `TypeScript`
* `React`
####  외부 라이브러리
* `kotlin-faker`
* `spring-security`

### 2. 뭘 만들건데?

* 일단은 도서 검색 API를 이용한 도서 판매 프로젝트로 가자
* 회원가입 - 인증 - 네이버, 카카오 로그인 API 등 붙이기
* 물품구매 - 외부 API (ex - 아임포트) 붙이기
* 포인트 적립, 이벤트, 지역별 인기도서 등 기능도 염두에 두자 

### 3. 기타 고려사항
* 일단 `구현`에 초점을 맞춘다. 이후 리팩토링을 하는 방식으로..
* 프로젝트 진척사항을 `Obsidian`에 빠짐없이 기록한다.
* `commit message`는 최대한 신경써서 작성한다.
* Mocking은 `kotlin-faker` 라이브러리를 사용한다.