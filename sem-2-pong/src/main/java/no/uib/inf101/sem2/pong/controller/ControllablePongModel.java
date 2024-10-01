package no.uib.inf101.sem2.pong.controller;

import no.uib.inf101.sem2.pong.model.GameState;
import no.uib.inf101.sem2.pong.model.Paddle;

/**
 * The ControllablePongModel interface contains the methods from the 
 * model that the controller needs to control the game. The controller
 * gets the information it needs from the model through the methods in
 * this interface. The controller needs to know the position of the 
 * paddles, the current game state and the width and height of the
 * game.
 */
public interface ControllablePongModel {

    /**
     * Gets the paddle player 1 object.
     * @return paddle player 1 object of the type Paddle.
     */
    public Paddle getPaddle1();

    /**
     * Gets the paddle player 2 object.
     * @return paddle player 2 object of the type Paddle.
     */
    public Paddle getPaddle2();

    /**
     * Gets the current game state. The current gamestate decides
     * what is drawn on the screen, what is updated in the model 
     * and what can be controlled by the user. The game state can 
     * be either ACTIVE_GAME, GAME_OVER or WELCOME_SCREEN
     * @return the current game state of the game.
     */
    public GameState getGameState();

    /**
     * Method for setting the game state to ACTIVE_GAME.
     * Setting the game state to ACTIVE_GAME will make the game start
     * The ball will start moving and the paddles can be controlled
     * Also the score will update when a player scores.
     */
    public void setGameStateToActive();

    /**
     * Method for setting the game state to WELCOME_SCREEN.
     * Setting the game state to WELCOME_SCREEN will make the game stop
     * A Welcome screen will be drawn and the paddles will
     * be reset to their initial positions.
     */
    public void setGameStateToWelcomeScreen();
}
