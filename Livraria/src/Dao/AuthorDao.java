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

import Model.Author;

/**
 *
 * @author Jonathan
 */
public class AuthorDao {

    public static int insert(Author author) {

        Connection conn = UtilsDao.getConnection();
        int response = 0;

        try {
            // faz a insercao da ordem de compra
            String queryAuthor = "INSERT INTO `library`.`author`(`name`)"
                    + "VALUES (?)";

            PreparedStatement prepAuthor = conn.prepareStatement(queryAuthor, Statement.RETURN_GENERATED_KEYS);
            prepAuthor.setString(1, author.getNome());

            prepAuthor.executeUpdate();

            // pega o id da ordem de venda
            ResultSet idAthor = prepAuthor.getGeneratedKeys();
            if (idAthor.next()) {

                response = idAthor.getInt(1);

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
    public static Author consultar(int id) {
        Connection conn = UtilsDao.getConnection();
        Author author = new Author();

        try {
            String queryAuthor = "Select * from library.author " +
                    "where auid = ?";

            PreparedStatement prepAuthor = conn.prepareStatement(queryAuthor);
            prepAuthor.setInt(1, id);

            ResultSet idAthor = prepAuthor.executeQuery();
            if (idAthor.next()) {

                author.setId(idAthor.getInt("auid"));
                author.setNome(idAthor.getString("name"));
            } else {
                author = null;
            }
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            author = null;
        }
        return author;
    }

    // atualiza a ordem
    public static boolean atualizar(Author author) {
        Connection conn = UtilsDao.getConnection();
        boolean response = false;

        try {

            String queryAuthor = "UPDATE library.books " +
                    "SET name = ? WHERE auid = ?";

            PreparedStatement prepAuthor = conn.prepareStatement(queryAuthor);

            prepAuthor.setString(1, author.getNome());
            prepAuthor.setInt(2, author.getId());

            response = prepAuthor.executeUpdate() > 0;
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
            String queryAuthor = "DELETE FROM library.books WHERE auid = ?";

            PreparedStatement prepAuthor = conn.prepareStatement(queryAuthor);

            prepAuthor.setInt(1, id);

            response = prepAuthor.executeUpdate() > 0;

            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            response = false;
        }

        return response;
    }

    public static List<Author> listar() {
        Connection conn = UtilsDao.getConnection();
        List<Author> authors = new ArrayList<Author>();

        try {
            String queryAuthor = "Select * from library.author";

            PreparedStatement prepAuthor = conn.prepareStatement(queryAuthor);

            ResultSet idAthor = prepAuthor.executeQuery();
            while (idAthor.next()) {
                Author author = new Author();

                author.setId(idAthor.getInt("auid"));
                author.setNome(idAthor.getString("name"));
                authors.add(author);
            }
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            authors = null;
        }
        return authors;
    }
}
