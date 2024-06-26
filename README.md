## 목차
- [프로젝트](#프로젝트)
  - [프로젝트 소개](#1-프로젝트-소개)    
  - [프로젝트 기능](#2-프로젝트-기능)    
  - [사용 기술](#3-사용-기술)
     - [개발 Tool](#3-1-개발-tool)
     - [백엔드](#3-2-백엔드)
     - [프론트엔드](#3-3-프론트엔드)

- [구조 및 설계](#구조-및-설계)
  - [패키지 구조](#1-패키지-구조)
  - [DB 설계](#2-db-설계)
  - [API 설계](#3-api-설계)

- [실행 화면](#실행-화면)
<br>

## 프로젝트
### 1. 프로젝트 소개
SpringBoot 를 이용한 기본적인 CRUD 게시판에 다양한 라이브러리를 활용하여 입체적인 게시판 구축
- **Spring Security** - 회원정보, 게시판 권한 등 다양한 보안 설정
- **JPA** - DB와 연동하여 많은 양의 데이터를 효율적으로 사용
- **Validation** - 데이터 유효성 검사
- **AJAX** - 비동기 통신을 활용한 댓글 기능

### 2. 프로젝트 기능
- **Board** - CRUD, 페이징, 검색, 조회수
- **User** - 로그인, 로그아웃, 회원등록, 회원정보, 비밀번호변경, 회원탈퇴
- **Comment** - 등록, 삭제

### 3. 사용 기술

#### 3-1 개발 Tool
- IntelliJ IDEA 2023.3.5 (Community Edition)
- Visual Studio Code 1.87.2

#### 3-2 백엔드

##### 주요 프레임워크 / 라이브러리
- Java 17
- SpringBoot 3.2.3
- JPA(Spring Data JPA)
- Spring Security
- Thymeleaf
- Validation
- Devtools
- Lombok
  
##### Build Tool
- Gradle 7.4

##### DataBase
- MariaDB 10.11
- MySQL Workbench 8.0 CE

#### 3-3 프론트엔드
- HTML/CSS
- JavaScript
- Bootstrap 5.3.3
- Thymeleaf
<br>

## 구조 및 설계   
   
### 1. 패키지 구조
```
📦src
 ┣ 📂main
 ┃ ┣ 📂generated
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂wj
 ┃ ┃ ┃ ┃ ┗ 📂board
 ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomUserDetails.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomUserDetailsService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebSecurityConfig.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentApiController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜HomeController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserEntity.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂validator
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AbstractValidator.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CustomValidators.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardApplication.java
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┃ ┗ 📂css
 ┃ ┃ ┃ ┃ ┣ 📜sign-in.css
 ┃ ┃ ┃ ┃ ┗ 📜sticky-footer-navbar.css
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┣ 📜detail.html
 ┃ ┃ ┃ ┃ ┣ 📜list.html
 ┃ ┃ ┃ ┃ ┣ 📜update.html
 ┃ ┃ ┃ ┃ ┗ 📜write.html
 ┃ ┃ ┃ ┣ 📂fragments
 ┃ ┃ ┃ ┃ ┗ 📜common.html
 ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┣ 📜changePassword.html
 ┃ ┃ ┃ ┃ ┣ 📜info.html
 ┃ ┃ ┃ ┃ ┣ 📜login.html
 ┃ ┃ ┃ ┃ ┗ 📜register.html
 ┃ ┃ ┃ ┣ 📜index.html
 ┃ ┃ ┃ ┗ 📜message.html
 ┃ ┃ ┗ 📜application.properties
```

### 2. DB 설계
#### board table
![DB구조2](https://github.com/zwanz94/BoardProject/assets/141385082/acb30d1d-152d-4fea-945b-9013a7e88f5a)
#### user table
![DB구조3](https://github.com/zwanz94/BoardProject/assets/141385082/db0b0fd9-a084-4530-8e24-09a12d30b73f)
#### comment table
![DB구조](https://github.com/zwanz94/BoardProject/assets/141385082/d562f041-dd0f-4eb2-9ed1-36a02c87275e)

### 3. API 설계
#### Board API
|**기능**|**Method**|**URL**|**Return Page**|
|------|---|---|---|
|전체 목록|GET|/boards/list|전체 목록 페이지|
|페이징/검색|GET|/boards/list/?page={pagenumber}&searchKeyword={keyword}|전체 목록 페이지|
|상세 보기|GET|/boards/list/{id}|상세 페이지|
|등록|GET|/boards/write|등록 폼 페이지|
|등록 폼|POST|/boards/write|전체 목록 페이지|
|삭제|GET|/boards/delete/{id}|전체 목록 페이지|
|수정|GET|/boards/update/{id}|수정 폼 페이지|
|수정 폼|POST|/boards/update|전체 목록 페이지|

#### User API
|**기능**|**Method**|**URL**|**Return Page**|
|------|---|---|---|
|로그인|GET|/users/login|로그인 폼 페이지|
|회원등록|GET|/users/register|회원등록 폼 페이지|
|회원등록 폼|POST|/users/register|로그인 페이지|
|회원정보|GET|/users/info|회원정보 페이지|
|비밀번호변경|GET|/users/change-password|비밀번호변경 폼 페이지|
|비밀번호변경 폼|POST|/users/change-password|회원정보 페이지|
|회원삭제|GET|/users/delete|메인 페이지|

#### Comment API
|**기능**|**Method**|**URL**|
|------|---|---|
|댓글 전체 목록|GET|/boards/list/{boardId}/comments|
|댓글 작성|POST|/boards/list/{boardId}/comments|
|댓글 삭제|DELETE|/boards/list/{boardId}/comments/{commentId}|
<br>

## 실행 화면

<details>
  <summary>비회원</summary>
  <img src="https://github.com/zwanz94/BoardProject/assets/141385082/6c70836a-4126-4ce7-87e3-bab97fa0530c">
  <div align="center">
    <img src="https://github.com/zwanz94/BoardProject/assets/141385082/4bf5c20b-72b3-4d00-b682-e1fe116d2ef4">
  </div>
  <div align="center">비회원 권한 : 메인화면, 게시판, 상세보기</div>
</details>

<details>
  <summary>회원</summary>
  <img src="https://github.com/zwanz94/BoardProject/assets/141385082/18dee57e-4392-43ea-be7e-5114555cdf46">
  <div align="center">
    <img src="https://github.com/zwanz94/BoardProject/assets/141385082/ac33cf91-b2f7-4100-850e-56ad69c6ff0f">
  </div>
  <div align="center">
    <img src="https://github.com/zwanz94/BoardProject/assets/141385082/4ef60f49-b2c3-487f-9e76-0cb883646057">
  </div>
  <div align="center">회원 권한 : 모든 권한</div>
</details>

<details>
  <summary>수정/삭제 권한</summary>
  <img src="https://github.com/zwanz94/BoardProject/assets/141385082/9829e17f-b815-46e8-ada2-9046a8e03f6c">
  <div align="center">
    <img src="https://github.com/zwanz94/BoardProject/assets/141385082/8fddb341-e91b-41b4-ae35-f22f18925f5c">
  </div>
  <div align="center">자신의 글과 댓글만 수정/삭제 권한 부여</div>
</details>

<details>
  <summary>회원 관리</summary>
  <img src="https://github.com/zwanz94/BoardProject/assets/141385082/d9557622-2544-4641-a50b-2f574fbd8181">
  <img src="https://github.com/zwanz94/BoardProject/assets/141385082/b373b7c3-0aac-4ca5-ab97-8f20ee171028">
  <div align="center">로그인, 회원가입, 정보수정, 회원탈퇴</div>
</details>

<details>
  <summary>검색/페이징</summary>
  <img src="https://github.com/zwanz94/BoardProject/assets/141385082/f791213a-9b16-4919-a54c-3962a4161dbc">
  <img src="https://github.com/zwanz94/BoardProject/assets/141385082/c32f34b0-33ae-449b-ad09-ca6d9c9448e9">
  <div align="center">prev - 이전 페이지리스트의 마지막 페이지 / next - 다음 페이지리스트의 첫번째 페이지</div>
</details>

<details>
  <summary>유효성 검사</summary>
  <div align="center">
    <img src="https://github.com/zwanz94/BoardProject/assets/141385082/ce618946-af56-49e6-a50e-63436e12c939">
  </div>
  <img src="https://github.com/zwanz94/BoardProject/assets/141385082/de99fd28-ee71-4e7f-a03b-de30f53db470">
  <img src="https://github.com/zwanz94/BoardProject/assets/141385082/c98fa40d-5f80-454f-a4d5-8ee250b2d974">
  <div align="center">공백 체크, 중복 체크, 입력값 대조</div>
</details>
