/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.util.Scanner;

import Dao.UserDao;
import Model.User;

/**
 *
 * @author Jonathan
 */
public class UserBLL {
    public UserBLL() {
        user = new User();
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void login() {

        System.out.println("Você já tem um usuario cadastrado? ");
        System.out.println("Não = 0 / Sim = 1");
        if (Integer.parseInt(new Scanner(System.in).nextLine()) == 1) {

            System.out.println("Digite id do Usuario: ");
            user = UserDao.consultar(Integer.parseInt(new Scanner(System.in).nextLine()));
            if (user.getId() > 0) {
                System.out.println("Bem vindo " + user.getName());
            }

        } else {
            System.out.println("Digite seu nome: ");
            user.setName(new Scanner(System.in).nextLine());
            user.setType("Cliente");
            user.setId(UserDao.insert(user));
            if (user.getId() > 0) {
                System.out.println("Bem vindo " + user.getName());
            }
        }

    }
}
