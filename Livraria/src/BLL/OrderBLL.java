/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dao.OrderDao;
import Dao.OrderItensDao;
import Model.Order;
import Model.OrderItens;
import Model.User;

/**
 *
 * @author Jonathan
 */
public class OrderBLL {
    private Order order;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void gerenciarOrdem() {
        boolean repite = true;
        while (repite) {

            System.out.println("---------------------------------");
            System.out.println("Escolha qual ação deseja realizar: ");
            System.out.println("Opção | Ação");
            System.out.println("  1     nova Ordem");
            if (OrderDao.consultarPorUser(user.getId()).size() > 0) {

                System.out.println("  2     visualizar Ordens");
                System.out.println("  3     alterar Ordem");
                System.out.println("  4     remover Ordem");
            }
            System.out.println("  5     VOLTAR");

            switch (new Scanner(System.in).nextLine()) {
                case "1":
                    System.out.println("Criando Ordem");
                    criarOrdem();
                    break;

                case "2":
                    listHitorico();

                    break;

                case "3":
                    alterarOrdem();

                    break;

                case "4":
                    excluirOrdem();

                    break;

                case "5":
                    repite = false;
                    break;

                default:

                    System.out.println("Opção incorreta");
                    break;
            }
        }
    }

    public void criarOrdem() {
        order = new Order();
        order.setUser(user);
        OrderItensBLL orderItensBLL = new OrderItensBLL();
        orderItensBLL.setList(new ArrayList<OrderItens>());
        orderItensBLL.gerenciarItens();
        order.setListOrderItens(orderItensBLL.getList());

        System.out.println("Finalizando Ordem");
        finalizarOrdem();
        // for (OrderItens item : order.getListOrderItens()) {

        // String resultado = OrderItensDao.insert(item) ? "cadastrado" : "não
        // cadastrado";
        // System.out.println("OrderItens " + item.hashCode() + " " + resultado);

        // }
    }

    public void finalizarOrdem() {
        if (order.getId() > 0) {

            OrderDao.atualizar(order);
            for (OrderItens orderItens : order.getListOrderItens()) {
                if (orderItens.getId() > 0) {

                    if (OrderItensDao.atualizar(orderItens)) {
                        System.out.println(
                                "compra de " + orderItens.getQuantidade() + " livros de "
                                        + orderItens.getBook().getTitle()
                                        + " atualizada com sucesso");
                    }
                } else {

                    orderItens.setOrder(order);
                    orderItens.setId(OrderItensDao.insert(orderItens));
                    if (orderItens.getId() > 0) {
                        System.out.println(
                                "compra de " + orderItens.getQuantidade() + " livros de "
                                        + orderItens.getBook().getTitle()
                                        + " criada com sucesso");
                    }
                }
            }

        } else {

            order.setId(OrderDao.insert(order));
            for (OrderItens orderItens : order.getListOrderItens()) {
                orderItens.setOrder(order);
                orderItens.setId(OrderItensDao.insert(orderItens));
                if (orderItens.getId() > 0) {
                    System.out.println(
                            "compra de " + orderItens.getQuantidade() + " livros de " + orderItens.getBook().getTitle()
                                    + " criada com sucesso");
                }
            }
        }

    }

    public void listHitorico() {
        System.out.println("Historico de ordens");
        System.out.println("Id   |  Date    |   Q.Livros");
        for (Order order : OrderDao.consultarPorUser(user.getId())) {
            System.out.println(
                    order.getId() + "   " + order.getDate().substring(0, 10) + "   " + order.getNumberofbooks());
        }

    }

    public void alterarOrdem() {
        System.out.println("Informe o Id da ordem que deseja alterar?");
        int id = Integer.parseInt(new Scanner(System.in).nextLine());
        order = OrderDao.consultar(id);
        if (order.getUser().getId() == user.getId()) {

            OrderItensBLL orderItensBLL = new OrderItensBLL();
            orderItensBLL.setList(order.getListOrderItens());
            orderItensBLL.gerenciarItens();
            order.setListOrderItens(orderItensBLL.getList());

            System.out.println("Finalizando Ordem");
            finalizarOrdem();
        }
    }

    public void excluirOrdem() {
        listHitorico();
        System.out.println("Informe o Id da Ordem que deseja remover?");

        int idOrder = Integer.parseInt(new Scanner(System.in).nextLine());
        if (OrderItensDao.deletePorIdOrder(idOrder)) {
            if (OrderDao.delete(idOrder)) {

                System.out.println("Ordem removida com sucesso");

                listHitorico();
            }
        }
    }

}
