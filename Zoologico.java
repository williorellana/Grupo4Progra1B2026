package zoologico;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
* @author GRUPO4
*/

/*
 * Clase Zoologico — gestiona la colección de animales.
 */
public class Zoologico {

    // ── Atributos ─────────────────────────────────────────────────
    private ArrayList<Animal> animales;
    private static final String CSV_FILE = "zoo_aurora.csv";

    // ── Constructor ───────────────────────────────────────────────
    public Zoologico() {
        this.animales = new ArrayList<>();
    }

    // ── Verificadores de tipo registrado ──────────────────────────
    public boolean tieneMamifero() {
        for (Animal a : animales) {
            if (a instanceof Mamifero) return true;
        }
        return false;
    }

    public boolean tieneAve() {
        for (Animal a : animales) {
            if (a instanceof Ave) return true;
        }
        return false;
    }

    public boolean tieneReptil() {
        for (Animal a : animales) {
            if (a instanceof Reptil) return true;
        }
        return false;
    }

    // ── Operaciones básicas ───────────────────────────────────────
    public void agregarAnimal(Animal animal) {
        animales.add(animal);
    }

    public ArrayList<Animal> getAnimales() {
        return animales;
    }

    public int getCantidadAnimales() {
        return animales.size();
    }

    public Animal getUltimoAnimal() {
        if (animales.isEmpty()) return null;
        return animales.get(animales.size() - 1);
    }

    // Exportar a CSV
    public String exportarCSV() throws IOException {
        String fechaHora = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE))) {
            // Metadatos
            pw.println("# ZOOLOGICO LA AURORA - Reporte de Animales");
            pw.println("# Fecha de exportacion: " + fechaHora);
            pw.println("# Total de animales: " + animales.size());
            // Encabezados
            /*pw.println("Tipo,Nombre,Especie,Edad (años),Peso (lb)," +
                       "Dieta,Hábitat,Consumo Diario (lb)," +
                       "Pelaje,Nocturno," +
                       "Envergadura (cm),Puede Volar," +
                       "Venenoso,Longitud (cm)");*/
            pw.print(animales);
            
            // Filas de datos
        }

        return new java.io.File(CSV_FILE).getAbsolutePath();
    }
}