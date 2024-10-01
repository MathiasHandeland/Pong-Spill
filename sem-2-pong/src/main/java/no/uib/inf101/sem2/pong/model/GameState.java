package no.uib.inf101.sem2.pong.model;

/**
 * This is an enum for the different game states.
 * The game state decides what is drawn on the screen, 
 * what is updated in the model and what can be controlled by the user.
 * The game can be in one of the following states:
 * WELCOME_SCREEN: the game is at the welcome screen where the player 
 * can start the game.
 * ACTIVE_GAME: the game is currently being played and the view is 
 * updated based on input from the user,
 * that is handled by the controller.
 * GAME_OVER: the game is over and the player can view their score and
 * choose to go back to the WELCOME_SCREEN where the game can be reset.
 */
public enum GameState {
    
    WELCOME_SCREEN, ACTIVE_GAME, GAME_OVER;    
}
