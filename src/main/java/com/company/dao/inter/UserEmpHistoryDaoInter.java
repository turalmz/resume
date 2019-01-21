package com.company.dao.inter;

import com.company.entity.EmpHistory;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author TURAL
 */
public interface UserEmpHistoryDaoInter {
    
    public List<EmpHistory> getAllEmpHistoryByUserId(int id);

}
