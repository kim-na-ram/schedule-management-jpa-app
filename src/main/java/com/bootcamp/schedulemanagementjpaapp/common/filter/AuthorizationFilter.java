package com.bootcamp.schedulemanagementjpaapp.common.filter;

import com.bootcamp.schedulemanagementjpaapp.common.enums.Authority;
import com.bootcamp.schedulemanagementjpaapp.common.exception.ApiException;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.common.constant.Const.USER_AUTHORITY;
import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.REQUIRED_ADMIN_AUTHORITY;

@Slf4j(topic = "AuthorizationFilter")
@Order(2)
@Component
@NonNullApi
public class AuthorizationFilter extends OncePerRequestFilter implements FilterUtil {
    private List<String> blackList = List.of("/api/schedules");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String url = request.getRequestURI();

            if (checkUrlPath(url, blackList)) {
                if (checkHttpMethod(request.getMethod(), List.of("PATCH", "DELETE"))) {
                    String authority = String.valueOf(request.getAttribute(USER_AUTHORITY));

                    log.info("권한 체크");
                    if (!authority.equals(Authority.ADMIN.getUserRole())) {
                        throw new ApiException(REQUIRED_ADMIN_AUTHORITY);
                    }
                }
            }

            filterChain.doFilter(request, response);
        } catch (ApiException e) {
            errorResponse(response, e);
        }
    }
}
