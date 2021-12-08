package refugio.controller;

import java.util.Collection;
import refugio.dao.AnimalDAO;
import refugio.dao.DosisDAO;
import refugio.dao.GenericoDAO;
import refugio.model.Animal;
import refugio.model.Dosis;

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
      
    public void insertarAnimal(Animal animal){
        dao = new AnimalDAO();
        dao.insert(animal);
    }
    
    public Dosis obtenerDosis(Animal animal){
        dao = new DosisDAO();
        return (Dosis) dao.getAll(animal);
    }
    
    public void vacunasObligatorias(){
        
    }
}
