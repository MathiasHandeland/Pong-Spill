package no.uib.inf101.sem2.pong.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * The TestPongModel class contains the tests for the PongModel class.
 * The tests are used to check if the PongModel class actually does
 * what it is supposed to do. The critical things to test are:
 * - makeNewPaddles() method creates two new paddles
 * - makeNewBall() method creates a new ball
 * - checkPaddleWallCollision() method checks if the paddles collide 
 * with the wall
 * - the checkPaddleBallCollision methods to checks if the ball 
 * collides with the paddles
 * - checkBallWallCollision() method checks if the ball collides with
 * the wall
 * - checkBallGoalCollision() method checks if the ball collides with
 * the goal
 */
public class TestPongModel {
    
    @Test
    public void testMakeNewPaddles() {
        PongModel model = new PongModel();
        model.makeNewPaddles();
        // Check that the paddles were created
        assertNotNull(model.getPaddle1());
        assertNotNull(model.getPaddle2());

        // Check that the paddles are in the correct position
        Paddle paddle1 = model.getPaddle1();
        Paddle paddle2 = model.getPaddle2();

        assertEquals(25, paddle1.getPaddleXPos());
        assertEquals(model.getGameHeight() / 2 - model.getPaddleHeight() / 2, paddle1.getPaddleYPos());
        assertEquals(25, paddle1.getPaddleWidth());
        assertEquals(100, paddle1.getPaddleHeight());
        
        assertEquals(model.getGameWidth() - model.getPaddleWidth() - paddle2.getPaddleWidth(), paddle2.getPaddleXPos());
        assertEquals(model.getGameHeight() / 2 - model.getPaddleHeight() / 2, paddle2.getPaddleYPos());
        assertEquals(25, paddle2.getPaddleWidth());
        assertEquals(100, paddle2.getPaddleHeight());
    }

    @Test 
    public void testMakeNewBall() {
        PongModel model = new PongModel();
        model.makeNewBall();
        // verify that newBall() method creates a new ball
        assertNotNull(model.getBall());
        // verify that the ball's width and height are set to BALL_DIAMETER
        assertEquals(model.getBall().getBallWidth(), model.getBallDiameter());
        assertEquals(model.getBall().getBallHeight(), model.getBallDiameter());
        // verify that the ball is created in the middle of the screen on the x-axis
        assertEquals(model.getBall().getBallX(), (model.getGameWidth()/2)-(model.getBallDiameter()/2));
        // verify that the ball is created at a random position on the y-axis
        assertTrue(model.getBall().getBallY() >= 0 && model.getBall().getBallY() <= model.getGameHeight()-model.getBallDiameter());
        // verify that the ball is given a random velocity
        assertTrue(model.getBall().getBallXVelocity() != 0 && model.getBall().getBallYVelocity() != 0);    
    }

    @Test
    public void testCheckPaddleWallCollision() {
        PongModel model = new PongModel();
        model.getPaddle1().setPaddleYPos(-10);
        model.getPaddle2().setPaddleYPos(model.getGameHeight() + 10 - model.getPaddle2().getPaddleHeight());
        model.checkPaddleWallCollision();
        assertEquals(model.getPaddle1().getPaddleYPos(), 0);
        assertEquals(model.getPaddle2().getPaddleYPos(), model.getGameHeight() - model.getPaddle2().getPaddleHeight());
    }

    @Test
    public void testCheckBallWallCollision() {
        PongModel model = new PongModel();
        Ball ball = model.getBall();

        // Set ball position and velocity so that it collides with top wall
        ball.setBallYPos(0);
        ball.setBallYDirection(-5);
        model.checkBallWallCollision();
        assertEquals(5, ball.getBallYVelocity());

        // Set ball position and velocity so that it collides with bottom wall
        ball.setBallYPos(model.getGameHeight() - ball.getBallHeight());
        ball.setBallYDirection(5);
        model.checkBallWallCollision();
        assertEquals(-5, ball.getBallYVelocity());

        // Set ball position and velocity so that it does not collide with a wall
        ball.setBallYPos(model.getGameHeight() / 2);
        ball.setBallYDirection(3);
        model.checkBallWallCollision();
        assertEquals(3, ball.getBallYVelocity());
    }

    @Test
    public void testCheckBallPaddle1Collision() {
        PongModel model = new PongModel();
        Ball ball = model.getBall();
        Paddle paddle1 = model.getPaddle1();

        // set initial velocity
        double initialXVelocity = ball.getBallXVelocity();
        double initialYVelocity = ball.getBallYVelocity();
    
        // set ball to collide with paddle1
        ball.setBallXPos(paddle1.getPaddleXPos() + paddle1.getPaddleWidth());
        ball.setBallYPos(paddle1.getPaddleYPos() + paddle1.getPaddleHeight() / 2);
        // check collision with paddle1
        model.checkBallPaddle1Collision();

        // check that the ball's velocity is changed after collision
        assertNotEquals(0, ball.getBallXVelocity());
        assertNotEquals(0, ball.getBallYVelocity());
         
        // check that the ball's velocity is not changed if the ball is not moving towards the paddle
        ball.setBallXDirection(0);
        ball.setBallYDirection(0);
        model.checkBallPaddle1Collision();
        assertEquals(0, ball.getBallXVelocity());
        assertEquals(0, ball.getBallYVelocity());

        // check that the ball's velocity is not changed if the ball is not colliding with the paddle
        ball.setBallXPos(paddle1.getPaddleXPos() + paddle1.getPaddleWidth() + 1);
        ball.setBallYPos(paddle1.getPaddleYPos() + paddle1.getPaddleHeight() / 2);
        model.checkBallPaddle1Collision();
        assertEquals(0, ball.getBallXVelocity());
        assertEquals(0, ball.getBallYVelocity());

        // check that the x velocity or y velocity or both is increased if the ball hits the paddle
        ball.setBallXPos(paddle1.getPaddleXPos() + paddle1.getPaddleWidth() - 1);
        ball.setBallYPos(paddle1.getPaddleYPos() + paddle1.getPaddleHeight() / 2);
        model.checkBallPaddle1Collision();
        assertTrue(ball.getBallXVelocity() != initialXVelocity || ball.getBallYVelocity() != initialYVelocity);
        
        // check if the ball gets stuck in the paddle and the ball is not moved just out of the paddle
        ball.setBallXPos(paddle1.getPaddleXPos() + paddle1.getPaddleWidth() - 1);
        ball.setBallYPos(paddle1.getPaddleYPos() + paddle1.getPaddleHeight() / 2);
        model.checkBallPaddle1Collision();
        assertEquals(paddle1.getPaddleXPos() + paddle1.getPaddleWidth() + 1, ball.getBallX());
        assertEquals(paddle1.getPaddleYPos() + paddle1.getPaddleHeight() / 2, ball.getBallY());
    }
    
    @Test
    public void testCheckBallPaddle2Collision() {
        PongModel model = new PongModel();
        Ball ball = model.getBall();
        Paddle paddle2 = model.getPaddle2();

        // set initial velocity
        double initialXVelocity = ball.getBallXVelocity();
        double initialYVelocity = ball.getBallYVelocity();

        // set ball to collide with paddle2
        ball.setBallXPos(paddle2.getPaddleXPos() - ball.getBallWidth());
        ball.setBallYPos(paddle2.getPaddleYPos() + paddle2.getPaddleHeight() / 2);
        // check collision with paddle2
        model.checkBallPaddle2Collision();

        // check that the ball's velocity is changed after collision
        assertNotEquals(0, ball.getBallXVelocity());
        assertNotEquals(0, ball.getBallYVelocity());

        // check that the ball's velocity is not changed if the ball is not moving towards the paddle
        ball.setBallXDirection(0);
        ball.setBallYDirection(0);
        model.checkBallPaddle2Collision();
        assertEquals(0.0, ball.getBallXVelocity(), 0.00001);
        assertEquals(0, ball.getBallYVelocity());

        // check that the ball's velocity is not changed if the ball is not colliding with the paddle
        ball.setBallXPos(paddle2.getPaddleXPos() - ball.getBallWidth() - 1);
        ball.setBallYPos(paddle2.getPaddleYPos() + paddle2.getPaddleHeight() / 2);
        model.checkBallPaddle2Collision();
        assertEquals(0, ball.getBallXVelocity());
        assertEquals(0, ball.getBallYVelocity());

        // check that the velocity is increased if the ball hits the paddle
        ball.setBallXPos(paddle2.getPaddleXPos() - ball.getBallWidth() + 1);
        ball.setBallYPos(paddle2.getPaddleYPos() + paddle2.getPaddleHeight() / 2);
        model.checkBallPaddle2Collision();
        assertTrue(ball.getBallXVelocity() != initialXVelocity || ball.getBallYVelocity() != initialYVelocity);
        
        // check that the ball is moved just outside the paddle if it gets "stuck" inside the paddle
        ball.setBallXPos(paddle2.getPaddleXPos() + paddle2.getPaddleWidth() + 1);
        ball.setBallYPos(paddle2.getPaddleYPos() + paddle2.getPaddleHeight() / 2);
        model.checkBallPaddle2Collision();
        assertEquals(paddle2.getPaddleXPos() - paddle2.getPaddleWidth() - 1, ball.getBallX(), 0.00001);
        assertEquals(paddle2.getPaddleYPos() + paddle2.getPaddleHeight() / 2, ball.getBallY(), 0.00001);
    }

    @Test
    public void testCheckBallGoalCollision() {
        PongModel model = new PongModel();
        
        // initial scores should be zero
        assertEquals(0, model.getScorePlayer1());
        assertEquals(0, model.getScorePlayer2());

        // check collision with left goal and update score
        model.getBall().setBallXPos(0);
        model.checkBallGoalCollision();
        assertEquals(0, model.getScorePlayer1());
        assertEquals(1, model.getScorePlayer2());

        // check collision with right goal and update score
        model.getBall().setBallXPos(model.getGameWidth() - model.getBallDiameter());
        model.checkBallGoalCollision();
        assertEquals(1, model.getScorePlayer1());
        assertEquals(1, model.getScorePlayer2());

        // check that new paddles and ball are created when a player scores
        model.checkBallGoalCollision();
        assertNotNull(model.getPaddle1());
        assertNotNull(model.getPaddle2());
        assertNotNull(model.getBall());
    }
}
