package com.gestionaleonstage.sviluppo;

import com.gestionaleonstage.HomeApplication;
import com.gestionaleonstage.database.DbConnection;
import com.gestionaleonstage.database.DbScript;
import com.gestionaleonstage.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerSviluppo {
    @FXML
    private Button btnCreateDb;
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

    public void createDbScript() {
        DbConnection dbConnection= new DbConnection();
        DbScript.initTable(dbConnection.getConnection());
        PopUp.confermationAlert("Database creato correttamente");
    }
}
