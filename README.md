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
