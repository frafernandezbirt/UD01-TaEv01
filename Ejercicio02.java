import java.io.*;
import java.nio.charset.StandardCharsets;

public class Ejercicio02 {
   public static void main(String[] args) throws IOException {
      // Archivo que queremos leer y escribir.
      String rutaArchivo = "." + File.separator + "Nombres.dat";
      String rutaArchivoSalida = "." + File.separator + "salida.dat";
    
      try {
         // Usamos un BufferedReader para leer el archivo de texto con codificación UTF-8
         BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(rutaArchivo), StandardCharsets.UTF_8));
         // Usamos un BufferedWriter para escribir el archivo de salida
         BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaArchivoSalida), StandardCharsets.UTF_8));
          
         String linea;
         
         // Leer línea por línea
         while ((linea = lector.readLine()) != null) {
            // Dividir la línea en palabras
            String[] partes = linea.split(" ");
            
            // El primer elemento es el nombre
            if (partes.length > 0) {
               String nombre = partes[0];
               
               // Mostrar solo el nombre
               //System.out.println(nombre);
               
               // Verificar si el nombre tiene exactamente 5 letras
               if (nombre.length() == 5) {
                  // Mostrar el nombre que cumple la condición
                  System.out.println(nombre);
                  // Escribir el nombre en el archivo de salida
                  escritor.write(nombre + "\n");
                  // Agregar un salto de línea
                  // escritor.newLine();
               }
            }
         }
      
         // Cerrar el lector y el escritor
         lector.close();
         escritor.close();
      
      } catch (IOException excepcion) {
         // Manejo de errores
         System.out.println("Ocurrió un error al leer el archivo: " + excepcion.getMessage());
      }
   }
}
