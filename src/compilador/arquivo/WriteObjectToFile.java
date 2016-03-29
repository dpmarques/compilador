package compilador.arquivo;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class WriteObjectToFile {
        public static void escrever(String usuario) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Usuario.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(usuario);
            objectOutputStream.flush();
            objectOutputStream.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
