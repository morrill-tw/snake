package view;

public interface SnakeView {

  void renderGame();

  /**
   *
   * @param gameStarter
   */
  void setPlayerActionLister(PlayerActions gameStarter);
}
