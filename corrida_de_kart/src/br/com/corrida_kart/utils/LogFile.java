package br.com.corrida_kart.utils;

import br.com.corrida_kart.exception.InvalidData;
import br.com.corrida_kart.model.domain.Lap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFile {
    private String filePath;
    public LogFile(String filePath) {
        this.filePath = filePath;
    }

    public List<Lap> readLogFile() {
        List<Lap> laps = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\s+");

                if (isValidData(data)) {
                    String time = data[0];
                    String driverCode = data[1];
                    String driverName = data[3];
                    int lapNumber = Integer.parseInt(data[4]);
                    String lapTime = data[5];
                    double averageSpeed = Double.parseDouble(data[6].replace(",", "."));

                    laps.add(new Lap(time, driverCode, driverName, lapNumber, lapTime, averageSpeed));
                } else {
                    throw new InvalidData(line);
                }
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return laps;
    }

    private boolean isValidData(String[] data) {
        return data.length >= 7 && isNumeric(data[4]) && isNumericWithComma(data[6]);
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private boolean isNumericWithComma(String str) {
        return str.matches("\\d+(,\\d+)?");
    }
}
