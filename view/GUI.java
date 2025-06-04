package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Stack;
import java.util.List;
import control.historicoDAO;
import model.historico;
import model.transacao;
import model.usuario;
import control.saldoDAO;

public class GUI extends JFrame{
    private usuario usuarioLogado;
    private JLabel labelSaldo;
    private historico historicoTransacoes = new historico();
    private JTextPane areahistorico;
    private StyledDocument docHistorico;
    private double saldoAtual = 0.0;



    public GUI(usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        try {
            Stack<transacao> transacoesSalvas = historicoDAO.carregarHistorico(usuarioLogado.getUsuario());

            for (transacao t : transacoesSalvas) {
                historicoTransacoes.adicionarTransacao(t);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            saldoAtual = saldoDAO.carregarSaldo(usuarioLogado.getUsuario());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //cria o painel geral
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


        // painel de saida
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


        //painel de saldo
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

        JButton botaoRefazer = new JButton("Refazer");
        botaoRefazer.setBackground(new Color(0, 129, 6));
        botaoRefazer.setForeground(Color.WHITE);
        botaoRefazer.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoRefazer.setFocusPainted(false);
        botaoRefazer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoRefazer.setMaximumSize(new Dimension(200, 40));
        botaoRefazer.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botaoDesfazer = new JButton("Desfazer");
        botaoDesfazer.setBackground(new Color(139,0,0));
        botaoDesfazer.setForeground(Color.WHITE);
        botaoDesfazer.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoDesfazer.setFocusPainted(false);
        botaoDesfazer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoDesfazer.setMaximumSize(new Dimension(200, 40));
        botaoDesfazer.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botaoDownload = new JButton("Download");
        botaoDownload.setBackground(Color.BLUE);
        botaoDownload.setForeground(Color.WHITE);
        botaoDownload.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoDownload.setFocusPainted(false);
        botaoDownload.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoDownload.setMaximumSize(new Dimension(200, 40));
        botaoDownload.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel painelbotoeshistorico = new JPanel();
        painelbotoeshistorico.add(botaoRefazer);
        painelbotoeshistorico.add(botaoDesfazer);
        painelbotoeshistorico.add(botaoDownload);

        JScrollPane scroll = new JScrollPane(areahistorico);
        painelhistorico.add(scroll, BorderLayout.CENTER);
        painelhistorico.add(painelbotoeshistorico, BorderLayout.SOUTH);


        abas.add("Entrada", painelentrada);
        abas.add("Saída", painelsaida);
        abas.add("Saldo", painelsaldo);
        abas.add("Histórico", painelhistorico);

        botaoDownload.addActionListener( e -> {
            gerarArquivoDownload();
        });

        botaoAdicionarEntrada.addActionListener(e ->{
            try{ //faz com que no painel de entrada atualize o saldo e o historico
                double valor = Double.parseDouble(campoentrada.getText());
                if (valor <= 0) throw new NumberFormatException();
                transacao t = new transacao("entrada", "Valor adicionado manualmente", valor);
                historicoTransacoes.adicionarTransacao(t);
                saldoAtual += valor;
                atualizarHistorico();
                atualizarSaldo();

                try{
                    saldoDAO.salvarSaldo(usuarioLogado.getUsuario(), saldoAtual);
                    historicoDAO.salvarHistorico(usuarioLogado.getUsuario(), historicoTransacoes.getTransacoes());
                } catch (Exception ex){
                    ex.printStackTrace();
                }

                campoentrada.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um valor válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                campoentrada.setText("");
            }

        });
        botaoAdicionarSaida.addActionListener(e -> {
            try { //faz com que no painel de saida atualize o saldo e o historico
                double valor = Double.parseDouble(camposaida.getText());
                if (valor <= 0) throw new NumberFormatException();
                transacao t = new transacao("saida", "Valor removido manualmente", valor);
                historicoTransacoes.adicionarTransacao(t);
                saldoAtual -= valor;
                atualizarHistorico();
                atualizarSaldo();

                try{
                    saldoDAO.salvarSaldo(usuarioLogado.getUsuario(), saldoAtual);
                    historicoDAO.salvarHistorico(usuarioLogado.getUsuario(), historicoTransacoes.getTransacoes());
                } catch (Exception ex){
                    ex.printStackTrace();
                }

                camposaida.setText("");

            } catch (NumberFormatException ex) { //mostra um erro de I/O para o usuario
                JOptionPane.showMessageDialog(this, "Digite um valor válido.", "Erro", JOptionPane.ERROR_MESSAGE);}
            camposaida.setText("");
        });

        botaoDesfazer.addActionListener(e -> {
            transacao desfeita = historicoTransacoes.desfazerTransacao();
            if (desfeita != null){
                if (desfeita.getTipo().equals("entrada")) {
                    saldoAtual -= desfeita.getValor();
                } else {
                    saldoAtual += desfeita.getValor(); // já está certo aqui
                }

                try {
                    saldoDAO.salvarSaldo(usuarioLogado.getUsuario(), saldoAtual);
                    historicoDAO.salvarHistorico(usuarioLogado.getUsuario(), historicoTransacoes.getTransacoes());
                    atualizarSaldo();
                    atualizarHistorico();
                    JOptionPane.showMessageDialog(this, "Transação desfeita com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Não há transações para desfazer.","Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });


        botaoRefazer.addActionListener(e -> {
            transacao refeita = historicoTransacoes.refazerTransacao();
            if (refeita != null){
                if (refeita.getTipo().equals("entrada")) {
                    saldoAtual += refeita.getValor();
                } else {
                    saldoAtual -= refeita.getValor(); // CORRIGIDO: subtrai se for saída
                }

                try {
                    saldoDAO.salvarSaldo(usuarioLogado.getUsuario(), saldoAtual);
                    historicoDAO.salvarHistorico(usuarioLogado.getUsuario(), historicoTransacoes.getTransacoes());
                    atualizarSaldo();
                    atualizarHistorico();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
            JOptionPane.showMessageDialog(this, "Não há transações para refazer.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
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

        atualizarHistorico();
        atualizarSaldo();
    }
    public void atualizarSaldo(){
        labelSaldo.setText(String.format("Saldo atual: R$%.2f", saldoAtual));

        if (saldoAtual > 0){
            labelSaldo.setForeground(new Color(0,128,0));
        } else if (saldoAtual < 0){
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
    public void gerarArquivoDownload(){
        List<transacao> transacoes = historicoTransacoes.getTransacoes();
        if (transacoes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma transação para exportar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Histórico de transações");
        sb.append("\n");

        for (transacao t : transacoes){
            sb.append("Tipo: ").append(t.getTipo()).append("\n")
                    .append("Descrição: ").append(t.getDescricao()).append("\n")
                    .append("Data: ").append(t.getData()).append("\n")
                    .append("Valor: R$").append(t.getValor()).append("\n").append("\n");
        }

        try {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Salvar Transações");
            jfc.setSelectedFile(new File(usuarioLogado.getUsuario() + ".txt"));
            int userselection = jfc.showSaveDialog(this);

            if (userselection == jfc.APPROVE_OPTION){
                File arquivo = jfc.getSelectedFile();
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))){
                    bw.write(sb.toString());
                    JOptionPane.showMessageDialog(this, "Arquivo salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (IOException ex){
            JOptionPane.showMessageDialog(this, "Erro ao salvar arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}