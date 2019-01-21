/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import com.company.dao.impl.EmpHistoryDaoImpl;
import com.company.dao.impl.SkillDaoImpl;
import com.company.dao.impl.UserDaoImpl;
import com.company.dao.impl.UserEmpHistoryDaoImpl;
import com.company.dao.impl.UserSkillDaoImpl;
import com.company.dao.inter.EmpHistoryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserEmpHistoryDaoInter;

/**
 *
 * @author TURAL
 */
public class Conext {
    public static UserDaoImpl instanceUserDao() {
       return new UserDaoImpl();
    }
    public static UserSkillDaoImpl instanceUserSkillDao() {
       return new UserSkillDaoImpl();
    }

    public static UserEmpHistoryDaoInter instanceUserEmpHistoryDao() {
       return new UserEmpHistoryDaoImpl();
    }
    public static EmpHistoryDaoInter instanceEmpHistoryDao() {
       return new EmpHistoryDaoImpl();
    }

    static SkillDaoInter instanceSkillDao() {
       return new SkillDaoImpl();
    }
}
