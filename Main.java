package zoologico;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * SISTEMA DE GESTIÓN - ZOOLÓGICO LA AURORA Fase 2: Arreglos y Búsqueda
 *
 * @author GRUPO 4
 */
public class Main{

    private static final Scanner sc = new Scanner(System.in, "UTF-8");

    public static void main(String[] args){
        try{
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        }catch(Exception e){
        }

        Zoologico zoo = new Zoologico();
        mostrarBienvenida();
        menuPrincipal(zoo);
        sc.close();
    }

// Menu principal
    private static void menuPrincipal(Zoologico zoo) {
        boolean salir = false;
        while(!salir){
            titulo("-- Menú principal --");
            System.out.println();
            System.out.println("1. Zoo");
            System.out.println("2. Fase 2 – Arreglos y Búsqueda");
            System.out.println("3. Fase III");
            System.out.println("4. Salir");
            System.out.println();
            String opcion = leerTexto("Seleccione una opción: ");

            switch(opcion){
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

    //  OPCIÓN 1
    private static void menuZoo(Zoologico zoo){
        boolean volver = false;
        while(!volver){
            titulo("-- Gestión de animales --");
            System.out.println();
            System.out.println("1. Agregar nuevo animal");
            System.out.println("2. Ver todos los animales del Zoo");
            System.out.println("3. Exportar datos a CSV");
            System.out.println("4. Volver al menú principal");
            System.out.println();
            String opcion = leerTexto("Seleccione una opción: ");

            switch(opcion){
                case "1":
                    agregarAnimalSimple(zoo);
                    break;
                case "2":
                    verAnimalesCompleto(zoo);
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

    //  OPCIÓN 2
    private static void menuFase2(Zoologico zoo){
        boolean volver = false;
        while(!volver){
            titulo("-- Fase 2: Arreglos y Búsqueda --");
            System.out.println();
            System.out.println("a. Agregar Mamífero");
            System.out.println("b. Agregar Ave");
            System.out.println("c. Agregar Reptil");
            System.out.println("d. Buscar animal por identificador");
            System.out.println("e. Buscar animal por nombre");
            System.out.println("f. Ordenar arreglo por identificador");
            System.out.println("g. Mostrar todos los animales");
            System.out.println("h. Mostrar estadísticas");
            System.out.println("i. Regresar al menú principal");
            System.out.println();
            String opcion = leerTexto("Seleccione una opción: ").toLowerCase();

            switch(opcion){
                case "a":
                    fase2AgregarMamifero(zoo);
                    break;
                case "b":
                    fase2AgregarAve(zoo);
                    break;
                case "c":
                    fase2AgregarReptil(zoo);
                    break;
                case "d":
                    fase2BuscarPorId(zoo);
                    break;
                case "e":
                    fase2BuscarPorNombre(zoo);
                    break;
                case "f":
                    fase2Ordenar(zoo);
                    break;
                case "g":
                    fase2MostrarTodos(zoo);
                    break;
                case "h":
                    fase2Estadisticas(zoo);
                    break;
                case "i":
                    volver = true;
                    break;
                default:
                    System.out.println("Seleccione una opción válida (a-i).");
                    pausa();
            }
        }
    }

    // gregar Mamífero
    private static void fase2AgregarMamifero(Zoologico zoo){
        titulo("-- Agregar Mamífero --");
        if(zoo.estaLleno()){
            System.out.println("El Zoo ya está lleno");
            pausa();
            return;
        }
        try{
            long id = leerIdUnico(zoo);
            String nombre = leerTexto("Nombre: ");
            String especie = leerTexto("Tipo de mamifero: ");
            int años = leerEntero("Edad (años): ", 0, 150);
            double peso = leerDouble("Peso (lb): ", 0.1);
            double consumo = leerDouble("Consumo diario (lb/día): ", 0.01);
            String pelaje = leerTexto("Tipo de pelaje (Liso/Lanoso/Corto): ");
            String tamaño = leerTexto("Tamaño (Grande/Mediano/Pequeño): ");
            String fuerza = leerTexto("Nivel de fuerza (Mucha/Media/Poca): ");
            String cazador = leerTexto("Es cazador? (S/N): ");

            Mamifero m = new Mamifero(id, nombre, especie, años, peso, consumo,
                    pelaje, tamaño, fuerza, cazador);
            zoo.agregarAnimal(m);
            System.out.println("\n Mamífero '" + nombre + "' registrado exitosamente.");
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        pausa();
    }

    // gregar Ave
    private static void fase2AgregarAve(Zoologico zoo){
        titulo("-- Agregar Ave --");
        if(zoo.estaLleno()){
            System.out.println("El Zoo ya está lleno");
            pausa();
            return;
        }
        try{
            long id = leerIdUnico(zoo);
            String nombre = leerTexto("Nombre: ");
            String especie = leerTexto("Tipo de ave: ");
            int años = leerEntero("Edad (años): ", 0, 100);
            double peso = leerDouble("Peso (lb): ", 0.01);
            double consumo = leerDouble("Consumo diario (lb/día): ", 0.01);
            String pico = leerTexto("Tipo de pico (Curvo/Ganchudo): ");
            boolean vista = leerBooleano("Vista excelente? (S/N): ");
            String plumas = leerTexto("Tipo de plumaje (Cola/Vuelo/Semipluma): ");

            Ave ave = new Ave(id, nombre, especie, años, peso, consumo,
                    pico, vista, plumas);
            zoo.agregarAnimal(ave);
            System.out.println("\n Ave '" + nombre + "' registrada exitosamente.");
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        pausa();
    }

    // agregar Reptil
    private static void fase2AgregarReptil(Zoologico zoo){
        titulo("-- Agregar Reptil --");
        if(zoo.estaLleno()){
            System.out.println("El Zoo ya está lleno");
            pausa();
            return;
        }
        try{
            long id = leerIdUnico(zoo);
            String nombre = leerTexto("Nombre: ");
            String especie = leerTexto("Tipo de reptil: ");
            int años = leerEntero("Edad (años): ", 0, 200);
            double peso = leerDouble("Peso (lb): ", 0.01);
            double consumo = leerDouble("Consumo diario (lb/día): ", 0.01);
            boolean venenoso = leerBooleano("Es venenoso? (S/N): ");
            double longitud = leerDouble("Longitud (cm): ", 1.0);
            boolean mandibula = leerBooleano("Tiene mandíbula poderosa? (S/N): ");
            boolean nadador = leerBooleano("Es excelente nadador? (S/N): ");

            Reptil r = new Reptil(id, nombre, especie, años, peso, consumo,
                    venenoso, longitud, mandibula, nadador);
            zoo.agregarAnimal(r);
            System.out.println("\n Reptil '" + nombre + "' registrado exitosamente.");
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        pausa();
    }

    // uscar por ID
    private static void fase2BuscarPorId(Zoologico zoo){
        titulo("-- Buscar animal por identificador --");
        if(zoo.getCantidad() == 0){
            System.out.println("No hay animales registrados.");
            pausa();
            return;
        }
        try{
            long id = leerLong("Ingrese el ID a buscar: ");
            int pos = zoo.buscarPorId(id);
            if(pos == -1){
                System.out.println("No se encontró ningún animal con ID " + id + ".");
            }else{
                System.out.println("\n Animal encontrado en la posición " + pos + " del arreglo:\n");
                System.out.println(zoo.getAnimal(pos));
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        pausa();
    }

    // Buscar por nombre
    private static void fase2BuscarPorNombre(Zoologico zoo){
        titulo("-- Buscar animal por nombre --");
        if(zoo.getCantidad() == 0){
            System.out.println("No hay animales registrados.");
            pausa();
            return;
        }
        String nombre = leerTexto("Ingrese el nombre a buscar: ");
        int[] indices = zoo.buscarPorNombre(nombre);
        if (indices.length == 0) {
            System.out.println("No se encontró ningún animal con nombre '" + nombre + "'.");
        }else{
            System.out.println("\n Se encontraron " + indices.length + " animal(es):\n");
            for(int idx : indices){
                System.out.println("  ── Posición " + idx + " ─────────────────────────────────────");
                System.out.println(zoo.getAnimal(idx));
                System.out.println();
            }
        }
        pausa();
    }

    // denar arreglo
    private static void fase2Ordenar(Zoologico zoo){
        titulo("-- Ordenar arreglo por identificador --");
        if (zoo.getCantidad() == 0) {
            System.out.println("No hay animales registrados.");
            pausa();
            return;
        }
        System.out.println("1. Ascendente (menor a mayor)");
        System.out.println("2. Descendente (mayor a menor)");
        System.out.println();
        String op = leerTexto("Seleccione: ");
        boolean asc;
        if(op.equals("1")){
            asc = true;
        }else if(op.equals("2")){
            asc = false;
        }else{
            System.out.println("Opción inválida.");
            pausa();
            return;
        }

        zoo.ordenarPorId(asc);
        System.out.println("\n Arreglo ordenado " + (asc ? "ascendente" : "descendente") + ":\n");
        imprimirTablaResumen(zoo);
        pausa();
    }

    // Mostrar todos
    private static void fase2MostrarTodos(Zoologico zoo){
        titulo("-- Todos los animales registrados --");
        if(zoo.getCantidad() == 0){
            System.out.println("No hay animales registrados.");
            pausa();
            return;
        }
        imprimirTablaResumen(zoo);
        pausa();
    }

    // ── h. Estadísticas ───────────────────────────────────────────────────────
    private static void fase2Estadisticas(Zoologico zoo){
        titulo("-- Estadísticas del zoológico --");
        if(zoo.getCantidad() == 0){
            System.out.println("No hay animales registrados.");
            pausa();
            return;
        }

        Animal mayor = zoo.getMayorConsumidor();
        Animal menor = zoo.getMenorConsumidor();

        System.out.println();
        System.out.printf("  %-38s: %d%n", "Total de animales registrados", zoo.getCantidad());
        System.out.printf("  %-38s: %d%n", "Cantidad de mamíferos", zoo.contarMamiferos());
        System.out.printf("  %-38s: %d%n", "Cantidad de aves", zoo.contarAves());
        System.out.printf("  %-38s: %d%n", "Cantidad de reptiles", zoo.contarReptiles());
        System.out.printf("  %-38s: %s (%.2f lb/día)%n",
                "Animal con mayor consumo de alimento",
                mayor.getNombre(), mayor.getConsumoDiario());
        System.out.printf("  %-38s: %s (%.2f lb/día)%n",
                "Animal con menor consumo de alimento",
                menor.getNombre(), menor.getConsumoDiario());
        System.out.printf("  %-38s: %.2f años%n",
                "Promedio de edad de los animales", zoo.getPromedioEdad());

        // Distribución visual
        System.out.println("\n  Distribución por tipo:\n");
        imprimirBarra("Mamifero", zoo.contarMamiferos());
        imprimirBarra("Ave", zoo.contarAves());
        imprimirBarra("Reptil", zoo.contarReptiles());

        pausa();
    }

    //  OPCIÓN 3
    private static void menuFase3(Zoologico zoo){
        titulo("Estadísticas");
        if(zoo.getCantidad() == 0){
            System.out.println("No hay animales registrados.");
            pausa();
            return;
        }

        int total = zoo.getCantidad();
        double sumaConsumo = 0;
        Animal mayorConsumidor = zoo.getAnimal(0);
        Animal menorConsumidor = zoo.getAnimal(0);

        for(int i = 0; i < total; i++){
            Animal a = zoo.getAnimal(i);
            sumaConsumo += a.getConsumoDiario();
            if(a.getConsumoDiario() > mayorConsumidor.getConsumoDiario()){
                mayorConsumidor = a;
            }
            if(a.getConsumoDiario() < menorConsumidor.getConsumoDiario()){
                menorConsumidor = a;
            }
        }

        System.out.printf("  %-35s: %d%n", "Total de animales", total);
        System.out.printf("  %-35s: %.2f lb/dia%n", "Consumo total diario", sumaConsumo);
        System.out.printf("  %-35s: %.2f lb/dia%n", "Consumo promedio diario", sumaConsumo / total);
        System.out.printf("  %-35s: %s (%.2f lb/dia)%n",
                "Mayor consumidor", mayorConsumidor.getNombre(), mayorConsumidor.getConsumoDiario());
        System.out.printf("  %-35s: %s (%.2f lb/dia)%n",
                "Menor consumidor", menorConsumidor.getNombre(), menorConsumidor.getConsumoDiario());

        System.out.println("\n -- Consumo de alimento --\n");
        for (int i = 0; i < total; i++) {
            Animal a = zoo.getAnimal(i);
            System.out.printf("  %-22s | 7 dias: %7.2f lb | 30 dias: %8.2f lb%n",
                    a.getNombre(), a.calcularAlimento(7), a.calcularAlimento(30));
        }

        System.out.println("\n Distribución:\n");
        imprimirBarra("Mamifero", zoo.contarMamiferos());
        imprimirBarra("Ave", zoo.contarAves());
        imprimirBarra("Reptil", zoo.contarReptiles());

        pausa();
    }

    //  HELPERS
    private static void agregarAnimalSimple(Zoologico zoo){
        titulo("-- Agregar nuevo animal --");
        boolean hayM = zoo.tieneMamifero();
        boolean hayA = zoo.tieneAve();
        boolean hayR = zoo.tieneReptil();

        if(hayM && hayA && hayR){
            System.out.println("Ya se registró un animal de cada tipo.");
            pausa();
            return;
        }
        if(!hayM){
            System.out.println("1. Mamífero");
        }
        if(!hayA){
            System.out.println("2. Ave");
        }
        if(!hayR){
            System.out.println("3. Reptil");
        }
        System.out.println();
        String tipo = leerTexto("Seleccione el tipo: ");

        try{
            switch(tipo){
                case "1":
                    if(hayM){
                        System.out.println("Ya existe un mamífero.");
                    }else{
                        registrarMamiferoSimple(zoo);
                    }
                    break;
                case "2":
                    if(hayA){
                        System.out.println("Ya existe un ave.");
                    }else{
                        registrarAveSimple(zoo);
                    }
                    break;
                case "3":
                    if(hayR){
                        System.out.println("Ya existe un reptil.");
                    } else {
                        registrarReptilSimple(zoo);
                    }
                    break;
                default:
                    System.out.println("Tipo inválido.");
                    pausa();
                    return;
            }
            if(zoo.getCantidad() > 0){
                Animal ultimo = zoo.getUltimoAnimal();
                System.out.println("\n-- Cálculo de alimento --\n");
                int dias = leerEntero("¿Para cuántos días calcular el consumo? (1-365): ", 1, 365);
                double total = ultimo.calcularAlimento(dias);
                System.out.printf("Animal  : %s (%s)%n", ultimo.getNombre(), ultimo.getTipoAnimal());
                System.out.printf("Consumo : %.2f lb/día%n", ultimo.getConsumoDiario());
                System.out.printf("Días    : %d%n", dias);
                System.out.printf("Total   : %.2f lb en %d día(s)%n", total, dias);
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        pausa();
    }

    // registrar con id automático
    private static long generarId(Zoologico zoo){
        long id = zoo.getCantidad() + 1L;
        while (zoo.existeId(id)){
            id++;
        }
        return id;
    }

    private static void registrarMamiferoSimple(Zoologico zoo){
        subtitulo("-- Registro de mamifero --");
        long id = generarId(zoo);
        String nombre = leerTexto("Nombre: ");
        String especie = leerTexto("Tipo de mamifero: ");
        int años = leerEntero("Edad (años): ", 0, 150);
        double peso = leerDouble("Peso (lb): ", 0.1);
        double consumo = leerDouble("Consumo diario (lb/día): ", 0.01);
        String pelaje = leerTexto("Tipo de pelaje: ");
        String tamaño = leerTexto("Tamaño: ");
        String fuerza = leerTexto("Nivel de fuerza: ");
        String cazador = leerTexto("Cazador (S/N): ");
        zoo.agregarAnimal(new Mamifero(id, nombre, especie, años, peso, consumo, pelaje, tamaño, fuerza, cazador));
        System.out.println("\n Mamífero '" + nombre + "' registrado.");
    }

    private static void registrarAveSimple(Zoologico zoo){
        subtitulo("-- Registro de ave --");
        long id = generarId(zoo);
        String nombre = leerTexto("Nombre: ");
        String especie = leerTexto("Tipo de ave: ");
        int años = leerEntero("Edad (años): ", 0, 100);
        double peso = leerDouble("Peso (lb): ", 0.01);
        double consumo = leerDouble("Consumo diario (lb/día): ", 0.01);
        String pico = leerTexto("Tipo de pico: ");
        boolean vista = leerBooleano("¿Vista excelente? (S/N): ");
        String plumas = leerTexto("Tipo de plumaje: ");
        zoo.agregarAnimal(new Ave(id, nombre, especie, años, peso, consumo, pico, vista, plumas));
        System.out.println("\n Ave '" + nombre + "' registrada.");
    }

    private static void registrarReptilSimple(Zoologico zoo){
        subtitulo("-- Registro de reptil --");
        long id = generarId(zoo);
        String nombre = leerTexto("Nombre: ");
        String especie = leerTexto("Tipo de reptil: ");
        int años = leerEntero("Edad (años): ", 0, 200);
        double peso = leerDouble("Peso (lb): ", 0.01);
        double consumo = leerDouble("Consumo diario (lb/día): ", 0.01);
        boolean venenoso = leerBooleano("¿Es venenoso? (S/N): ");
        double longitud = leerDouble("Longitud (cm): ", 1.0);
        boolean mandibula = leerBooleano("¿Mandíbula poderosa? (S/N): ");
        boolean nadador = leerBooleano("¿Excelente nadador? (S/N): ");
        zoo.agregarAnimal(new Reptil(id, nombre, especie, años, peso, consumo, venenoso, longitud, mandibula, nadador));
        System.out.println("\n Reptil '" + nombre + "' registrado.");
    }

    private static void verAnimalesCompleto(Zoologico zoo){
        titulo("-- Listado de animales --");
        System.out.println();
        if(zoo.getCantidad() == 0){
            System.out.println("No hay animales registrados.");
        }else{
            for(int i = 0; i < zoo.getCantidad(); i++){
                System.out.println("\n  ── Animal #" + (i + 1) + " ──────────────────────────────────────");
                System.out.println(zoo.getAnimal(i));
            }
        }
        pausa();
    }

    private static void exportarCSV(Zoologico zoo){
        titulo("-- Exportar datos a CSV --");
        if(zoo.getCantidad() == 0){
            System.out.println("Primero registre un animal.");
            pausa();
            return;
        }
        try{
            String ruta = zoo.exportarCSV();
            System.out.println("Datos exportados exitosamente.");
            System.out.println("Archivo: " + ruta);
        }catch(Exception e){
            System.out.println("Error al exportar: " + e.getMessage());
        }
        pausa();
    }

    // Tablas CSV
    private static void imprimirTablaResumen(Zoologico zoo){
        System.out.println();
        System.out.printf("  %-6s | %-18s | %-5s | %-10s | %s%n",
                "ID", "Nombre", "Años", "Tipo", "Dato específico");
        System.out.println("  " + "-".repeat(72));
        for(int i = 0; i < zoo.getCantidad(); i++){
            Animal a = zoo.getAnimal(i);
            System.out.printf("  %-6d | %-18s | %-5d | %-10s | %s%n",
                    a.getIdAnimal(), a.getNombre(), a.getAños(),
                    a.getTipoAnimal(), a.getDatoEspecifico());
        }
        System.out.println();
    }

    // Barra de distribucion de alimentos
    private static void imprimirBarra(String tipo, int cantidad){
        StringBuilder barra = new StringBuilder();
        for(int i = 0; i < cantidad * 10; i++){
            //barra.append("█");
        }
        System.out.printf("  %-12s: %s %n", tipo, cantidad + " %");
    }

    private static void mostrarBienvenida(){
        System.out.println();
        System.out.println("-- ZOOLOGICO LA AURORA --");
        System.out.println();
    }

    private static void mostrarDespedida(){
        System.out.println();
        System.out.println("Nos vemos pronto!!!.");
        System.out.println();
    }

    private static void titulo(String texto){
        System.out.println();
        System.out.println("  " + texto);
    }

    private static void subtitulo(String texto){
        System.out.println("  " + texto);
    }

    private static void pausa(){
        System.out.print("\n Presiona enter para continuar.");
        sc.nextLine();
    }

    // VALIDACION DE DATOS
    private static String leerTexto(String prompt){
        String valor;
        do{
            System.out.print(prompt);
            valor = sc.nextLine().trim();
            if(valor.isEmpty()){
                System.out.println("El campo no puede estar vacío.");
            }
        }while(valor.isEmpty());
        return valor;
    }

    private static int leerEntero(String prompt, int min, int max) {
        while(true){
            try{
                System.out.print(prompt);
                int valor = Integer.parseInt(sc.nextLine().trim());
                if(valor >= min && valor <= max){
                    return valor;
                }
                System.out.printf("Ingrese un número entre %d y %d.%n", min, max);
            }catch(NumberFormatException e){
                System.out.println("Valor inválido. Ingrese un número entero.");
            }
        }
    }

    private static long leerLong(String prompt){
        while(true){
            try{
                System.out.print(prompt);
                return Long.parseLong(sc.nextLine().trim());
            }catch(NumberFormatException e){
                System.out.println("Valor inválido. Ingrese un número entero.");
            }
        }
    }

    // ID DUPLICADO
    private static long leerIdUnico(Zoologico zoo){
        while(true){
            long id = leerLong("ID del animal (número positivo): ");
            if(id <= 0){
                System.out.println("El ID debe ser mayor que 0.");
                continue;
            }
            if(zoo.existeId(id)){
                System.out.println("El identificador es único para cada animal");
                continue;
            }
            return id;
        }
    }

    private static double leerDouble(String prompt, double min){
        while(true){
            try{
                System.out.print(prompt);
                double valor = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                if(valor >= min){
                    return valor;
                }
                System.out.printf("Ingrese un número mayor o igual a %.2f.%n", min);
            }catch(NumberFormatException e){
                System.out.println("Valor inválido. Ingrese un número decimal.");
            }
        }
    }

    private static boolean leerBooleano(String prompt){
        while(true){
            System.out.print(prompt);
            String resp = sc.nextLine().trim().toUpperCase();
            if(resp.equals("S") || resp.equals("SI") || resp.equals("SÍ")){
                return true;
            }
            if(resp.equals("N") || resp.equals("NO")){
                return false;
            }
            System.out.println("Responda con S (sí) o N (no).");
        }
    }
}
