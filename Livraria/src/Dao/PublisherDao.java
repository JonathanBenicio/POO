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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import Model.Publisher;

/**
 *
 * @publisher Jonathan
 */
public class PublisherDao {

    public static Publisher consultar(int id) {
        Connection conn = UtilsDao.getConnection();
        Publisher publisher = new Publisher();

        try {
            String queryAuthor = "Select * from library.publisher " +
                    "where puid = ?";

            PreparedStatement prepAuthor = conn.prepareStatement(queryAuthor);
            prepAuthor.setInt(1, id);

            ResultSet idAthor = prepAuthor.executeQuery();
            if (idAthor.next()) {

                publisher.setId(idAthor.getInt("puid"));
                publisher.setName(idAthor.getString("name"));
                publisher.setEmail(idAthor.getString("email"));
            } else {
                publisher = null;
            }
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            publisher = null;
        }
        return publisher;
    }
}
