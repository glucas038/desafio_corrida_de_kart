package br.com.corrida_kart.exception;

public class InvalidData extends RuntimeException{
    private String line;

    public InvalidData(String line) {
        super(String.format("Dados inválidos presentes na linha -> %s",line));
        this.line = line;
    }

}
