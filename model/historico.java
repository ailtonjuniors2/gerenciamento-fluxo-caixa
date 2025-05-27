package model;

import java.io.*;
import java.util.Stack;
import java.util.List;
import java.time.LocalDate;

public class historico { //criação do historico
    private List<transacao> transacoes;

    public historico() {
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(transacao t) { //adiciona transação
        transacoes.push(t);
    }

    public Stack<transacao> getTransacoes() { // pega a transação
        return transacoes;
    }

    public void exibirHistorico() { //exibe ao usuario
        System.out.println("Historico de transações:");
        for (transacao t : transacoes) {
            System.out.println("Tipo: " + t.getTipo() + ", Descrição: " + t.getDescricao() + ", Valor: " + t.getValor() + ", Data: " + t.getData());
        }
    }

    public void removerTransacao() {
        if (!transacoes.isEmpty()) {
            transacao removida = transacoes.pop();
            System.out.println("Transação desfeita: " + removida.getDescricao());
                } else {
                    System.out.println("Nenhuma transação para desfazer.");
                }
            }
        }
