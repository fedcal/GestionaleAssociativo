package com.gestionaleonstage;

import com.gestionaleonstage.util.FolderTree;

import java.sql.SQLException;


public class Launcher {

    public static void main(String[] args) throws SQLException {
        FolderTree folderTree= new FolderTree();
        HomeApplication.main(args);

    }
}
