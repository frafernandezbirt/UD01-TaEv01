import java.io.*;

public class Ejercicio01 {
   public static void main(String[] args) throws IOException {
      // Archivo que queremos leer
      String rutaArchivo = "." + File.separator + "TextoHolaMundo.txt";
    
      try {
         // Usamos un BufferedReader para leer el archivo de texto
         BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
         String linea;
         
         // Leer l�nea por l�nea
         while ((linea = lector.readLine()) != null) {
            //System.out.println(linea);  // Mostrar la l�nea le�da
            String lineaInvertida = new StringBuilder(linea).reverse().toString();
            // Mostrar la l�nea invertida
            System.out.println(lineaInvertida);
         }
      
         // Cerrar el lector
         lector.close();
      
      } catch (IOException excepcion) {
         // Manejo de errores
         System.out.println("Ocurri� un error al leer el archivo: " + excepcion.getMessage());
      }
   }
}
