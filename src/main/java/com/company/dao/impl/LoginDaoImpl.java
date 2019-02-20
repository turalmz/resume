/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.LoginDaoInter;
import com.company.entity.Login;
import com.company.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TURAL
 */
public class LoginDaoImpl extends AbstractDAO implements LoginDaoInter {

    @Override
    public List<Login> getAll() {
        List<Login> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM login");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                int id = rs.getInt("Id");
                String name = rs.getString("username");
                String password = rs.getString("password");
                int usrId = rs.getInt("user_id");

                Login log = new Login(id, name,password,new User(usrId)) ;

                list.add(log);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return list;
    }

    @Override
    public Login getById(int userId) {
        Login usr = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM login WHERE ID = ?");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                int id = rs.getInt("Id");
                String name = rs.getString("username");
                String password = rs.getString("password");
                int usrId = rs.getInt("user_id");

                usr = new Login(id, name,password,new User(usrId)) ;

            }
        } catch (Exception ex) {

        }
        return usr;
    }

    @Override
    public boolean updateLogin(Login u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE login SET username=?,password=? WHERE id= ?");
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setInt(3, u.getId());
            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    public boolean insertLogin(Login skl) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("insert login (username,password,user_id) VALUES (?,?,?);",Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, skl.getUsername());
            stmt.setString(2, skl.getPassword());
            stmt.setInt(3, skl.getUser().getId());
            b = stmt.execute();
            ResultSet genKeys = stmt.getGeneratedKeys();
            if(genKeys.next()){
                skl.setId(genKeys.getInt(1));
            }

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    
    @Override
    public boolean removeLogin(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM login WHERE id=?;");
            stmt.setInt(1, id);
            return stmt.execute();


        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
    }

    @Override
    public List<Login> getByName(String sname,String pswrd) {
        List<Login> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM login WHERE username = ? AND password = ?;");
            stmt.setString(1, sname);
            stmt.setString(2, pswrd);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                int id = rs.getInt("Id");
                String name = rs.getString("username");
                String password = rs.getString("password");
                int usrId = rs.getInt("user_id");

                Login log = new Login(id, name,password,new User(usrId)) ;

                list.add(log);

            }
        } catch (Exception ex) {
            System.err.println("Houston, we have a problem");
        }
        return list;
    }

}
