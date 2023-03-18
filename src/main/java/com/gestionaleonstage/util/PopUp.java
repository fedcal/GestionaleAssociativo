package com.gestionaleonstage.util;

import javafx.scene.control.Alert;

public class PopUp {
    private static Alert alert;

    public static void infoAlert(String infoMessage){
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.getDialogPane().setMinWidth(500);
        alert.getDialogPane().setMinHeight(200);
        alert.show();
    }
    public static void errorAlert(String errorMessage){
        alert=new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorMessage);
        alert.getDialogPane().setMinWidth(500);
        alert.getDialogPane().setMinHeight(200);
        alert.show();
    }

    public static void confermationAlert(String confermationMessage){
        alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(confermationMessage);
        alert.getDialogPane().setMinWidth(500);
        alert.getDialogPane().setMinHeight(200);
        alert.show();
    }


}
