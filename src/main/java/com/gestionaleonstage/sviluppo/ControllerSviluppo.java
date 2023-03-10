package com.gestionaleonstage.sviluppo;

import com.gestionaleonstage.HomeApplication;
import com.gestionaleonstage.database.DbConnection;
import com.gestionaleonstage.database.DbScript;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller relativo alla sezione di sviluppo dedita alla gestione di account e impostazioni del sistema
 */
public class ControllerSviluppo {
    @FXML
    private Button btnCreateDb;
    /**
     * fx:id del pulsante relativo alla sotto sezione del database
     */
    @FXML
    private Button btnSetupDb;

    public void showDbView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("sviluppo/setupDb-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Gestionale Associativo - Database Info");
        stage.show();
    }

    public void createDbScript() throws SQLException {
        DbConnection dbConnection= new DbConnection();
        DbScript.initTable(dbConnection.getConnection());
    }
}
