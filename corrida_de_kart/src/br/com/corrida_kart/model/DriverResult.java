package br.com.corrida_kart.model;

import br.com.corrida_kart.utils.TimeUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DriverResult {
    private int finishingPosition;
    private String driverCode;
    private String driverName;
    private int lapsCompleted;
    private String totalRaceTime;
    private int bestLap;
    private String bestLapTime;
    private BigDecimal averageSpeed;
    private String arrivalTime;
    private String timeBehindWinner;

    public DriverResult(int finishingPosition, String driverCode, String driverName,
                        int lapsCompleted, String totalRaceTime) {
        this.finishingPosition = finishingPosition;
        this.driverCode = driverCode;
        this.driverName = driverName;
        this.lapsCompleted = lapsCompleted;
        this.totalRaceTime = totalRaceTime;
        this.bestLap = 0;
        this.bestLapTime = "00:00:00,000";
        this.averageSpeed = BigDecimal.valueOf(0.0);
        this.arrivalTime = "00:00:00,000";
        this.timeBehindWinner = "00:00:00,000";
    }

    public int getFinishingPosition() {
        return finishingPosition;
    }

    public void setFinishingPosition(int finishingPosition) {
        this.finishingPosition = finishingPosition;
    }

    public String getDriverName() {
        return driverName;
    }


    public int getLapsCompleted() {
        return lapsCompleted;
    }

    public void setLapsCompleted(int lapsCompleted) {
        this.lapsCompleted = lapsCompleted;
    }

    public String getTotalRaceTime() {
        return totalRaceTime;
    }

    public void setTotalRaceTime(String totalRaceTime) {
        this.totalRaceTime = totalRaceTime;
    }

    public int getBestLap() {
        return bestLap;
    }

    public void setBestLap(int bestLap) {
        this.bestLap = bestLap;
    }

    public String getBestLapTime() {
        return bestLapTime;
    }

    public void setBestLapTime(String bestLapTime) {
        this.bestLapTime = bestLapTime;
    }

    public BigDecimal getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(BigDecimal averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }


    public void calculateAverageSpeed() {
        BigDecimal result = this.averageSpeed.divide(BigDecimal.valueOf(lapsCompleted), 3, RoundingMode.HALF_UP);
        setAverageSpeed(result);
    }

    public boolean isFasterLap(String bestLapTime) {
        if(this.bestLapTime.compareTo(bestLapTime) > 0){
            return true;
        }
        return false;
    }

    public void updateTimeBehindWinner(String firstTime) {
        this.timeBehindWinner = TimeUtils.subtractTimes(this.arrivalTime, firstTime);
    }

    @Override
    public String toString() {
        return  "Posição: " + this.finishingPosition +
                "  Código Piloto: " + this.driverCode +
                "  Nome Piloto: " + this.driverName +
                "  Voltas Completadas: " + this.lapsCompleted +
                "  Tempo Total de Prova: " + this.totalRaceTime +
                "  Volta mais rápida: " + this.bestLap +
                "  Tempo volta mais rápida: " + this.bestLapTime +
                "  Velocidade média: " + this.averageSpeed +
                "  Tempo atrás: " + this.timeBehindWinner;
    }

}
