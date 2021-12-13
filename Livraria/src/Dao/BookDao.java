// /*
//  * To change this license header, choose License Headers in Project Properties.
//  * To change this template file, choose Tools | Templates
//  * and open the template in the editor.
//  */
// package Dao;

// import java.sql.Connection;

// /**
//  *
//  * @author Jonathan
//  */
// public class BookDao {

//     Connection conn;

//     public BookDao() {
//         this.conn = UtilsDao.getConnection();
//     }

//     public void insert() {
        
//         // DAOProduto daoProduto=new DAOProduto();

//         for (Book b : order.getBooks()) {

//             // Produto p=new Produto();
//             // p=daoProduto.consultaPorID(b);
//             // faz a consulta no banco pela quantidade do produto
//             prepLivro.setInt(1, b.getId());
//             ResultSet produtoDoBanco = prepLivro.executeQuery();
//             produtoDoBanco.next();
//             int qtdeDeLivroNoBanco = produtoDoBanco.getInt("qtde");

//             // adiciona o produto vinculando no orderitens
//             prep1.setInt(1, idOV);
//             prep1.setInt(2, b.getId());
//             prep1.setInt(3, b.getQtde());
//             prep1.addBatch();

//             // atualiza a tabela de produtos, decrementando o estoque.
//             prep2.setInt(1, qtdeDeLivroNoBanco - b.getQtde());
//             prep2.setInt(2, b.getId());
//             prep2.addBatch();

//         }
//     }
// }
