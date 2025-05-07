package model;
import java.time.LocalDate;

public class transacao{  //criação do painel de transação
    private String tipo, descricao;
    private double valor;
    private LocalDate data;

    public transacao(String tipo, String descricao, double valor){ //define o tipo a descrição, o valor e a data de transação
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.data = LocalDate.now();
    }
    //getters e setters dos modulos de transação
    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data){
        this.data = data;
    }
}