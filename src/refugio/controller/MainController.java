package refugio.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jairo
 */
public class MainController implements Initializable {

    @FXML
    private FontAwesomeIconView icnClose;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleClose(MouseEvent event) {
        if (event.getSource() == icnClose) {
            System.exit(0);
        }

    }

}
