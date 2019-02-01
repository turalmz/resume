/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.User;
import com.company.dao.inter.AbstractDAO;
import static com.company.dao.inter.AbstractDAO.connect;
import com.company.dao.inter.UserEmpHistoryDaoInter;
import com.company.entity.EmpHistory;
import com.company.entity.Skill;
import com.company.entity.UserSkill;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TURAL
 */
public class UserEmpHistoryDaoImpl extends AbstractDAO implements UserEmpHistoryDaoInter {

    public EmpHistory getUserEmpHistory(ResultSet rs) throws SQLException {

        int id = rs.getInt("em_id");

        int userId = rs.getInt("id");
        String header = rs.getString("header");
        String jobDescription = rs.getString("job_description");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        EmpHistory us = new EmpHistory(id, new User(userId), header, beginDate, endDate, jobDescription);
        System.out.println(us);
        return us;

    }

    @Override
    public List<EmpHistory> getAllEmpHistoryByUserId(int id) {
        List<EmpHistory> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT "
                    + " u.id,"
                    + " em.id as em_id "
                    + " em.header,"
                    + " em.begin_date,"
                    + " em.end_date,"
                    + " em.job_description"
                    + " FROM employment_history em "
                    + "  LEFT JOIN user u"
                    + " ON em.user_id = u.id "
                    + " WHERE em.user_id = ? ");
            stmt.setInt(1, id);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                EmpHistory us = getUserEmpHistory(rs);
                list.add(us);

            }
        } catch (Exception ex) {

        }
        return list;
    }

}
