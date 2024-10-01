package no.uib.inf101.sem2.pong.view;

import no.uib.inf101.sem2.pong.model.Ball;
import no.uib.inf101.sem2.pong.model.GameState;
import no.uib.inf101.sem2.pong.model.Paddle;

/**
 * The ViewablePongModel interface contains the methods from the model
 * that the view needs to draw the game. The view gets the information
 * it needs from the model through the methods in this interface.
 * The view needs to know the position of the paddles, the ball, the 
 * score of the players and the current game state.
 * The view also needs to know the width and height of the game.
 * The view can change the score of the players and the game state.
 */
public interface ViewablePongModel {

    /**
     * Gets the paddle player 1 object.
     * @return the paddle player 1 object of the type Paddle.
     */
    public Paddle getPaddle1();

    /**
     * Gets the paddle player 2 object.
     * @return the paddle player 2 object of the type Paddle.
     */
    public Paddle getPaddle2();

    /**
     * Gets the ball object.
     * @return the ball object of the type Ball.
     */
    public Ball getBall();

    /**
     * Gets the score of player 1.
     * @return the score of player 1.
     * The value of the score must be an integer.
     */
    public int getScorePlayer1();

    /**
     * Gets the score of player 2.
     * @return the score of player 2.
     * The value of the score must be an integer.
     */
    public int getScorePlayer2();

    /**
     * Gets the current game state. The current gamestate decides
     * what is drawn on the screen, what is updated in the model 
     * and what can be controlled by the user. The game state can 
     * be either ACTIVE_GAME, GAME_OVER or WELCOME_SCREEN.
     * @return the current game state of the game.
     */
    public GameState getGameState();

    /**
     * Sets the game state to GAME_OVER.
     */
    public void setGameStateToGameOver();

    /**
     * Method to change the score of player 1
     * It is used to reset the score to 0 when the game is over.
     * @param resetScoreToValue the value to reset the score to.
     * The reset value must be an integer.
     */
    public void setScorePlayer1(int resetScoreToValue);

    /**
     * Method to change the score of player 2.
     * It is used to reset the score to 0 when the game is over.
     * @param resetScoreToValue the value to reset the score to.
     * The reset value must be an integer.
     */
    public void setScorePlayer2(int resetScoreToValue);

    /**
     * Method for getting the width of the game.
     * @return an integer representing the width of the game.
     * The width of the game is 1000;
     */
    public int getGameWidth();

    /**
     * Method for getting the height of the game.
     * @return an integer representing the height of the game.
     * The height of the game is 550;
     */
    public int getGameHeight();
}

