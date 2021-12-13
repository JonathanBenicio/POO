/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;

/**
 *
 * @author Jonathan
 */
public class AuthorDao {

    Connection conn;

    public AuthorDao() {
        this.conn = UtilsDao.getConnection();
    }
}
