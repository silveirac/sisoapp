package com.dh.clinica.config;


import java.sql.*;


public class ConfiguracaoJDBC {
    private String jdbcDriver;
    private String dbUrl;
    private String user;
    private String password;

    public ConfiguracaoJDBC() {
        this.jdbcDriver = "org.h2.Driver";
        this.dbUrl = "jdbc:h2:mem:clinica;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'create.sql'";
        this.user = "sa";
        this.password = "";
    }
    public Connection conectaBancoDeDados(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(dbUrl,user,password);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
