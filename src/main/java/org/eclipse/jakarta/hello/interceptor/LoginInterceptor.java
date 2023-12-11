package org.eclipse.jakarta.hello.interceptor;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(servletNames = {"photosServlet","photoFormServlet"})
//@WebFilter(urlPatterns = {/admin/*}) _> TODO BAJO ESA URL VA AUTENTICADO (EJ: admin/photos)
public class LoginInterceptor implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
