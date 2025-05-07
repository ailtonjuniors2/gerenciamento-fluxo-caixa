package model;

import java.util.ArrayList;
import java.util.List;

    public class fluxocaixa{
    private static List<transacao> transacaoList = new ArrayList<>();

    public fluxocaixa(){ //para existir o construtor

    }

    public static void adicionartransacao(transacao t){
        transacaoList.add(t);
    }

    public void removertransacao(transacao t){
        transacaoList.remove(t);
    }

    public static double calcularsaldo(){

        if (transacaoList == null) {
            return 0.0;
        }

        double saldo = 0.0;//caso fosse nescessario fazer por linha de comando
        for (transacao t : transacaoList){
            if(t.getTipo().equalsIgnoreCase("entrada")){
                saldo += t.getValor();
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