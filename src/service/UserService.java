/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDao;

/**
 *
 * @author dell
 */
public class UserService {
    private UserDao dao = new UserDao();

    public boolean login(String username, String password) {
        return dao.checkLogin(username, password);
    }
}

