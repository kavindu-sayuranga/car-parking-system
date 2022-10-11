package controller;


import db.OnDeliveryDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Delivery;
import model.Parking;
import view.tm.DeliveryTM;
import view.tm.ParkingTM;

import java.io.IOException;

public class OnDeliveryFormController {
    public AnchorPane HomeContext;
    public ComboBox<String> cmbOnDelivery;
    public TableView<DeliveryTM> tblOnDelivery;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colDriverName;
    public TableColumn colLeftTime;


    public void initialize(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("In Parking");
        obList.add("On Delivery");
        cmbOnDelivery.setItems(obList);

        cmbOnDelivery.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("In Parking")) {
                Stage stage = (Stage) HomeContext.getScene().getWindow();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/InParkingForm.fxml"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.centerOnScreen();
            }else if(newValue.equals("On Delivery")){
                Stage stage = (Stage) HomeContext.getScene().getWindow();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/OnDeliveryForm.fxml"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.centerOnScreen();
            }
        });

        colVehicleNumber.setCellValueFactory(new PropertyValueFactory("vehicleNo"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colDriverName.setCellValueFactory(new PropertyValueFactory("driverName"));
        colLeftTime.setCellValueFactory(new PropertyValueFactory("leftTim"));

        loadAllVehicle();
    }

    private void loadAllVehicle() {

        ObservableList<DeliveryTM> obList = FXCollections.observableArrayList();
        for (Delivery d: OnDeliveryDatabase.deliveryTable) {
            DeliveryTM dtm = new DeliveryTM(d.getVehicleNo(),d.getVehicleType(),d.getDriverName(),d.getLeftTime());
            obList.add(dtm);
        }
        tblOnDelivery.setItems(obList);

    }

    public void btnAddDriverOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AddDriverForm.fxml"));
        Parent parent = loader.load();


        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Add Vehicle");
        stage.setScene(new Scene(parent));
        stage.show();
        stage.centerOnScreen();


    }

    public void btnAddVehicleOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AddVehicleForm.fxml"));
        Parent parent = loader.load();


        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Add Vehicle");
        stage.setScene(new Scene(parent));
        stage.show();
        stage.centerOnScreen();

    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) HomeContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ParkingForm.fxml"))));

        stage.centerOnScreen();

    }
}
