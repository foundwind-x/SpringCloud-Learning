package com.didispace.config.filter;

import com.didispace.common.servlet.BufferedServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: red
 * @Date: 2019/3/6 17:49
 * @Description:
 */
@Slf4j
public class SignatureFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = new BufferedServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {

    }
}
