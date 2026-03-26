package zoologico;

/**
 *
 * @author willi
 */
public class Mamifero extends Animal {

    // Atributos específicos del mamífero
    private String  pelaje;
    //private boolean esNocturno;
    private String tamaño;
    private String fuerza;
    private String cazador;

    // Constructor
    public Mamifero(String nombre, String especie, int edad, double peso,
                    double consumoDiario, String pelaje, String tamaño, 
                    String fuerza, String cazador) {
        super(nombre, especie, edad, peso, consumoDiario);
        this.pelaje = pelaje;
        this.tamaño = tamaño;
        this.fuerza = fuerza;
        this.cazador = cazador;
    }

    // Getters
    public String  getPelaje(){return pelaje;}
    public String getTamaño(){return tamaño;}
    public String getFuerza(){return fuerza;}
    public String getCazador(){return cazador;}

    // Polimorfismo
    @Override
    public String getTipoAnimal(){return "Mamífero";}

    @Override
    public String getTipoDieta(){return "Carnívora / Omnívora";}

    @Override
    public String getSonido(){ return "Rugido / Gruñido";}

    @Override
    public String getHabitat(){ return "Sabana / Bosque / Selva";}

    // toString extendido
    @Override
    public String toString() {
        return super.toString() + String.format(
            "\n  %-22s: %s\n  %-22s: %s",
            "Pelaje", pelaje,
            "Tamaño", tamaño,
            "Fuerza", fuerza,
            "Cazador", cazador
        );
    }

    // CSV extendido
    @Override
    public String toCSVRow() {
        return super.toCSVRow() + String.format(",%s,%s,,,,",
            pelaje, tamaño, fuerza, cazador);
    }
} 