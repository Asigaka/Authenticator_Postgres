package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpLogin;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private Button signUpEnter;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpLocation;

    @FXML
    private CheckBox signUpMale;

    @FXML
    private CheckBox signUpFemale;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        signUpEnter.setOnAction(event -> {
            signUpNewUser();
        });

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/sample.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }


    private void signUpNewUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstName = signUpName.getText();
        String lastName = signUpLastName.getText();
        String userName = signUpLogin.getText();
        String password = signUpPassword.getText();
        String location = signUpLocation.getText();
        String gender = "";
        if (signUpMale.isSelected()) {
            gender = "Мужской";
        } else {
            gender = "Женский";
        }

        User user = new User(firstName, lastName, userName, password, location, gender);

        databaseHandler.signUpUser(user);
    }
}