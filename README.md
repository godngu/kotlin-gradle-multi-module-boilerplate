# Gradle Multi Module 프로젝트  

## 기술 스택

---

- Layered Architecture
- kotlin
- gradle
- JPA
- H2 Database


## 목적

---

- 레이어 단위 모듈을 생성합니다.
- 각 모듈은 자신의 역할만을 수행 합니다.
- 모듈 단위로 필요한 설정 및 의존성만 가져갑니다.
- 계층간의 잘못된 호출을 방지합니다.
 

## 모듈별 목적, 네이밍

---

### api  

#### 목적

- api endpoint 제공
- 입력값 validation 처리

#### src definition

- ex) `MemberController`
- 요청, 응답 객체
  - ex) `MemberRegistrationRequest`, `MemberRegistrationResponse`


### application

#### 목적

- 트랜잭션의 시작이다.
- 도메인를 사용하여 하나의 완젼한 Use case를 구현한다.
- `Domain Entity`를 DTO 형태로 변환하여 `controller`에 전달한다.

#### src definition

- `@UseCase` 커스텀 어노테이션 사용
- ex) `MemberRegistrationUseCase` 인터페이스
- ex) `MemberRegistrationService` UseCase 구현체
- 요청, 응답 객체
  - 요청 객체에서 Command & Query 접미사 사용 
    - ex) `MemberRegistrationCommand` 
    - ex) `MemberSearchQuery`
  - ex) `MemberSearchResult`


### domain


### infrastructure
