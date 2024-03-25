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


## 프로젝트
### 1. 프로젝트 소개

### 2. 프로젝트 기능

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

