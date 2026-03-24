package zoologico;

/**
 * Clase Ave — hereda de Animal.
 * Aplica herencia y polimorfismo sobreescribiendo los métodos abstractos.
 */
public class Ave extends Animal {

    // ── Atributos específicos del ave ─────────────────────────────
    private double  envergadura;   // envergadura de alas en cm
    private boolean puedeVolar;

    // ── Constructor ───────────────────────────────────────────────
    public Ave(String nombre, String especie, int edad, double peso,
               double consumoDiario, double envergadura, boolean puedeVolar) {
        super(nombre, especie, edad, peso, consumoDiario);
        this.envergadura = envergadura;
        this.puedeVolar  = puedeVolar;
    }

    // ── Getters ───────────────────────────────────────────────────
    public double  getEnvergadura() { return envergadura; }
    public boolean isPuedeVolar()   { return puedeVolar; }

    // ── Polimorfismo: implementación de métodos abstractos ────────
    @Override
    public String getTipoAnimal() { return "Ave"; }

    @Override
    public String getTipoDieta()  { return "Granívora / Insectívora"; }

    @Override
    public String getSonido()     { return "Canto / Chillido"; }

    @Override
    public String getHabitat()    { return "Árbol / Cielo abierto"; }

    // ── toString extendido ────────────────────────────────────────
    @Override
    public String toString() {
        return super.toString() + String.format(
            "\n  %-22s: %.2f cm\n  %-22s: %s",
            "Envergadura",  envergadura,
            "Puede volar",  puedeVolar ? "Sí" : "No"
        );
    }

    // ── CSV extendido ─────────────────────────────────────────────
    @Override
    public String toCSVRow() {
        return super.toCSVRow() + String.format(",,,%.2f,%s,,",
            envergadura, puedeVolar ? "Sí" : "No");
    }
}
