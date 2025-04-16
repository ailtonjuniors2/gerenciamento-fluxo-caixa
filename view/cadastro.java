package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        painel.setLayout(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel painelcampos = new JPanel();
        painelcampos.setLayout(new GridLayout(2, 1, 10, 15));
        painelcampos.setBackground(new Color(245, 245, 245));

        // definição dos campos
        campoUser = new JTextField();
        campoSenha = new JPasswordField();

        campoUser.setPreferredSize(new Dimension(300, 40));
        campoSenha.setPreferredSize(new Dimension(300, 40));


        campoUser.setBorder(BorderFactory.createTitledBorder("Usuário"));
        campoSenha.setBorder(BorderFactory.createTitledBorder("Senha"));

        painelcampos.add(campoUser);
        painelcampos.add(campoSenha);


        // botao de cadastro
        botaoCadastro = new JButton("Cadastrar");
        botaoCadastro.setBackground(new Color(46, 204, 113));
        botaoCadastro.setForeground(Color.WHITE);
        botaoCadastro.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //componentes ao painel
        painel.add(painelcampos, BorderLayout.CENTER);

        //painel botao
        JPanel painelBotao = new JPanel();
        painelBotao.add(botaoCadastro);
        painel.add(painelBotao, BorderLayout.SOUTH);

        //açao para o botao
        botaoCadastro.addActionListener((ActionEvent e) -> {
                String user = campoUser.getText().trim(); //pega o texto e remove os espaços em branco
                String senha = new String(campoSenha.getPassword()).trim();

                if(user.isEmpty() || senha.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                usuario us = new usuario(user, senha);

                try{
                    if (usuarioDAO.verificarUsuarioCadastrado(user)) {
                        JOptionPane.showMessageDialog(this, "Usuário já cadastrado, tente novamente.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else {
                        usuarioDAO.salvarUsuario(us);
                        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }


            }catch (IOException ex){
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar o usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE) ;
                }
        });
        add(painel);
        setVisible(true);
    }

}