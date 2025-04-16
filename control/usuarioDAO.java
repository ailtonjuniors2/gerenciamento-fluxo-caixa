package control;

import model.usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class usuarioDAO {
    private static final String ARQUIVO = "usuarios.txt";

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
    public static boolean verificarUsuarioCadastrado() throws IOException{
        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists() || arquivo.length() == 0){
            return false;
        }

        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String linha = br.readLine();
        br.close();

        return linha != null && !linha.isEmpty();
    }
}
