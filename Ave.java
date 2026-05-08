package zoologico;

/**
 * Subclase Ave — hereda de Animal.
 *
 * @author GRUPO 4
 */
public class Ave extends Animal {

    // Atributos específicos
    private String pico;
    private boolean vista;
    private String plumas;

    // Constructor
    public Ave(long idAnimal, String nombre, String especie, int años,
            double peso, double consumoDiario,
            String pico, boolean vista, String plumas){
        super(idAnimal, nombre, especie, años, peso, consumoDiario);
        this.pico = pico;
        this.vista = vista;
        this.plumas = plumas;
    }

    // Getters
    public String getPico(){
        return pico;
    }

    public boolean getVista(){
        return vista;
    }

    public String getPlumas(){
        return plumas;
    }

    // Polimorfismo
    @Override
    public String getTipoAnimal(){
        return "Ave";
    }

    @Override
    public String getTipoDieta(){
        return "Granivora / Insectivora";
    }

    @Override
    public String getSonido(){
        return "Canto / Chillido";
    }

    @Override
    public String getHabitat(){
        return "Arbol / Cielo abierto";
    }

    @Override
    public String getDatoEspecifico(){
        return "Pico: " + pico;
    }

    @Override
    public String toString(){
        return super.toString() + String.format(
                "\n  %-22s: %s\n  %-22s: %s\n  %-22s: %s",
                "Pico", pico,
                "Vista", vista ? "Si" : "No",
                "Plumas", plumas
        );
    }

    @Override
    public String toCSVRow(){
        return super.toCSVRow() + "," + pico + "," + (vista ? "Si" : "No") + "," + plumas;
    }
}