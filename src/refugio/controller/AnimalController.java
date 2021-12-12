package refugio.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import refugio.dao.AnimalDAO;
import refugio.dao.DosisDAO;
import refugio.dao.GenericoDAO;
import refugio.model.Animal;
import refugio.model.Dosis;
import static refugio.model.Dosis.toStringVacunaId;

/**
 *
 * @author Jairo
 * @description Esta clase tendrá los métodos principales para obtener y generar
 * los datos de los animales.
 */
public class AnimalController {

    private GenericoDAO dao;

    public AnimalController(GenericoDAO controller) {
        this.dao = controller;
    }

    /**
     * Método que insertará al animal,
     * 
     * @param animal 
     */
    public void insertarAnimal(Animal animal) {
        dao = new AnimalDAO();
        dao.insert(animal);
    }

    /**
     * Método que nos devolverá las dosis de ese animal.
     * 
     * @param animal
     * @return 
     */
    public Dosis obtenerDosis(Animal animal) {
        dao = new DosisDAO();
        return (Dosis) dao.getAll(animal);
    }
    
    /**
     * Método que suministrará las vacunas obligatorias.
     */
    public void vacunasObligatorias() {
        Animal id = new Animal();
        dao = new AnimalDAO();
        GenericoDAO daoD = new DosisDAO();
        id = (Animal) dao.read(id);  //Último animal insertado.
        LocalDate timeNow = LocalDate.now(); //Hora actual
        String fechaVacuna = timeNow.format(DateTimeFormatter.ISO_DATE);

        Dosis nDosis = new Dosis(id.getId(), id.getEspecieId(), fechaVacuna, true);
        daoD.insert(nDosis);

    }
    
    /**
     * Método que suministrará las vacunas no obligatorias.
     * 
     * @param animal 
     * @param vacuna
     */
    public void vacunaNoObligatoria(Animal animal, String vacuna){
        dao = new AnimalDAO();
        GenericoDAO daoD = new DosisDAO();
        LocalDate timeNow = LocalDate.now(); //Hora actual
        String fechaVacuna = timeNow.format(DateTimeFormatter.ISO_DATE);

        Dosis nDosis = new Dosis(animal.getId(), animal.getEspecieId(), toStringVacunaId(vacuna),fechaVacuna, false);
        daoD.insert(nDosis);
    }
    
    /**
     * Método para editar al animal.
     * 
     * @param id
     * @param nombre
     * @param caract 
     */
    public void editarAnimal(int id, String nombre, String caract){
        Animal animal = new Animal (id, nombre, caract);
        dao = new AnimalDAO();
        dao.update(animal);
    }
    
    /**
     * Método que sirve para adoptar a un animal.
     */
    public void adoptarAnimal(int idespecie){
        dao = new AnimalDAO();
        dao.get(idespecie);
    }
    
    public void buscar(){
        
    }
}
