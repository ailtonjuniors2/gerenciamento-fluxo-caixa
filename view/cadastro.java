package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import control.usuarioDAO;
import model.usuario;

public class cadastro extends JFrame{
    private JTextField campoUser;
    private JPasswordField campoSenha;
    private JButton botaoCadastro;

    public cadastro(){
        setTitle("Cadastro do Usuário");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        campoUser = new JTextField();
        campoSenha = new JPasswordField();

        campoUser.setBorder(BorderFactory.createTitledBorder("Usuário"));
        campoSenha.setBorder(BorderFactory.createTitledBorder("Senha"));

    }

}