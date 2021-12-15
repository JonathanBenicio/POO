/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dao.OrderItensDao;
import Model.OrderItens;

/**
 *
 * @author Jonathan
 */
public class OrderItensBLL {

    private OrderItens orderItens;
    private List<OrderItens> list;

    public OrderItens getOrderItens() {
        return orderItens;
    }

    public void setOrderItens(OrderItens orderItens) {
        this.orderItens = orderItens;
    }

    public List<OrderItens> getList() {
        return list;
    }

    public void setList(List<OrderItens> list) {
        this.list = list;
    }

    public void criaItem() {
        orderItens = new OrderItens();

        BookBLL bookBLL = new BookBLL();

        bookBLL.listBook();
        
        System.out.println("--------------------------------");
        System.out.println("Seleciona o Id do livro que deseja comprar:");
        bookBLL.selecionarBook();

        orderItens.setBook(bookBLL.getBook());
        this.quantidadeBook();

    }


    public void quantidadeBook() {
        System.out.println("Quantos livros do " + orderItens.getBook().getTitle() + " deseja comprar?");

        orderItens.setQuantidade(Integer.parseInt(new Scanner(System.in).nextLine()));
    }

    public void gerenciarItens() {
        boolean repite = true;
        while (repite) {

            System.out.println("Escolha qual ação deseja realizar: ");
            System.out.println("Opção | Nome");
            System.out.println("  1     adicionar livro");
            if (list.size() > 0) {
                System.out.println("  2     listar carrinho");
                System.out.println("  3     alterar quantidade livro");
                System.out.println("  4     remover livro");
                System.out.println("  5     finalizar ordem ");
            }

            switch (new Scanner(System.in).nextLine()) {
                case "1":
                    criaItem();
                    list.add(getOrderItens());
                    System.out.println("Livro adicionado na ordem");
                    listarItens();
                    break;

                case "2":
                    listarItens();

                    break;

                case "3":
                    listarItens();

                    System.out.println("Informe o Id do livro deseja alterar quantidade?");
                    List<OrderItens> listoOrderItens = getList();
                    int id = Integer.parseInt(new Scanner(System.in).nextLine());
                    int index = 1;
                    for (OrderItens orderIten : listoOrderItens) {
                        if (index == id) {
                            setOrderItens(orderIten);
                            quantidadeBook();
                            orderIten = getOrderItens();
                        }
                        index++;
                    }
                    setList(listoOrderItens);
                    break;

                case "4":
                    listarItens();
                    System.out.println("Informe o Id do livro deseja remover?");
                    List<OrderItens> listemp = new ArrayList<OrderItens>();
                    int idBook = Integer.parseInt(new Scanner(System.in).nextLine());
                    int i = 1;
                    for (OrderItens item : getList()) {
                        if (i != idBook) {
                            listemp.add(item);
                        } else {
                            if (item.getId() > 0) {
                                OrderItensDao.delete(item.getId());
                                item.setQuantidade(item.getQuantidade() - 1);
                            }
                        }
                        i++;
                    }
                    setList(listemp);
                    break;

                case "5":
                    repite = false;
                    break;

                default:

                    System.out.println("Livro não selecionado");
                    break;
            }

        }

    }

    public void listarItens() {

        System.out.println("--------------------------------");
        System.out.println("Listagem de livros no carrinho");
        System.out.println("Id  |  Quantidade   |  Titulo");
        int index = 1;
        for (OrderItens orderItens : list) {
            System.out.println(index + "      " + orderItens.getQuantidade() + "      "
                    + orderItens.getBook().getTitle());
            index++;
        }
        System.out.println("--------------------------------");
    }

}
