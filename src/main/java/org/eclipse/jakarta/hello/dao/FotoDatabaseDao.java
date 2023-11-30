package org.eclipse.jakarta.hello.dao;

import org.eclipse.jakarta.hello.config.MysqlConnection;
import org.eclipse.jakarta.hello.model.Foto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FotoDatabaseDao implements FotoDaoI{

    public List<Foto> findAll() {
        try {
            // MysqlConnection connection = new MysqlConnection(); NO PUEDO HACER NEW
            MysqlConnection connection = MysqlConnection.getInstance(); // ASI SIEMPRE COJEMOS LA MISMA CONEXION

            // EL PARAMETRO NUNCA VA EN LA QUERY
            String sql = "select * from foto";

            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            // 1.Crear objeto a retornar
            List<Foto> resultado = new ArrayList<>();

            // 2. Recorremos ResultSet y rellenamos la lista
            while(resultSet.next()){
                Foto foto = new Foto();
                foto.setPrivada(false);
                foto.setUrl(resultSet.getString("url"));
                foto.setNombre(resultSet.getString("nombre"));

                resultado.add(foto);
            }

            return resultado;

        } catch (Exception e) {
            System.out.println("Error findAllFotos:" + e.getMessage());
        }
        return null;
    }

    @Override
    public void save(Foto foto) {
        try {
            MysqlConnection connection = MysqlConnection.getInstance();
            //String sql = "insert into foto(id,nombre,url) values (?,?,?)";
            String sql = "insert into foto(nombre,url) values (?,?)";

            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(sql);
            //preparedStatement.setLong(1,foto.getId());
            preparedStatement.setString(1,foto.getNombre());
            preparedStatement.setString(2,foto.getUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
