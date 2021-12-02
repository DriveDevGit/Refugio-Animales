package refugio.controller;

import java.util.Collection;
import refugio.dao.GenericoDAO;
import refugio.model.Animal;

/**
 *
 * @author Jairo
 * @description Esta clase tendrá los métodos principales para obtener y generar
 * los datos de los animales.
 */
public class AnimalController {

    private GenericoDAO animalDAO;

    public AnimalController(GenericoDAO controller) {
        this.animalDAO = controller;
    }
    
    public void insertarAnimal(Animal animal){
        animalDAO.insert(animal);
    }
}
