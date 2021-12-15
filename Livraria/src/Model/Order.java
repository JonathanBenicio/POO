/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonathan
 */

public class Order {

    public Order() {
        this.ListOrderItens = new ArrayList<OrderItens>();
    }

    private int id;
    private String date;
    private int numberofbooks = 0;
    private User User;
    private List<OrderItens> ListOrderItens;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberofbooks() {
        return numberofbooks;
    }

    public void setNumberofbooks(int numberofbooks) {
        this.numberofbooks = numberofbooks;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }

    public List<OrderItens> getListOrderItens() {
        return ListOrderItens;
    }

    public void setListOrderItens(List<OrderItens> listOrderItens) {
        this.ListOrderItens = listOrderItens;
    }

    // public void addBook(Book book, int quantidade) {
    // numberofbooks = +1;

    // OrderItens orderItens = new OrderItens(this, book);
    // orderItens.setQuantidade(quantidade);
    // }

    public void addOrderItens(OrderItens orderItens) {
        this.ListOrderItens.add(orderItens);

    }

}
