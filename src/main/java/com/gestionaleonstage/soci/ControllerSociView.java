package com.gestionaleonstage.soci;

import com.gestionaleonstage.database.DbConnection;
import com.gestionaleonstage.database.Example;
import com.gestionaleonstage.database.TableData;
import com.gestionaleonstage.entity.Ruoli;
import com.gestionaleonstage.entity.Soci;
import com.gestionaleonstage.exception.EmptySetException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller della view per visualizzare tutti i soci
 */
public class ControllerSociView implements Initializable {
    /**
     * TreeSet che contiene tutto l'elenco dei soci
     */
    private final TreeSet<Soci> elencoSoci = new TreeSet<>();

    private final TreeMap<Integer,String> elencoRuoli = new TreeMap<>();
    @FXML
    private Label ruoloSocio;
    @FXML
    private ListView listaSociView;
    @FXML
    private Label tessera;
    @FXML
    private Label nome;
    @FXML
    private Label cognome;
    @FXML
    private Label dataNascita;
    @FXML
    private Label luogoNascita;
    @FXML
    private Label indirizzo;
    @FXML
    private Label citta;
    @FXML
    private Label cap;
    @FXML
    private Label provincia;
    @FXML
    private Label email;
    @FXML
    private Label cellulare;
    @FXML
    private Label consensoDati;
    @FXML
    private Label minorenne;
    @FXML
    private Label dataAnnullamento;
    @FXML
    private Label dataIscrizione;
    @FXML
    private Label note;

    @FXML
    private void infoSocio(){
        if(listaSociView.getSelectionModel().getSelectedItem()==null){
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("Seleziona un socio prima per richiederne le informazioni");
            a.show();
        }else {
            String selectedSocio = listaSociView.getSelectionModel().getSelectedItem().toString();

            String[] socioSplit = selectedSocio.split(" ");
            int idTessera = Integer.parseInt(socioSplit[0]);
            Iterator<Soci> iterator = this.elencoSoci.iterator();
            boolean trovato = false;
            while (iterator.hasNext() && !trovato) {
                Soci socio = iterator.next();
                if (socio.getTessera() == idTessera) {
                    trovato = true;
                    this.tessera.setText(Integer.toString(socio.getTessera()));
                    this.nome.setText(socio.getNome());
                    this.cognome.setText(socio.getCognome());
                    this.dataNascita.setText(socio.getNascita() == null ? " " : socio.getNascita());
                    this.luogoNascita.setText(socio.getLuogoNascita());
                    this.indirizzo.setText(socio.getVia());
                    this.citta.setText(socio.getCitta());
                    this.cap.setText(socio.getCap());
                    this.provincia.setText(socio.getProvincia());
                    this.note.setText(socio.getNote() == null ? " " : socio.getNote());
                    this.email.setText(socio.getEmail());
                    this.cellulare.setText(socio.getTelefono());
                    this.consensoDati.setText(socio.getConsenso());
                    this.minorenne.setText(socio.getMinorenne());
                    this.dataAnnullamento.setText(socio.getdataAnnullamento() == null ? "" : socio.getdataAnnullamento());
                    this.dataIscrizione.setText(socio.getDataIscrizione());
                    this.ruoloSocio.setText(this.elencoRuoli.get(socio.getRuolo()));
                }
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cap.setText("");
        this.cellulare.setText("");
        this.dataIscrizione.setText("");
        this.consensoDati.setText("");
        this.tessera.setText("");
        this.nome.setText("");
        this.cognome.setText("");
        this.dataNascita.setText("");
        this.luogoNascita.setText("");
        this.indirizzo.setText("");
        this.citta.setText("");
        this.provincia.setText("");
        this.email.setText("");
        this.minorenne.setText("");
        this.dataAnnullamento.setText("");
        this.note.setText("");
        this.ruoloSocio.setText("");
        uploadRuoli();
        System.out.println(this.elencoRuoli.toString());
        uploadElencoSoci();
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
            listaSociView.getItems().add(socio);
        }
        try {
            dbConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void uploadRuoli() {
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
                socio.setRuolo(Integer.parseInt(tran.get(17).toString()));
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
