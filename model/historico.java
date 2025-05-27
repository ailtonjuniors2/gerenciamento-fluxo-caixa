package model;

import java.util.Stack;

public class historico {
    private Stack<transacao> transacoes;
    private Stack<transacao> transacoesDesfeitas;

    public historico() {
        this.transacoes = new Stack<>();
        this.transacoesDesfeitas = new Stack<>();
    }

    public void adicionarTransacao(transacao t) {
        transacoes.push(t);
        transacoesDesfeitas.clear();
    }

    public Stack<transacao> getTransacoes() {
        return transacoes;
    }

    public void desfazerTransacao() {
        if (!transacoes.isEmpty()) {
            transacao removida = transacoes.pop();
            transacoesDesfeitas.push(removida);
            System.out.println("Transação desfeita: " + removida.getDescricao());
        } else {
            System.out.println("Nenhuma transação para desfazer.");
        }
    }

    public void refazerTransacao() {
        if (!transacoesDesfeitas.isEmpty()) {
            transacao refeita = transacoesDesfeitas.pop();
            transacoes.push(refeita);
            System.out.println("Transação refeita: " + refeita.getDescricao());
        } else {
            System.out.println("Nenhuma transação para refazer.");
        }
    }
}
