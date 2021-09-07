package com.nappla;

import com.nappla.components.Button;
import com.nappla.controllers.AccountController;
import com.nappla.controllers.CreateAccountController;
import com.nappla.controllers.LoginController;
import com.nappla.models.Account;
import com.nappla.views.Auth;
import com.nappla.views.Login;
import com.nappla.views.WarningDialog;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static Connection conn = null;
    public Main() {
    }

    public static void createConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/bank";
        try {
            conn = DriverManager.getConnection(url, "postgres", "postgres");
            System.out.println("conectado");
        } catch (SQLException excecao) {
            new WarningDialog(null, "Erro ao conectar ao banco de dados");
            throw new SQLException();
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {

               Auth auth = new Auth();
               Account account = new Account();

               LoginController loginController = new LoginController(account, auth);
               CreateAccountController createAccountController = new CreateAccountController(account, auth);

            } catch (Exception e) {
                e.printStackTrace();
                new WarningDialog(null, "Error");
            }
        });
    }
}