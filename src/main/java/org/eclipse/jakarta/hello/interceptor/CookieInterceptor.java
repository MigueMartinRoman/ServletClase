package org.eclipse.jakarta.hello.interceptor;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter(servletNames = {"photosServlet", "photoFormServlet"})
public class CookieInterceptor implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        HttpSession httpSession = request.getSession();
        LocalDateTime lastactivity = (LocalDateTime) httpSession.getAttribute("lastactivity");

        LocalDateTime now = LocalDateTime.now();
        //String autenticado = (String)httpSession.getAttribute("autenticado");

        if (lastactivity != null && lastactivity.plusSeconds(30).isBefore(now)) {
            // Patrón: chain of responsability
            httpSession.invalidate();
            response.sendRedirect("login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
