package no.uib.inf101.sem2.pong.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import no.uib.inf101.sem2.pong.model.GameState;
import no.uib.inf101.sem2.pong.view.PongView;
   
/**
 * This is the controller class for the Pong game.
 * The controller handles input from the user and updates the model
 * accordingly. It listens to user input such as moving the paddle.
 * The controller communicates with the view to update the display.
 */
public class PongController implements KeyListener {
    
    private ControllablePongModel model;
    private PongView view;
    
    /**
     * Constructor for the PongController.
     * The controller handles input and updates the model accordingly.
     * The controller communicates with the view to 
     * update the display (ball, score, paddles).
     * @param model The ControllablePongModel representing the state. 
     * of the game. 
     * @param view the view of the game.
     */
    public PongController(ControllablePongModel model, PongView view) {
        this.model = model;
        this.view = view;
        
        this.view.addKeyListener(this);
        this.view.setFocusable(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.model.getGameState() == GameState.ACTIVE_GAME) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                this.model.getPaddle1().setPaddleYdirection(-10);
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                this.model.getPaddle1().setPaddleYdirection(10);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                this.model.getPaddle2().setPaddleYdirection(-10);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                this.model.getPaddle2().setPaddleYdirection(10);
            }
        }
        
        if (this.model.getGameState() == GameState.WELCOME_SCREEN) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                this.model.setGameStateToActive();
            }
        }

        if (this.model.getGameState() == GameState.GAME_OVER) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                this.model.setGameStateToWelcomeScreen();
            }     
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (this.model.getGameState() == GameState.ACTIVE_GAME) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                this.model.getPaddle1().setPaddleYdirection(0);
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                this.model.getPaddle1().setPaddleYdirection(0);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                this.model.getPaddle2().setPaddleYdirection(0);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                this.model.getPaddle2().setPaddleYdirection(0);
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}