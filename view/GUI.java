package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import control.usuarioDAO;
import model.historico;
import model.transacao;
import model.usuario;
import model.fluxocaixa;

public class GUI extends JFrame{
    private ArrayList<Double> transacoes = new ArrayList<>();
    private JLabel labelSaldo;
    private historico historicoTransacoes = new historico();
    private JTextPane areahistorico;
    private StyledDocument docHistorico;


    public GUI(){
        setTitle("Sistema de Fluxo de Caixa");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JTabbedPane abas = new JTabbedPane();

        //painel entrada
        JPanel painelentrada =  new JPanel(new BorderLayout());
        painelentrada.setLayout(new BoxLayout(painelentrada, BoxLayout.Y_AXIS));
        painelentrada.setBorder(new EmptyBorder(20, 40, 20, 40));
        painelentrada.setBackground(Color.WHITE);

        JLabel labelEntrada = new JLabel("Valor da Entrada");
        labelEntrada.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelEntrada.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoentrada = new JTextField();
        campoentrada.setMaximumSize(new Dimension(300,40));
        campoentrada.setFont(new Font("SansSerif", Font.BOLD, 14));
        campoentrada.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botaoAdicionarEntrada = new JButton("Adicionar");
        botaoAdicionarEntrada.setBackground(new Color(34,139,34));
        botaoAdicionarEntrada.setForeground(Color.WHITE);
        botaoAdicionarEntrada.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoAdicionarEntrada.setFocusPainted(false);
        botaoAdicionarEntrada.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoAdicionarEntrada.setMaximumSize(new Dimension(200, 40));
        botaoAdicionarEntrada.setAlignmentX(Component.CENTER_ALIGNMENT);


        painelentrada.add(Box.createRigidArea(new Dimension(0, 10)));
        painelentrada.add(labelEntrada);
        painelentrada.add(Box.createRigidArea(new Dimension(0, 10)));
        painelentrada.add(campoentrada);
        painelentrada.add(Box.createRigidArea(new Dimension(0, 20)));
        painelentrada.add(botaoAdicionarEntrada);


        // aba saida
        JPanel painelsaida = new JPanel();
        painelsaida.setLayout(new BoxLayout(painelsaida,BoxLayout.Y_AXIS));
        painelsaida.setBorder(new EmptyBorder(20, 40, 20, 40));
        painelsaida.setBackground(Color.WHITE);

        JLabel labelSaida = new JLabel("Valor da Saída");
        labelSaida.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelSaida.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField camposaida = new JTextField();
        camposaida.setMaximumSize(new Dimension(300, 40));
        camposaida.setFont(new Font("SansSerif", Font.BOLD, 14));
        camposaida.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botaoAdicionarSaida = new JButton("Remover");
        botaoAdicionarSaida.setBackground(new Color(244, 67, 54));
        botaoAdicionarSaida.setForeground(Color.WHITE);
        botaoAdicionarSaida.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoAdicionarSaida.setFocusPainted(false);
        botaoAdicionarSaida.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoAdicionarSaida.setMaximumSize(new Dimension(200, 40));
        botaoAdicionarSaida.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelsaida.add(Box.createRigidArea(new Dimension(0, 10)));
        painelsaida.add(labelSaida);
        painelsaida.add(Box.createRigidArea(new Dimension(0, 10)));
        painelsaida.add(camposaida);
        painelsaida.add(Box.createRigidArea(new Dimension(0, 20)));
        painelsaida.add(botaoAdicionarSaida);


        //painel saldo
        JPanel painelsaldo = new JPanel();
        painelsaldo.setLayout(new BoxLayout(painelsaldo, BoxLayout.Y_AXIS));
        painelsaldo.setBorder(new EmptyBorder(30, 30, 30, 30));
        painelsaldo.setBackground(Color.WHITE);


        labelSaldo = new JLabel("Saldo atual: R$ 0.00");
        labelSaldo.setFont(new Font("SansSerif", Font.BOLD, 24));
        labelSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelsaldo.add(Box.createVerticalGlue());
        painelsaldo.add(labelSaldo);
        painelsaldo.add(Box.createVerticalGlue());

        JPanel painelhistorico = new JPanel(new BorderLayout());
        areahistorico = new JTextPane();
        areahistorico.setEditable(false);
        docHistorico = areahistorico.getStyledDocument();

        JScrollPane scroll = new JScrollPane(areahistorico);
        painelhistorico.add(scroll, BorderLayout.CENTER);

        abas.add("Entrada", painelentrada);
        abas.add("Saída", painelsaida);
        abas.add("Saldo", painelsaldo);
        abas.add("Histórico", painelhistorico);

        botaoAdicionarEntrada.addActionListener(e ->{
            try{
                double valor = Double.parseDouble(campoentrada.getText());
                if (valor <= 0) throw new NumberFormatException();
                transacao t = new transacao("entrada", "Valor adicionado manualmente", valor);
                historicoTransacoes.adicionarTransacao(t);
                fluxocaixa.adicionartransacao(t);

                atualizarSaldo();
                atualizarHistorico();

                campoentrada.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um valor válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                campoentrada.setText("");
            }

        });
        botaoAdicionarSaida.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(camposaida.getText());
                if (valor <= 0) throw new NumberFormatException();
                transacao t = new transacao("saida", "Valor removido manualmente", valor);
                fluxocaixa.adicionartransacao(t);
                historicoTransacoes.adicionarTransacao(t);

                atualizarHistorico();
                atualizarSaldo();

                camposaida.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um valor válido.", "Erro", JOptionPane.ERROR_MESSAGE);}
                camposaida.setText("");
        });

        abas.addChangeListener(e -> {
            int abaSelecionada = abas.getSelectedIndex();

            if (abaSelecionada == 0){
                getRootPane().setDefaultButton(botaoAdicionarEntrada);
            } else if (abaSelecionada == 1){
                getRootPane().setDefaultButton(botaoAdicionarSaida);
            } else{
                getRootPane().setDefaultButton(null);
            }
        });
        add(abas);
        setVisible(true);
    }
    public void atualizarSaldo(){
        double saldo = fluxocaixa.calcularsaldo();
        labelSaldo.setText(String.format("Saldo atual: R$%.2f", saldo));

        if (saldo > 0){
            labelSaldo.setForeground(new Color(0,128,0));
        } else if (saldo < 0){
            labelSaldo.setForeground(new Color(139,0,0));
        } else {
            labelSaldo.setForeground(null);
        }
    }

    public void atualizarHistorico() {
        areahistorico.setText("");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (transacao t : historicoTransacoes.getTransacoes()) {
            String texto = "[" + t.getData().format(dtf) + "] "
                    + t.getTipo() + " - "
                    + t.getDescricao() + ": R$ "
                    + String.format("%.2f", t.getValor()) + "\n";


            texto += "-----------------------------------\n";

            try {
                SimpleAttributeSet estilo = new SimpleAttributeSet();

                if (t.getTipo().equalsIgnoreCase("entrada")){
                    StyleConstants.setForeground(estilo, new Color(0, 128, 0));
                } else{
                    StyleConstants.setForeground(estilo, new Color(139,0,0));
                }

                StyleConstants.setFontSize(estilo, 14);
                StyleConstants.setBold(estilo, true);


                docHistorico.insertString(docHistorico.getLength(), texto, estilo);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
