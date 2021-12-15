/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Jonathan
 */

public class OrderItens {

    private int id;
    private int quantidade;
    private Order Order;
    private Book Book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Book getBook() {
        return Book;
    }

    public void setBook(Book book) {
        this.Book = book;
    }

    public Order getOrder() {
        return Order;
    }

    public void setOrder(Order order) {
        this.Order = order;
    }
}
