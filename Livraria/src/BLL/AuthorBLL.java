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

    public void gerenciar() {
        boolean repite = true;
        while (repite) {

            System.out.println("---------------------------------");
            System.out.println("Escolha qual ação deseja realizar: ");
            System.out.println("Opção | Ação");
            System.out.println("  1     nova Autor");
            if (AuthorDao.listar().size() > 0) {

                System.out.println("  2     visualizar Autor");
                System.out.println("  3     alterar nomev Autor");
                System.out.println("  4     remover Autor");
            }
            System.out.println("  5     VOLTAR");

            switch (new Scanner(System.in).nextLine()) {
                case "1":
                    System.out.println("Criando Ordem");
                    criaAuthor();
                    break;

                case "2":
                    listAuthor();

                    break;

                case "3":
                    alterarAuthor();

                    break;

                case "4":
                    excluirAuthor();

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

    private void excluirAuthor() {
        listAuthor();
        System.out.println("--------------------------------");
        System.out.println("Seleciona o Id do livro que deseja remover:");
        selecionarAuthor();
        if (AuthorDao.delete(author.getId())) {

            System.out.println("Autor removida com sucesso");

            listAuthor();
        }
    }

    private void alterarAuthor() {
        
        selecionarAuthor();

        System.out.println("--------------------------------");
        System.out.println("Informe o Nome do Autor:");
        author.setNome(new Scanner(System.in).nextLine());
    }

    private void criaAuthor() {
        author = new Author();

        System.out.println("--------------------------------");
        System.out.println("Informe o Nome do Autor:");
        author.setNome(new Scanner(System.in).nextLine());


        if (AuthorDao.insert(author) > 0) {

            System.out.println("Autor Cadastrado com sucesso");
        }
    }
}
