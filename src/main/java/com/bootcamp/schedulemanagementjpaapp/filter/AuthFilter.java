package com.bootcamp.schedulemanagementjpaapp.filter;

import com.bootcamp.schedulemanagementjpaapp.contstant.ResponseCode;
import com.bootcamp.schedulemanagementjpaapp.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.repository.UserJPARepository;
import com.bootcamp.schedulemanagementjpaapp.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
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
                String headerToken = request.getHeader("Authorization");

                if (StringUtils.hasText(headerToken)) {
                    String token = headerToken.replace("Bearer ", "");

                    jwtUtil.verifyToken(token);
                    String email = jwtUtil.getUserEmail(token);

                    if (StringUtils.hasText(email)) {
                        userRepository.findByEmail(email)
                                .orElseThrow(() -> new ApiException(ResponseCode.NOT_EXIST_USER));

                        request.setAttribute("email", email);

                        filterChain.doFilter(request, response);
                    }

                } else {
                    throw new ApiException(ResponseCode.NOT_EXIST_TOKEN);
                }
            }
        } catch (ApiException e) {
            response.setCharacterEncoding("UTF-8");
            response.setStatus(e.getHttpStatus().value());
            response.getWriter().print(e.getResultMessage());
        }
    }
}