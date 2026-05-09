package zoologico;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase Zoologico — Fase 2: usa exclusivamente arreglo de tipo Animal[10].
 *
 * @author GRUPO 4
 */
public class Zoologico{

    // Atributos
    Animal[] animales = new Animal[10];
    private int cantidad;

    // Constructor
    public Zoologico(){
        this.cantidad = 0;
    }

    // Getters
    public int getCantidad(){
        return cantidad;
    }

    public boolean estaLleno(){
        return cantidad >= 10;
    }

    public Animal getAnimal(int indice){
        return animales[indice];
    }

    // Agregar animal
    public void agregarAnimal(Animal animal){
        if(estaLleno()){
            throw new IllegalStateException("El Zoo ya está lleno");
        }
        if(existeId(animal.getIdAnimal())){
            throw new IllegalArgumentException("El identificador es único para cada animal");
        }
        animales[cantidad] = animal;
        cantidad++;
    }

    // Verificar id duplicado
    public boolean existeId(long id){
        for(int i = 0; i < cantidad; i++){
            if(animales[i].getIdAnimal() == id){
                return true;
            }
        }
        return false;
    }

    // Búsqueda por id
    public int buscarPorId(long id){
        for(int i = 0; i < cantidad; i++) {
            if(animales[i].getIdAnimal() == id){
                return i;
            }
        }
        return -1;
    }

    // Búsqueda por nombre
    public int[] buscarPorNombre(String nombre){
        int count = 0;
        for(int i = 0; i < cantidad; i++){
            if(animales[i].getNombre().equalsIgnoreCase(nombre)){
                count++;
            }
        }
        int[] indices = new int[count];
        int j = 0;
        for(int i = 0; i < cantidad; i++){
            if(animales[i].getNombre().equalsIgnoreCase(nombre)){
                indices[j++] = i;
            }
        }
        return indices;
    }

    // Ordenamiento por idAnimal
    public void ordenarPorId(boolean ascendente){
        for(int i = 0; i < cantidad - 1; i++){
            for(int j = 0; j < cantidad - 1 - i; j++){
                boolean intercambiar = ascendente
                        ? animales[j].getIdAnimal() > animales[j + 1].getIdAnimal()
                        : animales[j].getIdAnimal() < animales[j + 1].getIdAnimal();
                if(intercambiar){
                    Animal tmp = animales[j];
                    animales[j] = animales[j + 1];
                    animales[j + 1] = tmp;
                }
            }
        }
    }

    // Estadísticas
    public int contarMamiferos(){
        int c = 0;
        for(int i = 0; i < cantidad; i++){
            if(animales[i] instanceof Mamifero){
                c++;
            }
        }
        return c;
    }

    public int contarAves(){
        int c = 0;
        for(int i = 0; i < cantidad; i++){
            if(animales[i] instanceof Ave){
                c++;
            }
        }
        return c;
    }

    public int contarReptiles(){
        int c = 0;
        for(int i = 0; i < cantidad; i++){
            if(animales[i] instanceof Reptil){
                c++;
            }
        }
        return c;
    }

    public Animal getMayorConsumidor(){
        if(cantidad == 0){
            return null;
        }
        Animal mayor = animales[0];
        for(int i = 1; i < cantidad; i++){
            if(animales[i].getConsumoDiario() > mayor.getConsumoDiario()){
                mayor = animales[i];
            }
        }
        return mayor;
    }

    public Animal getMenorConsumidor(){
        if(cantidad == 0){
            return null;
        }
        Animal menor = animales[0];
        for(int i = 1; i < cantidad; i++){
            if(animales[i].getConsumoDiario() < menor.getConsumoDiario()){
                menor = animales[i];
            }
        }
        return menor;
    }

    public double getPromedioEdad(){
        if(cantidad == 0){
            return 0;
        }
        int suma = 0;
        for(int i = 0; i < cantidad; i++){
            suma += animales[i].getAños();
        }
        return (double) suma / cantidad;
    }

    // opción1 
    public boolean tieneMamifero(){
        return contarMamiferos() > 0;
    }

    public boolean tieneAve(){
        return contarAves() > 0;
    }

    public boolean tieneReptil(){
        return contarReptiles() > 0;
    }

    public Animal getUltimoAnimal(){
        if (cantidad == 0) {
            return null;
        }
        return animales[cantidad - 1];
    }

    // Exportar CSV
    public String exportarCSV() throws IOException{
        String fechaHora = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        try(PrintWriter pw = new PrintWriter(new FileWriter("zoo_aurora.csv"))){
            pw.println("# ZOOLOGICO LA AURORA - Reporte de Animales");
            pw.println("# Fecha de exportacion: " + fechaHora);
            pw.println("# Total de animales: " + cantidad);
            System.out.println();
            pw.println("ID,Tipo,Nombre,Especie,Anos,Peso,Dieta,Habitat,ConsumoDiario,DatoExtra");
            for(int i = 0; i < cantidad; i++){
                pw.println(animales[i].toCSVRow());
            }
        }
        return new java.io.File("zoo_aurora.csv").getAbsolutePath();
    }
}
