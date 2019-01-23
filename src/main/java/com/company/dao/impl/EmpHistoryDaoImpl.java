/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.User;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmpHistoryDaoInter;
import com.company.entity.EmpHistory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TURAL
 */
public class EmpHistoryDaoImpl extends AbstractDAO implements EmpHistoryDaoInter {

    public EmpHistory getEmpHistory(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");

        int userId = rs.getInt("user_id");
        String header = rs.getString("header");
        String jobDescription = rs.getString("job_description");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        EmpHistory us = new EmpHistory(id, new User(userId), header, beginDate, endDate, jobDescription);
        System.out.println(us);
        return us;

    }

    @Override
    public List<EmpHistory> getAll() {
        List<EmpHistory> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM employment_history");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                EmpHistory us = getEmpHistory(rs);
                list.add(us);

            }
        } catch (Exception ex) {

        }
        return list;
    }

    @Override
    public EmpHistory getById(int id) {
        EmpHistory em = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employment_history WHERE ID = ?");
            stmt.setInt(1, id);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                em = getEmpHistory(rs);
                return em;

            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return em;
    }

    @Override
    public boolean updateEmpHistory(EmpHistory u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE employment_history SET header=? , job_description=? , begin_date = ? , end_date = ? WHERE ID= ?");
            stmt.setString(1, u.getHeader());
            stmt.setString(2, u.getJobDescription());
            stmt.setDate(3, u.getBeginDate());
            stmt.setDate(4, u.getEndDate());
            stmt.setInt(5, u.getId());
            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeEmpHistory(int id) {
        Connection conn;
        try {
            conn = connect();
            Statement stmt = conn.createStatement();
            return stmt.execute("DELETE employment_history  WHERE id=" + id);

        } catch (Exception ex) {
            return false;
        }
    }

}
