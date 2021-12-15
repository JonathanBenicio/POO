/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livraria;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import BLL.BookBLL;
import BLL.OrderBLL;
import BLL.UserBLL;
import Dao.OrderDao;
import Dao.UserDao;
import Model.Book;
import Model.Order;
import Model.OrderItens;
import Model.User;

/**
 *
 * @author Jonathan
 */
public class Livraria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean repite = true;
        while (repite) {

            System.out.println("---------------------------------");
            System.out.println("Escolha qual ação deseja realizar: ");
            System.out.println("Opção | Ação");
            System.out.println("  1     Entra como usuario");
            System.out.println("  2     Gerenciar Livros ");
            System.out.println("  3     Gerenciar Autores ");
            // System.out.println("  4     Gerenciar Editoras ");
            // System.out.println("  5     Gerenciar Usuarios ");
            System.out.println("  6     sair");

            switch (new Scanner(System.in).nextLine()) {
                case "1":
                    UserBLL userBLL = new UserBLL();
                    userBLL.login();
                    if (userBLL.getUser().getId() > 0) {

                        OrderBLL orderBLL = new OrderBLL();
                        orderBLL.setUser(userBLL.getUser());
                        orderBLL.gerenciarOrdem();

                    } else {

                        System.out.println("Não foi possivel realizar o login");
                    }
                    break;

                case "2":
                    BookBLL bookBLL = new BookBLL();
                    bookBLL.gerenciar();
                    break;

                // case "3":
                // listHitorico();

                // break;

                // case "4":
                // // listarItens();
                // // System.out.println("Informe o Id do livro deseja remover?");
                // // List<OrderItens> list = new ArrayList<OrderItens>();
                // // int idBook = Integer.parseInt(new Scanner(System.in).nextLine());
                // // for (OrderItens orderItens : order.getListOrderItens()) {
                // // if (orderItens.getBook().getId() != idBook) {
                // // list.add(orderItens);
                // // }
                // // }
                // // order.setListOrderItens(list);
                // break;

                // case "5":
                //     break;
                case "6":
                    repite = false;
                    break;

                default:

                    System.out.println("Opção incorreta");
                    break;
            }
        }

        // Order order = new Order();
        // User user =new User();
        // user.setId(1);
        // order.setUser(user);
        // order.setNumberofbooks(5);
        // boolean response = OrderDao.insert(order);

        // Order order = OrderDao.consultar(1);
        // order.setNumberofbooks(1);
        // var a = OrderDao.atualizar(order);
        // boolean a = OrderDao.delete(1);
        // if (a) {

        // }

        // CreateTable a = new CreateTable();
        // try {
        // a.create();
        // } catch (ClassNotFoundException ex) {
        // Logger.getLogger(Livraria.class.getName()).log(Level.SEVERE, null, ex);
        // }
    }

}
