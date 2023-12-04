package org.eclipse.jakarta.hello.dao;

import org.eclipse.jakarta.hello.config.MysqlConnection;
import org.eclipse.jakarta.hello.model.Foto;
import org.eclipse.jakarta.hello.model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements UsuarioDaoI{
    public List<Usuario> findAll() {
        try {
            // MysqlConnection connection = new MysqlConnection(); NO PUEDO HACER NEW
            MysqlConnection connection = MysqlConnection.getInstance(); // ASI SIEMPRE COJEMOS LA MISMA CONEXION

            // EL PARAMETRO NUNCA VA EN LA QUERY
            String sql = "select * from usuarios";

            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            // 1.Crear objeto a retornar
            List<Usuario> resultado = new ArrayList<>();

            // 2. Recorremos ResultSet y rellenamos la lista
            while(resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setUsername(resultSet.getString("username"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setNombre(resultSet.getString("nombre"));

                resultado.add(usuario);
            }

            return resultado;

        } catch (Exception e) {
            System.out.println("Error findAllUsuarios:" + e.getMessage());
        }
        return null;
    }

    @Override
    public Usuario findUsuarioByUsernameAndPassword(String user, String password) {
        try {
            MysqlConnection connection = MysqlConnection.getInstance();
            String sql = "select * from usuarios where usuario=? and password=?";

            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(sql);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setUsername(resultSet.getString("usuario"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setNombre(resultSet.getString("id"));
                return usuario;
            }

            return null;
        } catch (Exception e){
            System.out.println("Error findUsuarioByUsernameAndPassword");
        }
        return null;
    }
}
