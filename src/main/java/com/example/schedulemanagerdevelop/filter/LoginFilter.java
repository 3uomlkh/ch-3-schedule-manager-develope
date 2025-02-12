package com.example.schedulemanagerdevelop.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/auth/signup", "/auth/login", "/auth/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 실행: {}", requestURI);

        if (!isWhiteList(requestURI)) {
            // 세션이 없는 경우에 새로운 세션 생성 하지 않음
            HttpSession session = httpRequest.getSession(false);

            // 로그인하지 않는 사용자인 경우
            if (session == null || session.getAttribute("sessionKey") == null) {
                log.warn("비로그인 유저 요청: {}", requestURI);
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
                return;
            }

            log.info("로그인된 유저 요청: {}", session.getAttribute("sessionKey"));
        }

        chain.doFilter(request, response);

    }

    // WHITE LIST에 포함되는지 확인하는 메서드
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
