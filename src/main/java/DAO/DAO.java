/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author savio
 */
public class DAO {
    
    public Connection connect() throws ClassNotFoundException{
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:" + "5432/oflix";
            String usuario = "postgres";
            String senha = "admin";
            
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
}
