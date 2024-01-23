package br.com.corrida_kart.utils;

import br.com.corrida_kart.model.Lap;
import br.com.corrida_kart.model.DriverResult;
import br.com.corrida_kart.model.RaceResult;

import java.math.BigDecimal;
import java.util.*;

public class RaceDataProcessor {

    private final List<Lap> laps;
    private List<DriverResult> driverResults;

    public RaceDataProcessor(List<Lap> laps) {
        this.laps = laps;
        this.driverResults = null;
    }

    public void processRaceData() {
        calculateDriverResults(laps);
        RaceResult raceResult = calculateRaceResult();
        printResults(raceResult);
    }

    private void calculateDriverResults(List<Lap> laps) {

        Map<String, DriverResult> resultMap = new HashMap<>();

        for (Lap lap : laps) {
            resultMap.computeIfAbsent(lap.getDriverCode(), code ->
                    new DriverResult(0, code, lap.getDriverName(), 0, "00:00.000"));

            DriverResult result = resultMap.get(lap.getDriverCode());
            updateDriverResult(result, lap);
        }

        driverResults = new ArrayList<>(resultMap.values());
        driverResults.sort(Comparator.comparing(DriverResult::getTotalRaceTime));
        finalizeDriverResults();
        calculateTimeBehindWinner();
    }

    private void updateDriverResult(DriverResult result, Lap lap) {
        if (lap.getLapNumber() > 4) {
            return;
        }

        result.setTotalRaceTime(TimeUtils.sumTimes(result.getTotalRaceTime(), lap.getLapTime()));
        result.setAverageSpeed(result.getAverageSpeed().add(BigDecimal.valueOf(lap.getAverageSpeed())));
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

    private RaceResult calculateRaceResult() {
        RaceResult result = new RaceResult();

        for (DriverResult driverResult : driverResults) {
            if (result.getBestLap() == 0 || result.isFasterLap(driverResult.getBestLapTime())) {
                result.updateBestLap(driverResult.getDriverName(), driverResult.getBestLap(), driverResult.getBestLapTime());
            }
        }

        return result;
    }

    private void printResults(RaceResult raceResult) {
        for (DriverResult result : driverResults) {
            System.out.println(result);
        }

        System.out.println(raceResult);
    }
}
