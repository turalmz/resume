/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import com.company.dao.impl.CountryDaoImpl;
import com.company.dao.impl.EmpHistoryDaoImpl;
import com.company.dao.impl.SkillDaoImpl;
import com.company.dao.impl.UserDaoImpl;
import com.company.dao.impl.UserEmpHistoryDaoImpl;
import com.company.dao.impl.UserSkillDaoImpl;
import com.company.dao.impl.LoginDaoImpl;
import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.EmpHistoryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.LoginDaoInter;
import com.company.dao.inter.UserEmpHistoryDaoInter;
import com.company.dao.inter.UserSkillDaoInter;

/**
 *
 * @author TURAL
 */
public class Context {

    public static UserDaoInter instanceUserDao() {
        return new UserDaoImpl();
    }

    public static UserSkillDaoInter instanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }

    public static UserEmpHistoryDaoInter instanceUserEmpHistoryDao() {
        return new UserEmpHistoryDaoImpl();
    }

    public static EmpHistoryDaoInter instanceEmpHistoryDao() {
        return new EmpHistoryDaoImpl();
    }

    public static SkillDaoInter instanceSkillDao() {
        return new SkillDaoImpl();
    }

    public static CountryDaoInter instanceCountryDao() {
        return new CountryDaoImpl();
    }

    public static LoginDaoInter instanceLoginDao() {
        return new LoginDaoImpl();
    }

}
