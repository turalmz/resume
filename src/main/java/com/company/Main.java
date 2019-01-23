/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;
import com.company.dao.inter.EmpHistoryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.UserSkill;
import com.company.dao.inter.UserEmpHistoryDaoInter;
import com.company.entity.EmpHistory;
/**
 *
 * @author TURAL
 */
public class Main {
    

    
    public static void main(String[] args) throws Exception {
/*
//        UserDaoInter us= new UserDaoImpl();//thightly coupling
        UserDaoInter us = Conext.instanceUserDao();///loosly coupling
        us.getAll();
        User u = us.getById(1);
        u.setEmail("aaaaaaaaaaaaa");
        System.out.println(us.updateUser(u));
        //jdbc interface Connection,Statement,ResultSet,
        
  */      
        UserSkillDaoInter usk = Context.instanceUserSkillDao();
        
        for (UserSkill userSkill : usk.getAllSkillByUserId(2)) {
            System.out.println(userSkill);
        }
        
        
        UserEmpHistoryDaoInter ueh = Context.instanceUserEmpHistoryDao();
        
        for (EmpHistory userSkill : ueh.getAllEmpHistoryByUserId(1)) {
            System.out.println(userSkill);
        }
        
        
        EmpHistoryDaoInter eh = Context.instanceEmpHistoryDao();
        
        System.out.println(eh.getById(1));

        
        SkillDaoInter s = Context.instanceSkillDao();
        
        System.out.println(s.getById(1));
    }
    
}
