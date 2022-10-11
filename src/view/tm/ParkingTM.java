package view.tm;

public class ParkingTM {

    String vehiNo;
    String  vehiType;
    String parkSlot;
    String parkTime;

    public ParkingTM(String vehiNo, String vehiType, String parkSlot, String parkTime) {
        this.vehiNo = vehiNo;
        this.vehiType = vehiType;
        this.parkSlot = parkSlot;
        this.parkTime = parkTime;
    }

    public ParkingTM() {
    }

    public String getVehiNo() {
        return vehiNo;
    }

    public void setVehiNo(String vehiNo) {
        this.vehiNo = vehiNo;
    }

    public String getVehiType() {
        return vehiType;
    }

    public void setVehiType(String vehiType) {
        this.vehiType = vehiType;
    }

    public String getParkSlot() {
        return parkSlot;
    }

    public void setParkSlot(String parkSlot) {
        this.parkSlot = parkSlot;
    }

    public String getParkTime() {
        return parkTime;
    }

    public void setParkTime(String parkTime) {
        this.parkTime = parkTime;
    }

    @Override
    public String toString() {
        return "ParkingTM{" +
                "vehiNo='" + vehiNo + '\'' +
                ", vehiType='" + vehiType + '\'' +
                ", parkSlot='" + parkSlot + '\'' +
                ", parkTime='" + parkTime + '\'' +
                '}';
    }
}
