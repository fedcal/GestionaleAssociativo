package com.gestionaleonstage.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DbScript {

    public DbScript(){}

    public static final void initTable(Connection db) {
        int rs;
        try (Statement statement = db.createStatement()) {
            try {
                //RUOLI
                String queryRuoli = "CREATE TABLE IF NOT EXISTS ruolo(idRuolo int PRIMARY KEY AUTO_INCREMENT, titolo varchar(20))";
                rs = statement.executeUpdate(queryRuoli);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella dei ruoli.\n");
            }

            try {
                //CARICA RUOLI
                String queryLoadRuoli = "INSERT INTO ruolo VALUES (1,\'Socio\'),(2,\'Carica vuota\'),(3,\'Segretario\'),(4,\'Vice Presidente\'),(5,\'Presidente\'),(6,\'Volontario\')";
                rs = statement.executeUpdate(queryLoadRuoli);
            }catch (Exception e){
                System.out.println("Impossibile caricare i vari ruoli all'interno della tabella relativa.\n");
            }

            try {
                //SOCI
                String querySoci = "CREATE TABLE IF NOT EXISTS socio(id_tessera int PRIMARY KEY, data_iscrizione varchar(50) NOT NULL, data_approvazione varchar(50)" +
                        ", cognome varchar(50) NOT NULL, nome varchar(50) NOT NULL, nascita varchar(50) NOT NULL, luogo_nascita varchar(50), via varchar(150) NOT NULL," +
                        "citta varchar(50) NOT NULL,cap varchar(10), telefono varchar(15) NOT NULL, provincia varchar(50) NOT NULL, email varchar(200) NOT NULL," +
                        "data_annullamento varchar(50), consenso varchar(5), minorenne varchar(5),note varchar(500), fk_ruolo int NOT NULL, FOREIGN KEY (fk_ruolo) REFERENCES ruolo(idRuolo))";
                rs = statement.executeUpdate(querySoci);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella dei soci.\n");
            }

            try {
                //FONDO
                String queryFondo = "CREATE TABLE IF NOT EXISTS fondo(id_fondo int PRIMARY KEY AUTO_INCREMENT, nome varchar(50), descrizione varchar(150))";
                rs = statement.executeUpdate(queryFondo);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella dei fondi.\n");
            }

            try {
                //TRANSAZIONI
                String queryTransazione = "CREATE TABLE IF NOT EXISTS transazione_fondo(id_transazione int PRIMARY KEY AUTO_INCREMENT, data varchar(20), descrizione varchar(250), tipologia varchar(10), flusso double(10,2), " +
                        "id_fondo_partenza int NOT NULL, id_fondo_arrivo int NOT NULL, FOREIGN KEY (id_fondo_partenza) REFERENCES fondo(id_fondo), FOREIGN KEY (id_fondo_arrivo) REFERENCES fondo(id_fondo))";
                rs = statement.executeUpdate(queryTransazione);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella delle transazioni.\n");
            }

            try {
                //TIPOLOGIA EVENTO
                String queryTipologiaEvento = "CREATE TABLE IF NOT EXISTS tipologia_evento(id_tipologia int PRIMARY KEY AUTO_INCREMENT, nome_tipologia varchar(150), descrizione varchar(500))";
                rs = statement.executeUpdate(queryTipologiaEvento);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella dei format degli eventi.\n");
            }

            try {
                //CARICA TIPOLOGIA EVENTO
                String queryLoadRuoli = "INSERT INTO tipologia_evento VALUES (1,\'Evento Singolo\',\'Evento che non si ripete con una certa freqienza \'),(2,\'2 settimane\', \'Evento che si ripete ogni 2 settimane\'),(3,\'Mensilmente\', \'Evento che si ripete una volta al mese\'),(4,\'Annuale\',\'Evento che si ripete annualmente\')";
                rs = statement.executeUpdate(queryLoadRuoli);
            }catch (Exception e){
                System.out.println("Impossibile caricare le tipologia di evento.\n");
            }

            try {
                //FORMAT EVENTO
                String queryFormatEvento = "CREATE TABLE IF NOT EXISTS format_evento(id_format int PRIMARY KEY AUTO_INCREMENT, nome varchar(150), descrizione varchar(500), fk_tipologia_evento int, FOREIGN KEY (fk_tipologia_evento) REFERENCES tipologia_evento(id_tipologia))";
                rs = statement.executeUpdate(queryFormatEvento);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella dei format degli eventi.\n");
            }

            try {
                //EVENTO CREATO
                String queryEventoCreato = "CREATE TABLE IF NOT EXISTS evento_creato(id_creato int PRIMARY KEY AUTO_INCREMENT, fk_format int, data varchar(20), FOREIGN KEY (fk_format) REFERENCES format_evento(id_format))";
                rs = statement.executeUpdate(queryEventoCreato);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella \' evento_creato \'.\n");
            }

            try {
                //GRUPPO
                String queryGruppo = "CREATE TABLE IF NOT EXISTS gruppo(id_gruppo int PRIMARY KEY AUTO_INCREMENT, nome varchar(25), descrizione varchar(50))";
                rs = statement.executeUpdate(queryGruppo);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella dei gruppi.\n");
            }

            try {
                //PARTECIPAZIONE
                String queryPartecipazione = "CREATE TABLE IF NOT EXISTS partecipazione(id_partecipazione int PRIMARY KEY AUTO_INCREMENT, fk_creato int, fk_socio int, fk_gruppo int, FOREIGN KEY (fk_creato) REFERENCES evento_creato(id_creato), " +
                        "FOREIGN KEY (fk_socio) REFERENCES socio(id_tessera), FOREIGN KEY (fk_gruppo) REFERENCES gruppo(id_gruppo))";
                rs = statement.executeUpdate(queryPartecipazione);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella delle partecipazioni.\n");
            }

            try {
                //CLIENTE
                String queryCliente = "CREATE TABLE IF NOT EXISTS cliente(id_cliente int PRIMARY KEY AUTO_INCREMENT, nome varchar(25), cognome varchar(25), cellulare varchar(15), codice_fiscale varchar(30), partita_iva varchar(25))";
                rs = statement.executeUpdate(queryCliente);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella dei clienti.\n");
            }

            try {
                //FATTURE EMESSE
                String queryFattureEmesse = " CREATE TABLE IF NOT EXISTS fattura_emessa(id_fattura int PRIMARY KEY AUTO_INCREMENT, data varchar(20), totale double(10,2), fk_cliente int, FOREIGN KEY (fk_cliente) REFERENCES cliente(id_cliente))";
                rs = statement.executeUpdate(queryFattureEmesse);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella delle fatture emesse.\n");
            }

            try {
                //SCONTRINO
                String queryScontrino = "CREATE TABLE IF NOT EXISTS scontrino_emesso(id_scontrino int PRIMARY KEY AUTO_INCREMENT, data varchar(20), totale double(10,2))";
                rs = statement.executeUpdate(queryScontrino);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella delgli scontrini emessi.\n");
            }

            try {
                //MERCE VENDUTA
                String queryMerceVenduta = "CREATE TABLE IF NOT EXISTS merce_venduta(id_merce int PRIMARY KEY AUTO_INCREMENT, quantita double(10,2), nome varchar(25), descrizione varchar(100), prezzo_unitario double(10,2))";
                rs = statement.executeUpdate(queryMerceVenduta);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella della merce venduta.\n");
            }

            try {
                //REFERENZE MERCE VENDUTA
                String queryRefMerceVenduta = "CREATE TABLE IF NOT EXISTS ref_merce_venduta(id_transazione int PRIMARY KEY AUTO_INCREMENT, fk_scontrino int, fk_fattura int, fk_merce int, FOREIGN KEY (fk_scontrino) REFERENCES scontrino_emesso(id_scontrino)," +
                        "FOREIGN KEY (fk_fattura) REFERENCES fattura_emessa(id_fattura), FOREIGN KEY (fk_merce) REFERENCES merce_venduta(id_merce))";
                rs = statement.executeUpdate(queryRefMerceVenduta);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella delle referenze merce venduta.\n");
            }

            try {
                //EVENTO VENDITE
                String queryEventoVendite = "CREATE TABLE IF NOT EXISTS evento_vendite(id_transazione int PRIMARY KEY AUTO_INCREMENT, fk_ref_merce_venduta int, fk_evento_creato int, FOREIGN KEY (fk_evento_creato) REFERENCES evento_creato(id_creato), " +
                        "FOREIGN KEY (fk_ref_merce_venduta) REFERENCES ref_merce_venduta(id_transazione))";
                rs = statement.executeUpdate(queryEventoVendite);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella delle vendite.\n");
            }

            try {
                //FORNITORE
                String queryFornitore = "CREATE TABLE IF NOT EXISTS fornitore(id_fornitore int PRIMARY KEY AUTO_INCREMENT, nome varchar(20),cognome varchar(20),indirizzo varchar(150),cellulare varchar(18))";
                rs = statement.executeUpdate(queryFornitore);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella dei fornitori.\n");
            }

            try {
                //FATTURE RICEVUTE
                String queryFattureRicevute = "CREATE TABLE IF NOT EXISTS fattura_ricevuta(id_fattura int PRIMARY KEY AUTO_INCREMENT, data varchar(20), totale double(10,2), fk_fornitore int, FOREIGN KEY (fk_fornitore) REFERENCES fornitore(id_fornitore))";
                rs = statement.executeUpdate(queryFattureRicevute);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella delle fatture ricevute.\n");
            }


            try {
                //SCONTRINO RICEVUTO
                String queryScontrinoRicevuto = "CREATE TABLE IF NOT EXISTS scontrino_ricevuto(id_scontrino int PRIMARY KEY AUTO_INCREMENT, data varchar(20), totale double(10,2))";
                rs = statement.executeUpdate(queryScontrinoRicevuto);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella dello scontrino ricevuto.\n");
            }

            try {
                //MERCE ACQUISTATA
                String queryMerceAcquistata = "CREATE TABLE IF NOT EXISTS merce_acquistata(id_merce int PRIMARY KEY AUTO_INCREMENT, quantita double(10,2), nome varchar(25), descrizione varchar(200), prezzo_unitario double(10,2))";
                rs = statement.executeUpdate(queryMerceAcquistata);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella della merce acquistata.\n");
            }

            try {
                //REFERENZA MERCE ACQUISTATA
                String queryRefMerceAcquistata = "CREATE TABLE IF NOT EXISTS ref_merce_acquistata(id_transazione int PRIMARY KEY AUTO_INCREMENT, fk_scontrino int, fk_fattura int, fk_merce int, FOREIGN KEY (fk_scontrino) REFERENCES scontrino_ricevuto(id_scontrino)," +
                        "FOREIGN KEY (fk_fattura) REFERENCES fattura_ricevuta(id_fattura), FOREIGN KEY (fk_merce) REFERENCES merce_acquistata(id_merce))";
                rs = statement.executeUpdate(queryRefMerceAcquistata);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella della REFERENZA MERCE ACQUISTATA.\n");
            }

            try {
                //MERCE AQUISTATA EVENTO
                String queryRefMerceAcquistata = "CREATE TABLE IF NOT EXISTS merce_acquistata_evento(id_transazione int PRIMARY KEY AUTO_INCREMENT, fk_merce_acquistata int, fk_evento int, FOREIGN KEY (fk_merce_acquistata) REFERENCES ref_merce_acquistata(id_transazione), FOREIGN KEY (fk_evento) REFERENCES evento_creato(id_creato))";
                rs = statement.executeUpdate(queryRefMerceAcquistata);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella della merce acquistata.\n");
            }

            try {
                //MAGAZZINO
                String queryMagazzino = "CREATE TABLE IF NOT EXISTS magazzino(id_merce int PRIMARY KEY AUTO_INCREMENT, titolo varchar(25), descrizione varchar(200), quantita double(10,2))";
                rs = statement.executeUpdate(queryMagazzino);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella del magazzino.\n");
            }

            try {
                //ACQUISTO MAGAZZINO
                String queryAcquistoMagazzino = "CREATE TABLE IF NOT EXISTS acquisto_magazzino(id_acquisto int PRIMARY KEY AUTO_INCREMENT, fk_merce int, fk_transazione int, FOREIGN KEY (fk_merce) REFERENCES magazzino(id_merce), " +
                        "FOREIGN KEY (fk_transazione) REFERENCES ref_merce_acquistata(id_transazione))";
                rs = statement.executeUpdate(queryAcquistoMagazzino);
            }catch (Exception e){
                System.out.println("Impossibile inizializzare la tabella della relazione acquisto magazzino.\n");
            }

        }catch (Exception e){
            System.out.println("Impossibile collegarsi al database per l'inizializzazione delle tabelle\n");
        }
    }

    public static boolean triggerQuery(String query, Connection db) throws SQLException {
        boolean queryDone=false;
        Statement statement=db.createStatement();
        int rs = statement.executeUpdate(query);
        if(rs==1){
            return queryDone=true;
        }else{
            return queryDone;
        }
    }
}
