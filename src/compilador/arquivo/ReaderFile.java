/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author kigs
 */
public class ReaderFile {
    public static String LerCodigo(String codigo) throws IOException {
        //File f = new File("C://ultimoUsuario.txt");
        File f = new File(codigo);
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
