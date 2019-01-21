/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.User;
import com.company.dao.inter.AbstractDAO;
import static com.company.dao.inter.AbstractDAO.connect;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.Skill;
import com.company.entity.UserSkill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TURAL
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter{


    public UserSkill getUserSkill(ResultSet rs) throws SQLException {

        int userId = rs.getInt("id");
        int skill_id = rs.getInt("skill_id");
        String skill_name = rs.getString("skill_name");
        int power = rs.getInt("power");
        UserSkill us = new UserSkill(null,new User(userId),new Skill(skill_id, skill_name), power);
        System.out.println(us);
        return us;

    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int id) {
        List<UserSkill> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();
   
            PreparedStatement stmt =  conn.prepareStatement("SELECT "
                    + " u.*,"
                    + " us.skill_id,s.name AS Skill_name ,"
                    + " us.power "
                    + " FROM "
                    + " user_skill us " 
                    + " LEFT JOIN user u ON us.user_id=u.id " 
                    + " LEFT JOIN skill s ON us.skill_id=s.id "
                    + " WHERE us.user_id = ? ");
            stmt.setInt(1, id);
            stmt.execute();

            ResultSet rs= stmt.getResultSet();

            while(rs.next()){
    
                UserSkill us = getUserSkill(rs);
                list.add(us);

            }    
        } catch (Exception ex) {
            
        }
        return list;
    }


}
