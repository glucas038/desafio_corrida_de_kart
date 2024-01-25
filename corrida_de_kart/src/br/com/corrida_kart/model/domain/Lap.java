package br.com.corrida_kart.model.domain;

public class Lap {
    private final String time;
    private final int lapNumber;
    private final String driverCode;
    private final String driverName;
    private final String lapTime;
    private final double averageSpeed;

    public Lap(String time, String driverCode, String driverName, int lapNumber, String lapTime, double averageSpeed) {
        this.time = time;
        this.driverCode = driverCode;
        this.driverName = driverName;
        this.lapNumber = lapNumber;
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
        return String.format("Lap{time='%s', driverCode='%s', driverName='%s', lapNumber=%d, lapTime='%s', averageSpeed=%.2f}",
                time, driverCode, driverName, lapNumber, lapTime, averageSpeed);
    }

}
