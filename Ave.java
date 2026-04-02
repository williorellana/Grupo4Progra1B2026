package zoologico;

/**
 *
 * @author willi
 */

/*
 * Clase Ave — hereda de Animal.
 */
public class Ave extends Animal {

    // Atributos
    private String pico;
    private boolean vista;
    private String plumas;

    // Constructor
    public Ave(String nombre, String especie, int edad, double peso,
            double consumoDiario, String pico, boolean vista, String plumas) {
        super(nombre, especie, edad, peso, consumoDiario);
        this.pico = pico;
        this.vista = vista;
        this.plumas = plumas;
    }

    // Getters
    public String pico() {
        return pico;
    }

    public boolean vista() {
        return vista;
    }

    public String plumas() {
        return plumas;
    }

    // Polimorfismo
    @Override
    public String getTipoAnimal() {
        return "Ave";
    }

    @Override
    public String getTipoDieta() {
        return "Granívora / Insectívora";
    }

    @Override
    public String getSonido() {
        return "Canto / Chillido";
    }

    @Override
    public String getHabitat() {
        return "Árbol / Cielo abierto";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                "\n  %-22s: %-22s:  %-22s:",
                "Pico", pico,
                "Vista", vista ? "Si" : "No", 
                "plumas", plumas
        );
    }

    // CSV
    @Override
    public String toCSVRow() {
        return super.toCSVRow() + String.format(",,,,,,,%s,,", 
                pico, 
                vista ? "Si" : "No", 
                plumas);
    }
}
