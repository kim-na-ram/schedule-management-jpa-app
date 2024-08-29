# 일정 관리 앱 서버
## ERD
![스크린샷 2024-08-29 오전 10 19 24](https://github.com/user-attachments/assets/1929a170-b246-47c5-a611-ea6d5cc454f9)

<br>

## API 명세서
### 일정 등록
URL : `POST` `/api/schedules`
- Request Header

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | Authorization | String | O | JWT 토큰 |

- Request Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | title | String | O | 일정 제목 |
    | 0 | contents | String | O | 일정 내용 |

- Response Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | scheduleId | Long | O | 일정 고유번호 |
    | 0 | title | String | O | 일정 제목 |
    | 0 | contents | String | O | 일정 내용 |
    | 0 | email | String | O | 작성 유저 이메일 |
    | 0 | userName | String | O | 작성 유저명 |
    | 0 | weather | String | O | 작성일 날씨 |
    | 0 | regDate | LocalDateTime | O | 등록일 |
    | 0 | updateDate | LocalDateTime | O | 수정일 |

- 응답 코드

    | Result Message | HTTP Status |
    | --- | --- |
    | 정상 처리되었습니다. | 200 |
    | 존재하지 않는 담당자입니다. | 404 |
    | 일정 등록에 실패하였습니다. | 500 |

<br>

#### Request Json
```json
{
    "title" : "string",
    "contents" : "string"
}
```

#### Response Json
```json
{
    "scheduleId": 1,
    "title": "string",
    "contents": "string",
    "regDateWeather": "Freezing",
    "userName": "string",
    "userEmail": "string@string.com",
    "commentCount": 0,
    "managerList": [],
    "regDate": "2024-08-29T10:41:23.604395",
    "updateDate": "2024-08-29T10:41:23.604395"
}
```
<br>

### 일정 조회
URL : `GET` `/api/schedules/{scheduleId}`
- Path Variable

    | depth | 변수명        | 타입   | 필수 여부 | 비고    |
    | --- | --- | --- | --- | --- |
    | 0 | scheduleId | Long | O | 일정 고유번호 |

- Request Header

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | Authorization | String | O | JWT 토큰 |

- Response Body

    | depth | 변수명 | 타입             | 필수 여부 | 비고 |
    | --- | --- |----------------| --- | --- |
    | 0 | scheduleId | Long           | O | 일정 고유번호 |
    | 0 | title | String         | O | 일정 제목 |
    | 0 | contents | String         | O | 일정 내용 |
    | 0 | regDateWeather | String         | O | 작성일 날씨 |
    | 0 | commentCount | Integer        | O | 댓글 개수 |
    | 0 | userName | String         | O | 작성 유저명  |
    | 0 | userEmail | String         | O | 작성 유저 이메일 |
    | 0 | managerList | List\<Object\> | O | 담당 유저 목록 |
    | 1 | managerId | Long           | X | 담당 유저 고유번호 |
    | 1 | managerName | String         | X | 담당 유저명  |
    | 1 | managerEmail | String         | X | 담당 유저 이메일 |
    | 0 | regDate | LocalDateTime  | O | 등록일 |
    | 0 | updateDate | LocalDateTime  | O | 수정일 |

- 응답 코드

    | Result Message | HTTP Status |
    | --- | --- |
    | 정상 처리되었습니다. | 200 |
    | 유효하지 않은 토큰입니다. | 400 |
    | 유효 기간이 만료된 토큰입니다. | 401 |
    | 존재하지 않는 사용자입니다. | 404 |
    | 존재하지 않는 일정입니다. | 404 |
    | 일정 조회에 실패하였습니다. | 500 |

<br>

#### Response Json
```json
{
    "scheduleId": 1,
    "title": "string",
    "contents": "string",
    "regDateWeather": "Freezing",
    "userName": "string",
    "userEmail": "string@string.com",
    "commentCount": 0,
    "managerList": [],
    "regDate": "2024-08-29T10:41:23.604395",
    "updateDate": "2024-08-29T10:41:23.604395"
}
```
<br>

### 일정 목록 조회
URL : `GET` `/api/schedules`

- Query Parameter

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | pageNumber | Integer | X | 페이지 번호 |
    | 0 | pageSize | Integer | X | 페이지 크기 |

- Request Header

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | Authorization | String | O | JWT 토큰 |

- Response Body

    | depth | 변수명            | 타입             | 필수 여부 | 비고        |
    | --- |----------------|----------------| --- |-----------|
    | 0 | scheduleList   | List\<Object\> | O | 일정 목록     |
    | 1 | scheduleId     | Long           | O | 일정 고유번호   |
    | 1 | title          | String         | O | 일정 제목    |
    | 1 | contents       | String         | O | 일정 내용    |
    | 1 | regDateWeather | String         | O | 작성일 날씨    |
    | 1 | userName       | String         | O | 작성 유저명    |
    | 1 | userEmail      | String         | O | 작성 유저 이메일 |
    | 1 | commentCount   | Integer        | O | 댓글 개수     |
    | 1 | regDate        | LocalDateTime  | O | 등록일       |
    | 1 | updateDate     | LocalDateTime  | O | 수정일       |

<br>

#### Response Json
```json
[
    {
        "scheduleId": 2,
        "title": "string",
        "contents": "string",
        "regDateWeather": "Freezing",
        "userName": "string",
        "userEmail": "string@string.com",
        "commentCount": 0,
        "regDate": "2024-08-29T10:41:23.604395",
        "updateDate": "2024-08-29T10:41:23.604395"
    },
    {
        "scheduleId": 1,
        "title": "string",
        "contents": "string",
        "regDateWeather": "Freezing",
        "userName": "string",
        "userEmail": "string@string.com",
        "commentCount": 0,
        "regDate": "2024-08-29T10:41:23.604395",
        "updateDate": "2024-08-29T10:41:23.604395"
    }
]
```
<br>

### 일정 수정
URL : `PATCH` `/api/schedules/{scheduleId}`
- Path Variable

    | depth | 변수명        | 타입   | 필수 여부 | 비고    |
    | --- | --- | --- | --- | --- |
    | 0 | scheduleId | Long | O | 일정 고유번호 |

- Request Header

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | Authorization | String | O | JWT 토큰 |

- Request Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | title | String | X | 일정 제목 |
    | 0 | contents | String | X | 일정 내용 |
    | 0 | managerList | List<String> | X | 담당 유저 목록 |
    | 1 | managerEmail | String | X | 담당 유저 이메일 |

- Response Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | scheduleId | Long | O | 일정 고유번호 |
    | 0 | title | String | O | 일정 제목 |
    | 0 | contents | String | O | 일정 내용 |
    | 0 | regDateWeather | String | O | 작성일 날씨 |
    | 0 | userName | String | O | 작성 유저명 |
    | 0 | userEmail | String | O | 작성 유저 이메일 |
    | 0 | managerList | List<String> | O | 담당 유저 목록 |
    | 1 | managerId | Long | X | 담당 유저 고유번호 |
    | 1 | managerName | String | X | 담당 유저명 |
    | 1 | managerEmail | String | X | 담당 유저 이메일 |
    | 0 | regDate | LocalDateTime | O | 등록일 |
    | 0 | updateDate | LocalDateTime | O | 수정일 |

- 응답 코드

    | Result Message | HTTP Status |
    | --- | --- |
    | 정상 처리되었습니다. | 200 |
    | 유효하지 않은 토큰입니다. | 400 |
    | 유효 기간이 만료된 토큰입니다. | 401 |
    | 관리자 권한이 필요합니다. | 403 |
    | 존재하지 않는 일정입니다. | 404 |
    | 존재하지 않는 사용자입니다. | 404 |
    | 일정 수정에 실패하였습니다. | 500 |

<br>

#### Request Json
```json
{
    "title": "modify string",
    "contents" : "modify string",
    "managerList": ["string@string.com"]
}
```

#### Response Json
```json
{
    "scheduleId": 1,
    "title": "modify string",
    "contents": "modify string",
    "regDateWeather": "Freezing",
    "userName": "string",
    "userEmail": "string@string.com",
    "commentCount": 0,
    "managerList": [
        {
            "managerId": 1,
            "managerName": "string",
            "managerEmail": "string@string.com"
        }
    ],
    "regDate": "2024-08-29T10:41:23.604395",
    "updateDate": "2024-08-29T10:41:23.604395"
}
```

### 일정 삭제
URL : `DELETE` `/api/schedules/{scheduleId}`

- Path Variable

    | depth | 변수명        | 타입   | 필수 여부 | 비고    |
    | --- | --- | --- | --- | --- |
    | 0 | scheduleId | Long | O | 일정 고유번호 |

- Request Header

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | Authorization | String | O | JWT 토큰 |

- 응답 코드

    | Result Message    | HTTP Status |
    |-------------------| --- |
    | 정상 처리되었습니다.       | 200 |
    | 유효하지 않은 토큰입니다.    | 400 |
    | 유효 기간이 만료된 토큰입니다. | 401 |
    | 관리자 권한이 필요합니다.    | 403 |
    | 존재하지 않는 일정입니다.    | 404 |
    | 존재하지 않는 사용자입니다.   | 404 |
    | 일정 삭제에 실패하였습니다.   | 500 |

<br>

### 회원가입
URL : `POST` `/api/auth/join`

- Request Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | name | String | O | 유저명 |
    | 0 | email | String | O | 이메일 |
    | 0 | password | String | O | 비밀번호(암호화) |
    | 0 | authority | String | X | 권한 (관리자 or 일반 사용자) |

- Response Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | userId | Long | O | 유저 고유번호 |
    | 0 | name | String | O | 유저명 |
    | 0 | email | String | O | 이메일 |
    | 0 | regDate | LocalDateTime | O | 등록일 |
    | 0 | updateDate | LocalDateTime | O | 수정일 |
    | 0 | accessToken | String | O | JWT 토큰 |

- 응답 코드

    | Result Message | HTTP Status |
    | --- | --- |
    | 정상 처리되었습니다. | 200 |
    | 이미 존재하는 계정입니다. | 409 |
    | 사용자 등록에 실패하였습니다. | 500 |

<br>

#### Request Json
```json
{
    "name": "string",
    "password": "12341234",
    "email": "string@string.com",
    "authority": "관리자"
}
```

#### Response Json
```json
{
    "userId": 1,
    "name": "string",
    "email": "string@string.com",
    "authority": "관리자",
    "regDate": "2024-08-29T09:51:52.576106",
    "updateDate": "2024-08-29T09:51:52.576106",
    "accessToken": "stringstring.stringstringstring.stringstringstring"
}
```
<br>

### 로그인
URL : `POST` `/api/auth/login`

- Request Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고             |
    | --- | --- | --- | --- |----------------|
    | 0 | email | String | O | 이메일            |
    | 0 | password | String | O | 암호화된 비밀번호 |

- Response Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | accessToken | String | O | JWT 토큰 |

- 응답 코드

    | Result Message | HTTP Status |
    | --- | --- |
    | 정상 처리되었습니다. | 200 |
    | 잘못된 이메일 또는 비밀번호입니다. | 401 |
    | 로그인에 실패하였습니다. | 500 |

<br>

#### Request Json
```json
{
    "email": "string@string.com",
    "password": "12341234"
}
```

#### Response Json
```json
{
    "accessToken": "stringstring.stringstringstring.stringstringstring"
}
```

### 사용자 조회
URL : `GET` `/api/users/{userId}`

- Response Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | userId | Long | O | 유저 고유번호 |
    | 0 | name | String | O | 유저명 |
    | 0 | email | String | O | 이메일 |
    | 0 | authority | String | O | 권한 |
    | 0 | regDate | LocalDateTime | O | 등록일 |
    | 0 | updateDate | LocalDateTime | O | 수정일 |

- 응답 코드

    | Result Message | HTTP Status |
    | --- | --- |
    | 정상 처리되었습니다. | 200 |
    | 존재하지 않는 사용자입니다. | 404 |
    | 사용자 조회에 실패하였습니다. | 500 |

<br>

#### Response Json
```json
{
  "userId": 1,
  "name": "홍길동",
  "email": "string@test.com",
  "authority": "관리자",
  "regDate": "2024-08-29T10:41:02.501496",
  "updateDate": "2024-08-29T10:41:02.501496"
}
```
<br>

### 사용자 목록 조회
URL : `GET` `/api/users`

- Response Body

    | depth | 변수명 | 타입             | 필수 여부 | 비고 | 
    | --- | --- |----------------| --- | --- |
    | 0 | userList | List\<Object\> | O | 유저 목록 |
    | 1 | userId | Long           | O | 유저 고유번호 |
    | 1 | name | String         | O | 유저명 |
    | 1 | email | String         | O | 이메일 |
    | 1 | authority | String         | O | 권한 |
    | 1 | regDate | LocalDateTime  | O | 등록일 |
    | 1 | updateDate | LocalDateTime  | O | 수정일 |

- 응답 코드

    | Result Message | HTTP Status |
    | --- | --- |
    | 정상 처리되었습니다. | 200 |
    | 사용자 조회에 실패하였습니다. | 500 |

<br>

#### Response Json
```json
[
    {
        "userId": 1,
        "name": "string",
        "email": "string@string.com",
        "authority": "관리자",
        "regDate": "2024-08-29T10:41:02.501496",
        "updateDate": "2024-08-29T10:41:02.501496"
    },
    {
        "userId": 2,
        "name": "string",
        "email": "string1@string.com",
        "authority": "일반 사용자",
        "regDate": "2024-08-29T11:51:52.576106",
        "updateDate": "2024-08-29T13:21:24.738973"
    }
]
```
<br>

### 사용자 수정
URL :`PATCH` `/api/users`

- Query Parameter

    | depth | 변수명 | 타입     | 필수 여부 | 비고 |
    | --- | --- |--------| --- | --- |
    | 0 | userEmail | String | O | 수정할 유저 이메일 |

- Request Header

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | Authorization | String | O | JWT 토큰 |

- Request Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | name | String | O | 유저명 |

- Response Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | userId | Long | O | 유저 고유번호 |
    | 0 | name | String | O | 유저명 |
    | 0 | email | String | O | 이메일 |
    | 0 | authority | String | O | 권한 |
    | 0 | regDate | LocalDateTime | O | 등록일 |
    | 0 | updateDate | LocalDateTime | O | 수정일 |

- 응답 코드

    | Result Message | HTTP Status |
    | --- | --- |
    | 정상 처리되었습니다. | 200 |
    | 유효하지 않은 토큰입니다. | 400 |
    | 유효 기간이 만료된 토큰입니다. | 401 |
    | 존재하지 않는 사용자입니다. | 404 |
    | 사용자 수정에 실패하였습니다. | 500 |

<br>

#### Request Json
```json
{
    "name": "홍길동"
}
```

#### Response Json
```json
{
    "userId": 1,
    "name": "홍길동",
    "email": "string@string.com",
    "authority": "관리자",
    "regDate": "2024-08-29T10:41:02.501496",
    "updateDate": "2024-08-29T10:41:02.501496"
}
```
<br>

### 사용자 삭제
URL : `DELETE` `/api/users`

- Query Parameter

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | userEmail | String | O | 삭제할 유저 이메일 |

- Request Header

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | Authorization | String | O | JWT 토큰 |

- 응답 코드

    | Result Message | HTTP Status |
    | --- | --- |
    | 정상 처리되었습니다. | 200 |
    | 유효하지 않은 토큰입니다. | 400 |
    | 유효 기간이 만료된 토큰입니다. | 401 |
    | 존재하지 않는 사용자입니다. | 404 |
    | 사용자 삭제에 실패하였습니다. | 500 |

<br>

### 댓글 등록

URL : `POST` `/api/schedules/{scheduleId}/comments`

- Request Header

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | Authorization | String | O | JWT 토큰 |

- Request Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | contents | String | O | 댓글 내용 |

- Response Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | commentId | Long | O | 댓글 고유번호 |
    | 0 |  scheduleId | Long | O | 일정 고유번호 | 
    | 0 | contents | String | O | 댓글 내용 |
    | 0 | userName | String | O | 작성 유저명 |
    | 0 | userEmail | String | O | 작성 유저 이메일 |
    | 0 | regDate | LocalDateTime | O | 등록일 |
    | 0 | updateDate | LocalDateTime | O | 수정일 |

- 응답 코드

    | Result Message | HTTP Status |
    | --- | -- |
    | 정상 처리되었습니다. | 200 |
    | 존재하지 않는 일정입니다. | 404 |
    | 댓글 등록에 실패하였습니다. | 500|
<br>

### Request Json
```json
{
    "contents": "string"
}
```

### Response Json
```json
{
    "commentId": 1,
    "scheduleId": 1,
    "userName": "홍길동",
    "userEmail": "string@string.com",
    "content": "string",
    "regDate": "2024-08-29T13:34:39.779722",
    "updateDate": "2024-08-29T13:34:39.779722"
}
```
<br>

### 댓글 조회
URL : `GET` `/api/schedules/{scheduleId}/comments/{commentId}`

- Path Variable

    | depth | 변수명        | 타입   | 필수 여부 | 비고      |
    | --- | --- | --- | --- |---------|
    | 0 | scheduleId | Long | O | 일정 고유번호 |
    | 0 | commentId | Long | O | 댓글 고유번호 |

- Request Header

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | Authorization | String | O | JWT 토큰 |

- Response Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | commentId | Long | O | 댓글 고유번호 |
    | 0 |  scheduleId | Long | O | 일정 고유번호 | 
    | 0 | contents | String | O | 댓글 내용 |
    | 0 | userName | String | O | 작성 유저명 |
    | 0 | userEmail | String | O | 작성 유저 이메일 |
    | 0 | regDate | LocalDateTime | O | 등록일 |
    | 0 | updateDate | LocalDateTime | O | 수정일 |

- 응답 코드

    | Result Message | HTTP Status |
    | --- | --- |
    | 정상 처리되었습니다. | 200 |
    | 존재하지 않는 일정입니다. | 404 |
    | 존재하지 않는 댓글입니다. | 404 |
    | 댓글 조회에 실패하였습니다. | 500 |

<br>

#### Response Json
```json
{
    "commentId": 1,
    "scheduleId": 1,
    "userName": "string",
    "userEmail": "string@string.com",
    "content": "string",
    "regDate": "2024-08-29T13:34:39.779722",
    "updateDate": "2024-08-29T13:47:04.822419"
}
```
<br>

### 댓글 목록 조회
URL : `GET` `/api/schedules/{scheduleId}/comments`

- Response Body

    | depth | 변수명 | 타입             | 필수 여부 | 비고 |
    |-------| --- |----------------| --- | --- |
    | 0     | commentList | List\<Object\> | O | 댓글 목록 |
    | 1     | commentId | Long           | O | 댓글 고유번호 |
    | 1     |  scheduleId | Long | O | 일정 고유번호 | 
    | 1     | contents | String         | O | 댓글 내용 |
    | 1     | userName | String         | O | 작성 유저명 |
    | 1     | userEmail | String | O | 작성 유저 이메일 |
    | 1     | regDate | LocalDateTime  | O | 등록일 |
    | 1     | updateDate | LocalDateTime  | O | 수정일 |

- 응답 코드

    | Result Message | HTTP Status |
    | --- | --- |
    | 정상 처리되었습니다. | 200 |
    | 존재하지 않는 일정입니다. | 404 |
    | 댓글 조회에 실패하였습니다. | 500 |
<br>

#### Response Json
```json
[
    {
        "commentId": 1,
        "scheduleId": 1,
        "userName": "홍길동",
        "userEmail": "string@string.com",
        "content": "string",
        "regDate": "2024-08-29T13:34:39.779722",
        "updateDate": "2024-08-29T13:47:04.822419"
    },
    {
      "commentId": 2,
      "scheduleId": 1,
      "userName": "홍길동",
      "userEmail": "string@string.com",
      "content": "string",
      "regDate": "2024-08-29T13:47:10.822419",
      "updateDate": "2024-08-29T13:47:04.822419"
    }
]
```
<br>

### 댓글 수정
URL :`PATCH` `/api/schedules/{scheduleId}/comments/{commentId}`

- Path Variable

    | depth | 변수명        | 타입   | 필수 여부 | 비고      |
    | --- | --- | --- | --- |---------|
    | 0 | scheduleId | Long | O | 일정 고유번호 |
    | 0 | commentId | Long | O | 댓글 고유번호 |

- Request Header

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | Authorization | String | O | JWT 토큰 |

- Request Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | contents | String | O | 댓글 내용 |
- Response Body

    | depth | 변수명 | 타입 | 필수 여부 | 비고      |
    | --- | --- | --- | --- |---------|
    | 0 | commentId | Long | O | 댓글 고유번호 |
    | 0 | scheduleId | Long | O | 일정 고유번호 | 
    | 0 | contents | String | O | 일정 내용   |
    | 0 | userName | String | O | 작성 유저명  |
    | 0 | regDate | LocalDateTime | O | 등록일     |
    | 0 | updateDate | LocalDateTime | O | 수정일     |

- 응답 코드

    | Result Message | HTTP Status |
    | --- | --- |
    | 정상 처리되었습니다. | 200 |
    | 타인이 작성한 댓글은 수정이 불가능합니다. | 401 |
    | 존재하지 않는 일정입니다. | 404 |
    | 존재하지 않는 댓글입니다. | 404 |
    | 댓글 수정에 실패하였습니다. | 500 |

<br>

#### Request Json
```json
{
    "contents": "modify string"
}
```

#### Response Json
```json

{
    "commentId": 1,
    "scheduleId": 1,
    "userName": "홍길동",
    "userEmail": "string@string.com",
    "content": "modify string",
    "regDate": "2024-08-29T13:34:39.779722",
    "updateDate": "2024-08-29T13:34:39.779722"
}
```
<br>

### 댓글 삭제

URL : `DELETE` `/api/schedules/{scheduleId}/comments/{commentId}`

- Path Variable

    | depth | 변수명        | 타입   | 필수 여부 | 비고      |
    | --- | --- | --- | --- |---------|
    | 0 | scheduleId | Long | O | 일정 고유번호 |
    | 0 | commentId | Long | O | 댓글 고유번호 |

- Request Header

    | depth | 변수명 | 타입 | 필수 여부 | 비고 |
    | --- | --- | --- | --- | --- |
    | 0 | Authorization | String | O | JWT 토큰 |

- 응답 코드

    | Result Message          | HTTP Status |
    |-------------------------| --- |
    | 정상 처리되었습니다.             | 200 |
    | 타인이 작성한 댓글은 삭제가 불가능합니다. | 401 |
    | 존재하지 않는 일정입니다.          | 404 |
    | 존재하지 않는 댓글입니다.          | 404 |
    | 댓글 삭제에 실패하였습니다.         | 500 |
