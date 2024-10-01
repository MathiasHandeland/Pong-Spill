package no.uib.inf101.sem2.pong.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.awt.Color;

/**
 * The TestDefaultColorTheme class contains the tests for the
 * DefaultColorTheme class.
 * The tests are used to check if the DefaultColorTheme class
 * returns the correct colors.
 */
public class TestDefaultColorTheme {

    @Test
    public void sanityTestDefaultColorTheme() {
        ColorTheme color = new DefaultColorTheme();
        assertEquals(new Color(0,0,61), color.getBackgroundColor());
        assertEquals(new Color(0,0,0, 128), color.getTransparentBackgroundColor());
        assertEquals(Color.WHITE, color.getGamestateTxtColor());
        assertEquals(Color.WHITE, color.getDottedLineColor());
        assertEquals(Color.WHITE, color.getBallColor());
        assertEquals(Color.RED, color.getPaddle1Color());
        assertEquals(Color.BLUE, color.getPaddle2Color());
        assertEquals(Color.WHITE, color.getScoreTxtColor());
        assertEquals(Color.RED, color.getScorePlayer1Color());
        assertEquals(Color.BLUE, color.getScorePlayer2Color());    
    }
}
