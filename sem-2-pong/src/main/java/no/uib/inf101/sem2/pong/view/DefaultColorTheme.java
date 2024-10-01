package no.uib.inf101.sem2.pong.view;

import java.awt.Color;

/**
 * The DefaultColorTheme class implements the ColorTheme interface.
 * The DefaultColorTheme class contains the default colors of the game.
 * This class contains the implementation of the methods in the 
 * ColorTheme interface.  
 * The DefaultColorTheme class is used by the PongView class to get the
 * different colors used in the game and display them on the screen.
 */
public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getBackgroundColor() {
        return new Color(0,0,61);
    }

    @Override
    public Color getTransparentBackgroundColor() {
        return new Color(0,0,0, 128);
    }

    @Override
    public Color getGamestateTxtColor() {
        return Color.WHITE;
    }

    @Override
    public Color getDottedLineColor() {
        return Color.WHITE;
    }

    @Override
    public Color getBallColor() {
        return Color.WHITE;
    }

    @Override
    public Color getPaddle1Color() {
        return Color.RED;
    }

    @Override
    public Color getPaddle2Color() {
        return Color.BLUE;
    }

    @Override
    public Color getScoreTxtColor() {
        return Color.WHITE;
    }

    @Override
    public Color getScorePlayer1Color() {
        return Color.RED;
    }

    @Override
    public Color getScorePlayer2Color() {
        return Color.BLUE;
    }
}
