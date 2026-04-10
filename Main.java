package zoologico;

import java.util.Scanner;
import java.io.PrintStream;

/**
 *
 * @author GRUPO 4
 */
// SISTEMA DE GESTIÓN - ZOOLÓGICO LA AURORA 
public class Main {

    // Scanner
    private static final Scanner sc = new Scanner(System.in, "UTF-8");

    /*public static void main(String[] args) {
        Zoologico zoo = new Zoologico();
        mostrarBienvenida();
        menuPrincipal(zoo);
        sc.close();
    }*/
    public static void main(String[] args) {
        // 👇 Agrega esta línea aquí, al inicio
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            System.setIn(new java.io.BufferedInputStream(System.in));
        } catch (Exception e) {
        }

        Zoologico zoo = new Zoologico();
        mostrarBienvenida();
        menuPrincipal(zoo);
        sc.close();
    }

    // Menú
    private static void menuPrincipal(Zoologico zoo) {
        boolean salir = false;

        while (!salir) {
            titulo("-- Menú principal --");
            System.out.println();
            System.out.println("1. Zoo");
            System.out.println("2. Fase II");
            System.out.println("3. Fase III");
            System.out.println("4. Salir");
            System.out.println();
            String opcion = leerTexto("Seleccione una opción: ");

            switch (opcion) {
                case "1":
                    menuZoo(zoo);
                    break;
                case "2":
                    menuFase2(zoo);
                    break;
                case "3":
                    menuFase3(zoo);
                    break;
                case "4":
                    mostrarDespedida();
                    salir = true;
                    break;
                default:
                    System.out.println("Seleccione del 1 al 4.");
                    pausa();
            }
        }
    }

    // Opción 1.Zoo
    private static void menuZoo(Zoologico zoo) {
        boolean volver = false;

        while (!volver) {
            titulo("-- Gestión de animales --");
            System.out.println();
            System.out.println("1. Agregar nuevo animal");
            System.out.println("2. Ver todos los animales del Zoo");
            System.out.println("3. Exportar datos a CSV");
            System.out.println("4. Volver al menú principal");
            System.out.println();
            String opcion = leerTexto("Seleccione una opción: ");

            switch (opcion) {
                case "1":
                    agregarAnimal(zoo);
                    break;
                case "2":
                    verAnimales(zoo);
                    break;
                case "3":
                    exportarCSV(zoo);
                    break;
                case "4":
                    volver = true;
                    break;
                default:
                    System.out.println("Seleccione del 1 al 4.");
                    pausa();
            }
        }
    }

// Agregar animal
    private static void agregarAnimal(Zoologico zoo) {
        titulo("-- Agregar nuevo animal --");

        System.out.println("Tipos disponibles: ");
        System.out.println();
        System.out.println("1. Mamífero");
        System.out.println("2. Ave");
        System.out.println("3. Reptil");
        System.out.println();
        String tipo = leerTexto("Seleccione el tipo de animal: ");

        try {
            switch (tipo) {
                case "1":
                    registrarMamifero(zoo);
                    break;
                case "2":
                    registrarAve(zoo);
                    break;
                case "3":
                    registrarReptil(zoo);
                    break;
                default:
                    System.out.println("Tipo animal inválido.");
                    pausa();
                    return;
            }

            // Mostrar cálculo recursivo del último animal registrado
            if (zoo.getCantidadAnimales() > 0) {
                Animal ultimo = zoo.getUltimoAnimal();
                System.out.println();
                System.out.println("-- Consumo de alimento --");
                int dias = leerEntero("Para cuantos dias calcular el consumo (1-365): ", 1, 365);
                double total = ultimo.calcularAlimento(dias);
                System.out.printf("Animal  : %s (%s)%n", ultimo.getNombre(), ultimo.getTipoAnimal());
                System.out.printf("Consumo : %.2f lb/dia%n", ultimo.getConsumoDiario());
                System.out.printf("Dias    : %d%n", dias);
                System.out.printf("Total   : %.2f lb en %d dia(s)%n", total, dias);
            }

        } catch (Exception e) {
            System.out.println("Error al registrar el animal: " + e.getMessage());
        }

        pausa();
    }

    // Listar animales
    private static void verAnimales(Zoologico zoo) {
        titulo("-- Listado de animales --");

        if (zoo.getAnimales().isEmpty()) {
            System.out.println("No hay animales registrados.");
        } else {
            int i = 1;
            for (Animal animal : zoo.getAnimales()) {
                System.out.println("\n  ── Animal #" + i + " ──────────────────────────────────────");
                System.out.println(animal);
                i++;
            }
        }
        pausa();
    }

    // Exportar CSV
    private static void exportarCSV(Zoologico zoo) {
        titulo("-- Exportar datos a CSV --");

        if (zoo.getAnimales().isEmpty()) {
            System.out.println("Primero registre un animal.");
            pausa();
            return;
        }

        try {
            String ruta = zoo.exportarCSV();
            System.out.println("Datos exportados exitosamente.");
            System.out.println("Archivo: " + ruta);
        } catch (Exception e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
        pausa();
    }

    // Registro de tipo de animal
    private static void registrarMamifero(Zoologico zoo) {
        subtitulo("-- Registro de mamifero --");
        String nombre = leerTexto("Nombre: ");
        String especie = leerTexto("Tipo de mamifero: ");
        int edad = leerEntero("Edad (años): ", 0, 150);
        double peso = leerDouble("Peso (lb): ", 0.1);
        double consumo = leerDouble("Consumo diario (lb/día): ", 0.01);
        String pelaje = leerTexto("Tipo de pelaje: ");
        String tamaño = leerTexto("Tamaño: ");
        String fuerza = leerTexto("Nivel de fuerza: ");
        String cazador = leerTexto("Cazador (S/N): ");

        Mamifero m = new Mamifero(nombre, especie, edad, peso, consumo, pelaje, tamaño, fuerza, cazador);
        zoo.agregarAnimal(m);
        System.out.println("\n Mamifero '" + nombre + "' registrado exitosamente.");
    }

    private static void registrarAve(Zoologico zoo) {
        subtitulo("-- Registro de ave --");
        String nombre = leerTexto("Nombre: ");
        String especie = leerTexto("Tipo de ave: ");
        int edad = leerEntero("Edad (años): ", 0, 100);
        double peso = leerDouble("Peso (lb): ", 0.01);
        double consumo = leerDouble("Consumo diario (lb/dia): ", 0.01);
        String pico = leerTexto("Tipo de pico: ");
        boolean vista = leerBooleano("Vista excelente? (S/N): ");
        String plumas = leerTexto("Tipo de plumaje: ");

        Ave ave = new Ave(nombre, especie, edad, peso, consumo, pico, vista, plumas);
        zoo.agregarAnimal(ave);
        System.out.println("\n Ave '" + nombre + "' registrada exitosamente.");
    }

    private static void registrarReptil(Zoologico zoo) {
        subtitulo("-- Registro de reptil --");
        String nombre = leerTexto("Nombre: ");
        String especie = leerTexto("Tipo de reptil: ");
        int edad = leerEntero("Edad (años): ", 0, 200);
        double peso = leerDouble("Peso (lb): ", 0.01);
        double consumo = leerDouble("Consumo diario (lb/dia): ", 0.01);
        boolean venenoso = leerBooleano("Es venenoso? (S/N): ");
        double longitud = leerDouble("Longitud (cm): ", 1.0);
        boolean mandibulaPoderosa = leerBooleano("¿Tiene una mandibula poderosa? (S/N): ");
        boolean excelenteNadador = leerBooleano("¿Es un buen nadador? (S/N): ");

        Reptil r = new Reptil(nombre, especie, edad, peso, consumo, venenoso,
                longitud, mandibulaPoderosa, excelenteNadador);
        zoo.agregarAnimal(r);
        System.out.println("\n Reptil '" + nombre + "' registrado exitosamente.");
    }

    // Opcion 2
    private static void menuFase2(Zoologico zoo) {
        titulo("-- Alimentacion de Animales --");

        if (zoo.getAnimales().isEmpty()) {
            System.out.println("No hay animales registrados.");
            pausa();
            return;
        }

        System.out.println("Animales en el zoologico:\n");
        int i = 1;
        for (Animal a : zoo.getAnimales()) {
            System.out.printf("%d. %-20s (%s) — Dieta: %s%n",
                    i++, a.getNombre(), a.getTipoAnimal(), a.getTipoDieta());
        }

        System.out.println("\n Dieta\n");

        for (Animal a : zoo.getAnimales()) {
            int dias = leerEntero("\n  Dias para " + a.getNombre()
                    + " (" + a.getTipoAnimal() + "): ", 1, 365);
            double total = a.calcularAlimento(dias);
            System.out.printf("%-20s necesita %8.2f lb en %d dia(s).%n",
                    a.getNombre(), total, dias);
            System.out.printf("Dieta: %s | Habitat: %s | Sonido: %s%n",
                    a.getTipoDieta(), a.getHabitat(), a.getSonido());
        }

        pausa();
    }

    // Opcion 3
    private static void menuFase3(Zoologico zoo) {
        titulo("Estadisticas");

        if (zoo.getAnimales().isEmpty()) {
            System.out.println("No hay animales registrados.");
            pausa();
            return;
        }

        // Estadisticas
        int total = zoo.getCantidadAnimales();
        double sumaConsumo = 0;
        Animal mayorConsumidor = zoo.getAnimales().get(0);
        Animal menorConsumidor = zoo.getAnimales().get(0);

        for (Animal a : zoo.getAnimales()) {
            sumaConsumo += a.getConsumoDiario();
            if (a.getConsumoDiario() > mayorConsumidor.getConsumoDiario()) {
                mayorConsumidor = a;
            }
            if (a.getConsumoDiario() < menorConsumidor.getConsumoDiario()) {
                menorConsumidor = a;
            }
        }

        double promedio = sumaConsumo / total;

        System.out.printf("  %-35s: %d%n", "Total de animales", total);
        System.out.printf("  %-35s: %.2f lb/dia%n", "Consumo total diario", sumaConsumo);
        System.out.printf("  %-35s: %.2f lb/dia%n", "Consumo promedio diario", promedio);
        System.out.printf("  %-35s: %s (%.2f lb/dia)%n",
                "Mayor consumidor", mayorConsumidor.getNombre(), mayorConsumidor.getConsumoDiario());
        System.out.printf("  %-35s: %s (%.2f lb/dia)%n",
                "Menor consumidor", menorConsumidor.getNombre(), menorConsumidor.getConsumoDiario());

        // Recursividad
        System.out.println("\n Consumo\n");

        for (Animal a : zoo.getAnimales()) {
            double semanal = a.calcularAlimento(7);
            double mensual = a.calcularAlimento(30);
            System.out.printf("  %-22s | 7 dias: %7.2f lb | 30 dias: %8.2f lb%n",
                    a.getNombre(), semanal, mensual);
        }

        // Distribución por tipo
        System.out.println("\n Distribucion:\n");

        int mamiferos = 0, aves = 0, reptiles = 0;
        for (Animal a : zoo.getAnimales()) {
            if (a instanceof Mamifero) {
                mamiferos++;
            } else if (a instanceof Ave) {
                aves++;
            } else if (a instanceof Reptil) {
                reptiles++;
            }
        }

        imprimirBarra("Mamifero", mamiferos);
        imprimirBarra("Ave", aves);
        imprimirBarra("Reptil", reptiles);

        pausa();
    }

    private static void mostrarBienvenida() {
        System.out.println();
        System.out.println("-- ZOOLOGICO LA AURORA --");
        System.out.println();
    }

    private static void mostrarDespedida() {
        System.out.println();
        System.out.println("Nos vemos pronto.");
        System.out.println();
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("  " + texto);
    }

    private static void subtitulo(String texto) {
        System.out.println("  " + texto);
    }

    private static void pausa() {
        System.out.print("\n Presiona enter para continuar.");
        sc.nextLine();
    }

    private static void imprimirBarra(String tipo, int cantidad) {
        StringBuilder barra = new StringBuilder();
        for (int i = 0; i < cantidad * 10; i++) {
            barra.append("█");
        }
        System.out.printf("  %-12s: %s (%d)%n", tipo, barra.toString(), cantidad);
    }

    // Manejo de excepciones
    private static String leerTexto(String prompt) {
        String valor;
        do {
            System.out.print(prompt);
            valor = sc.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("El campo no puede estar vacio.");
            }
        } while (valor.isEmpty());
        return valor;
    }

    private static int leerEntero(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.printf("Ingrese un numero entre %d y %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Ingrese un numero entero.");
            }
        }
    }

    private static double leerDouble(String prompt, double min) {
        while (true) {
            try {
                System.out.print(prompt);
                double valor = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                if (valor >= min) {
                    return valor;
                }
                System.out.printf("Ingrese un numero mayor o igual a %.2f.%n", min);
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Ingrese un numero decimal.");
            }
        }
    }

    private static boolean leerBooleano(String prompt) {
        while (true) {
            System.out.print(prompt);
            String resp = sc.nextLine().trim().toUpperCase();
            if (resp.equals("S") || resp.equals("SI") || resp.equals("SÍ")) {
                return true;
            }
            if (resp.equals("N") || resp.equals("NO")) {
                return false;
            }
            System.out.println("Responda con S (sí) o N (no).");
        }
    }
}
