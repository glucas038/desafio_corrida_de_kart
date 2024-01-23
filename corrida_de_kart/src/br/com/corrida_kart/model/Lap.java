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
        return time;
    }

    public int getLapNumber() {
        return lapNumber;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getLapTime() {
        return lapTime;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    @Override
    public String toString() {
        return String.format(
                "\ntime='%s', driverCode='%s', driverName='%s', lapNumber=%d, lapTime='%s', averageSpeed=%.2f",
                time, driverCode, driverName, lapNumber, lapTime, averageSpeed
        );
    }
}
