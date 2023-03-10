package com.gestionaleonstage;

import com.gestionaleonstage.database.DbConnection;
import com.gestionaleonstage.database.DbScript;
import com.gestionaleonstage.util.FolderTree;

import java.sql.SQLException;

/**
 * Classe di supporto per la creazione del jar. Istanzia la connessione al DB e avvia l'appicazione
 */
public class Launcher {
    /**
     * Main della classe all'interno del quale evengono effettuate tutte le operazioni preliminari per avviiare l'applicazione
     * @param args argomenti dell'applicazione
     */
    public static void main(String[] args) throws SQLException {
        FolderTree folderTree= new FolderTree();
        HomeApplication.main(args);

    }
}