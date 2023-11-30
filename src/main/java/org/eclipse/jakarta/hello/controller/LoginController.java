package org.eclipse.jakarta.hello.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="LoginControllerServlet",urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("loginForm.jsp").forward(req,res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String usuario = req.getParameter("user");
        String password = req.getParameter("password");

        if (usuario.equals("politecnic") && password.equals("1234")){
            // Crear la sesión
            HttpSession session = req.getSession();
            session.setAttribute("usuario",usuario);
            session.setAttribute("autenticado","SI");

            res.sendRedirect("photos");
        } else {
            req.setAttribute("error","Usuario no válido");
            req.getRequestDispatcher("loginForm.jsp").forward(req,res);
        }
    }
}
