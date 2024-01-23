package br.com.corrida_kart.model;

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
        return  "VOLTA MAIS RÁPIDA DA CORRIDA ->  " +
                "  Nome do piloto: " + this.driverName +
                "  Volta mais rápida: " + this.bestLap +
                "  Tempo da volta mais rápida: " + this.bestLapTime;
    }

    public boolean isFasterLap(String bestLapTime) {
        if(this.bestLapTime.compareTo(bestLapTime) > 0){
            return true;
        }
        return false;
    }

    public void updateBestLap(String driverName, int bestLap, String bestLapTime) {
        this.driverName = driverName;
        this.bestLap = bestLap;
        this.bestLapTime = bestLapTime;
    }

}
