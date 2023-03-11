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

public class ControllerEventi {
    private Button btnAvviaBloccaEvento;
    private Button btnTipologiaEvento;
    private Button btnVisualizzaEventi;
    private Button btnModificaEventi;

    public void viewTipologia() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("eventi/tipologiaEventi/homeGestioneTipologiaEventi.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Gestionale Associativo - Gestione Tipologia Eventi");
        stage.show();
    }
}
