package org.eclipse.jakarta.hello.service;

import org.eclipse.jakarta.hello.dao.UsuarioDao;
import org.eclipse.jakarta.hello.dao.UsuarioDaoI;
import org.eclipse.jakarta.hello.model.Usuario;

public class UsuarioService implements UsuarioServiceI{
    private final UsuarioDaoI usuarioDao;

    public UsuarioService(UsuarioDaoI usuarioDao){
        this.usuarioDao = usuarioDao;
    }

    @Override
    public Usuario findUsuarioByUsernameAndPassword(String user, String password) {
        return this.usuarioDao.findUsuarioByUsernameAndPassword(user, password);
    }
}
