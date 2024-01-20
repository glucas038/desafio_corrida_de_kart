package br.com.corrida_kart.utils;
import br.com.corrida_kart.model.Lap;

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

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<Lap> readLogFile() {
        List<Lap> laps = new ArrayList();

        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));

            try {
                br.readLine();

                String line;
                while((line = br.readLine()) != null) {
                    String[] data = line.split("\\s+");
                    String time = data[0];
                    String driverCode = data[1];
                    String driverName = data[3];
                    int lapNumber = Integer.parseInt(data[4]);
                    String lapTime = data[5];
                    double averageSpeed = Double.parseDouble(data[6].replace(",", "."));
                    laps.add(new Lap(time, driverCode, driverName, lapNumber, lapTime, averageSpeed));
                }
            } catch (Throwable var13) {
                try {
                    br.close();
                } catch (Throwable var12) {
                    var13.addSuppressed(var12);
                }

                throw var13;
            }

            br.close();
            return laps;

        } catch (IOException var14) {
            System.err.println("IOException: " + var14.getMessage());
            throw new RuntimeException(var14);
        }
    }
}
