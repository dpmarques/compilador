package compilador.arquivo;


import java.io.FileInputStream;
import java.io.ObjectInputStream;
/**
 *
 * @author willian
 */
public class ReaderObjectToFile {
    
     public static String ler() {
            String usu = null;
            try {
                FileInputStream leitorArquivos = new FileInputStream("Usuario.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(leitorArquivos);

                usu = (String) objectInputStream.readObject();

                leitorArquivos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

           return usu;
    }
}
