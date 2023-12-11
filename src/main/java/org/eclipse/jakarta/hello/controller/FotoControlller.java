package org.eclipse.jakarta.hello.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.eclipse.jakarta.hello.config.MysqlConnection;
//import org.eclipse.jakarta.hello.dao.FotoDao;
import org.eclipse.jakarta.hello.dao.FotoDaoI;
import org.eclipse.jakarta.hello.dao.FotoDatabaseDao;
import org.eclipse.jakarta.hello.model.Foto;
import org.eclipse.jakarta.hello.service.FotoDatabaseService;
import org.eclipse.jakarta.hello.service.FotoMockService;
import org.eclipse.jakarta.hello.service.CrudServiceI;
import org.eclipse.jakarta.hello.service.FotoServiceI;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

@WebServlet(name = "photosServlet", value = "/photos")
public class FotoControlller extends HttpServlet {

    FotoServiceI fotosService;

    Properties properties = new Properties();

    public  FotoControlller() throws IOException {
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
        /*File directorioFotos = new File("C:\\Users\\Migue\\DAW 2023-2024\\Entorno Servidor\\jakartaee-hello-world\\src\\main\\webapp\\upload");

        File[] fotos = directorioFotos.listFiles();

        for (File foto:fotos){
            System.out.println(foto.getName()+"---"+foto.getAbsolutePath());
        }

         */

        /*
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Photos</h1>");
        // For imagenes y pinte <img>
        for (File foto:fotos){
            String img = "<img style=\"width:100px;\" src=\"upload/"+foto.getName()+ "\" alt=\"foto\">";
            out.println(img);
            //out.println("<img src='"+foto.getAbsolutePath()+"'>");
        }

        out.println("</body></html>");
         */
        //ResourceBundle rb = ResourceBundle.getBundle("application");
        try {
            HttpSession httpSession = request.getSession();
            String autenticado = (String)httpSession.getAttribute("autenticado");

            String politecnic = properties.getProperty("politecnic.prova");

            List<Foto> fotos =fotosService.findAll();

            if(autenticado!=null && autenticado.equals("SI")) {
                fotos = fotos.stream().filter(f->!f.getPrivada()).toList();

                //request.setAttribute("pictures",fotos.get(0));
                request.setAttribute("pictures", fotos);
                // recuperamos param titulo y usamos en fotos.jsp
                request.setAttribute("titulo", politecnic);
                //request.setAttribute("titulo", rb.getString("politecnic.prova"));
                // Patrón Dispatcher
                request.getRequestDispatcher("fotos.jsp").forward(request, response);
            } else {
                response.sendError(401,"Usuario no autorizado");
            }
        } catch (Exception ignored) {}
    }





}
