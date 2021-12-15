/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Book;
import Model.Order;
import Model.OrderItens;
import Model.User;

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

/*
/*

/**
 *
 * @author Jonathan
 */
public class OrderDao {

    // insercao da ordem de compra
    public static int insert(Order order) {

        Connection conn = UtilsDao.getConnection();
        int response = 0;

        try {
            // faz a insercao da ordem de compra
            String queryOrder = "INSERT INTO `library`.`order`(`date`,`numberofbooks`,`fkusid`)"
                    + "VALUES (?,?,?)";

            PreparedStatement prepOrder = conn.prepareStatement(queryOrder, Statement.RETURN_GENERATED_KEYS);
            prepOrder.setString(1, UtilsDao.dateFormat.format(new Date()));
            prepOrder.setInt(2, order.getListOrderItens().size());
            prepOrder.setInt(3, order.getUser().getId());

            prepOrder.executeUpdate();

            // pega o id da ordem de venda
            ResultSet idOrdemVenda = prepOrder.getGeneratedKeys();
            if (idOrdemVenda.next()) {

                response = idOrdemVenda.getInt(1);

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
    public static Order consultar(int id) {
        Connection conn = UtilsDao.getConnection();
        Order order = new Order();

        try {
            String queryOrder = "Select * from library.order " +
                    "where orid = ?";

            PreparedStatement prepOrder = conn.prepareStatement(queryOrder);
            prepOrder.setInt(1, id);

            ResultSet idOrder = prepOrder.executeQuery();
            if (idOrder.next()) {

                order.setId(idOrder.getInt("orid"));
                order.setDate(idOrder.getString("date"));
                order.setNumberofbooks(idOrder.getInt("numberofbooks"));
                order.setUser(UserDao.consultar(idOrder.getInt("fkusid")));
                order.setListOrderItens(OrderItensDao.consultarPorOrder(order));
            } else {
                order = null;
            }
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            order = null;
        }
        return order;
    }

    public static List<Order> consultarPorUser(int id) {
        Connection conn = UtilsDao.getConnection();
        List<Order> orders = new ArrayList<Order>();

        try {
            String queryOrder = "Select * from library.order " +
                    "where fkusid = ?";

            PreparedStatement prepOrder = conn.prepareStatement(queryOrder);
            prepOrder.setInt(1, id);

            ResultSet idOrder = prepOrder.executeQuery();
            while (idOrder.next()) {
                Order order = new Order();

                order.setId(idOrder.getInt("orid"));
                order.setDate(idOrder.getString("date"));
                order.setNumberofbooks(idOrder.getInt("numberofbooks"));
                orders.add(order);
            }
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            orders = null;
        }
        return orders;

    }

    // atualiza a ordem
    public static boolean atualizar(Order order) {
        Connection conn = UtilsDao.getConnection();
        boolean response = false;

        try {

            String queryOrder = "UPDATE library.order " +
                    "SET date = ?, numberofbooks = ? WHERE orid = ?";

            PreparedStatement prepOrder = conn.prepareStatement(queryOrder);

            prepOrder.setString(1, order.getDate().toString());
            prepOrder.setInt(2, order.getListOrderItens().size());
            prepOrder.setInt(3, order.getId());

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
            String queryOrder = "DELETE FROM library.order WHERE orid = ?";

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
