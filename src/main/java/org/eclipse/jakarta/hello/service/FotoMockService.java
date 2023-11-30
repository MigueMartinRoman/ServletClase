package org.eclipse.jakarta.hello.service;

import org.eclipse.jakarta.hello.model.Foto;

import java.util.ArrayList;
import java.util.List;

public class FotoMockService implements FotoServiceI {
    public List<Foto> findAll(){
        Foto foto = new Foto();
        foto.setPrivada(false);
        foto.setDescripcion("Hombre conduciendo un coche");
        foto.setNombre("car");
        foto.setUrl("https://images.westend61.com/0001379526pw/happy-man-driving-car-EBBF00067.jpg");

        Foto foto2 = new Foto();
        foto2.setPrivada(true);
        foto2.setDescripcion("Hombre conduciendo un coche");
        foto2.setNombre("car");
        foto2.setUrl("https://images.westend61.com/0001379526pw/happy-man-driving-car-EBBF00067.jpg");

        Foto foto3 = new Foto();
        foto3.setPrivada(false);
        foto3.setDescripcion("Hombre conduciendo un coche");
        foto3.setNombre("car");
        foto3.setUrl("https://images.westend61.com/0001379526pw/happy-man-driving-car-EBBF00067.jpg");

        List<Foto> fotos = new ArrayList<>();
        fotos.add(foto);
        fotos.add(foto2);
        fotos.add(foto3);

        return fotos;
    }
}
