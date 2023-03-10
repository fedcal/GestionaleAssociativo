package com.gestionaleonstage.soci;


import com.gestionaleonstage.HomeApplication;
import com.gestionaleonstage.database.DbConnection;
import com.gestionaleonstage.database.Example;
import com.gestionaleonstage.database.TableData;
import com.gestionaleonstage.entity.Soci;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Controller Soci
 */
public class ControllerSoci {
      /**
     * Funzione per aprire la view relativa all'aggiunta di un socio
     * @throws IOException Exception generata in caso non viene trovato il file FXML relativo
     */
    @FXML
    private void addSoci() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("sociArea/areaSoci-add.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Gestionale Associativo - Aggiungi soci");
        stage.show();
    }

    /**
     * Funzione per aprire la view relativa alla visualizzazione di un socio
     * @throws IOException Exception generata in caso non viene trovato il file FXML relativo
     */
    @FXML
    private void visualizzaSoci() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("sociArea/areaSoci-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Gestionale Associativo - Visualizza soci");
        stage.show();
    }

    /**
     * Metodo per visualizzare la vista per modificare i soci
     */
    public void modificaSoci( ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("sociArea/areaSoci-modify.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Gestionale Associativo - Modifica soci");
        stage.show();
    }

}
