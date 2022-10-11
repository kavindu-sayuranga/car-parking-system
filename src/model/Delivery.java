package model;

public class Delivery {

    String vehicleNo;
    String vehicleType;
    String driverName;
    String leftTime;

    public Delivery() {
    }

    public Delivery(String vehicleNo, String vehicleType, String driverName, String leftTime) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.driverName = driverName;
        this.leftTime = leftTime;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(String leftTime) {
        this.leftTime = leftTime;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "vehicleNo='" + vehicleNo + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", driverName='" + driverName + '\'' +
                ", leftTime='" + leftTime + '\'' +
                '}';
    }
}
