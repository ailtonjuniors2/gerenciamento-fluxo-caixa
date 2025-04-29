package view;

import control.usuarioDAO;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class login extends JFrame {
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton botaoLogin;
    private JButton botaoCadastro;

    public login(){
        setTitle("Login - Fluxo de Caixa");
        setSize(400,250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelprincipal = new JPanel();
        painelprincipal.setLayout(new BorderLayout());
        painelprincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelprincipal.setBackground(new Color(245, 245, 245));

        JLabel titulo = new JLabel("Acesso ao sistema", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setBorder(new EmptyBorder(0, 0, 10, 0));

        JPanel painelcampos = new JPanel();
        painelcampos.setLayout(new BoxLayout(painelcampos, BoxLayout.Y_AXIS));
        painelcampos.setBackground(new Color(245, 245, 245));

        //campos
        campoLogin = new JTextField();
        campoSenha = new JPasswordField();

        campoLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        campoSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        campoLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoSenha.setAlignmentX(Component.CENTER_ALIGNMENT);


        campoLogin.setBorder(BorderFactory.createTitledBorder("Login"));
        campoSenha.setBorder(BorderFactory.createTitledBorder("Senha"));

        painelcampos.add(campoLogin);
        painelcampos.add(campoSenha);

        //botao entrar
        botaoLogin = new JButton("Entrar");
        getRootPane().setDefaultButton(botaoLogin);
        botaoLogin.setBackground(new Color(66, 133, 244));
        botaoLogin.setForeground(Color.WHITE);
        botaoLogin.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //botao cadastrar
        botaoCadastro = new JButton("Cadastro");
        botaoCadastro.setBackground(new Color(52, 152, 219));
        botaoCadastro.setForeground(Color.WHITE);
        botaoCadastro.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel painelinferior = new JPanel();
        painelinferior.setLayout(new GridLayout(1, 2, 5, 5));
        painelinferior.setBackground(new Color(245, 245, 245));
        painelinferior.add(botaoLogin);
        painelinferior.add(botaoCadastro);

        painelprincipal.add(titulo, BorderLayout.NORTH);
        painelprincipal.add(painelcampos, BorderLayout.CENTER);
        painelprincipal.add(painelinferior, BorderLayout.SOUTH);

        add(painelprincipal);
        setVisible(true);

        // açao botao login
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = campoLogin.getText();
                String senha = new String(campoSenha.getPassword());

                try {
                    if (usuarioDAO.verificarLogin(login, senha)) {
                        JOptionPane.showMessageDialog(null, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new GUI();

                    } else {
                        JOptionPane.showMessageDialog(null, "Login ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex){
                    JOptionPane.showMessageDialog(null, "Erro ao acessar o sistema: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new cadastro();
            }
        });

    }
}