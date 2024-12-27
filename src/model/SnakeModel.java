package model;

import java.awt.Point;
import java.util.Map;

import shared.Direction;

/**
 * The current game state.
 */
public interface SnakeModel {

  /**
   * Returns the number of apples that have been eaten by the snake.
   * @return the number of apples eaten
   */
  int getNumApplesEaten();

  /**
   * Adds a new component to the front of the snake, and removes one from
   * the back. If the new component is in contact with an apple, does not
   * remove a component from the back.
   * @throws IllegalStateException if game is over
   */
  void moveSnake();

  /**
   * Returns a map of integers where each key-value pair represents a cartesian coordinate
   * with each key being an x-coordinate and its associated value the y-coordinate.
   * These coordinates represent the location of each component of the snake, with the first
   * coordinate in the map being the tail of the snake and the last being the head.
   * @return the map of coordinates
   */
  Map<Integer, Integer> getComponents();

  /**
   * Updates the direction of the snake to the given one.
   * @param newDirection the new direction of the snake
   * @throws IllegalStateException if the game is over
   */
  void updateDirection(Direction newDirection);

  /**
   * Returns the side length of the game grid.
   * @return the side length of the grid.
   */
  int getGridSize();

  /**
   * Determines whether the game is over or not.
   * @return true if the game is over and false otherwise
   */
  boolean isGameOver();

  /**
   * Returns the location of the apple as a point.
   * @return the location of the apple as a point
   */
  Point getAppleLocation();
}
