package br.com.corrida_kart.model.domain;

import br.com.corrida_kart.utils.TimeUtils;


public class DriverResult {
    private static final String DEFAULT_TIME = "00:00:00.000";
    private static final int DEFAULT_BEST_LAP = 0;
    private int finishingPosition;
    private final String driverCode;
    private final String driverName;
    private int lapsCompleted;
    private String totalRaceTime;
    private int bestLap;
    private String bestLapTime;
    private double averageSpeed;
    private String arrivalTime;
    private String timeBehindWinner;

    public DriverResult(int finishingPosition, String driverCode, String driverName,
                        int lapsCompleted, String totalRaceTime) {
        this.finishingPosition = finishingPosition;
        this.driverCode = driverCode;
        this.driverName = driverName;
        this.lapsCompleted = lapsCompleted;
        this.totalRaceTime = totalRaceTime;
        this.bestLap = DEFAULT_BEST_LAP;
        this.bestLapTime = DEFAULT_TIME;
        this.averageSpeed = 0;
        this.arrivalTime = DEFAULT_TIME;
        this.timeBehindWinner = DEFAULT_TIME;
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

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void calculateAverageSpeed() {
        if (lapsCompleted > 0) {
            double result = averageSpeed / lapsCompleted;
            setAverageSpeed(result);
        }
    }

    public boolean isFasterLap(String lapTime) {
        return this.bestLapTime.compareTo(lapTime) > 0;
    }

    public void updateTimeBehindWinner(String firstTime) {
        this.timeBehindWinner = TimeUtils.subtractTimes(this.arrivalTime, firstTime);
    }

    public String result() {
        return String.format("Posição Chegada: %d  " +
                        "Código Piloto: %s  " +
                        "Nome Piloto: %s  " +
                        "Qtde Voltas Completadas: %d  " +
                        "Tempo Total de Prova: %s",
                finishingPosition, driverCode, driverName, lapsCompleted, totalRaceTime);
    }

    public String bonusResult() {
        return String.format("Posição Chegada: %d  " +
                        "Nome Piloto: %s  " +
                        "Volta mais rápida: %d  " +
                        "Tempo volta mais rápida: %s  " +
                        "Velocidade média: %.3f  " +
                        "Tempo atrás: %s",
                finishingPosition, driverName, bestLap, bestLapTime,
                averageSpeed, timeBehindWinner);
    }

    @Override
    public String toString() {
        return String.format("Posição: %d  Código Piloto: %s  Nome Piloto: %s  Voltas Completadas: %d  " +
                        "Tempo Total de Prova: %s  Volta mais rápida: %d  Tempo volta mais rápida: %s  " +
                        "Velocidade média: %.3f  Tempo atrás: %s",
                finishingPosition, driverCode, driverName, lapsCompleted, totalRaceTime, bestLap, bestLapTime, averageSpeed, timeBehindWinner);
    }

}
