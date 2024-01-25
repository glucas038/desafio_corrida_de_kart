package br.com.corrida_kart.controller;

import br.com.corrida_kart.view.RaceView;

public class RaceController {

    private final RaceView view;

    public RaceController() {
        this.view = new RaceView();
    }

    public void startProgram() {
        view.show();
    }
}
