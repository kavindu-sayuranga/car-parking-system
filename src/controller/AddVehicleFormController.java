package controller;

import db.OnDeliveryDatabase;
import db.VehicleDatabase;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.*;

public class AddVehicleFormController {
    public TextField txtVehicleNumber;
    public TextField txtMaximumWeight;
    public TextField txtNoofPassengers;
    public ComboBox<String> cmbVehicleType;

    public void initialize() {
        cmbVehicleType.getItems().add("Van");
        cmbVehicleType.getItems().add("Bus");
        cmbVehicleType.getItems().add("Cargo Lorry");
    }


    public void btnAddVehicleOnAction(ActionEvent actionEvent) {

        int i = 0;

        for (Vehicle v: VehicleDatabase.vehicleTable) {
            if (v==null){
                break;
            }
            i++;

        }

        if (!txtVehicleNumber.getText().equals("") && !txtMaximumWeight.getText().equals("") && !txtNoofPassengers.getText().equals("")){

            if (cmbVehicleType.getSelectionModel().getSelectedItem().equalsIgnoreCase("Van")){
                int count = 0;
                for (Vehicle v : VehicleDatabase.vehicleTable) {
                    if (v == null) {
                        break;
                    }
                    if(v.getType().equals("Van")){
                        count++;
                    }
                }

                if (count < Slot.van.length) {

                    VehicleDatabase.vehicleTable[i] = new Van(txtVehicleNumber.getText(), "Van", Integer.parseInt(txtMaximumWeight.getText()), Integer.parseInt(txtNoofPassengers.getText()));
                    OnDeliveryDatabase.deliveryTable.add(new Delivery(txtVehicleNumber.getText(), "Van", "Recently Added", "Recently Added"));
                    txtVehicleNumber.setText("");
                    txtMaximumWeight.setText("");
                    txtNoofPassengers.setText("");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Van Successfully Added", ButtonType.OK);
                    alert.setTitle("Add Vehicle");
                    alert.setHeaderText("Successful");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The van limit has been exceeded!", ButtonType.OK);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unsuccessful");
                    alert.show();
                }
            }else if (cmbVehicleType.getSelectionModel().getSelectedItem().equalsIgnoreCase("Bus")){

                int count = 0;
                for (Vehicle v : VehicleDatabase.vehicleTable) {
                    if (v == null) {
                        break;
                    }
                    if(v.getType().equals("Bus")){
                        count++;
                    }
                }
                if (count < Slot.bus.length) {

                    VehicleDatabase.vehicleTable[i] = new Bus(txtVehicleNumber.getText(), "Bus", Integer.parseInt(txtMaximumWeight.getText()), Integer.parseInt(txtNoofPassengers.getText()));
                    OnDeliveryDatabase.deliveryTable.add(new Delivery(txtVehicleNumber.getText(), "Bus", "Recently Added", "Recently Added"));
                    txtVehicleNumber.setText("");
                    txtMaximumWeight.setText("");
                    txtNoofPassengers.setText("");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Bus Successfully Added", ButtonType.OK);
                    alert.setTitle("Add Vehicle");
                    alert.setHeaderText("Successful");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The bus limit has been exceeded!", ButtonType.OK);
                    alert.setTitle("Error");
                    alert.setHeaderText("Un Successful");
                    alert.show();
                }


            }else if (cmbVehicleType.getSelectionModel().getSelectedItem().equalsIgnoreCase("Cargo Lorry")){

                int count = 0;
                for (Vehicle v : VehicleDatabase.vehicleTable) {
                    if (v == null) {
                        break;
                    }
                    if(v.getType().equals("Cargo Lorry")){
                        count++;
                    }
                }
                if (count < Slot.cargolorry.length) {

                    VehicleDatabase.vehicleTable[i] = new Lorry(txtVehicleNumber.getText(), "Cargo Lorry", Integer.parseInt(txtMaximumWeight.getText()), Integer.parseInt(txtNoofPassengers.getText()));
                    OnDeliveryDatabase.deliveryTable.add(new Delivery(txtVehicleNumber.getText(), "Cargo Lorry", "Recently Added", "Recently Added"));
                    txtVehicleNumber.setText("");
                    txtMaximumWeight.setText("");
                    txtNoofPassengers.setText("");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cargo Lorry Successfully Added", ButtonType.OK);
                    alert.setTitle("Add Vehicle");
                    alert.setHeaderText("Successful");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The lorry limit has been exceeded!", ButtonType.OK);
                    alert.setTitle("Error");
                    alert.setHeaderText("Un Successful");
                    alert.show();
                }

            }


        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Enter All Details", ButtonType.OK);
            alert.setTitle("Error");
            alert.setHeaderText("Unsuccessful");
            alert.show();

        }
    }
}
