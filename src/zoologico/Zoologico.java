package zoologico;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Clase Zoologico — gestiona la colección de animales.
 * Maneja el registro, listado y persistencia en CSV.
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

    // ── Exportar a CSV ────────────────────────────────────────────
    /**
     * Guarda todos los animales en un archivo CSV de texto secuencial.
     *
     * @return Ruta absoluta del archivo generado.
     * @throws IOException si hay error al escribir el archivo.
     */
    public String exportarCSV() throws IOException {
        String fechaHora = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE))) {
            // Metadatos
            pw.println("# ZOOLÓGICO LA AURORA - Reporte de Animales");
            pw.println("# Fecha de exportación: " + fechaHora);
            pw.println("# Total de animales: " + animales.size());
            pw.println();
            // Encabezados
            pw.println("Tipo,Nombre,Especie,Edad (años),Peso (lb)," +
                       "Dieta,Hábitat,Consumo Diario (lb)," +
                       "Pelaje,Nocturno," +
                       "Envergadura (cm),Puede Volar," +
                       "Venenoso,Longitud (cm)");
            // Filas de datos
            for (Animal a : animales) {
                pw.println(a.toCSVRow());
            }
        }

        return new java.io.File(CSV_FILE).getAbsolutePath();
    }
}
