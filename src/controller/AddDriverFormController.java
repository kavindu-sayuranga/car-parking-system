package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class AddDriverFormController {
    public TextField txtDriverName;
    public TextField txtNIC;
    public TextField txtDLicense;
    public TextField txtAddress;
    public TextField txtContactNo;

    public void btnAddDriver(ActionEvent actionEvent) {

        if (!txtDriverName.getText().equals("") && !txtNIC.getText().equals("") && !txtDLicense.getText().equals("") && !txtAddress.getText().equals("") && !txtContactNo.getText().equals("")){
            txtDriverName.setText("");
            txtNIC.setText("");
            txtDLicense.setText("");
            txtAddress.setText("");
            txtContactNo.setText("");

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Driver Successfully Added", ButtonType.OK);
            alert.setTitle("Add Driver");
            alert.setHeaderText("Successful");
            alert.show();

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Complete All Details", ButtonType.OK);
            alert.setTitle("Error");
            alert.setHeaderText("Unsuccessful");
            alert.show();

        }
    }
}
