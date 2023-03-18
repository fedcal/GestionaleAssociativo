package com.gestionaleonstage.soci;

import com.gestionaleonstage.database.DbConnection;
import com.gestionaleonstage.database.DbScript;
import com.gestionaleonstage.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerAreaSoci implements Initializable {
    @FXML
    private ChoiceBox ruoliList;
    @FXML
    private DatePicker datePresentazione;
    @FXML
    private DatePicker dateNascita;
    @FXML
    private RadioButton minorenneNegativo;
    @FXML
    private RadioButton minorennePositivo;
    @FXML
    private TextField idTessera;
    @FXML
    private TextField nome;
    @FXML
    private TextField cognome;
    @FXML
    private TextField luogoNascita;
    @FXML
    private TextField cap;
    @FXML
    private TextField via;
    @FXML
    private TextField citta;
    @FXML
    private TextField provincia;
    @FXML
    private RadioButton consensoPositivo;
    @FXML
    private RadioButton consensoNegativo;
    @FXML
    private TextField cellulare;
    @FXML
    private TextField email;
    @FXML
    private void insertSocio() {
        boolean checked=false;
        if(consensoNegativo.isSelected() || consensoPositivo.isSelected()){
            checked=true;
        }
        if(idTessera.getText().isEmpty()||nome.getText().isEmpty()||cognome.getText().isEmpty()||luogoNascita.getText().isEmpty()
                ||cap.getText().isEmpty()||via.getText().isEmpty()||citta.getText().isEmpty()||provincia.getText().isEmpty()||
               cellulare.getText().isEmpty()||email.getText().isEmpty()|| !checked|| dateNascita.getEditor().getText().equals(null)||datePresentazione.getEditor().getText().equals(null) ){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Attenzione!!!\n Non hai completato tutti i campi necessari per l'aggiunta del socio");
            alert.show();

        }else {
                try{
                    DbConnection dbConnection= new DbConnection();
                    DbScript dbScript = new DbScript();
                    String consenso=this.consensoNegativo.isSelected() ? "No" : "Si";
                    String minorenne=this.minorenneNegativo.isSelected() ? "No" : "Si";
                    String nascitaDate=this.dateNascita.getEditor().getText();
                    String presentazioneDate=this.datePresentazione.getEditor().getText();
                    String presentazioneG,presentazioneM,presentazioneY,nascitaG,nascitaM,nascitaY;
                    String[] nascitaSplit=nascitaDate.split("/");
                    String[] presentazioneSplit=presentazioneDate.split("/");
                    nascitaG=nascitaSplit[0];
                    nascitaM=nascitaSplit[1];
                    nascitaY=nascitaSplit[2];

                    presentazioneG=presentazioneSplit[0];
                    presentazioneM=presentazioneSplit[1];
                    presentazioneY=presentazioneSplit[2];
                    int ruolo =0;

                    if(this.ruoliList.getValue().equals("Carica Vuota"))
                        ruolo = 1;
                    else if(this.ruoliList.getValue().equals("Segretario")) {
                        ruolo = 2;
                    }else if (this.ruoliList.getValue().equals("Vicepresidente")) {
                        ruolo = 3;
                    }else if(this.ruoliList.getValue().equals("Presidente")){
                        ruolo = 4;
                    }else if(this.ruoliList.getValue().equals("Volontario")){
                        ruolo = 5;
                    }

                    String query="insert into socio values ("+
                            this.idTessera.getText()+", '"+presentazioneY+"-"+presentazioneM+"-"+presentazioneG+"',"+"NULL"+",'"+this.cognome.getText()+"','"+this.nome.getText()+"', '"+nascitaY+"-"+nascitaM+"-"+nascitaG+"','"+this.luogoNascita.getText()+"','"+
                            this.via.getText()+"','"+this.citta.getText()+"','"+this.cap.getText()+"','"+this.cellulare.getText()+"','"+this.provincia.getText()+"','"+this.email.getText()+"',"+"NULL"+",'"+consenso+"','"+minorenne+"',"+"NULL"+",'"+ruolo+"')";
                    dbScript.triggerQuery(query,dbConnection.getConnection());
                    dbConnection.closeConnection();
                    PopUp.confermationAlert("Perfetto!!! Il socio è stato aggiunto.");
                }catch (Exception e){
                    PopUp.errorAlert("Errore!!! Non è stato possibile aggiungere il socio.");
                }
            }
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        this.consensoPositivo.setToggleGroup(toggleGroup);
        this.consensoNegativo.setToggleGroup(toggleGroup);
        ToggleGroup toggleGroup2 = new ToggleGroup();
        this.minorenneNegativo.setToggleGroup(toggleGroup2);
        this.minorennePositivo.setToggleGroup(toggleGroup2);

        //TODO chiamata db

        this.ruoliList.getItems().add(0,"Socio");
        this.ruoliList.getItems().add(1,"Carica vuota");
        this.ruoliList.getItems().add(2,"Segretario");
        this.ruoliList.getItems().add(3,"Vicepresidente");
        this.ruoliList.getItems().add(4,"Presidente");
        this.ruoliList.getItems().add(5,"Volontario");
    }

    private void clearField(){
        this.idTessera.clear();
        this.nome.clear();
        this.cognome.clear();
        this.luogoNascita.clear();
        this.via.clear();
        this.citta.clear();
        this.cap.clear();
        this.provincia.clear();
        this.cellulare.clear();
        this.email.clear();
        this.minorennePositivo.setSelected(false);
        this.minorenneNegativo.setSelected(false);
        this.consensoNegativo.setSelected(false);
        this.consensoPositivo.setSelected(false);
        this.datePresentazione.setValue(null);
        this.dateNascita.setValue(null);
    }
}
