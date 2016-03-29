
package compilador.arquivo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterLog {
    
    public static void escrever(String mensagem) throws IOException{
        //O segundo parametro "true" indica append para o arquivo em questao.
        String logFile = "Registro.txt";

            FileWriter fileWriter = new FileWriter(logFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String date = (new java.util.Date()).toString();
            String msg = date + "\r\n" + mensagem + "\r\n\r\n";
            bufferedWriter.write(msg);
            bufferedWriter.flush();
            bufferedWriter.close();
    }

    public static void escreveUsuario(String mensagem) throws IOException {
        //O segundo parametro "true" indica append para o arquivo em questao.
        String logFile = "Usuario.txt";

        FileWriter fileWriter = new FileWriter(logFile, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String msg = mensagem;
        bufferedWriter.write(msg);
        bufferedWriter.flush();
        bufferedWriter.close();
     }

}
