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
import java.util.ArrayList;
import java.util.List;

import Model.Book;
import Model.Order;
import Model.OrderItens;
import Model.User;

/**
 *
 * @author Jonathan
 */
public class OrderItensDao {

    public static int insert(OrderItens orderItens) {
        Connection conn = UtilsDao.getConnection();
        int response = 0;

        try {

            String query1 = "INSERT INTO library.orderitens (quantidade, fkorid, fkboid) "
                    + "VALUES (?,?,?)";

            PreparedStatement prep1 = conn.prepareStatement(
                    query1, Statement.RETURN_GENERATED_KEYS);

            prep1.setInt(1, orderItens.getQuantidade());
            prep1.setInt(2, orderItens.getOrder().getId());
            prep1.setInt(3, orderItens.getBook().getId());

            prep1.executeUpdate();

            // pega o id da ordem de venda
            ResultSet idOrdemVenda = prep1.getGeneratedKeys();
            if (idOrdemVenda.next()) {

                response = idOrdemVenda.getInt(1);

            } else {
                response = 0;
            }
            conn.close();

            // ResultSet idOrdemItem = prep1.getGeneratedKeys();
            // idOrdemItem.next();
            // orderItens.setId(idOrdemItem.getInt(1));

            // prep1.executeBatch();

            conn.close();
        } catch (Exception e) {
            // TODO: handle exception
            response = 0;
        }

        return response;
    }

    // public static int insert(OrderItens orderitens) {

    // Connection conn = UtilsDao.getConnection();
    // int response = 0;

    // try {
    // // faz a insercao da ordem de compra
    // String queryOrder = "INSERT INTO
    // `library`.`orderitens`(`quantidade`,`fkorid`,`fkboid`)"
    // + "VALUES (?,?,?)";

    // PreparedStatement prepOrder = conn.prepareStatement(queryOrder,
    // Statement.RETURN_GENERATED_KEYS);
    // prepOrder.setInt(1, orderitens.getQuantidade());
    // prepOrder.setInt(2, orderitens.getOrder().getId());
    // prepOrder.setInt(3, orderitens.getBook().getId());

    // prepOrder.executeUpdate();

    // // pega o id da ordem de venda
    // ResultSet idOrdemVenda = prepOrder.getGeneratedKeys();
    // if (idOrdemVenda.next()) {

    // response = idOrdemVenda.getInt(1);

    // } else {
    // response = 0;
    // }
    // conn.close();

    // } catch (Exception e) {
    // // TODO: handle exception
    // response = 0;
    // }
    // return response;

    // }

    // consulta ordem
    public static OrderItens consultar(int id) {
        Connection conn = UtilsDao.getConnection();
        OrderItens orderitens = null;

        try {
            String queryOrder = "Select * from library.orderitens " +
                    "where oritid = ?";

            PreparedStatement prepOrder = conn.prepareStatement(queryOrder);
            prepOrder.setInt(1, id);

            ResultSet ordeResultSet = prepOrder.executeQuery();
            if (ordeResultSet.next()) {
                orderitens = new OrderItens();

                orderitens.setId(ordeResultSet.getInt("oritid"));
                orderitens.setQuantidade(ordeResultSet.getInt("quantidade"));
                orderitens.setOrder(OrderDao.consultar(ordeResultSet.getInt("fkorid")));
                orderitens.setBook(BookDao.consultar(ordeResultSet.getInt("fkboid")));
            } else {
                orderitens = null;
            }
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            orderitens = null;
        }
        return orderitens;
    }

    public static List<OrderItens> consultarPorOrder(Order order) {
        Connection conn = UtilsDao.getConnection();
        List<OrderItens> list = new ArrayList<OrderItens>();

        try {
            String queryOrder = "Select * from library.orderitens " +
                    "where fkorid = ?";

            PreparedStatement prepOrder = conn.prepareStatement(queryOrder);
            prepOrder.setInt(1, order.getId());

            ResultSet ordeResultSet = prepOrder.executeQuery();
            while (ordeResultSet.next()) {
                OrderItens orderitens = new OrderItens();

                orderitens.setId(ordeResultSet.getInt("oritid"));
                orderitens.setQuantidade(ordeResultSet.getInt("quantidade"));
                orderitens.setOrder(order);
                orderitens.setBook(BookDao.consultar(ordeResultSet.getInt("fkboid")));
                list.add(orderitens);
            }
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            list = null;
        }
        return list;
    }

    // atualiza a ordem
    public static boolean atualizar(OrderItens orderItens) {
        Connection conn = UtilsDao.getConnection();
        boolean response = false;

        try {

            String queryOrder = "UPDATE library.orderitens " +
                    "SET quantidade = ?, fkorid = ?, fkboid = ? WHERE oritid = ?";

            PreparedStatement prepOrder = conn.prepareStatement(queryOrder);

            prepOrder.setInt(1, orderItens.getQuantidade());
            prepOrder.setInt(2, orderItens.getOrder().getId());
            prepOrder.setInt(3, orderItens.getBook().getId());

            response = prepOrder.executeUpdate() > 0;
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
            String queryOrder = "DELETE FROM library.orderitens WHERE oritid = ?";

            PreparedStatement prepOrder = conn.prepareStatement(queryOrder);

            prepOrder.setInt(1, id);

            response = prepOrder.executeUpdate() > 0;

            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            response = false;
        }

        return response;
    }

    public static boolean deletePorIdOrder(int id) {
        Connection conn = UtilsDao.getConnection();
        boolean response = false;

        try {
            String queryOrder = "DELETE FROM library.orderitens WHERE fkorid = ?";

            PreparedStatement prepOrder = conn.prepareStatement(queryOrder);

            prepOrder.setInt(1, id);

            response = prepOrder.executeUpdate() > 0;

            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            response = false;
        }

        return response;
    }

}
