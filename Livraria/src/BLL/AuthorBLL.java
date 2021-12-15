/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.util.Scanner;

import Dao.AuthorDao;
import Model.Author;

/**
 *
 * @author Jonathan
 */
public class AuthorBLL {

    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {

        this.author = author;
    }

    public void listAuthor() {
        System.out.println("--------------------------------");
        System.out.println("Id  |  Nome  ");
        for (Author author : AuthorDao.listar()) {
            System.out.println(author.getId() + "    " + author.getNome());
        }
    }

    public void selecionarAuthor() {

        System.out.println("Seleciona o Id do Autor:");
        int idAuthor = Integer.parseInt(new Scanner(System.in).nextLine());
        this.author = AuthorDao.consultar(idAuthor);
    }
}
