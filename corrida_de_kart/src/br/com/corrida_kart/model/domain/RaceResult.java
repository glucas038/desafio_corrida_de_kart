package br.com.corrida_kart.model.domain;

public class RaceResult {

    private String driverName;
    private int bestLap;
    private String bestLapTime;

    public RaceResult() {
        this.driverName = "";
        this.bestLap = 0;
        this.bestLapTime = "";
    }

    public int getBestLap() {
        return bestLap;
    }


    @Override
    public String toString() {
        return String.format("VOLTA MAIS RÁPIDA DA CORRIDA\n" +
                        "Nome do piloto: %s  Volta mais rápida: %d  Tempo da volta mais rápida: %s",
                driverName, bestLap, bestLapTime);
    }

    public boolean isFasterLap(String lapTime) {
        return this.bestLapTime.compareTo(lapTime) > 0;
    }

    public void updateBestLap(String driverName, int bestLap, String bestLapTime) {
        this.driverName = driverName;
        this.bestLap = bestLap;
        this.bestLapTime = bestLapTime;
    }

}
