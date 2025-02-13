# 🚀 일정 관리 앱 과제 - develop

![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.2-green?style=for-the-badge&logo=springboot&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-6.6.5.Final-%236DB33F?style=for-the-badge&logo=hibernate&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange?style=for-the-badge&logo=mysql&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-Repository-black?style=for-the-badge&logo=github&logoColor=white)


## 📌 프로젝트 소개
이 프로젝트는 **일정을 효과적으로 관리할 수 있는 일정 관리 앱 API**입니다.  
사용자는 **회원가입 및 로그인**을 통해 인증 후, **일정을 생성, 조회, 수정, 삭제**할 수 있습니다.  
또한, **댓글 기능을 통해 의견을 나누고, 사용자 정보를 조회 및 수정**할 수 있습니다.

### 주요 기능
- **회원 관리**: 회원가입, 로그인, 사용자 조회, 정보 수정, 삭제
- **일정 관리**: 일정 생성, 조회, 수정, 삭제
- **인증 및 권한 관리**: 세션 기반 로그인, 인증 필터 적용
- **댓글 기능**: 일정에 대한 댓글 작성, 조회, 수정, 삭제

### 📜 과제 요구사항 목록
- Lv.0
  - [X] API 명세서 작성
  - [X] ERD 작성
  - [X] SQL 작성
- Lv.1
  - [X] 일정 CRUD
  - [X] `JPA Auditing` 활용
- Lv.2
  - [X] 유저 CRUD
  - [X] `JPA Auditing` 활용
  - [X] 연관관계 구현
- Lv.3
  - [X] 회원가입 기능
  - [X] 유저에 비밀번호 필드 추가
- Lv.4
  - [X] Cookie/Session을 활용한 로그인 및 로그아웃 
  - [X] 필터를 활용한 인증처리
- Lv.5
  - [X] `Validation`을 활용한 다양한 예외처리
  - [X] 정규표현식 사용
- Lv.6
  - [X] 비밀번호 암호화
- Lv.7
  - [X] 댓글 CRUD
  - [X] `JPA Auditing` 활용
  - [X] 연관관계 구현
- Lv.8 일정 페이징 조회
  - [X] `Pageable`과 `Page` 인터페이스를 활용한 페이지네이션
  - [X] 디폴트 페이지 크기는 10으로 적용
  - [X] 일정 수정일 기준 내림차순 정렬

## 🛠️ 기술 스택

- **Language:** Java 17
- **Framework:** Spring Boot 3.4, Spring MVC, Spring Validation
- **Database:** MySQL
- **ORM:** Spring Data JPA (Hibernate)
- **Security:** BCrypt Password Encoder (at.favre.lib:bcrypt)
- **Version Control:** Git, **GitHub**

## 📆 개발 기간
2025-02-06 ~ 2025-02-13

## 📂 프로젝트 구조
```
schedulemanagerdevelop
├── config
├── controller
├── dto
│   ├── request
│   ├── response
├── entity
├── exception
│   ├── custom
│   ├── handler
│   │   ├── ErrorMessage.java
│   │   ├── ErrorResponse.java
├── filter
├── repository
├── service
└── ScheduleManagerDevelopApplication
```

## 📖 API 명세서
### 인증/인가
<div style="overflow-x: auto;">

| **Method** | **Endpoint**              | **Description** | **Parameters** | **Request Body**                                              | **Response**                                          | **Status Code** |
|------------|---------------------------|-----------------|----------------|---------------------------------------------------------------|-------------------------------------------------------|-----------------|
| `POST`     | `/auth/signup`              | 회원가입            | 없음             | `{ "username": string, "email": string, "password": string }` | `{ "id": long, "username": string, "email": string }` | `201 CREATED`        |
| `POST`      | `/auth/login`              | 로그인             | 없음             | `{ "email": string, "password": string }`                                                            | 없음                                                    | `200 OK`        |
| `POST`      | `/auth/logout`         | 로그아웃            | 없음             | 없음                                                            | 없음                                                    | `200 OK`        |
</div>

<br>

### 일정
<div style="overflow-x: auto;">

| **Method** | **Endpoint**       | **Description** | **Parameters**    | **Request Body**                                              | **Response**                                                                                                                                                                          | **Status Code**  |
|------------|--------------------|-----------------|-------------------|---------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------|
| `POST`     | `/schedules`       | 일정 생성           | 없음                | `{ "title": string, "contents": string }`                     | `{ "id": long, "title": string, "contents": string }`                                                                                                                                 | `201 CREATED`    |
| `GET`      | `/schedules/all`   | 일정 목록 조회        | 없음                | 없음                                                            | `[ { "id": long, "title": string, "contents": string }, ... ]`                                                                                                                        | `200 OK`         |
| `GET`      | `/schedules/{id}`  | 일정 단건 조회        | Path: <br> - `id` | 없음                                                            | `{ "id": long, "title": string, "contents": string }`                                                                                                                                 | `200 OK`         |
| `GET`      | `/schedules/paged` | 일정 페이지네이션       | Query: <br> - `page` (default: 1) <br> - `size` (default: 10)           | 없음                                                              | 페이지 형태의 `{ "content": [ { "title": string, "contents": string, "commentCount": long, "createdAt": string, "modifiedAt": string, "username": string }, ... ], "pageable": {...}, ... }` | `200 OK`                 |
| `PUT`      | `/schedules/{id}`  | 일정 수정           | Path: <br> - `id` | `{ "title": string, "contents": string, "password": string }` | 없음                                                                                                                                                                                    | `200 OK`         |
| `DELETE`   | `/schedules/{id}`  | 일정 삭제           | Path: <br> - `id` | 없음                                                            | 없음                                                                                                                                                                                    | `204 NO_CONTENT` |
</div>

<br>

### 유저
<div style="overflow-x: auto;">

| **Method** | **Endpoint**       | **Description** | **Parameters**    | **Request Body**                                   | **Response**                                                                                                                                                                           | **Status Code**  |
|------------|--------------------|-----------------|-------------------|----------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------|
| `GET`      | `/members/{id}`   | 유저 조회           | Path: <br> - `id` | 없음                                                 | `{ "username": string, "email": string }`                                                                                                                                              | `200 OK`         |
| `PATCH`     | `/members/password` | 유저 비밀번호 수정      | 없음                | `{ "oldPassword": string, "newPassword": string }` | 없음                                                                                                                                                                                       | `200 OK`         |
| `PATCH`      | `/members/username` | 유저 이름 수정        | 없음                  | `{ "username": string }`                           | 없음 | `200 OK`                 |
| `DELETE`   | `/members/{id}`  | 유저 삭제           | Path: <br> - `id` | 없음                                                 | 없음                                                                                                                                                                                     | `204 NO_CONTENT` |
</div>

<br>

### 댓글
<div style="overflow-x: auto;">

| **Method** | **Endpoint**              | **Description** | **Parameters**    | **Request Body**         | **Response**                                                                                           | **Status Code**  |
|------------|---------------------------|-----------------|-------------------|--------------------------|--------------------------------------------------------------------------------------------------------|------------------|
| `POST`     | `/schedules/{id}/comments` | 댓글 생성           | Path: <br> - `id` | `{ "contents": string }` | `{ "id": long, "contents": string, "memberName": string, "createdAt": string, "modifiedAt": string  }` | `201 CREATED`    |
| `GET`      | `/schedules/{id}/comments` | 댓글 조회           | Path: <br> - `id`                | 없음                       | `{ "id": long, "contents": string, "memberName": string, "createdAt": string, "modifiedAt": string  }`                                                                                                     | `200 OK`         |
| `PATCH`    | `/comments/{id}`          | 댓글 수정           | Path: <br> - `id`                  | `{ "contents": string }` | `{ "id": long, "contents": string, "memberName": string, "createdAt": string, "modifiedAt": string  }`                                                                                                     | `200 OK`         |
| `DELETE`   | `/comments/{id}`                        | 댓글 삭제           | Path: <br> - `id` | 없음                       | 없음                                                                                                     | `204 NO_CONTENT` |
</div>

<br>

## 🖼️ ERD
![Image](https://github.com/user-attachments/assets/8e2ec89d-4172-4fc7-b9e9-bc237345425a)

## 🗂️ SQL
### schedule
```sql
CREATE TABLE schedule (
    schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    contents LONGTEXT NOT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    modified_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    member_id BIGINT NOT NULL,
    CONSTRAINT fk_schedule_member FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE
);
```
### member
```sql
CREATE TABLE member (
    member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(50) NOT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    modified_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);
```
### comment
```sql
CREATE TABLE comment (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    contents VARCHAR(255) NOT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    modified_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    member_id BIGINT NOT NULL,
    schedule_id BIGINT NOT NULL,
    CONSTRAINT fk_comment_member FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE,
    CONSTRAINT fk_comment_schedule FOREIGN KEY (schedule_id) REFERENCES schedule(schedule_id) ON DELETE CASCADE
);
```