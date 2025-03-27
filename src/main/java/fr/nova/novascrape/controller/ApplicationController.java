package fr.nova.novascrape.controller;

import fr.nova.novascrape.view.LogoContainer;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private MFXTextField textField;

    @FXML
    private MFXButton button;

    @FXML
    private StackPane logoContainer;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void handleButtonClick() {
        System.out.println("Nom saisie: " + textField.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new LogoContainer(logoContainer, "images/nova-scrape-logo.png").setLogoContainer();
    }
}
