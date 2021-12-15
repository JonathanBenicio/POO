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

import Model.Book;
import Model.Publisher;

/**
 *
 * @author Jonathan
 */
public class BookDao {

    // public static Book consultar(int id) {
    // return new Book();
    // }
    // public static int insert() {
    // return 1;
    // // DAOProduto daoProduto=new DAOProduto();

    // // for (Book b : book.getBooks()) {

    // // // Produto p=new Produto();
    // // // p=daoProduto.consultaPorID(b);
    // // // faz a consulta no banco pela quantidade do produto
    // // prepLivro.setInt(1, b.getId());
    // // ResultSet produtoDoBanco = prepLivro.executeQuery();
    // // produtoDoBanco.next();
    // // int qtdeDeLivroNoBanco = produtoDoBanco.getInt("qtde");

    // // // adiciona o produto vinculando no orderitens
    // // prep1.setInt(1, idOV);
    // // prep1.setInt(2, b.getId());
    // // prep1.setInt(3, b.getQtde());
    // // prep1.addBatch();

    // // // atualiza a tabela de produtos, decrementando o estoque.
    // // prep2.setInt(1, qtdeDeLivroNoBanco - b.getQtde());
    // // prep2.setInt(2, b.getId());
    // // prep2.addBatch();

    // // }
    // }

    // insercao da ordem de compra
    public static int insert(Book book) {

        Connection conn = UtilsDao.getConnection();
        int response = 0;

        try {
            // faz a insercao da ordem de compra
            String queryBook = "INSERT INTO `library`.`books`(`title`,`paginas`,`fkpuid`,`fkauid`)"
                    + "VALUES (?,?,?,?)";

            PreparedStatement prepBook = conn.prepareStatement(queryBook, Statement.RETURN_GENERATED_KEYS);
            prepBook.setString(1, book.getTitle());
            prepBook.setInt(2, book.getPaginas());
            prepBook.setInt(3, book.getPublisher().getId());
            prepBook.setInt(4, book.getAuthor().getId());

            prepBook.executeUpdate();

            // pega o id da ordem de venda
            ResultSet idBook = prepBook.getGeneratedKeys();
            if (idBook.next()) {

                response = idBook.getInt(1);

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
    public static Book consultar(int id) {
        Connection conn = UtilsDao.getConnection();
        Book book = new Book();

        try {
            String queryBook = "Select * from library.books " +
                    "where boid = ?";

            PreparedStatement prepBook = conn.prepareStatement(queryBook);
            prepBook.setInt(1, id);

            ResultSet idBook = prepBook.executeQuery();
            if (idBook.next()) {

                book.setId(idBook.getInt("boid"));
                book.setTitle(idBook.getString("title"));
                book.setPaginas(idBook.getInt("paginas"));
                book.setPublisher(PublisherDao.consultar(idBook.getInt("fkpuid")));
                book.setAuthor(AuthorDao.consultar(idBook.getInt("fkauid")));
            } else {
                book = null;
            }
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            book = null;
        }
        return book;
    }

    // atualiza a ordem
    public static boolean atualizar(Book book) {
        Connection conn = UtilsDao.getConnection();
        boolean response = false;

        try {

            String queryBook = "UPDATE library.books " +
                    "SET title = ?, paginas = ?, fkpuid = ?, fkauid = ? WHERE boid = ?";

            PreparedStatement prepBook = conn.prepareStatement(queryBook);

            prepBook.setString(1, book.getTitle());
            prepBook.setInt(2, book.getPaginas());
            prepBook.setInt(3, book.getPublisher().getId());
            prepBook.setInt(4, book.getAuthor().getId());
            prepBook.setInt(5, book.getId());

            response = prepBook.executeUpdate() > 0;
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
            String queryBook = "DELETE FROM library.books WHERE boid = ?";

            PreparedStatement prepBook = conn.prepareStatement(queryBook);

            prepBook.setInt(1, id);

            response = prepBook.executeUpdate() > 0;

            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            response = false;
        }

        return response;
    }

    public static List<Book> listar() {
        Connection conn = UtilsDao.getConnection();
        List<Book> books = new ArrayList<Book>();

        try {
            String queryBook = "Select * from library.books";

            PreparedStatement prepBook = conn.prepareStatement(queryBook);

            ResultSet idBook = prepBook.executeQuery();
            while (idBook.next()) {
                Book book = new Book();

                book.setId(idBook.getInt("boid"));
                book.setTitle(idBook.getString("title"));
                book.setPaginas(idBook.getInt("paginas"));
                book.setPublisher(PublisherDao.consultar(idBook.getInt("fkpuid")));
                book.setAuthor(AuthorDao.consultar(idBook.getInt("fkpuid")));
                books.add(book);
            }
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            books = null;
        }
        return books;
    }
}
