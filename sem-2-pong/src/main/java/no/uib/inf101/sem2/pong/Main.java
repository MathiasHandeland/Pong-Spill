package no.uib.inf101.sem2.pong;

import no.uib.inf101.sem2.pong.controller.PongController;
import no.uib.inf101.sem2.pong.model.PongModel;
import no.uib.inf101.sem2.pong.view.PongView;
import javax.swing.JFrame;

/**
 * This is the main class of the Pong game.
 * It creates a new PongModel, a new PongView and a new PongController.
 * The PongModel is the model of the game and it is responsible for
 * updating the state of the game. The PongView is the view of the game
 * and it is responsible for drawing the game.
 * The PongController is the controller of the game and it is 
 * responsible for updating the model and the view based on used input.
 * The main method also creates a JFrame and adds the PongView to it. 
 * This makes the PongView visible on the screen.
 * The main method also contains a game loop that updates the model 
 * and repaints the view. The Thread.sleep(10) makes the game run at
 * 100 frames per second. This is done to make the game run smoothly.
 * @author Mathias Lillesund Handeland
 */
public class Main {
 
    public static void main(String[] args) {

      PongModel model = new PongModel(); 
      PongView view = new PongView(model);
      new PongController(model, view);
      
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
      frame.setTitle("PONG");
      frame.setContentPane(view);
      frame.pack();
      frame.setVisible(true);
      frame.setResizable(false);
      frame.setLocationRelativeTo(null);

      // Game loop
      while (true) {
        model.run();
        view.repaint();
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }  
    } 
}
