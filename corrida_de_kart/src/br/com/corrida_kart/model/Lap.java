package br.com.corrida_kart.model;

public class Lap {
    private String time;
    private int lapNumber;
    private String driverCode;
    private String driverName;
    private String lapTime;
    private double averageSpeed;

    public Lap(String time, String driverCode, String driverName, int lapNumber, String lapTime, double averageSpeed) {
        this.time = time;
        this.lapNumber = lapNumber;
        this.driverCode = driverCode;
        this.driverName = driverName;
        this.lapTime = lapTime;
        this.averageSpeed = averageSpeed;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLapNumber() {
        return this.lapNumber;
    }

    public void setLapNumber(int lapNumber) {
        this.lapNumber = lapNumber;
    }

    public String getDriverCode() {
        return this.driverCode;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLapTime() {
        return this.lapTime;
    }

    public void setLapTime(String lapTime) {
        this.lapTime = lapTime;
    }

    public double getAverageSpeed() {
        return this.averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public String toString() {
        return "\ntime='" + this.time + "', driverCode='" + this.driverCode + "', driverName='" + this.driverName + "', lapNumber=" + this.lapNumber + ", lapTime='" + this.lapTime + "', averageSpeed=" + this.averageSpeed;
    }
}
