package com.bootcamp.schedulemanagementjpaapp.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
    // common response code
    SUCCESS("정상 처리되었습니다", HttpStatus.OK),

    // schedule response code
    FAIL_REGISTER_SCHEDULE("일정 등록에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_GET_SCHEDULE("일정 조회에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_UPDATE_SCHEDULE("일정 수정에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_EXIST_SCHEDULE("존재하지 않는 일정입니다.", HttpStatus.NOT_FOUND),

    // comment response code
    FAIL_REGISTER_COMMENT("댓글 등록에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_GET_COMMENT("댓글 조회에 실패하였습니다", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_UPDATE_COMMENT("댓글 수정에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_DELETE_COMMENT("댓글 삭제에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_EXIST_COMMENT("존재하지 않는 댓글입니다.", HttpStatus.NOT_FOUND),
    UNAUTHORIZED_UPDATE_COMMENT("타인이 작성한 댓글은 수정이 불가능합니다.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED_DELETE_COMMENT("타인이 작성한 댓글은 삭제가 불가능합니다.", HttpStatus.UNAUTHORIZED),

    // user response code
    FAIL_REGISTER_USER("사용자 등록에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_LOGIN_USER("사용자 로그인에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_GET_USER("사용자 조회에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_UPDATE_USER("사용자 수정에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_DELETE_USER("사용자 삭제에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_USER_AUTHORITY("유효하지 않은 권한입니다.", HttpStatus.BAD_REQUEST),
    REQUIRED_ADMIN_AUTHORITY("관리자 권한이 필요합니다.", HttpStatus.FORBIDDEN),
    DUPLICATE_USER_EMAIL("이미 존재하는 계정입니다.", HttpStatus.CONFLICT),
    NOT_EXIST_USER("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND),
    WRONG_EMAIL_OR_PASSWORD("잘못된 이메일 또는 비밀번호입니다.", HttpStatus.UNAUTHORIZED),
    REQUIRED_ACCESS_TOKEN("토큰이 필요합니다.", HttpStatus.BAD_REQUEST),
    INVALID_ACCESS_TOKEN("유효하지 않은 토큰입니다.", HttpStatus.BAD_REQUEST),
    EXPIRE_ACCESS_TOKEN("유효 기간이 만료된 토큰입니다.", HttpStatus.UNAUTHORIZED)
    ;

    private final String resultMessage;
    private final HttpStatus httpStatus;

    ResponseCode(String resultMessage, HttpStatus httpStatus) {
        this.resultMessage = resultMessage;
        this.httpStatus = httpStatus;
    }
}
