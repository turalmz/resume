/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.Login;

import java.util.List;

/**
 *
 * @author TURAL
 */
public interface LoginDaoInter {

    List<Login> getAll();

    public Login getById(int id);

    boolean updateLogin(Login log);

    boolean removeLogin(int id);

    public List<Login> getByName(String username);

    public boolean insertLogin(Login log);
}