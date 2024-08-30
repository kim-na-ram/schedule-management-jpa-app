package com.bootcamp.schedulemanagementjpaapp.common.filter;

import com.bootcamp.schedulemanagementjpaapp.common.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.common.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static com.bootcamp.schedulemanagementjpaapp.common.constant.Const.USER_AUTHORITY;
import static com.bootcamp.schedulemanagementjpaapp.common.constant.Const.USER_EMAIL;
import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.REQUIRED_ACCESS_TOKEN;

@Slf4j(topic = "AuthenticationFilter")
@Order(1)
@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter implements FilterUtil {
    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String BEARER_PREFIX = "Bearer ";

    private final List<String> whiteList = List.of("/api/auth");

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String url = request.getRequestURI();

            if (!checkUrlPath(url, whiteList)) {
                String headerToken = request.getHeader(AUTHORIZATION_HEADER);

                if (StringUtils.hasText(headerToken)) {
                    log.info("JWT 토큰 검증 시작");
                    String token = headerToken.replace(BEARER_PREFIX, "");

                    jwtUtil.verifyToken(token);

                    log.info("JWT 토큰 검증 완료");

                    String email = jwtUtil.getUserEmail(token);
                    String authority = jwtUtil.getAuthority(token);

                    if (StringUtils.hasText(email) && StringUtils.hasText(authority)) {
                        log.info("JWT 토큰으로부터 User 정보 보내줌");

                        request.setAttribute(USER_EMAIL, email);
                        request.setAttribute(USER_AUTHORITY, authority);
                    }

                } else {
                    throw new ApiException(REQUIRED_ACCESS_TOKEN);
                }
            }

            filterChain.doFilter(request, response);
        } catch (ApiException e) {
            errorResponse(response, e);
        }
    }
}