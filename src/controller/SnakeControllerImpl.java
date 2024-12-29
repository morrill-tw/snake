package controller;

import model.MutableSnakeModel;
import view.SnakeView;

public class SnakeControllerImpl implements SnakeController {
  private final MutableSnakeModel model;
  private final SnakeView view;

  public SnakeControllerImpl(MutableSnakeModel model, SnakeView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void run() {
    while(!model.isGameOver()) {
      model.moveSnake();
      view.renderGame();
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}
