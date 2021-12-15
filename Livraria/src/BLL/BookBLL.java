/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.util.List;
import java.util.Scanner;

import Dao.AuthorDao;
import Dao.BookDao;
import Dao.PublisherDao;
import Model.Book;

/**
 *
 * @author Jonathan
 */
public class BookBLL {

    private Book book;
    private List<Book> listBook;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getListBook() {
        return listBook;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }

    public void selecionarBook() {
        listBook();

        book = listBook.get(Integer.parseInt(new Scanner(System.in).nextLine()) - 1);

    }

    public void listBook() {

        listBook = BookDao.listar();
        System.out.println("--------------------------------");
        System.out.println("Id  |  Titulo  |  Quant. paginas");
        int i = 1;
        for (Book book : listBook) {
            System.out.println(i + "    " + book.getTitle() + "    " + book.getPaginas());
            i++;
        }
    }

    public void gerenciar() {
        boolean repite = true;
        while (repite) {

            System.out.println("---------------------------------");
            System.out.println("Escolha qual ação deseja realizar: ");
            System.out.println("Opção | Ação");
            System.out.println("  1     nova Livro");
            if (BookDao.listar().size() > 0) {

                System.out.println("  2     visualizar Livros");
                System.out.println("  3     alterar Livro");
                System.out.println("  4     remover Livro");
            }
            System.out.println("  5     VOLTAR");

            switch (new Scanner(System.in).nextLine()) {
                case "1":
                    System.out.println("Criando Ordem");
                    criaBook();
                    break;

                case "2":
                    listBook();

                    break;

                case "3":
                    alterarBook();

                    break;

                case "4":
                    excluirBook();

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

    private void excluirBook() {
        listBook();
        System.out.println("--------------------------------");
        System.out.println("Seleciona o Id do livro que deseja remover:");
        selecionarBook();
        if (BookDao.delete(book.getId())) {

            System.out.println("Livro removida com sucesso");

            listBook();
        }
    }

    private void alterarBook() {

        System.out.println("--------------------------------");
        System.out.println("Seleciona o Id do livro que deseja alterar:");

        selecionarBook();
        boolean repite = true;
        while (repite) {

            System.out.println("---------------------------------");
            System.out.println("Escolha qual ação deseja realizar: ");
            System.out.println("Opção | Ação");
            System.out.println("  1     alterar Titulo");
            System.out.println("  2     Alterar quantidade de Paginas");
            System.out.println("  3     alterar Autor");
            System.out.println("  4     alterar Editora");
            System.out.println("  5     salvar alteração");
            System.out.println("  6     VOLTAR");

            switch (new Scanner(System.in).nextLine()) {
                case "1":
                    System.out.println("--------------------------------");
                    System.out.println("Informe o titulo do Livro: " + book.getTitle());
                    book.setTitle(new Scanner(System.in).nextLine());
                    break;
                case "2":
                    System.out.println("Informe a quantidade de paginas do livro: " + book.getPaginas());
                    book.setPaginas(Integer.parseInt(new Scanner(System.in).nextLine()));

                    break;
                case "3":
                    AuthorBLL authorBLL = new AuthorBLL();
                    authorBLL.listAuthor();
                    authorBLL.selecionarAuthor();
                    book.setAuthor(authorBLL.getAuthor());
                    break;

                case "4":
                    System.out.println("Seleciona o Id da Editora do Livro:");
                    book.setPublisher(PublisherDao.consultar(Integer.parseInt(new Scanner(System.in).nextLine())));

                    break;

                case "5":
                    BookDao.atualizar(book);
                    repite = false;

                    break;
                case "6":
                    repite = false;
                    break;

                default:
                    break;
            }

        }
    }

    private void criaBook() {
        book = new Book();

        System.out.println("--------------------------------");
        System.out.println("Informe o titulo do Livro:");
        book.setTitle(new Scanner(System.in).nextLine());

        System.out.println("Informe a quantidade de paginas do livro:");
        book.setPaginas(Integer.parseInt(new Scanner(System.in).nextLine()));

        AuthorBLL authorBLL = new AuthorBLL();
        authorBLL.listAuthor();
        authorBLL.selecionarAuthor();
        book.setAuthor(authorBLL.getAuthor());

        System.out.println("Seleciona o Id da Editora do Livro:");
        book.setPublisher(PublisherDao.consultar(Integer.parseInt(new Scanner(System.in).nextLine())));

        if (BookDao.insert(book) > 0) {

            System.out.println("Livro Cadastrado com sucesso");
        }
    }

}
