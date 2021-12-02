package refugio.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import refugio.dao.AnimalDAO;
import refugio.model.Animal;

/**
 * FXML Controller class
 *
 * @author Jairo
 * @description Controlador principal donde tendremos todas las acciones del
 * SceneBuilder.
 */
public class MainController implements Initializable {

    private AnimalDAO dao;
    private boolean tablaMostrada = false;

    @FXML
    private FontAwesomeIconView icnClose;
    @FXML
    private Pane banner;
    @FXML
    private Button index;
    @FXML
    private Button adoption;
    @FXML
    private TextField search;
    @FXML
    private Button aboutme;
    @FXML
    private TableView<Animal> tableList;
    @FXML
    private TableColumn<Animal, String> nameCol;
    @FXML
    private TableColumn<Animal, Character> genreCol;
    @FXML
    private TableColumn<Animal, String> colorCol;
    @FXML
    private TableColumn<Animal, String> raceCol;
    @FXML
    private TableColumn<Animal, Double> weightCol;
    @FXML
    private TableColumn<Animal, String> caractCol;
    @FXML
    private Label lblImageView;
    @FXML
    private ImageView imageView;
    @FXML
    private Pane paneAdoption;
    @FXML
    private Pane paneIndex;
    @FXML
    private TableColumn<Animal, String> especieCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paneIndex.toFront();
    }

    @FXML
    private void handleClose(MouseEvent event) {
        if (event.getSource() == icnClose) {
            System.exit(0);
        }

    }

    /**
     * Pestaña que mostrará a todos los animales.
     *
     * @param event El ActionListener mostrará la tabla y activará el filtro de
     * búsqueda.
     */
    @FXML
    private void actionListAdoption(ActionEvent event) {
        paneAdoption.toFront();
        if (tablaMostrada == false) {
            this.mostrarTabla();
            tablaMostrada = true;
        }
    }

    /**
     *
     * @param event ActionListener que nos devolverá a la vista del índice.
     */
    @FXML
    private void actionIndex(ActionEvent event) {
        paneIndex.toFront();
    }

    /**
     * Componente que mostrará la penstaña de adopción.
     *
     * @param event El ActionListener mostrará la tabla y activará el filtro de
     * búsqueda.
     */
    @FXML
    private void clickSearch(MouseEvent event) {
        paneAdoption.toFront();
        if (tablaMostrada == false) {
            this.mostrarTabla();
            tablaMostrada = true;
        }
    }

    /**
     * Método que nos servirá para simplifar el código. Este método rellena la
     * tabla de animales y activa el filtro.
     */
    public void mostrarTabla() {
        dao = new AnimalDAO();
        Collection<Animal> animales = dao.getAll();
        nameCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("Nombre"));
        genreCol.setCellValueFactory(new PropertyValueFactory<Animal, Character>("Sexo"));
        colorCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("Color"));
        especieCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("Especie"));
        raceCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("Raza"));
        weightCol.setCellValueFactory(new PropertyValueFactory<Animal, Double>("Peso"));
        caractCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("Caract"));
        tableList.getItems().addAll(animales);

        FilteredList<Animal> datosFiltrados = new FilteredList<>(tableList.getItems(), p -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            datosFiltrados.setPredicate(animal -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (animal.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (animal.getColor().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (animal.getCaract().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Animal> datosOrdenados = new SortedList<>(datosFiltrados);

        datosOrdenados.comparatorProperty().bind(tableList.comparatorProperty());
        tableList.setItems(datosOrdenados);
    }

}
