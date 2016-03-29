package compilador.arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ReaderLog {
    
    public String usuarioAnterior() throws IOException {
        //File f = new File("C://ultimoUsuario.txt");
        File f = new File("Usuario.txt");
        String linha = "";
        
        if (f.exists()) {
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                linha = bufferedReader.readLine();
            }

            bufferedReader.close();
        }
        
        return linha;
    }

}
