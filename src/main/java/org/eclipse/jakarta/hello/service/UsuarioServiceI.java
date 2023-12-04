package org.eclipse.jakarta.hello.service;

import org.eclipse.jakarta.hello.model.Usuario;

public interface UsuarioServiceI extends CrudServiceI<Usuario> {
    Usuario findUsuarioByUsernameAndPassword(String user, String Password);

}
