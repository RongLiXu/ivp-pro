package com.xunyat.iot.vmp.conf.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录成功
 */
@Component
public class LogoutHandler implements LogoutSuccessHandler {

    private final static Logger logger = LoggerFactory.getLogger(LogoutHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String username = request.getParameter("username");
        logger.info("[退出登录成功] - [{}]", username);
    }
}
