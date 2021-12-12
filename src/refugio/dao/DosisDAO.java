/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refugio.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import refugio.controller.AnimalController;
import refugio.model.Animal;
import static refugio.model.Animal.toRazaString;
import refugio.model.Dosis;
import static refugio.model.Dosis.toIdVacunaString;
import refugio.util.ConnectionManager;

/**
 *
 * @author Jairo
 */
public class DosisDAO implements GenericoDAO {

    private AnimalController AController;
    
    /**
     * Método que nos dará las vacunas de un animal en concreto.
     * 
     * @param t
     * @return 
     */
    @Override
    public Object read(Animal t) {
        Dosis dosis = new Dosis();
        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentencia = connection.createStatement()) {

            ResultSet resultado = sentencia.executeQuery("SELECT id_vacuna, fecha FROM `dosis` D JOIN `animal` A ON (A.id=D.id_animal) WHERE D.id_animal=" + t.getId() + ";");

            while (resultado.next()) {
                int id_animal = t.getId();
                String vacuna = toIdVacunaString(resultado.getInt("id_vacuna"));
                String fecha = resultado.getString("fecha");

                dosis = new Dosis(id_animal, vacuna, fecha);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error");
        }
        return dosis;
    }

    @Override
    public Collection getAll(Animal t) {
        List dosisList = new ArrayList();

        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentencia = connection.createStatement()) {
            ResultSet resultado = sentencia.executeQuery("SELECT id_vacuna, fecha FROM `dosis` D JOIN `animal` A ON (A.id=D.id_animal) WHERE D.id_animal=" + t.getId() + ";");

            while (resultado.next()) {
                int id_animal = t.getId();
                String vacuna = toIdVacunaString(resultado.getInt("id_vacuna"));
                String fecha = resultado.getString("fecha");

                Dosis dosis = new Dosis(id_animal, vacuna, fecha);
                dosisList.add(dosis);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error");
        }
        return dosisList;
    }

    @Override
    public List<Dosis> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(Object t) {
        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentencia = connection.createStatement();
                Statement sentencia2 = connection.createStatement();) {

            Dosis dosis = (Dosis) t;
            int id_animal = dosis.getId_animal();
            int id_especie = dosis.getId_especie();
            int id_vacuna = dosis.getId_vacuna();
            String fecha = dosis.getFecha();
            boolean esencial = dosis.isEsencial();
            
            if (esencial == true) {
                if (id_especie == 1) {
                    ResultSet resultado = sentencia.executeQuery("SELECT V.`id` FROM `vacuna` V JOIN vacuna_especie E ON (V.id=E.id_vacuna) WHERE V.escencial=1 AND E.id_especie=1;");
                    
                    while (resultado.next()) {
                        id_vacuna = resultado.getInt("id");
                        sentencia2.executeUpdate("INSERT INTO dosis (id_animal, id_vacuna, fecha) VALUES (" + id_animal + "," + id_vacuna + ",'" + fecha + "');");
                    }
                } else {
                    ResultSet resultado = sentencia.executeQuery("SELECT V.`id` FROM `vacuna` V JOIN vacuna_especie E ON (V.id=E.id_vacuna) WHERE V.escencial=1 AND E.id_especie=2;");
                    while (resultado.next()) {
                        id_vacuna = resultado.getInt("id");
                        sentencia2.executeUpdate("INSERT INTO dosis (id_animal, id_vacuna, fecha) VALUES (" + id_animal + "," + id_vacuna + ",'" + fecha + "');");
                    }

                }

            }
            else{
                sentencia2.executeUpdate("INSERT INTO dosis (id_animal, id_vacuna, fecha) VALUES (" + id_animal + "," + id_vacuna + ",'" + fecha + "');");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error");
        }
    }

    @Override
    public void update(Object t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void get(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection search(int nCampos, String colorCombo, String razaCombo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
