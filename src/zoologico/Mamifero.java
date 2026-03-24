package zoologico;

/**
 * Clase Mamifero — hereda de Animal.
 * Aplica herencia y polimorfismo sobreescribiendo los métodos abstractos.
 */
public class Mamifero extends Animal {

    // ── Atributos específicos del mamífero ────────────────────────
    private String  pelaje;
    private boolean esNocturno;

    // ── Constructor ───────────────────────────────────────────────
    public Mamifero(String nombre, String especie, int edad, double peso,
                    double consumoDiario, String pelaje, boolean esNocturno) {
        super(nombre, especie, edad, peso, consumoDiario);
        this.pelaje      = pelaje;
        this.esNocturno  = esNocturno;
    }

    // ── Getters ───────────────────────────────────────────────────
    public String  getPelaje()     { return pelaje; }
    public boolean isEsNocturno()  { return esNocturno; }

    // ── Polimorfismo: implementación de métodos abstractos ────────
    @Override
    public String getTipoAnimal() { return "Mamífero"; }

    @Override
    public String getTipoDieta()  { return "Carnívora / Omnívora"; }

    @Override
    public String getSonido()     { return "Rugido / Gruñido"; }

    @Override
    public String getHabitat()    { return "Sabana / Bosque"; }

    // ── toString extendido ────────────────────────────────────────
    @Override
    public String toString() {
        return super.toString() + String.format(
            "\n  %-22s: %s\n  %-22s: %s",
            "Pelaje",    pelaje,
            "Nocturno",  esNocturno ? "Sí" : "No"
        );
    }

    // ── CSV extendido ─────────────────────────────────────────────
    @Override
    public String toCSVRow() {
        return super.toCSVRow() + String.format(",%s,%s,,,,",
            pelaje, esNocturno ? "Sí" : "No");
    }
}
