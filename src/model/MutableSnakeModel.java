package model;

import shared.Direction;

/**
 * The current game state.
 */
public interface MutableSnakeModel extends ImmutableSnakeModel {

  /**
   * Adds a new component to the front of the snake, and removes one from
   * the back. If the new component is in contact with an apple, it does not
   * remove a component from the back. In this case, the location of the apple
   * is updated to a new location, different from its previous, and one that
   * does not intersect with any of the snake's components.
   * @throws IllegalStateException if game is over
   */
  void moveSnake();

  /**
   * Updates the direction of the snake to the given one.
   * @param newDirection the new direction of the snake
   * @throws IllegalStateException if the game is over
   * @throws IllegalStateException if updating to the given direction
   *          would cause the snake to fold in on itself
   */
  void updateDirection(Direction newDirection);

}
