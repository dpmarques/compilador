package compilador.arquivo;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;


public class WriteFile {
        public static void escrever(String codigo,String nome_arq) {
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
       // String local = "C:/Users/comp15/Desktop/asd/codigo.txt";

            FileWriter fileWriter = new FileWriter(nome_arq, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(codigo);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException erro){
            JOptionPane.showMessageDialog(null, erro);
        }
        
    }
}
