package br.com.corrida_kart.model;

public class RaceResult {
    private int finishingPosition;
    private String driverCode;
    private String driverName;
    private int lapsCompleted;
    private String totalRaceTime;

    public RaceResult(int finishingPosition, String driverCode, String driverName, int lapsCompleted, String totalRaceTime) {
        this.finishingPosition = finishingPosition;
        this.driverCode = driverCode;
        this.driverName = driverName;
        this.lapsCompleted = lapsCompleted;
        this.totalRaceTime = totalRaceTime;
    }

    public int getFinishingPosition() {
        return this.finishingPosition;
    }

    public void setFinishingPosition(int finishingPosition) {
        this.finishingPosition = finishingPosition;
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

    public int getLapsCompleted() {
        return this.lapsCompleted;
    }

    public void setLapsCompleted(int lapsCompleted) {
        this.lapsCompleted = lapsCompleted;
    }

    public String getTotalRaceTime() {
        return this.totalRaceTime;
    }

    public void setTotalRaceTime(String totalRaceTime) {
        this.totalRaceTime = totalRaceTime;
    }
}
