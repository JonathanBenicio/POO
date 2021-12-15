/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import Model.User;
import Model.User;

import java.util.List;

/**
 *
 * @author Jonathan
 */
public class UserDao {

    public static int insert(User user) {

        Connection conn = UtilsDao.getConnection();
        int response = 0;

        try {
            // faz a insercao da ordem de compra
            String queryUser = "INSERT INTO `library`.`user`(`name`,`type`)"
                    + "VALUES (?,?)";

            PreparedStatement prepUser = conn.prepareStatement(queryUser, Statement.RETURN_GENERATED_KEYS);
            prepUser.setString(1, user.getName());
            prepUser.setString(2, user.getType());

            prepUser.executeUpdate();

            // pega o id da ordem de venda
            ResultSet idUser = prepUser.getGeneratedKeys();
            if (idUser.next()) {

                response = idUser.getInt(1);

            } else {
                response = 0;
            }
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            response = 0;
        }
        return response;

    }

    public static User consultar(int id) {
        Connection conn = UtilsDao.getConnection();
        User user = new User();

        try {
            String queryUser = "Select * from library.user " +
                    "where usid = ?";

            PreparedStatement prepUser = conn.prepareStatement(queryUser);
            prepUser.setInt(1, id);

            ResultSet userResultSet = prepUser.executeQuery();
            if (userResultSet.next()) {

                user.setId(userResultSet.getInt("usid"));
                user.setName(userResultSet.getString("name"));
                user.setType(userResultSet.getString("type"));
            } else {
                user = null;
            }
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            user = null;
        }
        return user;
    }
}
