package controller;

import db.DriverDatabase;
import db.InParkingDatabase;
import db.OnDeliveryDatabase;
import db.VehicleDatabase;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Observable;

public class ParkingFormController {
    public ComboBox<String> cmbSelectVehicle;
    public ComboBox<String> cmbDriver;
    public AnchorPane HomeContext;
    public Label lblSlot;
    public Label lblVehicleType;
    public Label lblDate;
    public Label lblTime;
    public Button btnPark;
    public Button btnDelivery;

    public void initialize(){
        ObservableList<String> vehicleNumb = FXCollections.observableArrayList();
        for (Vehicle vehicle: VehicleDatabase.vehicleTable) {
            if (vehicle != null){
                vehicleNumb.add(vehicle.getNum());
            }
        }

        cmbSelectVehicle.setItems(vehicleNumb);

        ObservableList<String> driverName = FXCollections.observableArrayList();
        for (Driver driver: DriverDatabase.driverTable) {
            if (driver != null){
                driverName.add(driver.getName());
            }
        }

        cmbDriver.setItems(driverName);

        cmbSelectVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Vehicle v: VehicleDatabase.vehicleTable) {
                if (v != null){
                    if (newValue.equals(v.getNum())){
                        lblVehicleType.setText(v.getType());
                        boolean noPark = true;
                        boolean noDelivery = true;

                        for (Parking p: InParkingDatabase.parkingTable) {
                            if (p.getVehiNo().equals(v.getNum())){
                                noPark = false;
                                break;
                            }
                        }

                        for (Delivery d: OnDeliveryDatabase.deliveryTable) {
                            if (d.getVehicleNo().equals(v.getNum())){
                                noDelivery = false;
                                break;
                            }
                        }

                        if (noPark){
                            searchSlot();
                            btnPark.setDisable(false);
                            btnDelivery.setDisable(true);

                        }

                        if (noDelivery){
                            lblSlot.setText("0");
                            btnPark.setDisable(true);
                            btnDelivery.setDisable(false);
                        }
                    }
                }
            }
        });

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
            lblDate.setText(currentDate.getDayOfMonth() + "-" + currentDate.getMonthValue() + "-" + currentDate.getYear());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }

    public void btnParkVehicleOnAction(ActionEvent actionEvent) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   HH:mm");
        Date date = new Date();

        if (!cmbSelectVehicle.getSelectionModel().isEmpty() && !lblVehicleType.getText().equals("")){
            InParkingDatabase.parkingTable.add(new Parking(cmbSelectVehicle.getSelectionModel().getSelectedItem(),lblVehicleType.getText(),lblSlot.getText(),formatter.format(date)));
            for (Delivery d:OnDeliveryDatabase.deliveryTable) {
                if (d.getVehicleNo().equalsIgnoreCase(cmbSelectVehicle.getSelectionModel().getSelectedItem()) && d.getVehicleType().equalsIgnoreCase(lblVehicleType.getText())){
                    OnDeliveryDatabase.deliveryTable.remove(d);
                    break;
                }
            }

            lblSlot.setText("");
            lblVehicleType.setText("");

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Park Your Vehicle Now.", ButtonType.OK);
            alert.setTitle("Parking");
            alert.setHeaderText("Successful");
            alert.show();

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Try Again.", ButtonType.OK);
            alert.setTitle("Error");
            alert.setHeaderText("Unsuccessful");
            alert.show();
        }

    }

    public void btnOnDeliveryOnAction(ActionEvent actionEvent) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   HH:mm");
        Date date = new Date();

        if (!cmbDriver.getSelectionModel().isEmpty() && !lblVehicleType.getText().equals("") && !cmbSelectVehicle.getSelectionModel().isEmpty()) {

            boolean notExists = true;
            for (Delivery d : OnDeliveryDatabase.deliveryTable) {
                if (cmbDriver.getSelectionModel().getSelectedItem().equals(d.getDriverName())) {
                    notExists = false;
                }
            }
            if (notExists) {
            OnDeliveryDatabase.deliveryTable.add(new Delivery(cmbSelectVehicle.getSelectionModel().getSelectedItem(), lblVehicleType.getText(), cmbDriver.getSelectionModel().getSelectedItem(), formatter.format(date)));
            for (Parking p : InParkingDatabase.parkingTable) {
                if (p.getVehiNo().equalsIgnoreCase(cmbSelectVehicle.getSelectionModel().getSelectedItem()) && p.getVehiType().equalsIgnoreCase(lblVehicleType.getText())) {
                    InParkingDatabase.parkingTable.remove(p);
                    break;
                }
            }

            lblSlot.setText("");
            lblVehicleType.setText("");

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Can Start Your Delivery.", ButtonType.OK);
            alert.setTitle("Delivery");
            alert.setHeaderText("Successful");
            alert.show();

        }else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Already in Shift.", ButtonType.OK);
                alert.setTitle("Error");
                alert.setHeaderText("Unsuccessful");
                alert.show();
            }

    }

        if (cmbDriver.getSelectionModel().isEmpty() && cmbSelectVehicle.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Not Selected Yet!", ButtonType.OK);
            alert.setTitle("Error");
            alert.setHeaderText("Unsuccessful");
            alert.show();

        }else if (cmbDriver.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Driver Not Selected Yet!", ButtonType.OK);
            alert.setTitle("Error");
            alert.setHeaderText("Unsuccessful");
            alert.show();

        }else if (cmbSelectVehicle.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vehicle Number Not Selected Yet!", ButtonType.OK);
            alert.setTitle("Error");
            alert.setHeaderText("Unsuccessful");
            alert.show();

        }

    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/loginForm.fxml"));
        Parent parent = loader.load();

        LoginFormController controller = loader.getController();
        controller.getContext(HomeContext);


        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Login");
        stage.setScene(new Scene(parent));
        stage.show();
        stage.centerOnScreen();



    }

    public void searchSlot(){
        if (lblVehicleType.getText().equals("Van")){
            for (int i = 0; i< Slot.van.length; i++){
                boolean yes = true;
                for (Parking p: InParkingDatabase.parkingTable) {
                    if (p.getParkSlot().equals(String.valueOf(Slot.van[i]))){
                        yes = false;
                        break;
                    }
                }

                if (yes){
                    lblSlot.setText(String.valueOf(Slot.van[i]));
                    return;
                }
            }
        }else if (lblVehicleType.getText().equals("Cargo lorry")){
            for (int i = 0; i< Slot.cargolorry.length; i++){
                boolean yes = true;
                for (Parking p: InParkingDatabase.parkingTable) {
                    if (p.getParkSlot().equals(String.valueOf(Slot.cargolorry[i]))){
                        yes = false;
                        break;
                    }
                }

                if (yes){
                    lblSlot.setText(String.valueOf(Slot.cargolorry[i]));
                    return;
                }
            }
        }else if (lblVehicleType.getText().equals("Bus")){
            lblSlot.setText("14");
        }
    }


}
