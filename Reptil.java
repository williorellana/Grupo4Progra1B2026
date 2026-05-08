package zoologico;

/**
 * Subclase Reptil — hereda de Animal.
 *
 * @author GRUPO 4
 */
public class Reptil extends Animal{

    // Atributos específicos
    private boolean esVenenoso;
    private double longitud;
    private boolean mandibulaPoderosa;
    private boolean excelenteNadador;

    // Constructor
    public Reptil(long idAnimal, String nombre, String especie, int años,
            double peso, double consumoDiario,
            boolean esVenenoso, double longitud,
            boolean mandibulaPoderosa, boolean excelenteNadador){
        super(idAnimal, nombre, especie, años, peso, consumoDiario);
        this.esVenenoso = esVenenoso;
        this.longitud = longitud;
        this.mandibulaPoderosa = mandibulaPoderosa;
        this.excelenteNadador = excelenteNadador;
    }

    // Getters
    public boolean esVenenoso(){
        return esVenenoso;
    }

    public double getLongitud(){
        return longitud;
    }

    public boolean getMandibulaPoderosa(){
        return mandibulaPoderosa;
    }

    public boolean getExcelenteNadador(){
        return excelenteNadador;
    }

    // Polimorfismo
    @Override
    public String getTipoAnimal(){
        return "Reptil";
    }

    @Override
    public String getTipoDieta(){
        return "Carnivora";
    }

    @Override
    public String getSonido(){
        return "Gruñido o bramido";
    }

    @Override
    public String getHabitat(){
        return "Pantano o rio";
    }

    @Override
    public String getDatoEspecifico(){
        return "Longitud: " + String.format("%.2f cm", longitud);
    }

    @Override
    public String toString(){
        return super.toString() + String.format(
                "\n  %-22s: %s\n  %-22s: %.2f cm\n  %-22s: %s\n  %-22s: %s",
                "Venenoso", esVenenoso ? "Si" : "No",
                "Longitud", longitud,
                "Mandibula poderosa", mandibulaPoderosa ? "Si" : "No",
                "Excelente nadador", excelenteNadador ? "Si" : "No"
        );
    }

    @Override
    public String toCSVRow(){
        return super.toCSVRow() + "," + (esVenenoso ? "Si" : "No") + "," + longitud
                + "," + (mandibulaPoderosa ? "Si" : "No") + "," + (excelenteNadador ? "Si" : "No");
    }
}
