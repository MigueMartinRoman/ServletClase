package org.eclipse.jakarta.hello.interceptor;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        HttpSession httpSession = request.getSession();
        String autenticado = (String)httpSession.getAttribute("autenticado");

        if (autenticado!=null && autenticado.equals("SI")){
            // Patrón: chain of responsability
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
