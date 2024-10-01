package no.uib.inf101.sem2.pong.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import no.uib.inf101.sem2.pong.model.Ball;
import no.uib.inf101.sem2.pong.model.GameState;
import no.uib.inf101.sem2.pong.model.Paddle;
 
/**
 * The PongView class draws the game based on the model given.
 * The view gets the information it needs from the model through the 
 * methods in the ViewablePongModel interface.
 * The view is a JPanel and is added to the JFrame in the main class.
 * The view is responsible for drawing the game, the paddles, the ball,
 * the score and the welcome screen and game over screen.
 * The Dimension GAME_SIZE is the size of the game. The colors of the 
 * game are set in the ColorTheme interface. Therefore the view needs to
 * have a ColorTheme object. 
 */
public class PongView extends JPanel {

    private final Dimension GAME_SIZE;
    private ViewablePongModel model;
    private ColorTheme colortheme;

    /**
     * Constructor for the PongView class.
     * Initializes the model and sets the preferred size, focusability,
     * and background color of the panel.
     * @param model The ViewablePongModel representing the state 
     * of the game. The view draws the game based on the model.
     */
    public PongView(ViewablePongModel model) {
        this.model = model;
        this.colortheme = new DefaultColorTheme();

        this.GAME_SIZE = new Dimension(model.getGameWidth(), model.getGameHeight());
        this.setPreferredSize(GAME_SIZE);
        this.setFocusable(true);
        this.setBackground(colortheme.getBackgroundColor()); 
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2); 
    }

    private void drawGame(Graphics2D g2) {
        if (this.model.getGameState() == GameState.WELCOME_SCREEN) {
            drawWelcomeScreen(g2);
            this.model.setScorePlayer1(0);
            this.model.setScorePlayer2(0);
        }
        else if (this.model.getScorePlayer1() == 7) { 
            this.model.setGameStateToGameOver();
            drawGameOverScreenPlayer1Wins(g2);
        }
        else if (this.model.getScorePlayer2() == 7) {
            this.model.setGameStateToGameOver();
            drawGameOverScreenPlayer2Wins(g2);
        } 
        
        if (this.model.getGameState() == GameState.ACTIVE_GAME) {
            drawDottedLine(g2);
            drawball(g2);
        }

        drawPaddles(g2);
        drawScore(g2);
    }

    private void drawWelcomeScreen(Graphics2D g2) {
        g2.setColor(colortheme.getTransparentBackgroundColor());
        g2.fillRect(0, 0, model.getGameWidth(), model.getGameHeight());

        g2.setColor(colortheme.getGamestateTxtColor());
        g2.setFont(new Font("Monospaced", Font.BOLD, 150));
        Inf101Graphics.drawCenteredString(g2, "PONG", model.getGameWidth() / 2,
        model.getGameHeight() / 2);

        g2.setFont(new Font("Monospaced", Font.BOLD, 24));
        Inf101Graphics.drawCenteredString(g2, "Press space to start", 
        model.getGameWidth() / 2, model.getGameHeight() / 2 + 100);       
    }

    private void drawGameOverScreenPlayer1Wins(Graphics2D g2) {
        g2.setColor(colortheme.getTransparentBackgroundColor());
        g2.fillRect(0, 0, model.getGameWidth(), model.getGameHeight());

        g2.setColor(colortheme.getGamestateTxtColor());
        g2.setFont(new Font("Monospaced", Font.BOLD, 100));
        Inf101Graphics.drawCenteredString(g2, "GAME OVER", model.getGameWidth() / 2,
        model.getGameHeight() / 2);
    
        g2.setFont(new Font("Monospaced", Font.BOLD, 20));
        Inf101Graphics.drawCenteredString(g2, "Player 1 Won!", model.getGameWidth() / 2,
        model.getGameHeight() / 2 + 100);
        Inf101Graphics.drawCenteredString(g2, "Press Enter to return to the welcome screen",
        model.getGameWidth() / 2, model.getGameHeight() / 2 + 150);
    }

    private void drawGameOverScreenPlayer2Wins(Graphics2D g2) {
        g2.setColor(colortheme.getTransparentBackgroundColor());
        g2.fillRect(0, 0, model.getGameWidth(), model.getGameHeight());

        g2.setColor(colortheme.getGamestateTxtColor());
        g2.setFont(new Font("Monospaced", Font.BOLD, 100));
        Inf101Graphics.drawCenteredString(g2, "GAME OVER", model.getGameWidth() / 2,
        model.getGameHeight() / 2);
    
        g2.setFont(new Font("Monospaced", Font.BOLD, 20));
        Inf101Graphics.drawCenteredString(g2, "Player 2 Won!", model.getGameWidth() / 2,
        model.getGameHeight() / 2 + 100); 
        Inf101Graphics.drawCenteredString(g2, "Press Enter to return to the welcome screen",
        model.getGameWidth() / 2, model.getGameHeight() / 2 + 150);
    }
    
    private void drawDottedLine(Graphics2D g2) {
        g2.setColor(colortheme.getDottedLineColor());
        for (int i = 0; i < model.getGameHeight(); i += 63) {
            g2.fillRect(model.getGameWidth() / 2, i, 6, 45);
        }
    }

    private void drawPaddles(Graphics2D g2) {
        Paddle paddle1 = model.getPaddle1();
        g2.setColor(colortheme.getPaddle1Color());
        g2.fillRect(paddle1.getPaddleXPos(), paddle1.getPaddleYPos(), 
        paddle1.getPaddleWidth(), paddle1.getPaddleHeight());
        
        Paddle paddle2 = model.getPaddle2();
        g2.setColor(colortheme.getPaddle2Color());
        g2.fillRect(paddle2.getPaddleXPos(), paddle2.getPaddleYPos(), 
        paddle2.getPaddleWidth(), paddle2.getPaddleHeight());
    }

    private void drawball(Graphics2D g2) {
        Ball ball = model.getBall();
        g2.setColor(colortheme.getBallColor());
        g2.fillOval(ball.getBallX(), ball.getBallY(),
        ball.getBallWidth(),ball.getBallHeight());
    }

    private void drawScore(Graphics2D g2) {        
        g2.setFont(new Font("SansSerif", Font.BOLD|Font.ITALIC, 24));
        g2.setColor(colortheme.getScoreTxtColor());
        g2.drawString("Score Player 1:", 100, 25);
        g2.setColor(colortheme.getScorePlayer1Color());
        g2.drawString(String.valueOf(model.getScorePlayer1()), 290, 25);

        g2.setColor(colortheme.getScoreTxtColor());
        g2.drawString("Score Player 2:", 700, 25);
        g2.setColor(colortheme.getScorePlayer2Color());
        g2.drawString(String.valueOf(model.getScorePlayer2()), 890, 25);
    }
}