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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TURAL
 */
public class LoginDaoImpl extends AbstractDAO implements LoginDaoInter {


    public Login getLogin(ResultSet rs) throws SQLException {

        int id = rs.getInt("Id");
        String name = rs.getString("username");
        String password = rs.getString("password");
        int usrId = rs.getInt("user_id");
        Login log = new Login(id, name,password,new User(usrId)) ;

        System.out.println(log);
        return log;

    }


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

                Login log = getLogin(rs) ;

                list.add(log);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return list;
    }

    @Override
    public Login getById(int userId) {
        Login log = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM login WHERE ID = ?");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                 log = getLogin(rs) ;

            }
        } catch (Exception ex) {

        }
        return log;
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
    public Login getByUserameAndPassword(String sname,String pswrd) {
        Login log = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM login WHERE username = ? AND password = ?;");
            stmt.setString(1, sname);
            stmt.setString(2, pswrd);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                log = getLogin(rs) ;

            }
        } catch (Exception ex) {
            System.err.println("Houston, we have a problem");
        }
        return log;
    }

}
