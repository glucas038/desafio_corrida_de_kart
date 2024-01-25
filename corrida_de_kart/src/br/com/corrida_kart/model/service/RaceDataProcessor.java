package br.com.corrida_kart.model.service;

import br.com.corrida_kart.model.domain.Lap;
import br.com.corrida_kart.model.domain.DriverResult;
import br.com.corrida_kart.model.domain.RaceResult;
import br.com.corrida_kart.utils.TimeUtils;

import java.util.*;

public class RaceDataProcessor {

    private static final int MAX_LAP_NUMBER = 4;
    private List<DriverResult> driverResults;
    private String driverResultsString;
    private RaceResult raceResult;
    private String raceResultString;
    private String bonusResultString;


    public RaceDataProcessor() {
        this.raceResult = new RaceResult();
        this.driverResultsString = "RESULTADO DA CORRIDA\n";
        this.raceResultString = "";
        this.bonusResultString = "RESULTADO DA CORRIDA - BÔNUS\n";

    }

    public String getDriverResultsString() {
        return driverResultsString;
    }

    public String getRaceResultString() {
        return raceResultString;
    }

    public String getBonusResultString() {
        return bonusResultString;
    }

    public void processRaceData(List<Lap> laps) {
        calculateDriverResults(laps);
        calculateRaceResult();
        printResults();
    }

    private void calculateDriverResults(List<Lap> laps) {
        Map<String, DriverResult> resultMap = initializeDriverResultsMap(laps);

        driverResults = new ArrayList<>(resultMap.values());
        driverResults.sort(Comparator.comparing(DriverResult::getTotalRaceTime));
        finalizeDriverResults();
        calculateTimeBehindWinner();
    }

    private Map<String, DriverResult> initializeDriverResultsMap(List<Lap> laps) {
        Map<String, DriverResult> resultMap = new HashMap<>();

        for (Lap lap : laps) {
            resultMap.computeIfAbsent(lap.getDriverCode(), code ->
                    new DriverResult(0, code, lap.getDriverName(), 0, "00:00.000"));

            DriverResult result = resultMap.get(lap.getDriverCode());
            updateDriverResult(result, lap);
        }

        return resultMap;
    }

    private void updateDriverResult(DriverResult result, Lap lap) {
        if (lap.getLapNumber() > MAX_LAP_NUMBER) {
            return;
        }

        result.setTotalRaceTime(TimeUtils.sumTimes(result.getTotalRaceTime(), lap.getLapTime()));
        result.setAverageSpeed(result.getAverageSpeed() + lap.getAverageSpeed());
        result.setLapsCompleted(Math.max(result.getLapsCompleted(), lap.getLapNumber()));

        if (lap.getTime().compareTo(result.getArrivalTime()) > 0) {
            result.setArrivalTime(lap.getTime());
        }

        if (lap.getLapNumber() == 1 || result.isFasterLap(lap.getLapTime())) {
            result.setBestLapTime(lap.getLapTime());
            result.setBestLap(lap.getLapNumber());
        }

    }

    private void finalizeDriverResults() {
        for (DriverResult result : driverResults) {
            result.calculateAverageSpeed();
        }

        for (int i = 0; i < driverResults.size(); i++) {
            driverResults.get(i).setFinishingPosition(i + 1);
        }
    }

    private void calculateTimeBehindWinner() {
        String firstTime = "";
        for (DriverResult driverResult : driverResults) {
            if (driverResult.getFinishingPosition() == 1) {
                firstTime = driverResult.getArrivalTime();
            }
            driverResult.updateTimeBehindWinner(firstTime);
        }
    }

    private void calculateRaceResult() {

        for (DriverResult driverResult : driverResults) {
            if (raceResult.getBestLap() == 0 || raceResult.isFasterLap(driverResult.getBestLapTime())) {
                raceResult.updateBestLap(driverResult.getDriverName(), driverResult.getBestLap(), driverResult.getBestLapTime());
            }
        }

    }

    private void printResults() {
        StringBuilder driverResultsStringBuilder = new StringBuilder("RESULTADO DA CORRIDA\n");
        StringBuilder bonusResultStringBuilder = new StringBuilder("RESULTADO DA CORRIDA - BÔNUS\n");

        for (DriverResult result : driverResults) {
            driverResultsStringBuilder.append(result.result()).append("\n");
            bonusResultStringBuilder.append(result.bonusResult()).append("\n");
        }

        driverResultsString = driverResultsStringBuilder.toString();
        bonusResultString = bonusResultStringBuilder.toString();
        raceResultString = raceResult.toString();
    }
}
