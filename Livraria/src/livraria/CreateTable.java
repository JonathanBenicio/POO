package livraria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonathan
 */
public class CreateTable {

    public void create() throws SQLException, ClassNotFoundException {
        // Connection Configuration
        Properties connConfig = new Properties();
        connConfig.setProperty("user", "root");
        connConfig.setProperty("password", "jonathan");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/library", connConfig)) {

            // Disable Auto-Commit
            conn.setAutoCommit(false);

            // user
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.user ("
                                + "usid int PRIMARY KEY AUTO_INCREMENT,"
                                + "name VARCHAR(25),"
                                + "type VARCHAR(25))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // publisher
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.publisher ("
                                + "puid int PRIMARY KEY AUTO_INCREMENT,"
                                + "name VARCHAR(25),"
                                + "email VARCHAR(100))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // author
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.author ("
                                + "auid int PRIMARY KEY AUTO_INCREMENT,"
                                + "name VARCHAR(25))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // book
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.books ("
                                + "boid int PRIMARY KEY AUTO_INCREMENT,"
                                + "title VARCHAR(25),"
                                + "paginas int NOT NULL,"
                                + "fkpuid int NOT NULL,"
                                + "fkauid int NOT NULL,"
                                + "CONSTRAint FK_puid FOREIGN KEY (fkpuid)"
                                + " REFERENCES LIBRARY.publisher(puid),"
                                + "CONSTRAint FK_auid FOREIGN KEY (fkauid)"
                                + " REFERENCES LIBRARY.author(auid))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // order
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.order ("
                                + "orid int PRIMARY KEY AUTO_INCREMENT,"
                                + "date datetime(6),"
                                + "numberofbooks int NOT NULL,"
                                + "fkusid int NOT NULL,"
                                + "CONSTRAint FK_pusid FOREIGN KEY (fkusid)"
                                + " REFERENCES LIBRARY.user(usid))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // order itens
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS library.orderitens ("
                                + "oritid int PRIMARY KEY AUTO_INCREMENT,"
                                + "quantidade int NOT NULL,"
                                + "fkorid int NOT NULL," // id da venda
                                + "fkboid int NOT NULL," // id do livro
                                + "CONSTRAint FK_orid FOREIGN KEY (fkorid)"
                                + " REFERENCES LIBRARY.order(orid),"
                                + "CONSTRAint FK_boid FOREIGN KEY (fkboid)"
                                + " REFERENCES LIBRARY.books(boid)"
                                + ")"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
