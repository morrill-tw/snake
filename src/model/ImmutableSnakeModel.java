package model;

import java.awt.*;
import java.util.List;

public interface ImmutableSnakeModel {

  /**
   * Returns the number of apples that have been eaten by the snake.
   * @return the number of apples eaten
   */
  int getNumApplesEaten();

  /**
   * Returns a list of points where each point represents the location of one component
   * of the snake's body. The first component represents the snake's tail and the last
   * component represents the snake's head.
   * @return a list of points--one for each of the snake's components
   */
  List<Point> getComponents();

  /**
   * Returns the side length of the game grid.
   * @return the side length of the grid.
   */
  int getGridSize();

  /**
   * Determines whether the game is over or not. The game is over if the snake
   * has bumped into the boundary or itself.
   * @return true if the game is over and false otherwise
   */
  boolean isGameOver();

  /**
   * Determines whether the game is won, meaning that the snake has grown to fill out
   * the entirety of the screen.
   * @return true if the game is won and false otherwise
   */
  boolean isGameWon();

  /**
   * Returns the location of the apple as a point.
   * @return the location of the apple as a point
   */
  Point getAppleLocation();
}
