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

        String password = rs.getString("password");


        String address = rs.getString("address");
        String profileDescription = rs.getString("profile_description");
        Date birthDate = null;
        try {
            birthDate = rs.getDate("birth_date");
        } catch (Exception ex) {
            System.err.println("Houston, we have a problem");
        }
        int birthPlace = rs.getInt("birth_place");
        String natinalityName =  rs.getString("nationality_name");
        int nationality = rs.getInt("nationality");
        String countryName =  rs.getString("contry_name");

        User us = new User(id, firstname, lastname, email, phone,password, address, profileDescription, birthDate, new Country(birthPlace,countryName,null), new Country(nationality,null,natinalityName));
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
            stmt.execute("SELECT *,n.name as nationality_name,c.name as contry_name FROM user u" +
                    "  LEFT JOIN country c " +
                    "  ON u.birth_place = c.id " +
                    "  LEFT JOIN country n " +
                    "  ON u.nationality = n.id ");
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

    public List<User> getAll(String  name,String surname,Integer nat ) {
        List<User> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();
            String sql = "SELECT *,n.name as nationality_name,c.name as contry_name FROM user u" +
                    "  LEFT JOIN country c " +
                    "  ON u.birth_place = c.id " +
                    "  LEFT JOIN country n " +
                    "  ON u.nationality = n.id "+
                    " WHERE 1=1 ";

            if(name!= null){

                sql += " and firstname = ? ";

            }
            if(surname!= null){

                sql += " and lastname = ? ";

            }
            if(nat!= null){

                sql += " and nationality = ? ";

            }
//            System.out.println(sql);
            PreparedStatement stmt = conn.prepareStatement(sql);

            int count = 1;
            if(name!= null){
                stmt.setString(count, name);
                count ++;
            }
            if(surname!= null){

                stmt.setString(count, surname);
                count ++;
            }
            if(nat!= null){

                stmt.setInt(count, nat);
            }

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
            String sql = "SELECT *,n.name as nationality_name,c.name as contry_name FROM user u" +
                    "  LEFT JOIN country c " +
                    "  ON u.birth_place = c.id " +
                    "  LEFT JOIN country n " +
                    "  ON u.nationality = n.id "+
                    "  WHERE u.id = ? ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
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
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (firstname , lastname , email , phone ,profile_description  ,address ,birth_date ,birth_place , nationality ) VALUES (? , ? ,  ? ,  ?, ? , ? , ? , ?, ?) ;",Statement.RETURN_GENERATED_KEYS);

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

            ResultSet genKeys = stmt.getGeneratedKeys();
            if(genKeys.next()){
                u.setId(genKeys.getInt(1));
            }

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

    @Override
    public User findByEmail(String email) {
        User log = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT *,n.name as nationality_name,c.name as contry_name FROM user u" +
                    "  LEFT JOIN country c " +
                    "  ON u.birth_place = c.id " +
                    "  LEFT JOIN country n " +
                    "  ON u.nationality = n.id "+
                    "  WHERE u.email = ?;");
            stmt.setString(1, email);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                log = getUser(rs) ;

            }
        } catch (Exception ex) {
            System.err.println("Houston, we have a problem");
        }
        return log;
    }
}
