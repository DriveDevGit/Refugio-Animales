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

    @Override
    public Object read(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Animal> getAll() {
        List animales = new ArrayList();
        
        try(Connection connection = ConnectionManager.getInstance().getConnection();
            Statement sentencia = connection.createStatement()){
            
            ResultSet resultado = sentencia.executeQuery("SELECT nombre, sexo, color_predominante, id_raza_predominante,"
                                                        + "peso, características FROM animal");
                       
            while(resultado.next()){
                String nombre = resultado.getString("nombre");
                char sexo = resultado.getString("sexo").charAt(0);
                String color = resultado.getString("color_predominante");
                String raza = toRazaString(resultado.getInt("id_raza_predominante"));
                double peso = resultado.getDouble("peso");
                String caract = resultado.getString("características");
                  
                Animal animal = new Animal(nombre, sexo, color, raza, peso, caract);
                animales.add(animal);
            }
            
            
        }catch(SQLException ex){
            ex.printStackTrace();
            System.err.println("Error");
        }
        return animales;
    }

    @Override
    public void insert(Object t) {
        try(Connection connection = ConnectionManager.getInstance().getConnection();
            Statement sentencia = connection.createStatement()){
            
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
            
            System.out.println(nombre+ " " + sexo + " " + fechaNac + " " + color + " " + idraza + " " + peso + " " + fechaAlta+ " " + caract);
            
            sentencia.executeUpdate("INSERT INTO animal (nombre, sexo, fecha_nac, color_predominante, id_raza_predominante, peso, fecha_arribo, características)" +
                                                            "VALUES ('"+nombre+"','"+sexo+"','"+fechaNac+"','"+color+"',"+idraza+","+peso+",'"+fechaAlta+"','"+caract+"');");

                              
        }catch(SQLException ex){
            ex.printStackTrace();
            System.err.println("Error");
        }
    }

    @Override
    public void update(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteByID(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
