/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livraria;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Dao.OrderDao;
import Model.Order;
import Model.User;

/**
 *
 * @author Jonathan
 */
public class Livraria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){

        // Order order = new Order();
        // User user =new User();
        // user.setId(1);
        // order.setUser(user);
        // order.setNumberofbooks(5);
        // boolean response = OrderDao.insert(order);
        
        // Order order = OrderDao.consultar(1);
        // order.setNumberofbooks(1);
        // var a = OrderDao.atualizar(order);
        boolean a = OrderDao.delete(1);
        if(a){

        }
        

        // CreateTable a = new CreateTable();
        // try {
        //     a.create();
        // } catch (ClassNotFoundException ex) {
        //     Logger.getLogger(Livraria.class.getName()).log(Level.SEVERE, null, ex);
        // }
    }

}
