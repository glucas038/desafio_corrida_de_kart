package br.com.corrida_kart.model.service;

import br.com.corrida_kart.model.domain.Lap;
import br.com.corrida_kart.utils.LogFile;

import java.util.List;

public class RaceModel {

    private final RaceDataProcessor raceDataProcessor;

    public RaceModel() {
        this.raceDataProcessor = new RaceDataProcessor();
    }

    public void processLogFile(String filePath) {
        List<Lap> laps = new LogFile(filePath).readLogFile();
        raceDataProcessor.processRaceData(laps);
    }

    public String getScreenMessage(int currentPage) {
        if (currentPage == 1) {
            return raceDataProcessor.getDriverResultsString();
        } else if (currentPage == 2) {
            return raceDataProcessor.getBonusResultString();
        }
        return raceDataProcessor.getRaceResultString();
    }

}
