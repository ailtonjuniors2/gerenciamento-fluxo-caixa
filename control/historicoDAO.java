package control;

import model.transacao;

import java.io.*;
import java.util.Stack;

public class historicoDAO {

    private static String getArquivoHistorico(String usuario) {
        return "data/" + usuario + "_historico.txt";
    }

    public static void salvarHistorico(String usuario, Stack<transacao> transacoes) throws IOException {
        File dir = new File("data");
        if (!dir.exists()) dir.mkdir();

        BufferedWriter bw = new BufferedWriter(new FileWriter(getArquivoHistorico(usuario)));
        for (transacao t : transacoes) {
            bw.write(t.getTipo() + "|" + t.getDescricao() + "|" + t.getValor() + "|" + t.getData());
            bw.newLine();
        }
        bw.close();
    }

    public static Stack<transacao> carregarHistorico(String usuario) throws IOException {
        Stack<transacao> transacoes = new Stack<>();
        File file = new File(getArquivoHistorico(usuario));
        if (!file.exists()) return transacoes;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linha;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split("\\|");
            if (dados.length == 4) {
                transacao t = new transacao(dados[0], dados[1], Double.parseDouble(dados[2]));
                t.setData(java.time.LocalDate.parse(dados[3]));
                transacoes.push(t);
            }
        }
        br.close();
        return transacoes;
    }
}
