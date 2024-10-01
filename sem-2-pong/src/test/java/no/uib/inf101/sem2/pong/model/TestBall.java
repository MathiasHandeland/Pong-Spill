package no.uib.inf101.sem2.pong.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * The TestBall class contains the tests for the Ball class.
 * The critical things to test are:
 * - Ball constructor creates a new ball object
 * - moveBall() method moves the ball
 * - increaseVelocity() method increases the velocity of the ball
 * - reverseXVelocity() method reverses the x velocity of the ball
 * - reverseYVelocity() method reverses the y velocity of the ball
 */
public class TestBall {

    Ball ball = new Ball(100, 200, 10, 10);

    @Test
    public void testBallConstructor() {
        assertEquals(100, ball.getBallX());
        assertEquals(200, ball.getBallY());
        assertEquals(10, ball.getBallWidth());
        assertEquals(10, ball.getBallHeight());
    }

    @Test
    public void testMoveBall() {
        ball.setBallXDirection(10);
        ball.setBallYDirection(22);
        ball.moveBall();
        assertEquals(110, ball.getBallX());
        assertEquals(222, ball.getBallY());
    }

    @Test
    public void testReverseXVelocity() {
        ball.setBallXDirection(2);
        ball.setBallYDirection(2);
        ball.reverseXVelocity();
        assertEquals(-2, ball.getBallXVelocity());
    }

    @Test
    public void testReverseYVelocity() {
        ball.setBallXDirection(2);
        ball.setBallYDirection(2);
        ball.reverseYVelocity();
        assertEquals(-2, ball.getBallYVelocity());
    }

    @Test
    public void testIncreaseVelocity() {
        double initialXVelocity = ball.getBallXVelocity();
        double initialYVelocity = ball.getBallYVelocity();

        ball.increaseVelocity();
        // Check that the velocity increased by 15%
        assertEquals(initialXVelocity * 1.15, ball.getBallXVelocity(), 0.001);
        assertEquals(initialYVelocity * 1.15, ball.getBallYVelocity(), 0.001);
        // the delta value is used to account for any small rounding errors when checking equality
   }
}
