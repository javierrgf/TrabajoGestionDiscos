import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

class Disco {
    private String titulo;
    private String artista;
    private int anio;

    public Disco(String titulo, String artista, int anio) {
        this.titulo = titulo;
        this.artista = artista;
        this.anio = anio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getAnio() {
        return anio;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Artista: " + artista + ", Año: " + anio;
    }
}

public class GestionDiscos {
    private ArrayList<Disco> discos;

    public GestionDiscos() {
        discos = new ArrayList<>();
    }

    public void agregarDisco(String titulo, String artista, int anio) {
        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("El título del disco no puede estar vacío.");
            return;
        }
        if (artista == null || artista.trim().isEmpty()) {
            System.out.println("El artista no puede estar vacío.");
            return;
        }
        if (anio <= 0) {
            System.out.println("El año debe ser un valor positivo.");
            return;
        }
        Disco nuevoDisco = new Disco(titulo, artista, anio);
        discos.add(nuevoDisco);
        System.out.println("Disco agregado: " + nuevoDisco);
    }

    public void mostrarDiscos() {
        if (discos.isEmpty()) {
            System.out.println("La lista de discos está vacía.");
        } else {
            System.out.println("Lista de discos:");
            int contador = 1;
            for (Disco disco : discos) {
                System.out.println(contador + ". " + disco);
                contador++;
            }
        }
    }

    public void eliminarDisco(String titulo) {
        boolean encontrado = false;
        Iterator<Disco> iterator = discos.iterator();
        while (iterator.hasNext()) {
            Disco disco = iterator.next();
            if (disco.getTitulo().equalsIgnoreCase(titulo)) {
                iterator.remove();
                System.out.println("Disco eliminado: " + disco);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Disco no encontrado.");
        }
    }

    public static void main(String[] args) {
        GestionDiscos gestionDiscos = new GestionDiscos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar disco");
            System.out.println("2. Mostrar discos");
            System.out.println("3. Eliminar disco");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = -1;

            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opción inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Consumir el salto de línea
                continue;
            }

            scanner.nextLine(); // Consumir el salto de línea después del número

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del disco: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese el artista del disco: ");
                    String artista = scanner.nextLine();
                    System.out.print("Ingrese el año del disco: ");
                    int anio = -1;
                    try {
                        anio = scanner.nextInt();
                        gestionDiscos.agregarDisco(titulo, artista, anio);
                    } catch (InputMismatchException e) {
                        System.out.println("Año inválido. Debe ser un número entero.");
                        scanner.nextLine(); // Consumir el salto de línea
                    }
                    break;
                case 2:
                    gestionDiscos.mostrarDiscos();
                    break;
                case 3:
                    System.out.print("Ingrese el título del disco a eliminar: ");
                    String tituloEliminar = scanner.nextLine();
                    gestionDiscos.eliminarDisco(tituloEliminar);
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
