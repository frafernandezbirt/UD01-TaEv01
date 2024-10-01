import java.io.*;
public class Ejercicio04 {
   public static void main(String[] args) throws IOException {
      /*Longitud del registro = 132, repartidos como sigue:
            int (ID): 4 bytes (Los enteros ocupan 4 bytes)
            DNI: 20 bytes (redondeo a 10 caracteres * 2 bytes = 20 bytes)
            Nombre: 40 bytes (20 caracteres * 2 bytes = 40 bytes)
            Identidad secreta: 40 bytes (20 caracteres * 2 bytes = 40 bytes)
            Tipo: 20 bytes (10 caracteres * 2 bytes = 20 bytes)
            int (peso): 4 bytes
            int (altura): 4 bytes
      */  
      final int long_registro = 132;     
      File fichero = new File("."+ File.separator + "Marvel.dat");
      // Si el fichero existe se borra para crear uno nuevo desde cero.
      if(fichero.exists()){
         fichero.delete();
      }
      //declara el fichero de acceso aleatorio
      RandomAccessFile file = new RandomAccessFile(fichero, "rw");
      //arrays con los datos
      int [] ids= {1, 2, 3, 4, 5, 6, 7};
      String[] dnis= {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
      String[] noms= {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
      String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe","James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
      String[] tipos = {"heroe","villano","heroe","heroe","villano","heroe","villano"};
      int[] pesos = {76,84,66,136,78,102,70};
      int[] alturas = {178,183,156,152,177,182,188};
      
      /* Preparamos los datos antes de escribirlos en el archivo de acceso aleatorio.Aun no esta disponible el dato. 
      Con StringBuffer podemos manejar y manipular cadenas de texto de manera mas eficiente que String, que es inmutable.
      */
      StringBuffer bufferDni = null;
      StringBuffer bufferNom = null;
      StringBuffer bufferIdentidad = null;
      StringBuffer bufferTipo = null;
      int cuantos = dnis.length;
      int posicion;
   
      // Escribir los registros de los personajes en el archivo
      for (int i = 0; i < cuantos; i++) {
         posicion = i * long_registro;
         file.seek(posicion);
      
         // Escribir el ID del personaje
         file.writeInt(ids[i]);
      
         // Escribir el DNI del personaje
         bufferDni = new StringBuffer(dnis[i]);
         // Ajustamos la longitud del DNI a 10 caracteres
         bufferDni.setLength(10);
         file.writeChars(bufferDni.toString());
      
         // Escribir el nombre del personaje
         bufferNom = new StringBuffer(noms[i]);
         // Ajustamos la longitud del nombre a 20 caracteres
         bufferNom.setLength(20);
         file.writeChars(bufferNom.toString());
      
         // Escribir la identidad secreta
         bufferIdentidad = new StringBuffer(identidades[i]);
         // Ajustamos la longitud de la identidad a 20 caracteres
         bufferIdentidad.setLength(20);
         file.writeChars(bufferIdentidad.toString());
      
         // Escribir el tipo (héroe o villano)
         bufferTipo = new StringBuffer(tipos[i]);
         // Ajustamos la longitud del tipo a 10 caracteres
         bufferTipo.setLength(10);
         file.writeChars(bufferTipo.toString());
      
         // Escribir el peso del personaje
         file.writeInt(pesos[i]);
      
         // Escribir la altura del personaje
         file.writeInt(alturas[i]);
      }     
        
      file.close();
      System.out.println("La carga de los personajes ha terminado correctamente.");
   }
}