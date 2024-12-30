package controller;

import model.MutableSnakeModel;
import shared.Direction;
import view.SnakeView;

public class SnakeControllerImpl implements SnakeController {
  private final MutableSnakeModel model;
  private final SnakeView view;
  private boolean gameStarted;

  public SnakeControllerImpl(MutableSnakeModel model, SnakeView view) {
    this.model = model;
    this.view = view;
    this.gameStarted = false;
  }

  @Override
  public void run() {
    while(!model.isGameOver()) {
      view.renderGame();
      if (gameStarted) {
        model.moveSnake();
        view.renderGame();
        try {
          Thread.sleep(150);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }

  @Override
  public void startGame() {
    this.gameStarted = true;
  }

  @Override
  public void changeDirection(Direction dir) {
    model.updateDirection(dir);
  }
}
