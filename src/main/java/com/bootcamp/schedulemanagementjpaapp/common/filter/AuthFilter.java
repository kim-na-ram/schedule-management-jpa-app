package com.bootcamp.schedulemanagementjpaapp.common.filter;

import com.bootcamp.schedulemanagementjpaapp.common.enums.Authority;
import com.bootcamp.schedulemanagementjpaapp.common.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.common.util.JWTUtil;
import com.bootcamp.schedulemanagementjpaapp.repository.UserJPARepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.bootcamp.schedulemanagementjpaapp.common.constant.Const.USER_AUTHORITY;
import static com.bootcamp.schedulemanagementjpaapp.common.constant.Const.USER_EMAIL;
import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.REQUIRED_ACCESS_TOKEN;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String BEARER_PREFIX = "Bearer ";

    private final JWTUtil jwtUtil;
    private final UserJPARepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String url = request.getRequestURI();

            if (StringUtils.hasText(url)
                    && url.startsWith("/api/auth")) {
                filterChain.doFilter(request, response);
            } else {
                String headerToken = request.getHeader(AUTHORIZATION_HEADER);

                if (StringUtils.hasText(headerToken)) {
                    String token = headerToken.replace(BEARER_PREFIX, "");

                    jwtUtil.verifyToken(token);
                    String email = jwtUtil.getUserEmail(token);
                    String authority = jwtUtil.getAuthority(token);

                    if (StringUtils.hasText(email) && StringUtils.hasText(authority)) {
                        userRepository.existsUserByEmail(email);

                        request.setAttribute(USER_EMAIL, email);
                        request.setAttribute(USER_AUTHORITY, Authority.from(authority));

                        filterChain.doFilter(request, response);
                    }

                } else {
                    throw new ApiException(REQUIRED_ACCESS_TOKEN);
                }
            }
        } catch (ApiException e) {
            response.setCharacterEncoding("UTF-8");
            response.setStatus(e.getHttpStatus().value());
            response.getWriter().print(e.getResultMessage());
        }
    }
}