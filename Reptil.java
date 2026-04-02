package zoologico;

/**
 *
 * @author willi
 */

/**
 * Clase Reptil — hereda de Animal.
 * Aplica herencia y polimorfismo sobreescribiendo los métodos abstractos.
 */
public class Reptil extends Animal {

    // Atributos
    private boolean esVenenoso;
    private double  longitud;   // longitud en m
    private boolean mandibulaPoderosa;
    private boolean excelenteNadador;

    // Constructor
    public Reptil(String nombre, String especie, int edad, double peso,
                  double consumoDiario, boolean esVenenoso, 
                  double longitud, boolean mandibulaPoderosa,
                  boolean excelenteNadador) {
        super(nombre, especie, edad, peso, consumoDiario);
        this.esVenenoso = esVenenoso;
        this.longitud = longitud;
        this.mandibulaPoderosa = mandibulaPoderosa;
        this.excelenteNadador = excelenteNadador;
    }

    // Getters
    public boolean esVenenoso(){return esVenenoso;}
    public double getLongitud(){return longitud;}
    public boolean getMandibula(){return mandibulaPoderosa;}
    public boolean getNadador(){return excelenteNadador;}

    // Polimorfismo
    @Override
    public String getTipoAnimal(){return "Reptil";}

    @Override
    public String getTipoDieta(){return "Carnívora";}

    @Override
    public String getSonido(){return "Gruñido o bramido";}

    @Override
    public String getHabitat(){return "Pantano o río";}

    @Override
    public String toString() {
        return super.toString() + String.format(
            "\n  %-22s: %s\n  %-22s: %.2f cm",
            "Venenoso",  esVenenoso ? "Sí" : "No",
            "Longitud",  longitud,
            "Mandibula poderosa", mandibulaPoderosa ? "Si" : "No",
            "Excelente nadador", excelenteNadador ? "Si" : "No"
        );
    }

    // CSV
    @Override
    public String toCSVRow() {
        return super.toCSVRow() + String.format(",,,,,,%s,%.2f",
            esVenenoso ? "Sí" : "No", longitud,
            mandibulaPoderosa ? "Si" : "No", 
            excelenteNadador ? "Si" : "No");
    }
}
