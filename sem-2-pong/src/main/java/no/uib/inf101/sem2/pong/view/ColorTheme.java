package no.uib.inf101.sem2.pong.view;

import java.awt.Color;

/**
 * The ColorTheme interface contains the methods that the view needs,
 * to know the different colors of the game. The view needs to know 
 * the colors of the background, the dotted line in the middle of the 
 * game, the ball, the paddles and the text in the different gamestates 
 */
public interface ColorTheme {

    /**
     * The background color of the game
     * @return a new Color with the RGB values 0,0,61.
     */
    public Color getBackgroundColor();

    /**
     * Method to get a transparent background color.
     * This color is used for the Gamestate WELCOME_SCREEN and 
     * GAME_OVER and is used to make the text more readable because
     * it is a transparent color placed on top of the game
     * @return a new Color with the RGB values 0,0,0 and an alpha value
     * of 128. The alfa value is used to make the color transparent.
     */
    public Color getTransparentBackgroundColor();

    /**
     * Method to get the color of the text in the gamestate.
     * This color is used for all gamestates.
     * @return the Color WHITE.
     */
    public Color getGamestateTxtColor();

    /**
     * Method to get the color of the dotted line in the
     * middle of the game. This color should not be the same
     * as the background color.
     * @return the Color WHITE.
     */
    public Color getDottedLineColor();

    /**
     * Method to get the color of the ball.
     * @return the Color WHITE.
     */
    public Color getBallColor();

    /**
     * Method to get the color of the paddle of player 1.
     * @return the Color RED.
     */
    public Color getPaddle1Color();

    /**
     * Method to get the color of the paddle of player 2.
     * @return the Color BLUE.
     */
    public Color getPaddle2Color();
    
    /**
     * Method to get the color of the text in the score display.
     * @return the Color WHITE.
     */
    public Color getScoreTxtColor();

    /**
     * Method to get the color of the score of player 1.
     * @return the Color RED.
     */
    public Color getScorePlayer1Color();

    /**
     * Method to get the color of the score of player 2.
     * @return the Color BLUE.
     */
    public Color getScorePlayer2Color();
}
