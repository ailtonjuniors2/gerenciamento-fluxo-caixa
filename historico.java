package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class historico { //criação do historico
    private List<transacao> transacoes;

    public historico() {
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(transacao t){
        transacoes.add(t);
    }

    public List<transacao> getTransacoes() {
        return transacoes;
    }

    public void exibirHistorico () { //exibe ao usuario
        System.out.println("Historico de transações:");
        for (transacao t : transacoes) {
            System.out.println("Tipo: " + t.getTipo() + ", Descrição: " + t.getDescricao() + ", Valor: " + t.getValor() + ", Data: " + t.getData());
        }
    }
    public void salvarHistorico(String dados) { //para salvar historico como arquivo de texto
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dados))) { //cria um leitor
            for (transacao t : transacoes) {
                String linha = t.getTipo() + ";" + t.getDescricao() + ";" + t.getValor() + ";" + t.getData();
                writer.write(t.getTipo() + ";" +
                        t.getDescricao() + ";" +
                        t.getValor() + ";" +
                        t.getData().toString());
                writer.newLine();
            }
            System.out.println("Transações salvas em: " + dados); //verifica mensagem de erro
        } catch (IOException e) {
            System.err.println("Erro ao salvar transações: " + e.getMessage());
        }
    }

    public void carregarHistorico (String dados) { //carrega o arquivo de historico
        try (BufferedReader reader = new BufferedReader((new FileReader(dados)))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");


                if (partes.length == 4) { //verifica se tem todas as partes
                    String tipo = partes[0];
                    String descricao = partes[1];
                    double valor = Double.parseDouble(partes[2]);
                    LocalDate data = LocalDate.parse(partes[3]);
                    transacao t = new transacao(tipo, descricao, valor, data);
                    transacoes.add(t);
                }
            }
            System.out.println("Transações carregadas de: " + dados);
        } catch (IOException e) {
            System.err.println("Erro ao carregar transações: " + e.getMessage());
        }
    }
}
