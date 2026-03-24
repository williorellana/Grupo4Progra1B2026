package zoologico;

/**
 * Clase Reptil — hereda de Animal.
 * Aplica herencia y polimorfismo sobreescribiendo los métodos abstractos.
 */
public class Reptil extends Animal {

    // ── Atributos específicos del reptil ──────────────────────────
    private boolean esVenenoso;
    private double  longitud;   // longitud en cm

    // ── Constructor ───────────────────────────────────────────────
    public Reptil(String nombre, String especie, int edad, double peso,
                  double consumoDiario, boolean esVenenoso, double longitud) {
        super(nombre, especie, edad, peso, consumoDiario);
        this.esVenenoso = esVenenoso;
        this.longitud   = longitud;
    }

    // ── Getters ───────────────────────────────────────────────────
    public boolean isEsVenenoso() { return esVenenoso; }
    public double  getLongitud()  { return longitud; }

    // ── Polimorfismo: implementación de métodos abstractos ────────
    @Override
    public String getTipoAnimal() { return "Reptil"; }

    @Override
    public String getTipoDieta()  { return "Carnívora"; }

    @Override
    public String getSonido()     { return "Siseo / Silencio"; }

    @Override
    public String getHabitat()    { return "Terrario / Zona húmeda"; }

    // ── toString extendido ────────────────────────────────────────
    @Override
    public String toString() {
        return super.toString() + String.format(
            "\n  %-22s: %s\n  %-22s: %.2f cm",
            "Venenoso",  esVenenoso ? "Sí" : "No",
            "Longitud",  longitud
        );
    }

    // ── CSV extendido ─────────────────────────────────────────────
    @Override
    public String toCSVRow() {
        return super.toCSVRow() + String.format(",,,,,,%s,%.2f",
            esVenenoso ? "Sí" : "No", longitud);
    }
}
