package refugio.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import refugio.dao.AnimalDAO;
import refugio.dao.DosisDAO;
import refugio.dao.GenericoDAO;
import refugio.model.Animal;
import static refugio.model.Animal.toRazaInteger;
import refugio.model.Dosis;

/**
 * FXML Controller class
 *
 * @author Jairo
 * @description Controlador principal donde tendremos todas las acciones del
 * SceneBuilder.
 */
public class MainController implements Initializable {

    private GenericoDAO dao;
    private AnimalController AController;
    private boolean tablaMostrada = false;
    private Animal animalClic;

    private ObservableList<String> generos = FXCollections.observableArrayList("Macho", "Hembra");
    private ObservableList<String> razas = FXCollections.observableArrayList("Siamés", "Persa", "Siberiano",
            "Bengalí", "Angora Turco", "Siberiano2",
            "Bulldog", "Labrador", "Caniche",
            "Pastor alemán", "Chihuahua", "Terrier",
            "Perro genérico", "Gato genérico");

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
    @FXML
    private Button insert;
    @FXML
    private Pane paneInsert;
    @FXML
    private TextField fieldNombreAlta;
    @FXML
    private ComboBox<String> sexoBoxAlta;
    @FXML
    private TextField colorFieldAlta;
    @FXML
    private ComboBox<String> razaBoxAlta;
    @FXML
    private TextField pesoFieldAlta;
    @FXML
    private TextArea caractFieldAlta;
    @FXML
    private Label lblErrorAlta;
    @FXML
    private DatePicker fechaNac;
    @FXML
    private Pane paneDatos;
    @FXML
    private Label nombreAnimal;
    @FXML
    private Label sexoAnimal;
    @FXML
    private Label razaAnimal;
    @FXML
    private Label caractAnimal;
    @FXML
    private Label fechaNacAnimal;
    @FXML
    private TableView<Dosis> tablaDosis;
    @FXML
    private TableColumn<Dosis, String> vacunaColumna;
    @FXML
    private TableColumn<Dosis, String> fechaVacunaColumna;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paneIndex.toFront();

        sexoBoxAlta.setItems(generos);
        razaBoxAlta.setItems(razas);
    }

    /**
     *
     * @param event El ActionListener cerrará el programa.
     */
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
     *
     * @param event El ActionListener cambiará a la pestaña de insertar
     */
    @FXML
    private void actionInsert(ActionEvent event) {
        paneInsert.toFront();
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
                } else if (animal.getRaza().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Animal> datosOrdenados = new SortedList<>(datosFiltrados);

        datosOrdenados.comparatorProperty().bind(tableList.comparatorProperty());
        tableList.setItems(datosOrdenados);

        /**
         * Esta clase interna nos dará la posibilidad de darle doble clic a una
         * fila para poder acceder a un nuevo panel.
         */
        tableList.setRowFactory(tv -> {
            TableRow<Animal> row = new TableRow<>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        Animal animalClicked = row.getItem();                                              
                        animalClic = animalClicked;
                        dao = new DosisDAO();                        
                        Collection<Dosis> dosisList = dao.getAll(animalClic);
                        actualizarPanelDatos(animalClicked, dosisList);
                        paneDatos.toFront();
                    }
                }
            });
            return row;
        });
    }

    /**
     * Método que nos servirá para dar de alta a un animal.
     *
     * @param event
     */
    @FXML
    private void subirAnimal(ActionEvent event) {
        AController = new AnimalController(dao);

        if (fieldNombreAlta.getText().equals("") || colorFieldAlta.getText().equals("")
                || pesoFieldAlta.getText().equals("") || caractFieldAlta.getText().equals("")) {
            lblErrorAlta.setVisible(true);
        } else if (sexoBoxAlta.getSelectionModel().isEmpty() || razaBoxAlta.getSelectionModel().isEmpty() || fechaNac.getValue() == null) {
            lblErrorAlta.setVisible(true);
        } else if (pesoFieldAlta.getText().charAt(0) < 48 || pesoFieldAlta.getText().charAt(0) > 57) {
            lblErrorAlta.setVisible(true);
        } else {
            Animal animal = new Animal(fieldNombreAlta.getText(), sexoBoxAlta.getValue().charAt(0), fechaNac.getValue(), colorFieldAlta.getText(), toRazaInteger(razaBoxAlta.getValue()),
                    Double.parseDouble(pesoFieldAlta.getText()), caractFieldAlta.getText());
            AController.insertarAnimal(animal);
            AController.vacunasObligatorias();
            lblErrorAlta.setVisible(false);
        }

    }
    
    private void actualizarPanelDatos(Animal animal, Collection dosis){
        
        nombreAnimal.setText(animal.getNombre());
        
        if(animal.getSexo()=='M'){
            sexoAnimal.setText("Macho");
        }
        else{
            sexoAnimal.setText("Hembra");
        }
        
        razaAnimal.setText(animal.getRaza());
        fechaNacAnimal.setText(animal.getFechanac());
        caractAnimal.setText(animal.getCaract());
        
        tablaDosis.getItems().clear();
        vacunaColumna.setCellValueFactory(new PropertyValueFactory<Dosis, String>("Vacuna"));
        fechaVacunaColumna.setCellValueFactory(new PropertyValueFactory<Dosis, String>("Fecha"));
        tablaDosis.getItems().addAll(dosis);
    }

}
