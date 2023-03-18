package com.gestionaleonstage.eventi;

import com.gestionaleonstage.HomeApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerTipologiaEventi {
    public Button aggiungiFormat;
    public Button modificaFormat;
    public Button infoFormat;

    public void addFormat() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("eventi/tipologiaEventi/aggiungiFormat.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Gestionale Associativo - Gestione Format Eventi");
        stage.show();
    }
}
