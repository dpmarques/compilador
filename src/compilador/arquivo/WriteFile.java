package compilador.arquivo;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class WriteFile {
        public static void escrever(String codigo) {
        /*try {
            FileOutputStream fileOutputStream = new FileOutputStream("codigo.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(codigo);
            objectOutputStream.flush();
            objectOutputStream.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try{
        String logFile = "C:/Users/comp15/Desktop/asd/codigo.txt";

            FileWriter fileWriter = new FileWriter(logFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(codigo);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e){
        
        }
        
    }
}
