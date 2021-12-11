package refugio.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import refugio.model.Animal;
import static refugio.model.Animal.toEspecieString;
import static refugio.model.Animal.toRazaString;
import refugio.util.ConnectionManager;

/**
 *
 * @author Jairo
 * @description Esta clase nos permitirá acceder a la base de datos para recoger
 * todos sus datos.
 */
public class AnimalDAO implements GenericoDAO {
    
    /**
     * Método que me devuelve la id y la especie del último animal insertado.
     * 
     * @param id
     * @return 
     */
    @Override
    public Object read(Animal id) {
        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentencia = connection.createStatement()) {

            ResultSet resultado = sentencia.executeQuery("SELECT A.`id`, R.`idespecie` FROM `animal` A JOIN `raza` R ON (A.`id_raza_predominante`=R.`id`) ORDER BY `id` ASC;");

            while (resultado.next()) {
                int id_animal = resultado.getInt("id");
                int id_especie = resultado.getInt("idespecie");
                id = new Animal(id_animal, id_especie);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error");
        }
        return id;
    }

    /**
     * Método que nos servirá para recoger todos los datos de los animales
     *
     * @return
     */
    @Override
    public List<Animal> getAll() {
        List animales = new ArrayList();

        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentencia = connection.createStatement()) {

            ResultSet resultado = sentencia.executeQuery("SELECT A.id, A.nombre, A.sexo, A.fecha_nac, A.color_predominante, R.idespecie, "
                    + "A.id_raza_predominante, A.peso, A.características FROM `animal` A JOIN `raza` R ON (A.id_raza_predominante=R.id)");

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                char sexo = resultado.getString("sexo").charAt(0);
                String fechaNac = resultado.getString("fecha_nac");
                String color = resultado.getString("color_predominante");
                String especie = toEspecieString(resultado.getInt("idespecie"));
                String raza = toRazaString(resultado.getInt("id_raza_predominante"));
                double peso = resultado.getDouble("peso");
                String caract = resultado.getString("características");

                Animal animal = new Animal(id, nombre, sexo, fechaNac, color, especie, raza, peso, caract);
                animales.add(animal);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error");
        }
        return animales;
    }

    /**
     * Método que nos servirá para dar de alta a los animales.
     *
     * @param t
     */
    @Override
    public void insert(Object t) {
        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentencia = connection.createStatement()) {

            Animal animal = (Animal) t;
            LocalDate timeNow = LocalDate.now();

            String nombre = animal.getNombre();
            char sexo = animal.getSexo();
            String fechaNac = animal.getFechanac();
            String color = animal.getColor();
            int idraza = animal.getRazaId();
            double peso = animal.getPeso();
            String fechaAlta = timeNow.format(DateTimeFormatter.ISO_DATE);
            String caract = animal.getCaract();

            sentencia.executeUpdate("INSERT INTO animal (nombre, sexo, fecha_nac, color_predominante, id_raza_predominante, peso, fecha_arribo, características)"
                    + "VALUES ('" + nombre + "','" + sexo + "','" + fechaNac + "','" + color + "'," + idraza + "," + peso + ",'" + fechaAlta + "','" + caract + "');");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error");
        }
    }

    @Override
    public void update(Object t) {
        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentencia = connection.createStatement()) {

            Animal animal = (Animal) t;
            
            int id = animal.getId();
            String nombre = animal.getNombre();
            String caract = animal.getCaract();

            sentencia.executeUpdate("UPDATE animal SET nombre = '"+nombre+"', características = '"+caract+"' WHERE id="+id+";");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error");
        }
    }

    @Override
    public Collection getAll(Animal t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get() {
        Object animal = new Animal();
        try (Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentencia = connection.createStatement()) {
            
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM `animal` ORDER BY fecha_arribo DESC;");

            while (resultado.next()) {
                int id_animal = resultado.getInt("id");
                int id_especie = resultado.getInt("idespecie");
                animal = new Animal(id_animal, id_especie);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error");
        }
        return animal;
    }

}
