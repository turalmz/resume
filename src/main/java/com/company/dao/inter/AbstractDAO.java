/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author TURAL
 */
public class AbstractDAO {//data access object
    public static Connection connect() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/resume";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url,username,password);
        return conn;
    } 
}
