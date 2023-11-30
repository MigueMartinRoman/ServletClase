package org.eclipse.jakarta.hello.service;

//import org.eclipse.jakarta.hello.dao.FotoDao;
import org.eclipse.jakarta.hello.dao.FotoDaoI;
import org.eclipse.jakarta.hello.model.Foto;

import java.util.List;

public class FotoDatabaseService implements FotoServiceI {

    private final FotoDaoI fotoDao;

    public FotoDatabaseService(FotoDaoI fotoDao){
        this.fotoDao = fotoDao;
    }

    public List<Foto> findAll(){
        // FotoDao fotoDao = new FotoDao(); NO DEBERIA HACERLO AQUI
        return fotoDao.findAll();
    }

    @Override
    public void insert(Foto foto){
        fotoDao.save(foto);
    }
}
