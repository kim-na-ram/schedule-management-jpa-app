package com.bootcamp.schedulemanagementjpaapp.common.filter;

import com.bootcamp.schedulemanagementjpaapp.common.exception.ApiException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface FilterUtil {
    default boolean checkUrlPath(String url, List<String> urlList) {
        return urlList.stream().anyMatch(url::startsWith);
    }

    default boolean checkHttpMethod(String method, List<String> allowedMethods) {
        return allowedMethods.stream().anyMatch(allowedMethod -> allowedMethod.equalsIgnoreCase(method));
    }

    default void errorResponse(HttpServletResponse response, ApiException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(e.getHttpStatus().value());
        response.getWriter().print(e.getResultMessage());
    }
}