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
public class Book {

    private int id;
    private String title;
    private int paginas;
    private Publisher Publisher;
    private Author Author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }


    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public Author getAuthor() {
        return Author;
    }

    public void setAuthor(Author author) {

        this.Author = author;
    }

    public Publisher getPublisher() {
        return Publisher;
    }

    public void setPublisher(Publisher Publisher) {

        this.Publisher = Publisher;
    }

}
