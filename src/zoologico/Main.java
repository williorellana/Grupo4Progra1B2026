package zoologico;

import java.util.Scanner;

/**
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║           SISTEMA DE GESTIÓN - ZOOLÓGICO LA AURORA              ║
 * ║        Programación Orientada a Objetos — Java / NetBeans        ║
 * ║   Herencia · Polimorfismo · Recursividad · Persistencia CSV      ║
 * ╚══════════════════════════════════════════════════════════════════╝
 *
 * Clase principal con el menú interactivo en consola.
 */
public class Main {

    // ── Scanner compartido ────────────────────────────────────────
    private static final Scanner sc = new Scanner(System.in);

    // ══════════════════════════════════════════════════════════════
    //  PUNTO DE ENTRADA
    // ══════════════════════════════════════════════════════════════
    public static void main(String[] args) {
        Zoologico zoo = new Zoologico();
        mostrarBienvenida();
        menuPrincipal(zoo);
        sc.close();
    }

    // ══════════════════════════════════════════════════════════════
    //  MENÚ PRINCIPAL
    // ══════════════════════════════════════════════════════════════
    private static void menuPrincipal(Zoologico zoo) {
        boolean salir = false;

        while (!salir) {
            titulo("MENÚ PRINCIPAL");
            System.out.println("  1. Zoo");
            System.out.println("  2. Fase II  –  Alimentación");
            System.out.println("  3. Fase III –  Estadísticas");
            System.out.println("  4. Salir");
            separador();

            String opcion = leerTexto("  Seleccione una opción: ");

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
                    System.out.println("  ⚠  Opción inválida. Seleccione entre 1 y 4.");
                    pausa();
            }
        }
    }

    // ══════════════════════════════════════════════════════════════
    //  OPCIÓN 1 — ZOO
    // ══════════════════════════════════════════════════════════════
    private static void menuZoo(Zoologico zoo) {
        boolean volver = false;

        while (!volver) {
            titulo("ZOOLÓGICO LA AURORA  –  Gestión de Animales");
            System.out.println("  1. Agregar nuevo animal");
            System.out.println("  2. Ver todos los animales del Zoo");
            System.out.println("  3. Exportar datos a CSV");
            System.out.println("  0. Volver al menú principal");
            separador();

            String opcion = leerTexto("  Seleccione una opción: ");

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
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println("  ⚠  Opción inválida. Intente nuevamente.");
                    pausa();
            }
        }
    }

    // ── Agregar animal ─────────────────────────────────────────────
    private static void agregarAnimal(Zoologico zoo) {
        titulo("AGREGAR NUEVO ANIMAL");

        // Determinar tipos disponibles (solo uno de cada tipo)
        boolean hayMamifero = zoo.tieneMamifero();
        boolean hayAve      = zoo.tieneAve();
        boolean hayReptil   = zoo.tieneReptil();

        if (hayMamifero && hayAve && hayReptil) {
            System.out.println("  ℹ  Ya se registró un animal de cada tipo.");
            System.out.println("     (Mamífero, Ave y Reptil ya están registrados)");
            pausa();
            return;
        }

        System.out.println("  Tipos disponibles:");
        if (!hayMamifero) System.out.println("    1. Mamífero");
        if (!hayAve)      System.out.println("    2. Ave");
        if (!hayReptil)   System.out.println("    3. Reptil");
        separador();

        String tipo = leerTexto("  Seleccione el tipo de animal: ");

        try {
            switch (tipo) {
                case "1":
                    if (hayMamifero) {
                        System.out.println("  ⚠  Ya existe un mamífero registrado.");
                    } else {
                        registrarMamifero(zoo);
                    }
                    break;
                case "2":
                    if (hayAve) {
                        System.out.println("  ⚠  Ya existe un ave registrada.");
                    } else {
                        registrarAve(zoo);
                    }
                    break;
                case "3":
                    if (hayReptil) {
                        System.out.println("  ⚠  Ya existe un reptil registrado.");
                    } else {
                        registrarReptil(zoo);
                    }
                    break;
                default:
                    System.out.println("  ⚠  Tipo inválido.");
                    pausa();
                    return;
            }

            // Mostrar cálculo recursivo del último animal registrado
            if (zoo.getCantidadAnimales() > 0) {
                Animal ultimo = zoo.getUltimoAnimal();
                System.out.println();
                separador();
                System.out.println("  📊 CÁLCULO DE CONSUMO DE ALIMENTO (Recursividad)");
                separador();
                int dias = leerEntero("  ¿Para cuántos días calcular el consumo? (1-365): ", 1, 365);
                double total = ultimo.calcularAlimento(dias);
                System.out.printf("  Animal  : %s (%s)%n", ultimo.getNombre(), ultimo.getTipoAnimal());
                System.out.printf("  Consumo : %.2f lb/día%n", ultimo.getConsumoDiario());
                System.out.printf("  Días    : %d%n", dias);
                System.out.printf("  Total   : %.2f lb en %d día(s)%n", total, dias);
            }

        } catch (Exception e) {
            System.out.println("  ✘  Error al registrar el animal: " + e.getMessage());
        }

        pausa();
    }

    // ── Ver animales ───────────────────────────────────────────────
    private static void verAnimales(Zoologico zoo) {
        titulo("LISTADO DE ANIMALES DEL ZOOLÓGICO");

        if (zoo.getAnimales().isEmpty()) {
            System.out.println("  ℹ  No hay animales registrados aún.");
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

    // ── Exportar CSV ───────────────────────────────────────────────
    private static void exportarCSV(Zoologico zoo) {
        titulo("EXPORTAR DATOS A CSV");

        if (zoo.getAnimales().isEmpty()) {
            System.out.println("  ⚠  No hay animales para exportar. Registre al menos uno.");
            pausa();
            return;
        }

        try {
            String ruta = zoo.exportarCSV();
            System.out.println("  ✔  Datos exportados exitosamente.");
            System.out.println("  📄 Archivo: " + ruta);
        } catch (Exception e) {
            System.out.println("  ✘  Error al exportar: " + e.getMessage());
        }
        pausa();
    }

    // ══════════════════════════════════════════════════════════════
    //  REGISTRO DE CADA TIPO DE ANIMAL
    // ══════════════════════════════════════════════════════════════
    private static void registrarMamifero(Zoologico zoo) {
        subtitulo("REGISTRO DE MAMÍFERO");
        String nombre      = leerTexto("  Nombre               : ");
        String especie     = leerTexto("  Especie               : ");
        int    edad        = leerEntero("  Edad (años)           : ", 0, 150);
        double peso        = leerDouble("  Peso (lb)              : ", 0.1);
        double consumo     = leerDouble("  Consumo diario (lb/día): ", 0.01);
        String pelaje      = leerTexto("  Tipo de pelaje        : ");
        boolean nocturno   = leerBooleano("  ¿Es nocturno? (S/N)   : ");

        Mamifero m = new Mamifero(nombre, especie, edad, peso, consumo, pelaje, nocturno);
        zoo.agregarAnimal(m);
        System.out.println("\n  ✔  Mamífero '" + nombre + "' registrado exitosamente.");
    }

    private static void registrarAve(Zoologico zoo) {
        subtitulo("REGISTRO DE AVE");
        String nombre        = leerTexto("  Nombre                  : ");
        String especie       = leerTexto("  Especie                  : ");
        int    edad          = leerEntero("  Edad (años)              : ", 0, 100);
        double peso          = leerDouble("  Peso (lb)                 : ", 0.01);
        double consumo       = leerDouble("  Consumo diario (lb/día)   : ", 0.01);
        double envergadura   = leerDouble("  Envergadura de alas (cm)  : ", 1.0);
        boolean volar        = leerBooleano("  ¿Puede volar? (S/N)       : ");

        Ave ave = new Ave(nombre, especie, edad, peso, consumo, envergadura, volar);
        zoo.agregarAnimal(ave);
        System.out.println("\n  ✔  Ave '" + nombre + "' registrada exitosamente.");
    }

    private static void registrarReptil(Zoologico zoo) {
        subtitulo("REGISTRO DE REPTIL");
        String nombre      = leerTexto("  Nombre                : ");
        String especie     = leerTexto("  Especie                : ");
        int    edad        = leerEntero("  Edad (años)            : ", 0, 200);
        double peso        = leerDouble("  Peso (lb)               : ", 0.01);
        double consumo     = leerDouble("  Consumo diario (lb/día) : ", 0.01);
        boolean venenoso   = leerBooleano("  ¿Es venenoso? (S/N)     : ");
        double longitud    = leerDouble("  Longitud (cm)           : ", 1.0);

        Reptil r = new Reptil(nombre, especie, edad, peso, consumo, venenoso, longitud);
        zoo.agregarAnimal(r);
        System.out.println("\n  ✔  Reptil '" + nombre + "' registrado exitosamente.");
    }

    // ══════════════════════════════════════════════════════════════
    //  OPCIÓN 2 — FASE II: ALIMENTACIÓN
    // ══════════════════════════════════════════════════════════════
    private static void menuFase2(Zoologico zoo) {
        titulo("FASE II  –  Alimentación de Animales");

        if (zoo.getAnimales().isEmpty()) {
            System.out.println("  ⚠  No hay animales registrados. Vaya al menú Zoo primero.");
            pausa();
            return;
        }

        System.out.println("  Animales en el zoológico:\n");
        int i = 1;
        for (Animal a : zoo.getAnimales()) {
            System.out.printf("    %d. %-20s (%s) — Dieta: %s%n",
                i++, a.getNombre(), a.getTipoAnimal(), a.getTipoDieta());
        }

        separador();
        System.out.println("\n  🍖 PROCESO DE ALIMENTACIÓN\n");
        separador();

        for (Animal a : zoo.getAnimales()) {
            int dias    = leerEntero("\n  Días para " + a.getNombre() +
                                     " (" + a.getTipoAnimal() + "): ", 1, 365);
            double total = a.calcularAlimento(dias);
            System.out.printf("  ✔  %-20s necesita %8.2f lb en %d día(s).%n",
                a.getNombre(), total, dias);
            System.out.printf("     Dieta: %s | Hábitat: %s | Sonido: %s%n",
                a.getTipoDieta(), a.getHabitat(), a.getSonido());
        }

        pausa();
    }

    // ══════════════════════════════════════════════════════════════
    //  OPCIÓN 3 — FASE III: ESTADÍSTICAS
    // ══════════════════════════════════════════════════════════════
    private static void menuFase3(Zoologico zoo) {
        titulo("FASE III  –  Estadísticas del Zoológico");

        if (zoo.getAnimales().isEmpty()) {
            System.out.println("  ⚠  No hay animales registrados.");
            pausa();
            return;
        }

        // ── Estadísticas generales ────────────────────────────────
        int    total         = zoo.getCantidadAnimales();
        double sumaConsumo   = 0;
        Animal mayorConsumidor = zoo.getAnimales().get(0);
        Animal menorConsumidor = zoo.getAnimales().get(0);

        for (Animal a : zoo.getAnimales()) {
            sumaConsumo += a.getConsumoDiario();
            if (a.getConsumoDiario() > mayorConsumidor.getConsumoDiario())
                mayorConsumidor = a;
            if (a.getConsumoDiario() < menorConsumidor.getConsumoDiario())
                menorConsumidor = a;
        }

        double promedio = sumaConsumo / total;

        System.out.printf("  %-35s: %d%n",    "Total de animales",       total);
        System.out.printf("  %-35s: %.2f lb/día%n", "Consumo total diario",    sumaConsumo);
        System.out.printf("  %-35s: %.2f lb/día%n", "Consumo promedio diario", promedio);
        System.out.printf("  %-35s: %s (%.2f lb/día)%n",
            "Mayor consumidor", mayorConsumidor.getNombre(), mayorConsumidor.getConsumoDiario());
        System.out.printf("  %-35s: %s (%.2f lb/día)%n",
            "Menor consumidor", menorConsumidor.getNombre(), menorConsumidor.getConsumoDiario());

        // ── Proyección semanal / mensual (recursividad) ───────────
        separador();
        System.out.println("\n  📅 PROYECCIÓN DE CONSUMO (Recursividad aplicada)\n");

        for (Animal a : zoo.getAnimales()) {
            double semanal = a.calcularAlimento(7);
            double mensual = a.calcularAlimento(30);
            System.out.printf("  %-22s | 7 días: %7.2f lb | 30 días: %8.2f lb%n",
                a.getNombre(), semanal, mensual);
        }

        // ── Distribución por tipo ─────────────────────────────────
        separador();
        System.out.println("\n  🐾 DISTRIBUCIÓN POR TIPO:\n");

        int mamiferos = 0, aves = 0, reptiles = 0;
        for (Animal a : zoo.getAnimales()) {
            if (a instanceof Mamifero) mamiferos++;
            else if (a instanceof Ave) aves++;
            else if (a instanceof Reptil) reptiles++;
        }

        imprimirBarra("Mamífero", mamiferos);
        imprimirBarra("Ave",      aves);
        imprimirBarra("Reptil",   reptiles);

        pausa();
    }

    // ══════════════════════════════════════════════════════════════
    //  PANTALLAS DECORATIVAS
    // ══════════════════════════════════════════════════════════════
    private static void mostrarBienvenida() {
        System.out.println();
        separadorDoble();
        System.out.println("  ╔══════════════════════════════════════════════════════╗");
        System.out.println("  ║         ZOOLÓGICO LA AURORA                         ║");
        System.out.println("  ║      Sistema de Gestión de Animales                 ║");
        System.out.println("  ║   Java · POO · Herencia · Polimorfismo              ║");
        System.out.println("  ╚══════════════════════════════════════════════════════╝");
        separadorDoble();
        System.out.println();
    }

    private static void mostrarDespedida() {
        System.out.println();
        separadorDoble();
        System.out.println("  ╔══════════════════════════════════════════════════════╗");
        System.out.println("  ║   ¡Gracias por usar el Sistema del Zoológico!       ║");
        System.out.println("  ║   La Aurora los despide con cariño y esperamos      ║");
        System.out.println("  ║   verle pronto entre nuestros animales.              ║");
        System.out.println("  ╚══════════════════════════════════════════════════════╝");
        separadorDoble();
        System.out.println();
    }

    private static void titulo(String texto) {
        System.out.println();
        separadorDoble();
        System.out.println("  " + texto);
        separadorDoble();
    }

    private static void subtitulo(String texto) {
        separador();
        System.out.println("  " + texto);
        separador();
    }

    private static void separador() {
        System.out.println("  " + "─".repeat(58));
    }

    private static void separadorDoble() {
        System.out.println("  " + "═".repeat(58));
    }

    private static void pausa() {
        System.out.print("\n  Presione ENTER para continuar...");
        sc.nextLine();
    }

    private static void imprimirBarra(String tipo, int cantidad) {
        StringBuilder barra = new StringBuilder();
        for (int i = 0; i < cantidad * 10; i++) barra.append("█");
        System.out.printf("  %-12s: %s (%d)%n", tipo, barra.toString(), cantidad);
    }

    // ══════════════════════════════════════════════════════════════
    //  MÉTODOS DE ENTRADA CON VALIDACIÓN Y MANEJO DE EXCEPCIONES
    // ══════════════════════════════════════════════════════════════
    private static String leerTexto(String prompt) {
        String valor;
        do {
            System.out.print(prompt);
            valor = sc.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("  ⚠  El campo no puede estar vacío.");
            }
        } while (valor.isEmpty());
        return valor;
    }

    private static int leerEntero(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor >= min && valor <= max) return valor;
                System.out.printf("  ⚠  Ingrese un número entre %d y %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("  ⚠  Valor inválido. Ingrese un número entero.");
            }
        }
    }

    private static double leerDouble(String prompt, double min) {
        while (true) {
            try {
                System.out.print(prompt);
                double valor = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                if (valor >= min) return valor;
                System.out.printf("  ⚠  Ingrese un número mayor o igual a %.2f.%n", min);
            } catch (NumberFormatException e) {
                System.out.println("  ⚠  Valor inválido. Ingrese un número decimal.");
            }
        }
    }

    private static boolean leerBooleano(String prompt) {
        while (true) {
            System.out.print(prompt);
            String resp = sc.nextLine().trim().toUpperCase();
            if (resp.equals("S") || resp.equals("SI") || resp.equals("SÍ")) return true;
            if (resp.equals("N") || resp.equals("NO"))                       return false;
            System.out.println("  ⚠  Responda con S (sí) o N (no).");
        }
    }
}
