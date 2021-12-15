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

    // public static Publisher consultar(int id) {
    //     Connection conn = UtilsDao.getConnection();
    //     Publisher publisher = new Publisher();

    //     try {
    //         String queryAuthor = "Select * from library.publisher " +
    //                 "where puid = ?";

    //         PreparedStatement prepAuthor = conn.prepareStatement(queryAuthor);
    //         prepAuthor.setInt(1, id);

    //         ResultSet idAthor = prepAuthor.executeQuery();
    //         if (idAthor.next()) {

    //             publisher.setId(idAthor.getInt("puid"));
    //             publisher.setName(idAthor.getString("name"));
    //             publisher.setEmail(idAthor.getString("email"));
    //         } else {
    //             publisher = null;
    //         }
    //         conn.close();

    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         publisher = null;
    //     }
    //     return publisher;
        
         // insercao da ordem de compra
    public static int insert(Publisher publisher) {

        Connection conn = UtilsDao.getConnection();
        int response = 0;

        try {
            // faz a insercao da ordem de compra
            String queryPublisher = "INSERT INTO `library`.`publisher`(`name`,`email`)"
                    + "VALUES (?,?)";

            PreparedStatement prepPublisher = conn.prepareStatement(queryPublisher, Statement.RETURN_GENERATED_KEYS);
            prepPublisher.setString(1, publisher.getName());
            prepPublisher.setString(2, publisher.getEmail());

            prepPublisher.executeUpdate();

            // pega o id da ordem de venda
            ResultSet idPublisher = prepPublisher.getGeneratedKeys();
            if (idPublisher.next()) {

                response = idPublisher.getInt(1);

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

    // consulta ordem
    public static Publisher consultar(int id) {
        Connection conn = UtilsDao.getConnection();
        Publisher publisher = new Publisher();

        try {
            String queryPublisher = "Select * from library.publisher " +
                    "where puid = ?";

            PreparedStatement prepPublisher = conn.prepareStatement(queryPublisher);
            prepPublisher.setInt(1, id);

            ResultSet idPublisher = prepPublisher.executeQuery();
            if (idPublisher.next()) {

                publisher.setId(idPublisher.getInt("puid"));
                
                publisher.setId(idPublisher.getInt("puid"));
                publisher.setName(idPublisher.getString("name"));
                publisher.setEmail(idPublisher.getString("email"));
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

    // atualiza a ordem
    public static boolean atualizar(Publisher publisher) {
        Connection conn = UtilsDao.getConnection();
        boolean response = false;

        try {

            String queryPublisher = "UPDATE library.publisher " +
                    "SET name = ?, email = ? WHERE puid = ?";

            PreparedStatement prepPublisher = conn.prepareStatement(queryPublisher);

            prepPublisher.setString(1, publisher.getName());
            prepPublisher.setString(2, publisher.getEmail());
            prepPublisher.setInt(3, publisher.getId());

            response = prepPublisher.executeUpdate() > 0;
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            response = false;
        }

        return response;

    }

    public static boolean delete(int id) {
        Connection conn = UtilsDao.getConnection();
        boolean response = false;

        try {
            String queryPublisher = "DELETE FROM library.publisher WHERE puid = ?";

            PreparedStatement prepPublisher = conn.prepareStatement(queryPublisher);

            prepPublisher.setInt(1, id);

            response = prepPublisher.executeUpdate() > 0;

            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            response = false;
        }

        return response;
    }

    public static List<Publisher> listar() {
        Connection conn = UtilsDao.getConnection();
        List<Publisher> list = new ArrayList<Publisher>();

        try {
            String queryPublisher = "Select * from library.publisher";

            PreparedStatement prepPublisher = conn.prepareStatement(queryPublisher);

            ResultSet idPublisher = prepPublisher.executeQuery();
            while (idPublisher.next()) {
                Publisher publisher = new Publisher();

                
                publisher.setId(idPublisher.getInt("puid"));
                publisher.setName(idPublisher.getString("name"));
                publisher.setEmail(idPublisher.getString("email"));
                list.add(publisher);
            }
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            list = null;
        }
        return list;
    }
}
