package no.uib.inf101.sem2.pong.model;

/**
 * This is the model class for the Paddle object
 * The paddel class represents the objects that the players can 
 * control to hit the ball and score in the game. This class contains 
 * information about the paddle's position, dimension and velocity.
*/
public class Paddle {

    private int paddleXPos; 
    private int paddleYPos; 
    private int paddleWidth; 
    private int paddleHeight;
     
    private int paddleYDirVelocity;
    
    /**
     * Constructor for the Paddle class. A paddle is initialized with
     * a x and y position, a width and a height. The paddles are the
     * objects that the rectangular objects that the
     * players can control to hit the ball and score a goal/point.
     * @param paddleXPos the x position of the paddle
     * @param paddleYPos the y position of the paddle
     * @param paddleWidth the width of the paddle
     * @param paddleHeight the height of the paddle
     */
    public Paddle(int paddleXPos, int paddleYPos, int paddleWidth, int paddleHeight) {
        this.paddleXPos = paddleXPos;
        this.paddleYPos = paddleYPos;
        this.paddleWidth = paddleWidth;
        this.paddleHeight = paddleHeight;
    }

    /**
     * Method for setting the y direction of the paddle.
     * @param yDirection the y direction to set the paddle to.
     * The paddle can only move vertically, and the y direction
     * can be changed by giving a integer argument. 
     */
    public void setPaddleYdirection(int yDirection) {
        paddleYDirVelocity = yDirection;
    }

    /**
     * Method for moving the paddle objects.
     * The paddles can only move vertically so the y 
     * position is changed by adding the y velocity/speed.
     */
    void movePaddle() {
        paddleYPos += paddleYDirVelocity; 
    }
    
    /**
     * Method for setting the y position of the paddle.
     * @param paddleYPos the preffered position to set the paddle to. 
     * The argument must be a integer value.
     */
    void setPaddleYPos(int paddleYPos) {
        this.paddleYPos = paddleYPos;
    }

    /**
     * Method for getting the x position of the paddle.
     * @return the x position of the paddle.
     */
    public int getPaddleXPos() {
        return paddleXPos;
    }
    
    /**
     * Method for getting the y position of the paddle.
     * @return the y position of the paddle.
     */
    public int getPaddleYPos() {
        return paddleYPos;
    }
    
    /**
     * Method for getting the width of the paddle.
     * @return the width of the paddle.
     */
    public int getPaddleWidth() {
        return paddleWidth;
    }
    
    /**
     * Method for getting the height of the paddle.
     * @return the height of the paddle.
     */
    public int getPaddleHeight() {
        return paddleHeight;
    }

    /**
     * Method for getting the y velocity/speed of the paddle.
     * @return the y velocity of the paddle.
     */
    int getPaddleYDirVelocity() {
        return paddleYDirVelocity;
    }
}
