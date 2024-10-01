package no.uib.inf101.sem2.pong.model;

import java.util.Random;
import no.uib.inf101.sem2.pong.controller.ControllablePongModel;
import no.uib.inf101.sem2.pong.view.ViewablePongModel;

/**
 * This is the model class for the Pong game.
 * The PongModel class represents the game state and contains the logic
 * for updating the game. The class implements the ViewablePongModel,
 * ControllablePongModel and Runnable interfaces.
 * The ViewablePongModel interface is implemented to allow the view to
 * access the elements that are to be displayed. ControllablePongModel
 * is implemented to allow the controller to access the elements that
 * are to be controlled. The Runnable interface is implemented to allow
 * the game thread to run the game logic.
 */
public class PongModel implements ViewablePongModel, ControllablePongModel, Runnable {
    
    private final int GAME_WIDTH = 1000;
    private final int GAME_HEIGHT = 550;
    private final int PADDLE_HEIGHT = 100;
    private final int PADDLE_WIDTH = 25;
    private final int BALL_DIAMETER = 20;

    private Random random;
    private GameState gamestate = GameState.WELCOME_SCREEN;
    private Thread gameThread;

    private Paddle paddle1; 
    private Paddle paddle2; 
    private Ball ball; 

    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    
    /**
     * Constructor for the PongModel.
     * When the model is initialized, the game starts and paddles
     * and a ball are created. The game thread is also started.
     * The run method is called when the game thread is started.
     */
    public PongModel() {
        makeNewPaddles();
        makeNewBall();

        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        if (gamestate == GameState.ACTIVE_GAME) { 
            ball.moveBall(); 
            paddle1.movePaddle();
            paddle2.movePaddle();
            checkCollisions();
        }
    }

    /**
     * Method for creating new paddles. One paddle is created on the
     * left side of the screen and one on the right side. They are
     * placed in the middle of the screen vertically. 
     */
    void makeNewPaddles() {
        paddle1 = new Paddle(25, GAME_HEIGHT/2 - PADDLE_HEIGHT/2, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle2 = new Paddle(GAME_WIDTH - 25 - PADDLE_WIDTH, GAME_HEIGHT/2 - PADDLE_HEIGHT/2, PADDLE_WIDTH, PADDLE_HEIGHT);  
    }

    /**
     * Method for creating a new ball. 
     * The ball is created in the middle of the screen
     * on the x-axis and at a random position on the y-axis.
     * The random position on the y-axis is between 0 and 
     * GAME_HEIGHT-BALL_DIAMETER. This is done to make sure 
     * that the ball is not placed outside the gamescreen.
     * The balls width and height are set to the field 
     * BALL_DIAMETER. This makes the ball circular.
     */
    void makeNewBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    private void checkCollisions() {
        checkPaddleWallCollision();
        checkBallWallCollision();
        checkBallPaddleCollision();
        checkBallGoalCollision();
    }

    /**
     * Method for checking if the paddles collide with the walls. The 
     * walls are the top and bottom of the gamescreen. If the paddles
     * collide with a wall, the paddle is placed on the edge of the 
     * wall to prevent them from going out of bounds.
     */
    void checkPaddleWallCollision() {
        // check paddle 1
        if (paddle1.getPaddleYPos() <= 0) {
            paddle1.setPaddleYPos(0);
        }
        if (paddle1.getPaddleYPos() >= GAME_HEIGHT - paddle1.getPaddleHeight()) {
            paddle1.setPaddleYPos(GAME_HEIGHT - paddle1.getPaddleHeight());
        }
        // check paddle 2
        if (paddle2.getPaddleYPos() <= 0) {
            paddle2.setPaddleYPos(0);
        }
        if (paddle2.getPaddleYPos() >= GAME_HEIGHT - paddle2.getPaddleHeight()) {
            paddle2.setPaddleYPos(GAME_HEIGHT - paddle2.getPaddleHeight());
        } 
    }

    /**
     * Method for checking if the ball collides with the walls. The 
     * walls are the top and bottom of the gamescreen. If the ball
     * collides with a wall, the ball bounces off the wall. The 
     * direction of the ball is reversed and the velocity 
     * is kept the same. 
     */
    void checkBallWallCollision() {
        if (ball.getBallY() <= 0) {
            ball.setBallYDirection(-ball.getBallYVelocity());
        }
        if (ball.getBallY() >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setBallYDirection(-ball.getBallYVelocity());
        }
    }

    private void checkBallPaddleCollision() {
        checkBallPaddle1Collision();
        checkBallPaddle2Collision();
    }

    /**
     * Method for checking if the ball collides with paddle 1
     * If the ball collides with the paddle, the ball bounces
     * off the paddle. The direction of the ball is reversed and the 
     * velocity is increased to make the game more challenging. 
     * 
     * ChatGPT helped me with the situation where the ball gets stuck
     * inside the paddle because this caused a glitch in the game.
     */
    void checkBallPaddle1Collision() {
        // check if the ball collides with the side of the paddle
        if (ball.getBallX() <= paddle1.getPaddleXPos() + paddle1.getPaddleWidth() && 
            ball.getBallY() + ball.getBallHeight() >= paddle1.getPaddleYPos() && 
            ball.getBallY() <= paddle1.getPaddleYPos() + paddle1.getPaddleHeight()) 
        {
            ball.reverseXVelocity();  
            ball.reverseYVelocity();
            ball.increaseVelocity(); 
    
            // check if the ball has become "stuck" inside the paddle
            if (ball.getBallX() + ball.getBallWidth() > paddle1.getPaddleXPos() && 
                ball.getBallY() + ball.getBallHeight()/2 >= paddle1.getPaddleYPos() && 
                ball.getBallY() + ball.getBallHeight()/2 <= paddle1.getPaddleYPos() + paddle1.getPaddleHeight()) 
            {
                // move the ball just outside the paddle
                ball.setBallXPos(paddle1.getPaddleXPos() + paddle1.getPaddleWidth() + 1);
    
                // reverse the Y-velocity to bounce the ball off the paddle
                ball.reverseYVelocity();
            }
            // check if the ball collides with the top or bottom (the short side) of the paddle
            else if (ball.getBallY() <= paddle1.getPaddleYPos() || 
                    ball.getBallY() + ball.getBallHeight() >= paddle1.getPaddleYPos() + paddle1.getPaddleHeight())
            {
                ball.reverseYVelocity();
            }
        }
    }
    
    /**
     * Method for checking if the ball collides with paddle 2
     * If the ball collides with the paddle, the ball bounces
     * off the paddle. The direction of the ball is reversed and the
     * velocity is increased to make the game more challenging.
     * 
     * ChatGPT helped me with the situation where the ball gets stuck
     * inside the paddle because this caused a glitch in the game.
     */
    void checkBallPaddle2Collision() {
        // check if the ball collides with the side of the paddle
        if (ball.getBallX() >= paddle2.getPaddleXPos() - paddle2.getPaddleWidth() && 
            ball.getBallY() + ball.getBallHeight() >= paddle2.getPaddleYPos() && 
            ball.getBallY() <= paddle2.getPaddleYPos() + paddle2.getPaddleHeight()) 
        {
            ball.reverseXVelocity();             
            ball.increaseVelocity(); 

            // check if the ball has become "stuck" inside the paddle
            if (ball.getBallX() + ball.getBallWidth() > paddle2.getPaddleXPos() && 
                ball.getBallY() + ball.getBallHeight()/2 >= paddle2.getPaddleYPos() && 
                ball.getBallY() + ball.getBallHeight()/2 <= paddle2.getPaddleYPos() + paddle2.getPaddleHeight()) 
            {
                // move the ball just outside the paddle
                ball.setBallXPos(paddle2.getPaddleXPos() - paddle2.getPaddleWidth() - 1);

                // reverse the Y-velocity to bounce the ball off the paddle
                ball.reverseYVelocity();
            }
            // check if the ball collides with the top or bottom (the short side) of the paddle
            else if (ball.getBallY() <= paddle2.getPaddleYPos() || 
                    ball.getBallY() + ball.getBallHeight() >= paddle2.getPaddleYPos() + paddle2.getPaddleHeight())
            {
                ball.reverseYVelocity();
            }
        }
    }

    /**
     * Method for checking if the ball collides with the goal. The 
     * goal is the left and right side of the gamescreen. If the ball
     * "collides" with a goal, the ball is reset to the middle of the
     * screen and the paddles are reset to their initial positions. 
     * The score of the player who scored is increased by 1.
     */
    void checkBallGoalCollision() {
        if (ball.getBallX() <= 0) {
            makeNewBall();
            makeNewPaddles();
            scorePlayer2++;
        }
        if(ball.getBallX() >= GAME_WIDTH - BALL_DIAMETER) {
            makeNewBall();
            makeNewPaddles();
            scorePlayer1++;
        }
    }

    @Override
    public Paddle getPaddle1() {
        return this.paddle1;
    }
    
    @Override
    public Paddle getPaddle2() {
        return this.paddle2;
    }

    @Override
    public Ball getBall() {
        return this.ball;
    }    

    @Override
    public int getScorePlayer1() {
        return this.scorePlayer1;
    }

    @Override
    public int getScorePlayer2() {
        return this.scorePlayer2;
    }

    @Override
    public GameState getGameState() {
        return this.gamestate;
    }

    @Override
    public void setGameStateToActive() {
        this.gamestate = GameState.ACTIVE_GAME;
    }

    @Override
    public void setGameStateToGameOver() {
        this.gamestate = GameState.GAME_OVER;
    }

    @Override
    public void setGameStateToWelcomeScreen() {
        this.gamestate = GameState.WELCOME_SCREEN;
    }

    @Override
    public void setScorePlayer1(int resetScoreToValue) {
        this.scorePlayer1 = resetScoreToValue;
    }

    @Override
    public void setScorePlayer2(int resetScoreToValue) {
        this.scorePlayer2 = resetScoreToValue;
    }

    @Override
    public int getGameWidth() {
        return this.GAME_WIDTH;
    }

    @Override
    public int getGameHeight() {
        return this.GAME_HEIGHT;
    }

    /**
     * Method for getting the height of the paddle
     * @return the height of the paddle
     */
    int getPaddleHeight() {
        return this.PADDLE_HEIGHT;
    }

    /**
     * Method for getting the width of the paddle
     * @return the width of the paddle
     */
    int getPaddleWidth() {
        return this.PADDLE_WIDTH;
    }

    /**
     * Method for getting the diameter of the ball
     * @return the diameter of the ball
     */
    int getBallDiameter() {
        return this.BALL_DIAMETER;
    }
}