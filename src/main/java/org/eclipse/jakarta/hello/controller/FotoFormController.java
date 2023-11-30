package org.eclipse.jakarta.hello.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jakarta.hello.dao.FotoDaoI;
import org.eclipse.jakarta.hello.dao.FotoDatabaseDao;
import org.eclipse.jakarta.hello.model.Foto;
import org.eclipse.jakarta.hello.service.FotoDatabaseService;
import org.eclipse.jakarta.hello.service.FotoMockService;
import org.eclipse.jakarta.hello.service.FotoServiceI;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "photoFormServlet", value = "/photoForm")
public class FotoFormController extends HttpServlet {

    FotoServiceI fotosService;

    Properties properties = new Properties();

    public FotoFormController() throws IOException {
        //MysqlConnection mysql = new MysqlConnection();
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        properties.load(input);


        boolean isDevelopment = Boolean.parseBoolean(properties.getProperty("development"));

        if (isDevelopment){
            fotosService = new FotoMockService();
        } else {
            // El control ya no está en el Service, sino que estará fuera
            FotoDaoI fotoDao = new FotoDatabaseDao();
            fotosService = new FotoDatabaseService(fotoDao);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            request.getRequestDispatcher("fotoForm.jsp").forward(request, response);
        } catch (Exception ignored) {}
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Ha entrado al post de Foto");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String url = request.getParameter("url");
        Boolean privada = false;
        if(
                request.getParameter("privada")!=null &&
                request.getParameter("privada").equals("si")
        ){
            privada = true;
        }

        Foto foto = new Foto();
        foto.setNombre(nombre);
        foto.setDescripcion(descripcion);
        foto.setUrl(url);
        foto.setPrivada(privada);

        try {
            fotosService.insert(foto);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        response.sendRedirect("photos");
    }

}
