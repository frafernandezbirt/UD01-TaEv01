import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Este programa utiliza la clase FileInputStream para leer los
 * metadatos de un fichero PDF.
 */
public class Ejercicio03 {
 
   public static void main(String[] args) {
      // Crear un objeto Scanner para leer la entrada del usuario
      Scanner scanner = new Scanner(System.in);
   
      // Pedir al usuario que ingrese el nombre del archivo
      System.out.print("Introduce el nombre del archivo (con extensión): ");
      String nombreArchivo = scanner.nextLine();
   
      // Crear un objeto File con el nombre proporcionado
      File archivo = new File(nombreArchivo);
      File inputFile = new File ("."+ File.separator + nombreArchivo);
   
      //cabecera de PDF en decimal
      int[] pdfSignature = {37, 80, 68, 70}; 
   
      try (
            InputStream inputStream = new FileInputStream(inputFile);
        ) {
         int[] pngHeader = new int[4];
      
         // lee los 8 primeros enteros decimales como cabecera de PDF
         for (int i = 0; i < 4; i++) {
            pngHeader[i] = inputStream.read();
         }
      
         if (!Arrays.equals(pngHeader, pdfSignature)) {
            System.out.println("No es un fichero PDF");
            System.exit(-1);
         }
      
         // Confirmamos que es un archivo pdf
         System.out.println("Es un archivo PDF.");
      
      
      } catch (IOException ex) {
         ex.printStackTrace();
      }
   }
}