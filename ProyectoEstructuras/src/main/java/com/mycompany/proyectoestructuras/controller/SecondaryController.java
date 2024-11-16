package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}