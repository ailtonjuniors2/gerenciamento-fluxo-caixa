package control;

import java.io.*;

public class saldoDAO{
    public static void salvarSaldo(String usuario, double saldo)throws Exception{
        String arquivo = "data/saldo_" + usuario + ".txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));
        bw.write(String.valueOf(saldo));
        bw.close();
    }

    public static double carregarSaldo(String usuario) throws Exception{
        return 0;
    }
}
