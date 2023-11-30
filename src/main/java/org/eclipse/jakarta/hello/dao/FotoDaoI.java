package org.eclipse.jakarta.hello.dao;

import org.eclipse.jakarta.hello.model.Foto;

import java.util.List;

public interface FotoDaoI {
    List<Foto> findAll();
    void save(Foto foto);

}
