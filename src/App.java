import java.util.Random;
import java.util.Scanner;

public class App {

    static Scanner scanner = new Scanner(System.in);
    static String[] planetas = { "Mercurio", "Venus", "Marte", "Jupiter", "Saturno", "Urano", "Neptuno" };
    static double[] distancias = { 91, 41, 78, 628, 1275, 2724, 4351 };// Distancia en millones de km
    static String[] naves = { "Voyager", "Mars Perseverance Rover", "Cassini-Huygens" };
    static double[] velocidades = { 61000.0, 39600.0, 124000.0 }; // Velocidad en km/h
    static double[] consumoCombustible = { 8000, 300, 3400 }; // consumo de combustible en toneladas por cada 480
                                                              // millones de km.
    static int naveSeleccionada; // apunta a la posición de la nave en listado, más no en nombre.
    static int planetaSeleccionado; // Indica la posición del planeta seleccionado en planetas

    public static void main(String[] args) throws Exception {
        int option;
        boolean option1selec = false;
        boolean option2selec = false;
        do {
            mostrarMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    seleccionarPlaneta();
                    option1selec = true;
                    break;
                case 2:
                    seleccionarNaveEspacial();
                    option2selec = true;
                    break;
                case 3:
                    if ((option1selec && option2selec) == true ) {
                        iniciarSimulacion();
                    } else {
                        System.out.println("Debe seleccionar primero las opciones 1 y 2 antes de iniciar la simulación.");
                    } 
                    break;
                case 4:
                    salir();
                    break;
                default:
                    break;
            }

        } while (option != 4);
    }

    private static void mostrarMenu() {
        System.out.println("__________________________________");
        System.out.println("----------Menu Principal----------");
        System.out.println("1. Planeta de destino.");
        System.out.println("2. Nave espacial.");
        System.out.println("3. Iniciar simulación de viaje.");
        System.out.println("4. Salir.");
        System.out.println("----------------------------------");
        System.out.println("Por favor elija una opción.");
    }

    private static void seleccionarPlaneta() {
        System.out.println("__________________________________");
        System.out.println("1. Planeta de destino.");
        System.out.println();
        for (int i = 0; i < planetas.length; i++) {
            System.out.println("Planeta " + (i + 1) + ": " + planetas[i] + " a una distancia de "
                    + distancias[i] + " millones de Kms.");
        }
        System.out.println();
        System.out.print("Seleccione un planeta: ");
        var seleccion = scanner.nextInt();
        if (seleccion > 0 && seleccion <= planetas.length) {
            System.out.println("El planeta seleccionado es: " + planetas[seleccion - 1]);
            planetaSeleccionado = seleccion - 1;
        } else {
            System.out.println("La opción seleccionada es invalida. Intente de nuevo.");
        }
    }

    private static void seleccionarNaveEspacial() {
        System.out.println("__________________________________");
        System.out.println("1. Nave espacial.");
        System.out.println();
        for (int i = 0; i < naves.length; i++) {
            System.out.println("Nave espacial " + (i + 1) + ": " + naves[i] + " que tiene una velocidad promedio de "
                    + velocidades[i] + " Km/h.");
        }
        System.out.println();
        System.out.print("Seleccione una nave espacial: ");
        var seleccion = scanner.nextInt();
        if (seleccion > 0 && seleccion <= naves.length) {
            System.out.println("La nave espacial seleccionada es: " + naves[seleccion - 1]);
            naveSeleccionada = seleccion - 1;
        } else {
            System.out.println("La opción seleccionada es invalida. Intente de nuevo.");
        }
    }

    private static void iniciarSimulacion() {
        System.out.println("_________________________________");
        System.out.println("---------Iniciando viaje---------");
        System.out.println();
        calcularRecursos();
        System.out.println();
        Random random = new Random();
        for (int progreso = 0; progreso <= 100; progreso += 10) {
            System.out.println("Viajando: " + progreso + " %");
            if (progreso == 0) {
                System.out.println("Iniciando aventura...");
            }
            if (progreso == 90) {
                System.out.println("Ya estamos llegando a tu destino...");
            }
            eventoInesperado(random);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error en la simulación del viaje.");
            }
        }
        System.out.println("Llegaste a tu destino.");
    }

    private static void eventoInesperado(Random random) {
        if (random.nextInt(10)<1) {
            int option;
            do{
                System.out.println("¡Evento inesperado!");
            System.out.println("¿Desea revisar el evento?");
            System.out.println("1. Si.");
            System.out.println("2. No.");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Encuentro con cinturones de asteroides más densos de lo esperado.");
                    System.out.println("Interviniendo ...");
                    System.out.println("Se implementaron escudos de protección para evitar daños causados por micrometeoritos y fragmentos de asteroides.");
                    return;
                case 2:
                    System.out.println("Revisar aeronave al terminar el viaje por posibles daños externos.");
                    break;
                default:
                    break;
            }
        } while (option !=2);

        }
    }

    private static void salir() {
        System.out.println("Gracias por participar en esta aventura. ¡Vuelve pronto!");
    }

    public static void calcularRecursos() {
        double combustible = (consumoCombustible[naveSeleccionada] * distancias[planetaSeleccionado]) / 480;
        double oxigeno = distancias[planetaSeleccionado] * 225; // unidades de oxigeno en ppm por cada millon de km/
                                                                // persona, se necesita 225 unidades de Oxigeno.
        System.out.println("El combustible necesario para el viaje es: " + combustible + " toneladas.");
        System.out.println("La cantidad de oxigeno necesario es: " + oxigeno + " ppm.");
    }
}
