/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author Jonathan
 */
public class Publisher {

    private int id;
    private String name;
    private String email;
    private List<Book> ListBooks;

    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getListBooks() {
        return ListBooks;
    }

    public void setListBooks(List<Book> listBooks) {
        this.ListBooks = listBooks;
    }
}
