package zoologico;

/**
 * Superclase Animal — Fase 2
 *
 * @author GRUPO 4
 */
public abstract class Animal{

    // Atributos
    private long idAnimal;
    private String nombre;
    private String especie;
    private int años;
    private double peso;
    private double consumoDiario;

    // Constructor
    public Animal(long idAnimal, String nombre, String especie, int años,
            double peso, double consumoDiario){
        this.idAnimal = idAnimal;
        this.nombre = nombre;
        this.especie = especie;
        this.años = años;
        this.peso = peso;
        this.consumoDiario = consumoDiario;
    }

    // Getters
    public long getIdAnimal(){
        return idAnimal;
    }

    public String getNombre(){
        return nombre;
    }

    public String getEspecie(){
        return especie;
    }

    public int getAños(){
        return años;
    }

    public double getPeso(){
        return peso;
    }

    public double getConsumoDiario(){
        return consumoDiario;
    }

    // polimorfismo
    public abstract String getTipoAnimal();
    public abstract String getTipoDieta();
    public abstract String getSonido();
    public abstract String getHabitat();
    public abstract String getDatoEspecifico();
    
    // recursividad
    public double calcularAlimento(int dias){
        if (dias <= 0) {
            return 0.0;
        }
        return consumoDiario + calcularAlimento(dias - 1);
    }

    // toString
    @Override
    public String toString(){
        return String.format(
                "  %-22s: %d\n"
                + "  %-22s: %s\n"
                + "  %-22s: %s\n"
                + "  %-22s: %s\n"
                + "  %-22s: %d años\n"
                + "  %-22s: %.2f lb\n"
                + "  %-22s: %s\n"
                + "  %-22s: %s\n"
                + "  %-22s: %s\n"
                + "  %-22s: %.2f lb/dia\n"
                + "  %-22s: %s",
                "ID", idAnimal,
                "Tipo", getTipoAnimal(),
                "Nombre", nombre,
                "Especie", especie,
                "Años", años,
                "Peso", peso,
                "Dieta", getTipoDieta(),
                "Habitat", getHabitat(),
                "Sonido", getSonido(),
                "Consumo diario", consumoDiario,
                getDatoEspecifico().split(":")[0], getDatoEspecifico().contains(":") ? getDatoEspecifico().split(":", 2)[1].trim() : ""
        );
    }

    // tabla
    public String toResumen(){
        return String.format("  ID: %-6d | Nombre: %-18s | Años: %-4d | Tipo: %-10s | %s",
                idAnimal, nombre, años, getTipoAnimal(), getDatoEspecifico());
    }

    public String toCSVRow(){
        return String.format("%d,%s,%s,%s,%d,%.2f,%s,%s,%.2f",
                idAnimal, getTipoAnimal(), nombre, especie, años, peso,
                getTipoDieta(), getHabitat(), consumoDiario);
    }
}
