package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.AdminDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Admin;
import static javafx.scene.paint.Color.RED;


import java.io.IOException;

public class LoginFormController {


    public AnchorPane LoginContext;
    public JFXTextField txtUsername;
    public JFXPasswordField pwdPassword;
    public AnchorPane tempParkingContext;


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {

        boolean bool = true;

        for (Admin data : AdminDatabase.adminTable) {
            if (data.getUsername().equals(txtUsername.getText()) && data.getPassword().equals(pwdPassword.getText())) {
                bool = false;
                Stage stage = (Stage) tempParkingContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/InParkingForm.fxml"))));
                stage.centerOnScreen();

                Stage stage1 = (Stage) txtUsername.getScene().getWindow();
                stage1.close();
            }
        }
        if (bool) {

            txtUsername.setUnFocusColor(RED);
            pwdPassword.setUnFocusColor(RED);

            txtUsername.setFocusColor(RED);
            pwdPassword.setFocusColor(RED);

        }


    }
    public void getContext(AnchorPane HomeContext) {
        tempParkingContext = HomeContext;
    }


    public void btnCancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        stage.close();

    }
}
