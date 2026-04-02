package zoologico;

/**
 *
 * @author willi
 */

public abstract class Animal {

    // Atributos
    private String nombre;
    private String especie;
    private int    edad;
    private double peso;          // en libras
    private double consumoDiario; // libras por día

    // Constructor
    public Animal(String nombre, String especie, int edad, double peso, double consumoDiario) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.peso = peso;
        this.consumoDiario = consumoDiario;
    }

    // Getters
    public String getNombre(){return nombre;}
    public String getEspecie(){return especie;}
    public int    getEdad(){return edad;}
    public double getPeso(){ return peso;}
    public double getConsumoDiario(){return consumoDiario;}

    // Polimorfismo
    public abstract String getTipoAnimal();
    public abstract String getTipoDieta();
    public abstract String getSonido();
    public abstract String getHabitat();

    public double calcularAlimento(int dias){
        if(dias <= 0) {
            return 0.0;
        }
        return consumoDiario + calcularAlimento(dias - 1);
    }

    /*
     * Retorna los datos básicos del animal en formato legible.
     */
    @Override
    public String toString() {
        return String.format(
            "  %-22s: %s\n"  +
            "  %-22s: %s\n"  +
            "  %-22s: %s\n"  +
            "  %-22s: %d años\n" +
            "  %-22s: %.2f lb\n" +
            "  %-22s: %s\n"  +
            "  %-22s: %s\n"  +
            "  %-22s: %s\n"  +
            "  %-22s: %.2f lb/día",
            "Tipo", getTipoAnimal(),
            "Nombre", nombre,
            "Especie", especie,
            "Edad", edad,
            "Peso", peso,
            "Dieta", getTipoDieta(),
            "Hábitat", getHabitat(),
            "Sonido", getSonido(),
            "Consumo diario", consumoDiario
        );
    }

    /*
     * Retorna una fila CSV con los datos del animal.
     * Las subclases deben extender este método.
     */
    public String toCSVRow() {
        return String.format("%s,%s,%s,%d,%.2f,%s,%s,%.2f",
            getTipoAnimal(), nombre, especie, edad, peso,
            getTipoDieta(), getHabitat(), consumoDiario);
    }
}