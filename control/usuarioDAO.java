package control;

import model.usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class usuarioDAO {
    private static final String ARQUIVO = "data/usuarios.txt";

    public static void salvarUsuario(usuario user) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true));
        bw.write(user.toCSV());
        bw.newLine();
        bw.close();

    }

    public static List<usuario> carregarUsuarios() throws IOException {
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
    public static boolean verificarUsuarioCadastrado(String user) throws IOException{
        List<usuario> usuarios = carregarUsuarios();
        for (usuario users : usuarios) {
            if (users.getUsuario().equalsIgnoreCase(user)) {
                return true;
            }
        }
        return false;
    }
    public static boolean verificarLogin(String user, String senha) throws IOException {
        List<usuario> usuarios = carregarUsuarios();
        for (usuario users : usuarios) {
            if (users.getUsuario().equalsIgnoreCase(user) && users.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
}
