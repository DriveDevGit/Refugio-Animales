package refugio.model;

/**
 *
 * @author Jairo
 */
public class Dosis {
    private int id_animal;
    private int id_vacuna;
    private int id_especie;
    private String vacuna;
    private String fecha;
    private boolean esencial;

    public Dosis() {
    }
    
    public Dosis(int id_animal, String vacuna, String fecha) {
        this.id_animal = id_animal;
        this.vacuna = vacuna;
        this.fecha = fecha;
    }
    
    public Dosis(int id_animal, int id_especie, String fecha, boolean esencial){
        this.id_animal = id_animal;
        this.id_especie = id_especie;
        this.fecha = fecha;
        this.esencial = esencial;
    }

    public int getId_especie() {
        return id_especie;
    }

    public void setId_especie(int id_especie) {
        this.id_especie = id_especie;
    }

    public boolean isEsencial() {
        return esencial;
    }

    public void setEsencial(boolean esencial) {
        this.esencial = esencial;
    }
    
    

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public int getId_vacuna() {
        return id_vacuna;
    }

    public void setId_vacuna(int id_vacuna) {
        this.id_vacuna = id_vacuna;
    }

    public String getVacuna() {
        return vacuna;
    }

    public void setVacuna(String vacuna) {
        this.vacuna = vacuna;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public static String toIdVacunaString(int id){
        switch(id){
            case 1: return "Moquillo";
            case 2: return "Parvovirus";
            case 3: return "Rabia";
            case 4: return "Adenovirus";
            case 5: return "Coronavirus";
            case 6: return "Leptospira";
            case 7: return "Herpes";
            case 8: return "Calcivirus";
            case 9: return "Panleucopenia";
            case 11: return "Peritonitis";
            default: return "Sin vacuna";
        }
    }
    
}
