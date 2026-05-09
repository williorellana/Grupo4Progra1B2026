package zoologico;

/**
 * Subclase Mamifero — hereda de Animal.
 *
 * @author GRUPO 4
 */
public class Mamifero extends Animal {

    // Atributo específico
    private String pelaje;
    private String tamaño;
    private String fuerza;
    private String cazador;

    // Constructor
    public Mamifero(long idAnimal, String nombre, String especie, int años,
            double peso, double consumoDiario,
            String pelaje, String tamaño, String fuerza, String cazador) {
        super(idAnimal, nombre, especie, años, peso, consumoDiario);
        this.pelaje = pelaje;
        this.tamaño = tamaño;
        this.fuerza = fuerza;
        this.cazador = cazador;
    }

    // Getters
    public String getPelaje(){
        return pelaje;
    }

    public String getTamaño(){
        return tamaño;
    }

    public String getFuerza(){
        return fuerza;
    }

    public String getCazador(){
        return cazador;
    }

    // Polimorfismo
    @Override
    public String getTipoAnimal(){
        return "Mamifero"; 
    }

    @Override
    public String getTipoDieta(){
        return "Carnivora / Omnivora";
    }

    @Override
    public String getSonido(){
        return "Rugido / Gruñido";
    }

    @Override
    public String getHabitat(){
        return "Sabana / Bosque / Selva";
    }

    @Override
    public String getDatoEspecifico(){
        return "Pelaje: " + pelaje;
    }

    @Override
    public String toString(){
        return super.toString() + String.format(
                "\n  %-22s: %s\n  %-22s: %s\n  %-22s: %s\n  %-22s: %s",
                "Pelaje", pelaje,
                "Tamaño", tamaño,
                "Fuerza", fuerza,
                "Cazador", cazador
        );
    }

    @Override
    public String toCSVRow(){
        return super.toCSVRow() + "," + pelaje + "," + tamaño + "," + fuerza + "," + cazador;
    }
}
