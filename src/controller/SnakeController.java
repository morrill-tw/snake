package controller;

import view.PlayerActions;

public interface SnakeController extends PlayerActions {

  /**
   * Starts a game of snake. Continuously runs the game loop until the game is deemed over.
   */
  void run() throws InterruptedException;

}
