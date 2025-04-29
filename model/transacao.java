package model;
import java.time.LocalDate;

public class transacao{
    private String tipo, descricao;
    private double valor;
    private LocalDate data;

    public transacao(String tipo, String descricao, double valor){
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.data = LocalDate.now();
    }

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