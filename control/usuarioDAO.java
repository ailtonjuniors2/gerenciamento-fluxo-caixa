package control;

import model.usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class usuarioDAO {//para criar o txt dos usuarios
    private static final String ARQUIVO = "data/usuarios.txt";

    public static void salvarUsuario(usuario user) throws IOException { //para salvar o usuario a partir de um txt
        BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true));
        bw.write(user.toCSV());
        bw.newLine();
        bw.close();

    }

    public static List<usuario> carregarUsuarios() throws IOException { //criar a lista de usuarios
        List<usuario> usuarios = new ArrayList<>();
        File file = new File(ARQUIVO);
        if (!file.exists())
            return usuarios;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linha;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length == 2) {
                usuarios.add(new usuario(dados[0], dados[1]));
            }
        }
        br.close();
        return usuarios;
    }
    public static boolean verificarUsuarioCadastrado(String user) throws IOException{ //verifica se o usuario existe no cadastro e ignora
        List<usuario> usuarios = carregarUsuarios();
        for (usuario users : usuarios) {
            if (users.getUsuario().equals(user)) {
                return true;
            }
        }
        return false;
    }
    public static boolean verificarLogin(String user, String senha) throws IOException { //para o login verificar que o usuario existe
        List<usuario> usuarios = carregarUsuarios();
        for (usuario users : usuarios) {
            if (users.getUsuario().equals(user) && users.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public static usuario buscarUsuario(String user) throws IOException{
        List<usuario> usuarios = carregarUsuarios();
        for (usuario users : usuarios){
            if (users.getUsuario().equals(user)){
                return users;
            }
        }
        return null;
    }
}
