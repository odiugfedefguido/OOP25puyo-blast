package it.unibo.controller;
//Traduce gli input del giocatore (tasti, mouse) in azioni. (NO FEDE)

import it.unibo.controller.interfaces.InputHandlerInterface;
import it.unibo.model.CannonModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements InputHandlerInterface, KeyListener {
    private final CannonModel model;
    private final CannonController controller;

    public InputHandler(CannonController controller, CannonModel model) {
        this.controller = controller;
        this.model = model;
    }

    // Metodo per gestire l'input da tastiera
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                if (model.getX() - 5 >= 0) {
                    model.moveLeft();
                    System.out.println("Cannon moved left. Current position: " + model.getX());
                }
            }
            case KeyEvent.VK_RIGHT -> {
                if( model.getX() + 5 <= 750){ 
                    model.moveRight();
                    System.out.println("Cannon moved right. Current position: " + model.getX());
                }
            }
            case KeyEvent.VK_SPACE -> System.out.println("Cannon fired!");
            case KeyEvent.VK_UP -> {
                model.aimUp();
                controller.updateCannonAngle(model.getAngle());
                System.out.println("Cannon aimed up. Current angle: " + model.getAngle());
            }
            case KeyEvent.VK_DOWN -> {
                model.aimDown();
                controller.updateCannonAngle(model.getAngle());
                System.out.println("Cannon aimed down. Current angle: " + model.getAngle());
            }
            default -> {
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilizzato in questo esempio, ma potrebbe essere necessario in futuro
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilizzato in questo esempio
    }


}