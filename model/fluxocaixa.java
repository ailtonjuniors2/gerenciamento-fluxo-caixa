package model;

import java.util.ArrayList;
import java.util.List;

    public class fluxocaixa{
    private List<transacao> transacaoList;

    public fluxocaixa(){
        this.transacaoList = new ArrayList<>();
    }

    public void adicionartransacao(transacao t){
        transacaoList.add(t);
    }

    public void removertransacao(transacao t){
        transacaoList.remove(t);
    }

    public double calcularsaldo(){
        double saldo = 0.0;
        for (transacao t : transacaoList){
            if(t.getTipo().equalsIgnoreCase("entrada")){
                saldo+= t.getValor();
            } else if(t.getTipo().equalsIgnoreCase("saida")){
                saldo -= t.getValor();
            }

        }
        return saldo;
    }

        public List<transacao> getTransacaoList() {
            return transacaoList;
        }
    }