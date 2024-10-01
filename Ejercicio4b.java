import java.io.*;
import java.util.Scanner;

public class Ejercicio4b {
    public static void main(String[] args) throws IOException {
        File fichero = new File("."+ File.separator + "Marvel.dat");
        // Declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");

        int id, peso, altura, posicion, diferenciaPeso = 0, pesoActual;
        char dni[] = new char[10];
        char nombre[] = new char[20];
        char identidad[] = new char[20];
        char tipo[] = new char[10];
        boolean coincide = false;

        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        
        // Pedir al usuario que ingrese el DNI del personaje
        System.out.println("Introduce el DNI (con letra) del personaje para control de peso:");
        String dniPersonaje = scanner.nextLine().toUpperCase();
         
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

            // Selecciono al personaje en cuestión
            if(dniStr.equals(dniPersonaje)) {
               coincide = true;  // El personaje ha sido coincide
               System.out.println("Introduzca su peso actual:");
               pesoActual = scanner.nextInt();
               if (pesoActual == peso){
                  System.out.println(nombreStr + " se mantiene en su peso anterior.");
               } else if ( pesoActual > peso) {
                  diferenciaPeso = pesoActual - peso;               
                  System.out.println(nombreStr + " ha engordado " + diferenciaPeso + " kilos.");
                  // Llamar al método para actualizar el peso en el archivo
                  actualizarPeso(file, posicion + 124, pesoActual);  // 124 bytes hasta el campo de peso
               } else {
                  diferenciaPeso = peso - pesoActual;
                  System.out.println(nombreStr + " ha adelgazado " + diferenciaPeso + " kilos.");
                  // Llamar al método para actualizar el peso en el archivo
                  actualizarPeso(file, posicion + 124, pesoActual);  // 124 bytes hasta el campo de peso
               }
               break;             
            }

            // Moverse a la siguiente posición (cada personaje ocupa 132 bytes)
            posicion += 132;
        }

        // Verificar si no se encontró el personaje después de recorrer todo el archivo
        if (!coincide) {
            System.out.println("El DNI no corresponde con ningún personaje.");
        }

         // Cerrar el archivo y el scanner.
        file.close(); 
        scanner.close();
    }
    
    // Método para actualizar el peso en el archivo
    private static void actualizarPeso(RandomAccessFile file, int posicionPeso, int nuevoPeso) throws IOException {
        // Moverse a la posición del peso del personaje
        file.seek(posicionPeso);
        // Escribir el nuevo peso (sobrescribe el valor anterior)
        file.writeInt(nuevoPeso);
        System.out.println("El peso ha sido actualizado correctamente en el archivo Marvel.dat.");
    }
}
