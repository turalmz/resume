/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.User;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author TURAL
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    public User getUser(ResultSet rs) throws SQLException {

        int id = rs.getInt("Id");

        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");

        String email = rs.getString("email");
        String phone = rs.getString("phone");

        String address = rs.getString("address");
        String profileDescription = rs.getString("profile_description");
        Date birthDate = null;
        try {
            birthDate = rs.getDate("birth_date");
        } catch (Exception ex) {
            System.err.println("Houston, we have a problem");
        }
        int birthPlace = rs.getInt("birth_place");
        int nationality = rs.getInt("nationality");

        User us = new User(id, firstname, lastname, email, phone, address, profileDescription, birthDate, new Country(birthPlace), new Country(nationality));
        System.out.println(us);
        return us;

    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM USER");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                System.out.println(rs.getInt("Id"));
                System.out.println(rs.getString("firstname"));
                System.out.println(rs.getString("lastname"));
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("phone"));

                User us = getUser(rs);
                list.add(us);

            }
        } catch (Exception ex) {

        }
        return list;
    }

    public List<User> getByUser(User usr) {
        List<User> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USER WHERE firstname LIKE ? AND lastname LIKE ?;");
            stmt.setString(1, usr.getFirstname());
            stmt.setString(2, usr.getLastname());

            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                System.out.println(rs.getInt("Id"));
                System.out.println(rs.getString("firstname"));
                System.out.println(rs.getString("lastname"));
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("phone"));

                User us = getUser(rs);
                list.add(us);
            }
        } catch (Exception ex) {

        }
        return list;
    }

    @Override
    public User getById(int userId) {
        User usr = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USER WHERE ID = ?;");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                System.out.println(rs.getInt("Id"));
                System.out.println(rs.getString("firstname"));
                System.out.println(rs.getString("lastname"));
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("phone"));

                usr = getUser(rs);

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return usr;
    }

    @Override
    public boolean updateUser(User u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE user SET firstname=? , lastname=? , email = ? , phone = ?,profile_description = ? ,address = ?,birth_date =?, birth_place = ?, nationality = ? WHERE id= ?;");
            stmt.setString(1, u.getFirstname());
            stmt.setString(2, u.getLastname());

            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());

            stmt.setString(5, u.getProfileDescription());
            stmt.setString(6, u.getAddress());

            if (u.getBirthDate() != null) {
                stmt.setDate(7, u.getBirthDate());
            } else {
                stmt.setString(7, null);
            }

            if (u.getBirthPlace() != null) {
                stmt.setInt(8, u.getBirthPlace().getId());
            } else {
                stmt.setString(8, null);
            }
            
            if (u.getNationality() != null) {
                stmt.setInt(9, u.getNationality().getId());
            } else {
                stmt.setString(9, null);
            }

            stmt.setInt(10, u.getId());
            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean insertUser(User u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (firstname , lastname , email , phone ,profile_description  ,address ,birth_date ,birth_place , nationality ) VALUES (? , ? ,  ? ,  ?, ? , ? , ? , ?, ?) ;");

            stmt.setString(1, u.getFirstname());
            stmt.setString(2, u.getLastname());

            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());

            stmt.setString(5, u.getProfileDescription());
            stmt.setString(6, u.getAddress());

            if (u.getBirthDate() != null) {
                stmt.setDate(7, u.getBirthDate());
            } else {
                stmt.setString(7, null);
            }

            if (u.getBirthPlace() != null) {
                stmt.setInt(8, u.getBirthPlace().getId());
            } else {
                stmt.setString(8, null);
            }
            
            if (u.getNationality() != null) {
                stmt.setInt(9, u.getNationality().getId());
            } else {
                stmt.setString(9, null);
            }


            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeUser(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM USER  WHERE ID=?;");
            stmt.setInt(1, id);
            System.out.println("id :" + String.valueOf(id));
            return stmt.execute();

        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
}
