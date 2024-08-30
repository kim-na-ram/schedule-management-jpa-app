package com.bootcamp.schedulemanagementjpaapp.common.exception;

import com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final String resultMessage;
    private final HttpStatus httpStatus;

    public ApiException(ResponseCode responseCode){
        super("[" + responseCode.getHttpStatus() + "] " + responseCode.getResultMessage());
        this.resultMessage = responseCode.getResultMessage();
        this.httpStatus = responseCode.getHttpStatus();
    }

    @Override
    public String getMessage() {
        return resultMessage;
    }
}