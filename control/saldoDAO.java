package control;

import java.io.*;

public class saldoDAO {
    private static String getArquivoSaldo(String usuario) { //cria o txt
        return "data/saldo_" + usuario + ".txt";
    }

    public static void salvarSaldo(String usuario, double saldo) throws Exception { //salva a permanencia do saldo
        File data = new File("data");
        if (!data.exists()) { //checa se o arquivo existe
            data.mkdirs();
        }
        
        String arquivo = getArquivoSaldo(usuario); //pega o arquivo txt do salvo
        BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));
        bw.write(String.valueOf(saldo));
        bw.close();
    }

    public static double carregarSaldo(String usuario) throws Exception { //carrega o arquivo do txt 
        String arquivo = getArquivoSaldo(usuario);
        File file = new File(arquivo);

        if (!file.exists()) {
            return 0.0;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linha = br.readLine();
        br.close();

        return linha != null ? Double.parseDouble(linha) : 0.0;
    }
}
