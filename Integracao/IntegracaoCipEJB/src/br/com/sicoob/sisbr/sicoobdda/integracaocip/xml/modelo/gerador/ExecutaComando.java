package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.gerador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ExecutaComando
 * 
 * @author Rafael.Silva
 */
public class ExecutaComando {

    private ExecutaComando() {
    }

    public static void executarComando(String comando) throws IOException {
        BufferedReader buffer = null;
        try {
            Process child = Runtime.getRuntime().exec(comando);
            buffer = new BufferedReader(new InputStreamReader(child.getInputStream()));
            String line = null;
            while ((line = buffer.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            if (buffer != null) {
                buffer.close();
            }
        }
    }
}
