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
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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
    private ObservableList<String> colores = FXCollections.observableArrayList("Moreno", "Negro", "Gris", "Rubio", "Blanco");

    private ObservableList<String> vacunaPerros = FXCollections.observableArrayList("Moquillo", "Parvovirus", "Rabia",
            "Adenovirus", "Coronavirus", "Leptospira");
    private ObservableList<String> vacunaGatos = FXCollections.observableArrayList("Coronavirus", "Herpes", "Calcivirus",
            "Panleucopenia", "Peritonitis");
    @FXML
    private Pane paneBuscar;
    @FXML
    private TableView<Animal> tablaBuscar;
    @FXML
    private TableColumn<Animal, String> nombreColumnaBuscar;
    @FXML
    private TableColumn<Animal, Character> sexoColumnaBuscar;
    @FXML
    private TableColumn<Animal, String> colorColumnaBuscar;
    @FXML
    private TableColumn<Animal, String> especieColumnaBuscar;
    @FXML
    private TableColumn<Animal, String> razaColumnaBuscar;
    @FXML
    private TableColumn<Animal, Double> pesoColumnaBuscar;
    @FXML
    private TableColumn<Animal, String> caractColumnaBuscar;
    @FXML
    private ComboBox<String> comboRaza;
    @FXML
    private ComboBox<String> comboColor;
    @FXML
    private Button botonBuscar;
    @FXML
    private Label lblCamposBuscar;
    @FXML
    private Pane paneDatos;
    @FXML
    private Label nombreAnimal;
    @FXML
    private TableView<Dosis> tablaDosis;
    @FXML
    private TableColumn<Dosis, String> vacunaColumna;
    @FXML
    private TableColumn<Dosis, String> fechaVacunaColumna;
    @FXML
    private Label sexoAnimal;
    @FXML
    private Label razaAnimal;
    @FXML
    private Label fechaNacAnimal;
    @FXML
    private Label caractAnimal;
    @FXML
    private Button editar;
    @FXML
    private Label idAnimal;
    @FXML
    private Pane banner;
    @FXML
    private FontAwesomeIconView icnClose;
    @FXML
    private Button inicio;
    @FXML
    private Button animales;
    @FXML
    private TextField filtrar;
    @FXML
    private Button buscar;
    @FXML
    private Button insertar;
    @FXML
    private Button adoptar;
    @FXML
    private Pane paneEditar;
    @FXML
    private Label sexoAnimalE;
    @FXML
    private Label razaAnimalE;
    @FXML
    private Label fechaNacAnimalE;
    @FXML
    private Button Hecho;
    @FXML
    private TextField editarNombre;
    @FXML
    private TextField editarCaract;
    @FXML
    private ComboBox<String> comboVacunas;
    @FXML
    private Button Insertar;
    @FXML
    private Label idAnimalE;
    @FXML
    private Label lblVacunaVacia;
    @FXML
    private Label lblExitoInsertar;
    private Pane paneIndece;
    @FXML
    private Pane paneAdoptar;
    @FXML
    private Button botonAdoptar;
    @FXML
    private Label lblPerro;
    @FXML
    private Label lblGato;
    @FXML
    private Label lblAnimal;
    @FXML
    private Label lblGracias;
    @FXML
    private Pane paneAdoption;
    @FXML
    private TableView<Animal> tablaAnimal;
    @FXML
    private TableColumn<Animal, String> nombreColumna;
    @FXML
    private TableColumn<Animal, Character> sexoColumna;
    @FXML
    private TableColumn<Animal, String> colorColumna;
    @FXML
    private TableColumn<Animal, String> especieColumna;
    @FXML
    private TableColumn<Animal, String> razaColumna;
    @FXML
    private TableColumn<Animal, Double> pesoColumna;
    @FXML
    private TableColumn<Animal, String> caractColumna;
    @FXML
    private TableColumn<Animal, String> adopcionColumna;
    @FXML
    private Pane paneInsertar;
    @FXML
    private TextField fieldNombreAlta;
    @FXML
    private ComboBox<String> sexoBoxAlta;
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
    private ComboBox<String> comboColorAlta;
    @FXML
    private Pane paneIndice;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paneIndice.toFront();
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
    private void actionListTable(ActionEvent event) {
        paneAdoption.toFront();
        if (tablaMostrada == false) {
            this.mostrarTabla();
            this.filtrarAnimal();
            tablaMostrada = true;
        } else {
            tablaAnimal.getItems().clear();
            this.mostrarTabla();
        }
    }

    /**
     *
     * @param event ActionListener que nos devolverá a la vista del índice.
     */
    @FXML
    private void actionInicio(ActionEvent event) {
        paneIndice.toFront();
    }

    /**
     * Componente que mostrará la penstaña de adopción.
     *
     * @param event El ActionListener mostrará la tabla y activará el filtro de
     * búsqueda.
     */
    @FXML
    private void clicFiltrar(MouseEvent event) {
        paneAdoption.toFront();
        if (tablaMostrada == false) {
            this.mostrarTabla();
            this.filtrarAnimal();
            tablaMostrada = true;
        } else {
            tablaAnimal.getItems().clear();
            this.mostrarTabla();
        }
    }

    /**
     *
     * @param event El ActionListener cambiará a la pestaña de insertar
     */
    @FXML
    private void actionInsertar(ActionEvent event) {
        sexoBoxAlta.setItems(generos);
        razaBoxAlta.setItems(razas);
        comboColorAlta.setItems(colores);
        paneInsertar.toFront();
    }

    /**
     *
     * @param event El ActionListener cambiará a la pestaña de adoptar.
     */
    @FXML
    private void actionAdoptar(ActionEvent event) {
        paneAdoptar.toFront();
    }
          
    /**
     * Método que nos llevas a la pestaña de buscar.
     * 
     * @param event 
     */
    @FXML
    private void actionBuscar(ActionEvent event) {
        comboRaza.setItems(razas);
        comboColor.setItems(colores);
        paneBuscar.toFront();
    }

    /**
     * Método que nos servirá para simplifar el código. Este método rellena la
     * tabla de animales y activa el filtro.
     */
    public void mostrarTabla() {
        dao = new AnimalDAO();
        Collection<Animal> animales = dao.getAll();
        nombreColumna.setCellValueFactory(new PropertyValueFactory<Animal, String>("Nombre"));
        sexoColumna.setCellValueFactory(new PropertyValueFactory<Animal, Character>("Sexo"));
        colorColumna.setCellValueFactory(new PropertyValueFactory<Animal, String>("Color"));
        especieColumna.setCellValueFactory(new PropertyValueFactory<Animal, String>("Especie"));
        razaColumna.setCellValueFactory(new PropertyValueFactory<Animal, String>("Raza"));
        pesoColumna.setCellValueFactory(new PropertyValueFactory<Animal, Double>("Peso"));
        caractColumna.setCellValueFactory(new PropertyValueFactory<Animal, String>("Caract"));
        adopcionColumna.setCellValueFactory(new PropertyValueFactory<Animal, String>("FechaAdop"));
        tablaAnimal.getItems().addAll(animales);
         
        /**
         * Esta clase interna nos dará la posibilidad de darle doble clic a una
         * fila para poder acceder a un nuevo panel.
         */
        tablaAnimal.setRowFactory(tv -> {
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
     * Método para filtrar las búsquedas.
     */
    private void filtrarAnimal() {

        FilteredList<Animal> datosFiltrados = new FilteredList<>(tablaAnimal.getItems(), p -> true);
        filtrar.textProperty().addListener((observable, oldValue, newValue) -> {
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

        datosOrdenados.comparatorProperty().bind(tablaAnimal.comparatorProperty());
        tablaAnimal.setItems(datosOrdenados);
    }

    /**
     * Método que nos servirá para dar de alta a un animal.
     *
     * @param event
     */
    @FXML
    private void subirAnimal(ActionEvent event) {
        AController = new AnimalController(dao);

        if (fieldNombreAlta.getText().equals("") || pesoFieldAlta.getText().equals("") || caractFieldAlta.getText().equals("") || Integer.parseInt(pesoFieldAlta.getText()) > 100) {
            lblErrorAlta.setVisible(true);
        } else if (sexoBoxAlta.getSelectionModel().isEmpty() || razaBoxAlta.getSelectionModel().isEmpty() || comboColorAlta.getSelectionModel().isEmpty()
                || fechaNac.getValue() == null) {
            lblErrorAlta.setVisible(true);
        } else if (pesoFieldAlta.getText().charAt(0) < 48 || pesoFieldAlta.getText().charAt(0) > 57) {
            lblErrorAlta.setVisible(true);
        } else {
            Animal animal = new Animal(fieldNombreAlta.getText(), sexoBoxAlta.getValue().charAt(0), fechaNac.getValue(), comboColorAlta.getValue(), toRazaInteger(razaBoxAlta.getValue()),
                    Double.parseDouble(pesoFieldAlta.getText()), caractFieldAlta.getText());
            AController.insertarAnimal(animal);
            AController.vacunasObligatorias();
            lblErrorAlta.setVisible(false);
        }
    }

    /**
     * Método para actualizar el panel de datos.
     *
     * @param animal
     * @param dosis
     */
    private void actualizarPanelDatos(Animal animal, Collection dosis) {

        idAnimal.setText(String.valueOf(animal.getId()));
        idAnimalE.setText(String.valueOf(animal.getId()));
        nombreAnimal.setText(animal.getNombre());

        if (animal.getSexo() == 'M') {
            sexoAnimal.setText("Macho");
            sexoAnimalE.setText("Macho");
        } else {
            sexoAnimal.setText("Hembra");
            sexoAnimalE.setText("Hembra");
        }

        razaAnimal.setText(animal.getRaza());
        fechaNacAnimal.setText(animal.getFechanac());
        caractAnimal.setText(animal.getCaract());
        razaAnimalE.setText(animal.getRaza());
        fechaNacAnimalE.setText(animal.getFechanac());

        tablaDosis.getItems().clear();
        vacunaColumna.setCellValueFactory(new PropertyValueFactory<Dosis, String>("Vacuna"));
        fechaVacunaColumna.setCellValueFactory(new PropertyValueFactory<Dosis, String>("Fecha"));
        tablaDosis.getItems().addAll(dosis);
    }

    /**
     * Método que nos llevará al panel de modificar.
     *
     * @param event
     */
    @FXML
    private void actionEditar(ActionEvent event) {
        paneEditar.toFront();
        if (animalClic.getEspecie().equals("Perro")) {
            comboVacunas.setItems(vacunaPerros);
        } else {
            comboVacunas.setItems(vacunaGatos);
        }
    }

    /**
     * Método que modificará los datos del animal.
     *
     * @param event
     */
    @FXML
    private void actionHecho(ActionEvent event) {
        AController = new AnimalController(dao);
        int id = Integer.parseInt(idAnimalE.getText());
        String nombre = "";
        String caract = "";
        String color = "";
        if (editarNombre.getText().equals("")) { //Si el campo nombre está vacío
            nombre = nombreAnimal.getText(); //Se dejará el nombre anterior
            if (editarCaract.getText().equals("")) { //Si el campo caract está vacío
                caract = caractAnimal.getText(); //Se dejarán las caract anteriores         

            } else {
                caract = editarCaract.getText(); //Se colocará las nuevas caract

            }
        } else {
            nombre = editarNombre.getText(); //Se colocará el nuevo nombre
            if (editarCaract.getText().equals("")) { //Si el campo caract está vacío
                caract = caractAnimal.getText(); //Se dejarán las caract anteriores

            } else {
                caract = editarCaract.getText(); //Se colocará las nuevas caract

            }
        }

        AController.editarAnimal(id, nombre, caract);
        editarNombre.setText("");
        editarCaract.setText("");
        paneAdoption.toFront();
    }

    /**
     * Método para insertar cualquier vacuna.
     *
     * @param event
     */
    @FXML
    private void actionInsertarVacuna(ActionEvent event) {
        AController = new AnimalController(dao);
        if (comboVacunas.getSelectionModel().isEmpty()) {
            lblVacunaVacia.setVisible(true);
        } else {
            lblVacunaVacia.setVisible(false);
            String vacuna = comboVacunas.getValue();
            AController.vacunaNoObligatoria(animalClic, vacuna);
            lblExitoInsertar.setText("La vacuna " + comboVacunas.getValue() + " ha sido suministrada a " + animalClic.getNombre());
            lblExitoInsertar.setVisible(true);
        }
    }
    
    /**
     * Método que servirá para filtrar por los campos elegidos.
     * 
     * @param event 
     */
    @FXML
    private void botonBuscar(ActionEvent event) {
        dao = new AnimalDAO();
        Collection<Animal> animales = null;
        tablaBuscar.getItems().clear();
        boolean exito = false;
        if (comboRaza.getSelectionModel().isEmpty() && comboColor.getSelectionModel().isEmpty()) {
            lblCamposBuscar.setVisible(true);
        } else if (!(comboRaza.getSelectionModel().isEmpty()) && comboColor.getSelectionModel().isEmpty()) {
            animales = dao.search(1, comboRaza.getValue(), "Vacío");
            lblCamposBuscar.setVisible(false);
            exito = true;
        } else if (comboRaza.getSelectionModel().isEmpty() && !(comboColor.getSelectionModel().isEmpty())) {
            animales = dao.search(2, "Vacío", comboColor.getValue());
            lblCamposBuscar.setVisible(false);
            exito = true;
        } else if (!(comboRaza.getSelectionModel().isEmpty()) && !(comboColor.getSelectionModel().isEmpty())) {
            animales = dao.search(3, comboRaza.getValue(), comboColor.getValue());
            lblCamposBuscar.setVisible(false);
            exito = true;
        }

        if (exito == true) {
            nombreColumnaBuscar.setCellValueFactory(new PropertyValueFactory<Animal, String>("Nombre"));
            sexoColumnaBuscar.setCellValueFactory(new PropertyValueFactory<Animal, Character>("Sexo"));
            colorColumnaBuscar.setCellValueFactory(new PropertyValueFactory<Animal, String>("Color"));
            especieColumnaBuscar.setCellValueFactory(new PropertyValueFactory<Animal, String>("Especie"));
            razaColumnaBuscar.setCellValueFactory(new PropertyValueFactory<Animal, String>("Raza"));
            pesoColumnaBuscar.setCellValueFactory(new PropertyValueFactory<Animal, Double>("Peso"));
            caractColumnaBuscar.setCellValueFactory(new PropertyValueFactory<Animal, String>("Caract"));
            tablaBuscar.getItems().addAll(animales);
        }

    }

    /**
     * La siguiente lista de métodos son acciones Drag and Drop, las cuales se
     * usan para arrastrar ciertos label y poder adoptar un perro, un gato o un
     * animal
     */
    @FXML
    private void adoptarOver(DragEvent event) {
        if (event.getGestureSource() != lblGracias
                && event.getDragboard().hasString()) {

            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @FXML
    private void adoptarDrop(DragEvent event) {
        AController = new AnimalController(dao);
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.getString().equals("Perro")) {
            AController.adoptarAnimal(1);
            success = true;
        } else if (db.getString().equals("Gato")) {
            AController.adoptarAnimal(2);
            success = true;
        } else if (db.getString().equals("Animal")) {
            AController.adoptarAnimal(5);
            success = true;
        }

        event.setDropCompleted(success);
        lblGracias.setVisible(true);
        event.consume();
    }

    @FXML
    private void perroDetected(MouseEvent event) {
        lblGracias.setVisible(false);
        Dragboard db = lblPerro.startDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString(lblPerro.getText());
        db.setContent(content);

        event.consume();

    }

    @FXML
    private void perroDone(DragEvent event) {

        if (event.getTransferMode() == TransferMode.MOVE) {
            lblPerro.setText("");
        }

        event.consume();
        lblPerro.setText("Perro");
    }

    @FXML
    private void gatoDetected(MouseEvent event) {
        lblGracias.setVisible(false);
        Dragboard db = lblGato.startDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString(lblGato.getText());
        db.setContent(content);

        event.consume();
    }

    @FXML
    private void gatoDone(DragEvent event) {
        if (event.getTransferMode() == TransferMode.MOVE) {
            lblGato.setText("");
        }

        event.consume();
        lblGato.setText("Gato");

    }

    @FXML
    private void animalDetected(MouseEvent event) {
        lblGracias.setVisible(false);
        Dragboard db = lblAnimal.startDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString(lblAnimal.getText());
        db.setContent(content);

        event.consume();
    }

    @FXML
    private void animalDone(DragEvent event) {
        if (event.getTransferMode() == TransferMode.MOVE) {
            lblAnimal.setText("");
        }

        event.consume();
        lblAnimal.setText("Animal");
    }
}
