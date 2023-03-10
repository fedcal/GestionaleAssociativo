package com.gestionaleonstage.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Classe di supporto per la connessione al database
 */
public class DbConnection {
    /** Driver esterno per la gestione del database*/
    private static String DRIVER_CLASS_NAME;
    /** Nome del DBMS**/
    private String DBMS = "";
    /**Contiene l'identificativo del server su cui risiede la base di dati*/
    private String SERVER = "";
    /**Contiene il nome della base di dati*/
    private String DATABASE = "";
    /**La porta su cui il DBMS MySQL accetta le connessioni*/
    private int PORT = 3306;
    /**Contiene il nome dell'utente per l'accesso alla base di dati*/
    private String USER_ID = "";
    /**Contiene la password di autenticazione per l'utente identificato da USER_ID*/
    private String PASSWORD = "";
    /**Connettore al database*/
    private Connection conn;

    /**
     * Costruttore di classe che istanzia la connessione al database. Può generare un'eccezione di tip SQL
     * se non riesce a connettersi o di altro tipo se non riesce a trovare il driver o non ha i permessi per accedere
     */
    public DbConnection(){

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("./configuration/DbConfigurator.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONObject jObject = (JSONObject) obj;
            this.DRIVER_CLASS_NAME = (String) jObject.get("DRIVER_CLASS_NAME");
            this.DBMS = (String) jObject.get("DBMS");
            this.SERVER = (String) jObject.get("SERVER");
            this.DATABASE = (String) jObject.get("DATABASE");
            this.PORT =  Integer.valueOf(jObject.get("PORT").toString());
            this.USER_ID = (String) jObject.get("USER_ID");
            this.PASSWORD = (String) jObject.get("PASSWORD");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try{
            Class.forName(DRIVER_CLASS_NAME).newInstance();
            String connectionString = this.DBMS + "://" + this.SERVER + ":" + this.PORT + "/" + this.DATABASE
                    + "?user=" + this.USER_ID + "&password=" + this.PASSWORD + "&serverTimezone=UTC";
            this.conn = DriverManager.getConnection(connectionString);

        }catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo per ottenere la connessione al DB
     * @return Connection
     */
    public Connection getConnection() {
        return this.conn;
    }

    /**
     * Metodo per la chiusura della connessione al database e del rilascio delle risorse
     * @throws SQLException Il metodo genera questa eccezione nel caso in cui non riesce a lanciare l'applicazione
     */
    public void closeConnection() throws SQLException {
        this.conn.close();
    }
}
