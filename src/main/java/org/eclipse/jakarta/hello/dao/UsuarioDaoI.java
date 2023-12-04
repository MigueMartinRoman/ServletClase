package org.eclipse.jakarta.hello.dao;

import org.eclipse.jakarta.hello.model.Usuario;

import java.util.List;

public interface UsuarioDaoI {
    List<Usuario> findAll();
    Usuario findUsuarioByUsernameAndPassword(String user, String password);
}
