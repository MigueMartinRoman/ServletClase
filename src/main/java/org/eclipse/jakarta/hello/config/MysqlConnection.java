package org.eclipse.jakarta.hello.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MysqlConnection {

    private Connection conn = null;
    private static MysqlConnection instance; // SIEMPRE MISMA INSTANCIA (SINGLETON)

    private MysqlConnection() { // PRIVATE PARA SINGLETON
        try {
            InputStream input = this.getClass().getClassLoader().getResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(input);

            final String bd = properties.getProperty("mysql.database");
            final String host = properties.getProperty("mysql.host");
            final String usuario = properties.getProperty("mysql.username");
            final String password = properties.getProperty("mysql.password");

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //DriverManager.registerDriver(new org.maria.jdbc.Driver());
            conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + bd, usuario,password);
            //conn = DriverManager.getConnection("jdbc:maria");
            if (conn != null){
                System.out.println("Conectado a la BBDD ["+conn+"] - OK");
            }
        } catch (Exception e) {
            System.out.println("Error MYSQL: " + e.getMessage());
        }
    }

    public static MysqlConnection getInstance(){
        if(instance == null) {
            instance = new MysqlConnection();
        }
        return instance;
    }

    public Connection getConexion(){
        return conn;
    }

    public void desconecta(){
        System.out.println("Desconectando de la BBDD...");
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e){
                System.out.println("No se ha podido cerrar la BBDD. Motivo :" + e.getMessage());
            }
        }
    }

}
