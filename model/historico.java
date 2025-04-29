package model;

import java.util.ArrayList;
import java.util.List;

public class historico {
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

    public void exibirHistorico () {
        System.out.println("Historico de transações:");
        for (transacao t : transacoes) {
            System.out.println("Tipo: " + t.getTipo() + ", Descrição: " + t.getDescricao() + ", Valor: " + t.getValor() + ", Data: " + t.getData());
        }
    }
}