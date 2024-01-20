package br.com.corrida_kart.utils;

import br.com.corrida_kart.model.Lap;
import br.com.corrida_kart.model.RaceResult;

import java.util.*;

public class Posicao {

    List<Lap> voltas;

    public static List <RaceResult> calculateRaceResult(List<Lap> laps){
        Map<String, RaceResult> resultMap = new HashMap<>();

        for (Lap lap: laps){

            if(!resultMap.containsKey(lap.getDriverCode())){
                resultMap.put(lap.getDriverCode(), new RaceResult(0, lap.getDriverCode(),
                        lap.getDriverName(), 0,"00:00.000"));
            }

            RaceResult result = resultMap.get(lap.getDriverCode());

            if (lap.getLapNumber() <= 4){
                String totalRaceTime = result.getTotalRaceTime();
                totalRaceTime = TimeUtils.sumTimes(totalRaceTime.replace(".",","), lap.getLapTime());
                result.setTotalRaceTime(totalRaceTime);
                result.setLapsCompleted(Math.max(result.getLapsCompleted(), lap.getLapNumber()));
            }

            resultMap.put(lap.getDriverCode(), result);

        }

        List<RaceResult> raceResults = new ArrayList<>(resultMap.values());
        raceResults.sort(Comparator.comparing(o -> o.getTotalRaceTime()));

        for (int i = 0; i < raceResults.size(); i++) {
            raceResults.get(i).setFinishingPosition(i+1);
        }

        return raceResults;
    }

    public static void result(List<Lap> laps){
        List <RaceResult> raceResults = calculateRaceResult(laps);
        for (RaceResult result: raceResults){
            System.out.println("Posição: " + result.getFinishingPosition() +
                    "  Código Piloto: " + result.getDriverCode() +
                    "  Nome Piloto: " + result.getDriverName() +
                    "  Voltas Completadas: " + result.getLapsCompleted() +
                    "  Tempo Total de Prova: " + result.getTotalRaceTime());
        }
    }


}





















