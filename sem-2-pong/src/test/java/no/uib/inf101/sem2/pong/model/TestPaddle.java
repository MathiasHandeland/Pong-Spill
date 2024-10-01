package no.uib.inf101.sem2.pong.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The TestPaddle class contains the tests for the Paddle class.
 * The critical things to test are:
 * - Paddle constructor creates a new paddle object
 * - check some of the getters and setters
 * - movePaddle() method moves the paddle object
 */
public class TestPaddle {
    
    @Test
    public void testPaddleConstructor() {
        Paddle paddle = new Paddle(0, 0, 50, 10);
        assertEquals(0, paddle.getPaddleXPos());
        assertEquals(0, paddle.getPaddleYPos());
        assertEquals(50, paddle.getPaddleWidth());
        assertEquals(10, paddle.getPaddleHeight());
    }

    @Test
    public void testGetPaddleXPos() {
        Paddle paddle = new Paddle(100, 200, 50, 20);
        int expectedXPos = 100;
        int actualXPos = paddle.getPaddleXPos();
        assertEquals(expectedXPos, actualXPos);
    }

    @Test
    public void testSetPaddleYPos() {
        Paddle paddle = new Paddle(0, 0, 50, 10);
        paddle.setPaddleYPos(5);
        assertEquals(5, paddle.getPaddleYPos());
    }

    @Test
    public void testSetPaddleYdirection() {
        Paddle paddle = new Paddle(0, 0, 50, 10);
        paddle.setPaddleYdirection(5);
        assertEquals(5, paddle.getPaddleYDirVelocity());
    }

    @Test
    public void testMovePaddle() {
        Paddle paddle = new Paddle(0, 0, 50, 10);
        paddle.setPaddleYdirection(5);
        paddle.movePaddle();
        assertEquals(5, paddle.getPaddleYPos());
    }   
}


