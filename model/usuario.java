package model;

public class usuario{
    public String usuario;
    private String senha;

    public usuario(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario(){
        return usuario;
    }

    public String getSenha(){
        return senha;
    }

    public String toCSV() {
        return usuario + "," + senha;
    }
}