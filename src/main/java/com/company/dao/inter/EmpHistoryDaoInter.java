/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.EmpHistory;
import java.util.List;

/**
 *
 * @author TURAL
 */
public interface EmpHistoryDaoInter {
     
    List<EmpHistory> getAll();
     
    public EmpHistory getById(int id);
     
    boolean updateEmpHistory(EmpHistory u);
     
    boolean removeEmpHistory(int id);
    
}
