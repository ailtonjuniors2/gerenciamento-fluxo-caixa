package model;

public class usuario{ //cria o usuario
    public String usuario;
    private String senha;

    public usuario(String usuario, String senha){ //define a senha e o nome
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