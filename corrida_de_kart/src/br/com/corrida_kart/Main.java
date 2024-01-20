package br.com.corrida_kart;

import br.com.corrida_kart.utils.LogFile;

import java.util.List;


public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        List voltas = (new LogFile("C:\\Users\\lucas\\Desktop\\tempo_voltas.log")).readLogFile();
        System.out.println(voltas);
    }
}