//FEDE

package it.unibo.controller;

import it.unibo.model.GameState;
import it.unibo.model.Grid;
import it.unibo.view.GameView;

public class GameLoop implements Runnable {
    private final GameState gameState;
    private final Grid grid;
    private final GameView gameView;

    private boolean running;
    private final int targetFPS = 60; // Frame rate desiderato

    public GameLoop(GameState gameState, Grid grid, GameView gameView) {
        this.gameState = gameState;
        this.grid = grid;
        this.gameView = gameView;
        this.running = false;
    }

    // Avvia il ciclo di gioco
    public void start() {
        running = true;
        new Thread(this).start(); // Esegui il loop in un thread separato
    }

    // Ferma il ciclo di gioco
    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerFrame = 1_000_000_000.0 / targetFPS;

        while (running) {
            long now = System.nanoTime();
            if ((now - lastTime) >= nsPerFrame) {
                lastTime = now;

                // Aggiorna lo stato del gioco
                update();

                // Ridisegna la grafica
                render();
            }

            // Controlla la pausa
            if (gameState.isPaused()) {
                try {
                    Thread.sleep(100); // Riduce il consumo di CPU in pausa
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Metodo per aggiornare lo stato del gioco
    private void update() {
        if (!gameState.isGameOver()) {
            // Aggiorna la logica della griglia
            grid.updateGrid();

            // Verifica condizioni di fine partita
            if (grid.isGridFull()) {
                gameState.setGameOver(true);
            }
        }
    }

    // Metodo per ridisegnare la grafica
    private void render() {
        gameView.repaint();
    }
}

