package com.gestionaleonstage.eventi;

import com.gestionaleonstage.database.DbConnection;
import com.gestionaleonstage.database.DbScript;
import com.gestionaleonstage.database.Example;
import com.gestionaleonstage.database.TableData;
import com.gestionaleonstage.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ControllerAggiungiTipologia implements Initializable {
    @FXML
    private Button salvaFormat;

    @FXML
    private TextField nomeEvento;

    @FXML
    private TextArea descrizioneEvento;
    @FXML
    private ChoiceBox tipologiaEvento;
    @FXML
    private Button aggiungiTipologia;

    public void aggiungiFormat() throws Exception {
        String nomeFormatEvento = this.nomeEvento.getText();
        int tipologiaEventoSelezionata = this.tipologiaEvento.getSelectionModel().getSelectedIndex() + 1;
        String descrizioneEventoFormat = this.descrizioneEvento.getText();

        if (this.tipologiaEvento.getSelectionModel().isEmpty()){
            PopUp.errorAlert("Selezionare la tipologia dell'evento");
            clearField();
            throw new RuntimeException("");
        }

        String query = " INSERT INTO format_evento VALUES ( 0, \"" + nomeFormatEvento + "\", \"" + descrizioneEventoFormat + "\"," + tipologiaEventoSelezionata + ")";
        DbConnection connection = new DbConnection();
        DbScript.triggerQuery(query, connection.getConnection());
        PopUp.confermationAlert("Format aggiunto correttamente");
        clearField();
    }
    private void clearField(){
        this.descrizioneEvento.setText("");
        this.nomeEvento.setText("");
        this.tipologiaEvento.getSelectionModel().clearSelection();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DbConnection connection = new DbConnection();
        TableData tableData = new TableData(connection);
        try{
            LinkedList<Example> trans;
            trans = tableData.getTransazioni("select * from tipologia_evento","tipologia_evento");

            for(Example data : trans){
                this.tipologiaEvento.getItems().add(data.get(1).toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
