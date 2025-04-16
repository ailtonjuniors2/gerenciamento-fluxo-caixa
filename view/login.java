package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton botaoLogin;

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



        botaoLogin = new JButton("Entrar");
        botaoLogin.setBackground(new Color(66, 133, 244));
        botaoLogin.setForeground(Color.WHITE);
        botaoLogin.setFocusPainted(false);
        botaoLogin.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel painelinferior = new JPanel();
        painelinferior.setBackground(new Color(245, 245, 245));
        painelinferior.add(botaoLogin);

        painelprincipal.add(titulo, BorderLayout.NORTH);
        painelprincipal.add(painelcampos, BorderLayout.CENTER);
        painelprincipal.add(painelinferior, BorderLayout.SOUTH);

        add(painelprincipal);
        setVisible(true);

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = campoLogin.getText();
                String senha = new String(campoSenha.getPassword());

                if(login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("123456")){
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    // new GUI();

                } else{
                    JOptionPane.showMessageDialog(null, "Login ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }
}