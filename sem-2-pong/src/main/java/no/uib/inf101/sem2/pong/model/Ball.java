package no.uib.inf101.sem2.pong.model;

import java.util.Random;
/**
 * This is the model class for the Ball object.
 * The ball object is created with a given x and y position and width 
 * and height. The ball is also initialized with a random x and y 
 * direction and a speed of 3. The ball moves in the x and y direction
 * with the given speed. The ball bounces off the walls and paddles.
 * The ball increases its speed by 15% every time it hits a paddle.
 * The ball is removed from the game when it hits the left or right 
 * wall. Then a point is added to the player who "scored" and 
 * a new ball is created, and the game continues.
 */
public class Ball {

    private int ballXPos;
    private int ballYPos;
    private int ballWidth;
    private int ballHeight;

    private double ballXVelocity;
    private double ballYVelocity;
    private int initialBallSpeed = 3;
    private Random random;

    /**
     * Constructor for the Ball class.
     * A ball object is created with the given x and y position 
     * and width and height. The ball is also initialized with a 
     * random x and y direction and a speed of 3.
     * @param ballXPos the x position of the ball
     * @param ballYPos the y position of the ball
     * @param ballWidth the width of the ball
     * @param ballHeight the height of the ball
     */
    public Ball(int ballXPos, int ballYPos, int ballWidth, int ballHeight) {
        this.ballXPos = ballXPos;
        this.ballYPos = ballYPos;
        this.ballWidth = ballWidth;
        this.ballHeight = ballHeight;

        randomXDirection();
        randomYDirection(); 
    }

    /**
     * Method for moving the ball. 
     * The balls x and y position is changed by the x and y velocity. 
     */
    void moveBall() {
        ballXPos += ballXVelocity;
        ballYPos += ballYVelocity;
    }

    private void randomXDirection() {
        random = new Random();
        int randomXDirection = random.nextInt(2) * 2 - 1;
        setBallXDirection(randomXDirection * initialBallSpeed);
    }

    private void randomYDirection() {
        random = new Random();
        int randomYDirection = random.nextInt(2) * 2 - 1;
        setBallYDirection(randomYDirection * initialBallSpeed);   
    }    

    /**
     * Sets the x direction of the ball
     * @param randomXDirection The x direction of 
     * the ball is set to a random value between -1 and 1.
     * The value -1 makes the ball move left and the value 1 makes
     * the ball move right. The random value is multiplied by the 
     * initial speed of the ball.
     */
    void setBallXDirection(double randomXDirection) {
        ballXVelocity = randomXDirection;
    }

    /**
     * Sets the y direction of the ball.
     * @param randomYDirection The y direction of 
     * the ball is set to a random value between -1 and 1.
     * The value -1 makes the ball move up and the value 1 makes
     * the ball move down. The random value is multiplied by the initial 
     * speed of the ball.
     */
    void setBallYDirection(double randomYDirection) {
        ballYVelocity = randomYDirection;
    }

    /**
     * Method to increase the speed of the ball.
     * Everytime the ball hits a paddle the speed is increased by 15%.
     * This makes the game more difficult as the game progresses.
     */
    void increaseVelocity() {
        ballXVelocity *=  1.15; 
        ballYVelocity *=  1.15; 
    }
    
    /**
     * Getter method for the balls x position.
     * @return the balls x position.
     */
    public int getBallX() {
        return ballXPos;
    }

    /**
     * Getter method for the balls y position.
     * @return the balls y position.
     */
    public int getBallY() {
        return ballYPos;
    }

    /**
     * Getter method for the balls width.
     * @return the balls width.
     */
    public int getBallWidth() {
        return ballWidth;
    }

    /**
     * Getter method for the balls height.
     * @return the balls height.
     */
    public int getBallHeight() {
        return ballHeight;
    }
    
    /**
     * Getter method for the balls y direction velocity.
     * @return the balls y velocity.
     */
    double getBallYVelocity() {
        return ballYVelocity;
    }

    /**
     * Getter method for the balls x direction velocity.
     * @return the balls x velocity.
     */
    double getBallXVelocity() {
        return ballXVelocity;
    }

    /*
     * Method used to reverse the ball's direction.
     * when it collides with a wall or paddle. 
     */
    void reverseXVelocity() {
        ballXVelocity = -ballXVelocity;
    }

    /*
     * Method used to reverse the ball's direction.
     * when it collides with a wall or paddle. 
     */
    void reverseYVelocity() {
        ballYVelocity = -ballYVelocity;
    }

    /**
     * Method used to set the balls x position.
     * @param ballXPos the x position of the ball.
     */
    void setBallXPos(int ballXPos) {
        this.ballXPos = ballXPos;
    }

    /**
     * Method used to set the balls y position.
     * @param ballYPos the y position of the ball.
     */
    void setBallYPos(int ballYPos) {
        this.ballYPos = ballYPos;
    }
}