package org.eclipse.jakarta.hello.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.eclipse.jakarta.hello.config.MysqlConnection;
import org.eclipse.jakarta.hello.dao.UsuarioDao;
import org.eclipse.jakarta.hello.dao.UsuarioDaoI;
import org.eclipse.jakarta.hello.model.Usuario;
import org.eclipse.jakarta.hello.service.UsuarioService;
import org.eclipse.jakarta.hello.service.UsuarioServiceI;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name="LoginControllerServlet",urlPatterns = "/login")
public class LoginController extends HttpServlet {

    UsuarioServiceI usuarioService;

    public LoginController(){
        UsuarioDaoI usuarioDao = new UsuarioDao();
        this.usuarioService = new UsuarioService(usuarioDao);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("loginForm.jsp").forward(req,res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        MysqlConnection mysqlConnection = MysqlConnection.getInstance();

        try {
            Usuario usuario = this.usuarioService.findUsuarioByUsernameAndPassword(user, password);
            if (usuario != null) {
                // Crear la sesión
                HttpSession session = req.getSession();
                session.setAttribute("usuario", usuario);
                session.setAttribute("autenticado", "SI");
                session.setAttribute("lastactivity", LocalDateTime.now());

                if (remember != null && remember.equals("true")){
                    //Cookie rememberCookie = new Cookie("remember", "SI");
                    Cookie rememberCookie = new Cookie("remember", usuario.getNombre());
                    rememberCookie.setMaxAge(60 * 60 * 24 * 365);
                    res.addCookie(rememberCookie);
                }
                res.sendRedirect("photos");
            } else {
                req.setAttribute("error", "Usuario no válido");
                req.getRequestDispatcher("loginForm.jsp").forward(req, res);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
