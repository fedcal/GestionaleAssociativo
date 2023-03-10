package com.gestionaleonstage.sviluppo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DatabaseModifierController implements Initializable {
    @FXML
    private TextField databaseName;
    @FXML
    private TextField serverName;
    @FXML
    private TextField portaName;
    @FXML
    private TextField utenteName;
    @FXML
    private TextField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("./configuration/DbConfigurator.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONObject jObject = (JSONObject) obj;
            this.databaseName.setText((String) jObject.get("DATABASE"));
            this.serverName.setText((String) jObject.get("SERVER"));
            this.portaName.setText(jObject.get("PORT").toString());
            this.utenteName.setText((String) jObject.get("USER_ID"));
            this.password.setText((String) jObject.get("PASSWORD"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void salvaInfo() throws IOException {
        JSONObject dbData = new JSONObject();
        dbData.put("DRIVER_CLASS_NAME","com.mysql.cj.jdbc.Driver");
        dbData.put("DBMS","jdbc:mysql");
        dbData.put("SERVER",this.serverName.getText());
        dbData.put("DATABASE",this.databaseName.getText());
        dbData.put("PORT",Integer.valueOf(this.portaName.getText()));
        dbData.put("USER_ID",this.utenteName.getText());
        dbData.put("PASSWORD",this.password.getText());

        FileWriter file = new FileWriter("src/main/java/com/gestionaleonstage/database/DbConfigurator.json");
        file.write(dbData.toJSONString());
        file.flush();

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Dati salvati con successo");
        alert.show();

    }
}
