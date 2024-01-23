package br.com.corrida_kart;

import br.com.corrida_kart.utils.LogFile;

import br.com.corrida_kart.utils.RaceDataProcessor;
import br.com.corrida_kart.utils.TimeUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List voltas = (new LogFile("C:\\Users\\lucas\\Desktop\\tempo_voltas.log")).readLogFile();
        RaceDataProcessor corrida = new RaceDataProcessor(voltas);
        corrida.processRaceData();

        String time1 = "04:21:33,555";
        String time2 = "2:00";


        System.out.println(TimeUtils.subtractTimes(time1, time2));


    }
}