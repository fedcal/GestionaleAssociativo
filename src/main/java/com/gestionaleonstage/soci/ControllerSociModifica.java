package com.gestionaleonstage.soci;

import com.gestionaleonstage.database.DbConnection;
import com.gestionaleonstage.database.DbScript;
import com.gestionaleonstage.database.Example;
import com.gestionaleonstage.database.TableData;
import com.gestionaleonstage.entity.Soci;
import com.gestionaleonstage.exception.EmptySetException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class ControllerSociModifica implements Initializable {
    private final TreeSet<Soci> elencoSoci=new TreeSet<>();
    private final HashMap<Integer,String> elencoRuoli = new HashMap<>();
    @FXML
    private ChoiceBox ruolo;
    @FXML
    private Button salva;
    @FXML
    private DatePicker dataApprovazione;
    @FXML
    private DatePicker dataRevoca;
    @FXML
    private ListView listSoci;
    @FXML
    private Button infoSocio;
    @FXML
    private TextField idTessera;
    @FXML
    private TextField nome;
    @FXML
    private TextField cognome;
    @FXML
    private TextField luogoNascita;
    @FXML
    private DatePicker dateNascita;
    @FXML
    private TextField via;
    @FXML
    private TextField cap;
    @FXML
    private TextField citta;
    @FXML
    private TextField provincia;
    @FXML
    private TextField cellulare;
    @FXML
    private TextField email;
    @FXML
    private RadioButton consensoPositivo;
    @FXML
    private RadioButton consensoNegativo;
    @FXML
    private RadioButton minorennePositivo;
    @FXML
    private RadioButton minorenneNegativo;
    @FXML
    private DatePicker datePresentazione;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        this.consensoPositivo.setToggleGroup(toggleGroup);
        this.consensoNegativo.setToggleGroup(toggleGroup);
        ToggleGroup toggleGroup2 = new ToggleGroup();
        this.minorenneNegativo.setToggleGroup(toggleGroup2);
        this.minorennePositivo.setToggleGroup(toggleGroup2);

        disableModifyArea();
        uploadElencoSoci();
        uploadViewSoci();
    }

    public void salvaInfoSocio() {
        if(this.dataApprovazione.getValue()==null){
            popUpErrorDate();
        }else{
            try {
                executeUpdateQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void executeUpdateQuery() throws SQLException {
        DbConnection dbConnection=new DbConnection();
        DbScript dbScript= new DbScript();
        String query="UPDATE socio SET ";
        query+="luogo_nascita = '"+this.luogoNascita.getText()+"', "+"nome = '"+this.nome.getText()+"',"+"cognome = '"+this.cognome.getText()+"',";
        query+="telefono = '"+this.cellulare.getText()+"', ";
        query+="email = '"+this.email.getText()+"',";

        if(this.minorennePositivo.isSelected()){
            query+="minorenne = 'Si',";
        }else{
            query+="minorenne = 'No',";
        }

        if(this.consensoPositivo.isSelected()){
            query+="consenso = 'Si',";
        }else{
            query+="consenso = 'No',";
        }

        query+="via = '"+this.via.getText()+"',";
        query+="citta = '"+this.citta.getText()+"',";
        query+="cap = '"+this.cap.getText()+"',";
        query+="provincia = '"+this.provincia.getText()+"',";
        query+="data_iscrizione = '"+this.datePresentazione.getValue().toString()+"',";
        query+="data_approvazione = '"+this.dataApprovazione.getValue().toString()+"',";

        if(this.dataRevoca.getValue()!=null)
            query+=", data_annullamento = '"+this.dataRevoca.getValue().toString()+"'";

        String ruoloSelected = this.ruolo.getSelectionModel().getSelectedItem().toString();
        int codeRuolo=0;
        for( Map.Entry<Integer,String> set : this.elencoRuoli.entrySet()){
            if(set.getValue().equals(ruoloSelected)){
                codeRuolo =  set.getKey();
            }
        }
        query+="fk_ruolo = "+String.valueOf(codeRuolo)+" ";


        query+="WHERE id_tessera = "+this.idTessera.getText()+";";

        boolean resultQuery=dbScript.triggerQuery(query,dbConnection.getConnection());
        try {
            dbConnection.closeConnection();
        }catch (Exception e){

        }
    }

    public void displayInfo() {

        if(listSoci.getSelectionModel().getSelectedItem()==null){
            popUpAllert();
        }else{
            popUpAllert();
            setInfoTextField(listSoci.getSelectionModel().getSelectedItem().toString());
            ableModifyArea();
        }
    }
    private void popUpErrorDate() {
        Alert a=new Alert(Alert.AlertType.ERROR);
        a.setContentText("Prima di salvare, imposta la data di approvazione della domanda del socio.");
        a.getDialogPane().setMinWidth(500);
        a.getDialogPane().setMinHeight(200);
        a.show();
    }

    private void setInfoTextField(String selectedSocio) {

        String[] split = selectedSocio.split(" ");

        Iterator<Soci> setIterator = elencoSoci.iterator();
        boolean trovato=false;
        while(setIterator.hasNext()&&trovato==false){
            Soci temp = (Soci) setIterator.next();
            if(temp.getTessera()==Integer.parseInt(split[0])){
                trovato=true;
                uploadInfoSoci(temp);
            }
        }
    }

    private void uploadInfoSoci(Soci temp) {
        this.idTessera.setText(String.valueOf( temp.getTessera()));
        this.nome.setText(temp.getNome());
        this.cognome.setText(temp.getCognome());
        this.luogoNascita.setText(temp.getLuogoNascita());
        this.dateNascita.setValue(LocalDate.parse(temp.getNascita(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        this.via.setText(temp.getVia());
        this.citta.setText(temp.getCitta());
        this.cap.setText(temp.getCap());
        this.provincia.setText(temp.getProvincia());
        this.cellulare.setText(temp.getTelefono());
        this.email.setText(temp.getEmail());
        this.datePresentazione.setValue(LocalDate.parse(temp.getDataIscrizione(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        if(temp.getMinorenne().equals("Si")){
            this.minorennePositivo.setSelected(true);
        }else{
            this.minorenneNegativo.setSelected(true);
        }
        if(temp.getConsenso().equals("Si")){
            this.consensoPositivo.setSelected(true);
        }else{
            this.consensoNegativo.setSelected(true);
        }
        if(temp.getdataAnnullamento()!=null){
            this.dataRevoca.setPromptText(temp.getdataAnnullamento());
        }
        if(temp.getDataApprovazione()!=null){
            this.dataApprovazione.setPromptText(temp.getDataApprovazione());
        }
        DbConnection dbConnection=new DbConnection();
        TableData tableData=new TableData(dbConnection);
        LinkedList<Example> trans;
        try{
            trans=tableData.getTransazioni("select * from ruolo","ruolo");
            for(Example tran : trans){
                this.elencoRuoli.put(Integer.valueOf(tran.get(0).toString()),tran.get(1).toString());
            }
        }catch (Exception e){

        }

        Set<Integer> chiaviRuoli = this.elencoRuoli.keySet();

        Iterator<Integer> it = chiaviRuoli.iterator();
        while (it.hasNext()){
            int index = it.next();
            this.ruolo.getItems().add(this.elencoRuoli.get(index));
            if(index == temp.getRuolo()){
                this.ruolo.setValue(this.elencoRuoli.get(index));
            }
        }
    }

    private void popUpAllert() {
        Alert a=new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Nella sezione destra sono apparse le informazioni relative al socio selezionato. Modifica solo i campi interessati, il resto delle informazioni rimarrà inalterato");
        a.getDialogPane().setMinWidth(500);
        a.getDialogPane().setMinHeight(200);
        a.show();
    }

    private void ableModifyArea() {
        this.salva.setDisable(false);
        this.minorennePositivo.setDisable(false);
        this.consensoNegativo.setDisable(false);
        this.consensoPositivo.setDisable(false);
        this.minorenneNegativo.setDisable(false);
        this.datePresentazione.setDisable(false);
        this.email.setDisable(false);
        this.cellulare.setDisable(false);

        this.nome.setDisable(false);
        this.cognome.setDisable(false);
        this.luogoNascita.setDisable(false);
        this.dateNascita.setDisable(false);
        this.via.setDisable(false);
        this.cap.setDisable(false);
        this.citta.setDisable(false);
        this.provincia.setDisable(false);
        this.dataApprovazione.setDisable(false);
        this.dataRevoca.setDisable(false);
        this.ruolo.setDisable(false);
    }

    private void disableModifyArea() {
        this.salva.setDisable(true);
        this.minorennePositivo.setDisable(true);
        this.consensoNegativo.setDisable(true);
        this.consensoPositivo.setDisable(true);
        this.minorenneNegativo.setDisable(true);
        this.datePresentazione.setDisable(true);
        this.email.setDisable(true);
        this.cellulare.setDisable(true);
        this.idTessera.setDisable(true);
        this.nome.setDisable(true);
        this.cognome.setDisable(true);
        this.luogoNascita.setDisable(true);
        this.dateNascita.setDisable(true);
        this.via.setDisable(true);
        this.cap.setDisable(true);
        this.citta.setDisable(true);
        this.provincia.setDisable(true);
        this.dataApprovazione.setDisable(true);
        this.dataRevoca.setDisable(true);
        this.ruolo.setDisable(true);
    }

    private void uploadViewSoci() {
        DbConnection dbConnection=new DbConnection();
        LinkedList<Example> trans;
        TableData tableData=new TableData(dbConnection);
        TreeSet<String> setSoci=new TreeSet<>();
        try {
            trans=tableData.getTransazioni("select * from socio","socio");

            for (Example tran : trans) {

                String socioElab = "";
                socioElab += tran.get(0).toString() + " ";
                socioElab += tran.get(4).toString() + " ";
                socioElab += tran.get(3).toString();
                setSoci.add(socioElab);
            }
        } catch (SQLException | EmptySetException e) {
            throw new RuntimeException(e);
        }
        Iterator<String> setIterator= setSoci.iterator();
        while (setIterator.hasNext()){
            String socio= setIterator.next().toString();
            this.listSoci.getItems().add(socio);
        }
        try {
            dbConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void uploadElencoSoci(){
        DbConnection dbConnection=new DbConnection();
        TableData tableData=new TableData(dbConnection);
        LinkedList<Example> trans;
        try{
            trans=tableData.getTransazioni("select * from socio","socio");

            for (Example tran : trans) {
                Soci socio = new Soci();
                socio.setTessera(Integer.parseInt(tran.get(0).toString()));
                socio.setDataIscrizione(tran.get(1).toString());

                if (tran.get(2) != null)
                    socio.setDataApprovazione(tran.get(2).toString());
                else
                    socio.setDataApprovazione("");

                socio.setCognome(tran.get(3).toString());
                socio.setNome(tran.get(4).toString());
                socio.setNascita(tran.get(5).toString());
                socio.setLuogoNascita(tran.get(6).toString());
                socio.setVia(tran.get(7).toString());
                socio.setCitta(tran.get(8).toString());
                socio.setCap(tran.get(9).toString());
                socio.setTelefono(tran.get(10).toString());
                socio.setProvincia(tran.get(11).toString());
                socio.setEmail(tran.get(12).toString());
                if (tran.get(13) != null)
                    socio.setdataAnnullamento(tran.get(13).toString());
                else
                    socio.setdataAnnullamento("");
                socio.setConsenso(tran.get(14).toString());
                socio.setMinorenne(tran.get(15).toString());
                if (tran.get(16) != null)
                    socio.setNote(tran.get(16).toString());
                else
                    socio.setNote("");
                this.elencoSoci.add(socio);
            }
            dbConnection.closeConnection();
        }catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Attenzione!!!\n Non è stato possibile recuperare la lsita dei soci");
            alert.show();
        }
    }
}