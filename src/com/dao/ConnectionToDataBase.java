package com.dao;
import java.sql.* ;
public class ConnectionToDataBase {
    private String url;
    private String username;
    private String password;


    public ConnectionToDataBase() {
        this.url = "jdbc:postgresql://localhost/"+Setting.DATABASE_NAME;
        this.username = Setting.USER;
        this.password = Setting.PASSWORD;
    }

    public Connection createConnection(){
        try{
            Connection connection = DriverManager.getConnection(
                    this.url, this.username, this.password
            );

            return connection;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
