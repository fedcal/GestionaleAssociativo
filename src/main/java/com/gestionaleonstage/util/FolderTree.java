package com.gestionaleonstage.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertFalse;

public class FolderTree {
    public FolderTree(){
        //CREAZIONE LOG FOLDER AND INIT FILE
        File fileWithRelativePath = new File( "logApplication");
        fileWithRelativePath.mkdir();

        //CONFIGURATION FOLDER
        fileWithRelativePath = new File( "configuration");
        fileWithRelativePath.mkdir();

        //DB CONFIGURATOR FILE
        JSONObject dbConfig = new JSONObject();
        dbConfig.put("DRIVER_CLASS_NAME","com.mysql.cj.jdbc.Driver");
        dbConfig.put("DATABASE","associazione");
        dbConfig.put("PORT",3306);
        dbConfig.put("PASSWORD","root");
        dbConfig.put("SERVER","localhost");
        dbConfig.put("USER_ID","root");
        dbConfig.put("DBMS","jdbc:mysql");


        try {
            FileWriter file = new FileWriter("./configuration/DbConfigurator.json");
            file.write(dbConfig.toJSONString());
            file.flush();

        } catch (IOException e) {
            System.out.println("impossibile creare file dbconfiguration");
        }



    }
}
