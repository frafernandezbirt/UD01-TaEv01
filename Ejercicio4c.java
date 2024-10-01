import java.io.*;
import java.util.Scanner;

public class Ejercicio4c {
    public static void main(String[] args) throws IOException {
        File fichero = new File("."+ File.separator + "Marvel.dat");
        // Declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        int id, peso, altura, posicion, contador = 0;
        char dni[] = new char[10];
        char nombre[] = new char[20];
        char identidad[] = new char[20];
        char tipo[] = new char[10];
        boolean coincide = false;

        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        
        // Pedir al usuario que ingrese el nombre del archivo
        System.out.print("Introduce un tipo de personaje: ");
        String tipoPersonaje = scanner.nextLine().toLowerCase();
         
        // Posición para leer los tipos de personajes.
        posicion = 104;
         
        while (posicion < file.length()) {
            file.seek(posicion); 
            
            for (int i = 0; i < tipo.length; i++) {
                tipo[i] = file.readChar();
            }
            String tipoStr = new String(tipo).trim();
            
            if(tipoStr.equals(tipoPersonaje)) {
               contador ++;
               //coincide = false;
            }
            posicion += 132;
        }
        
        // Si no se encontraron personajes
        if (contador == 0) {
            System.out.println("No existe " + tipoPersonaje + "s en el fichero.");
        } else {
            System.out.println("Se han encontrado " + contador + " " + tipoPersonaje + "s en el fichero.");
        }

        posicion = 0; // para situarnos al principio


        while (file.getFilePointer() != file.length()) {
            file.seek(posicion); // Nos posicionamos en la posición correspondiente

            // Leer ID (int, 4 bytes)
            id = file.readInt();

            // Leer DNI (10 caracteres, 20 bytes)
            for (int i = 0; i < dni.length; i++) {
                dni[i] = file.readChar();
            }
            String dniStr = new String(dni).trim();

            // Leer nombre (20 caracteres, 40 bytes)
            for (int i = 0; i < nombre.length; i++) {
                nombre[i] = file.readChar();
            }
            String nombreStr = new String(nombre).trim();

            // Leer identidad secreta (20 caracteres, 40 bytes)
            for (int i = 0; i < identidad.length; i++) {
                identidad[i] = file.readChar();
            }
            String identidadStr = new String(identidad).trim();

            // Leer tipo (10 caracteres, 20 bytes)
            for (int i = 0; i < tipo.length; i++) {
                tipo[i] = file.readChar();
            }
            String tipoStr = new String(tipo).trim();

            // Leer peso (int, 4 bytes)
            peso = file.readInt();

            // Leer altura (int, 4 bytes)
            altura = file.readInt();

            // Imprimir la información solo si el ID es mayor a 0
            if(tipoStr.equals(tipoPersonaje)) {
                System.out.printf("Personaje [dni: %s, nombre:%-10s, identidad:%-20s, tipo:%-10s, peso:%d, altura:%d]%n",
                        dniStr, nombreStr, identidadStr, tipoStr, peso, altura);
            }

            // Moverse a la siguiente posición (cada personaje ocupa 132 bytes)
            posicion += 132;
        }

         // Cerrar el archivo y el scanner.
        file.close(); 
        scanner.close();
    }
}
