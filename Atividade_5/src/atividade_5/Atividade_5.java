/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_5;

/**
 *
 * @author Jonathan
 */
public class Atividade_5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Livro livro = new Livro();
		
		livro.adicionarLivro("Harry Potter", "J. K. Rowling", 2);
		livro.adicionarLivro("The Hunger Games", "Suzanne Collins", 1);
		livro.listarLivro();
		livro.removerLivro(1);
		System.out.println("------------------");
		livro.listarLivro();
    }
    
}
