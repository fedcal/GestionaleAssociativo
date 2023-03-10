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



public class DbConnection {
    private static String DRIVER_CLASS_NAME;
    private String DBMS = "";
    private String SERVER = "";
    private String DATABASE = "";
    private int PORT = 3306;
    private String USER_ID = "";
    private String PASSWORD = "";
    private Connection conn;

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

    public Connection getConnection() {
        return this.conn;
    }

    public void closeConnection() throws SQLException {
        this.conn.close();
    }
}
