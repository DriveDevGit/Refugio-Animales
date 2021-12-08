package refugio.model;

/**
 *
 * @author Jairo
 */
public class Dosis {
    private int id_animal;
    private int id_vacuna;
    private String vacuna;
    private String fecha;

    public Dosis() {
    }
    
    public Dosis(int id_animal, String vacuna, String fecha) {
        this.id_animal = id_animal;
        this.vacuna = vacuna;
        this.fecha = fecha;
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
