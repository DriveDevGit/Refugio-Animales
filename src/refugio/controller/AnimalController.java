package refugio.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void insertarAnimal(Animal animal) {
        dao = new AnimalDAO();
        dao.insert(animal);
    }

    public Dosis obtenerDosis(Animal animal) {
        dao = new DosisDAO();
        return (Dosis) dao.getAll(animal);
    }

    public void vacunasObligatorias() {
        Animal id = new Animal();
        dao = new AnimalDAO();
        GenericoDAO daoD = new DosisDAO();
        id = (Animal) dao.read(id);
        LocalDate timeNow = LocalDate.now();
        String fechaVacuna = timeNow.format(DateTimeFormatter.ISO_DATE);

        Dosis nDosis = new Dosis(id.getId(), id.getEspecieId(), fechaVacuna, true);
        daoD.insert(nDosis);

    }
}
