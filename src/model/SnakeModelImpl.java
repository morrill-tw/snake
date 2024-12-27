package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.Direction;

public class SnakeModelImpl implements SnakeModel {
  private final List<Coordinate> components; // 0-based, last element represents snake head
  private final Apple apple;
  private int numApplesEaten;
  private final int gridSize;
  private Direction direction;
  private boolean gameOver;

  /**
   * Creates a new SnakeModel with the given gridSize. The grid is always a square, and it's size
   * is equal to its side length. The initial head location of the snake is one unit left from the
   * center of the grid, the initial size of the snake is three units long, the initial direction
   * of the snake is left, and the initial location of the apple is three units right from the
   * center of the grid.
   * @param size the side length of the game grid
   */
  public SnakeModelImpl(GridSize size) {
    this.gridSize = size.getVal();
    this.components = new ArrayList<Coordinate>();
    this.numApplesEaten = 0;
    this.direction = Direction.RIGHT;
    this.gameOver = false;

    int gridCenter = (gridSize - 1) / 2;
    this.apple = new AppleImpl(new CoordinateImpl(gridCenter + 3, gridCenter));

    // Add initial components
    this.components.add(new CoordinateImpl(gridCenter - 3, gridCenter));
    this.components.add(new CoordinateImpl(gridCenter - 2, gridCenter));
    this.components.add(new CoordinateImpl(gridCenter - 1, gridCenter)); // The head
  }

  /**
   * Possible values for the size of the game grid.
   */
  public enum GridSize {
    NINE(9),
    THIRTEEN(13),
    SEVENTEEN(17);

    private final int val;

    GridSize(int val) {
      this.val = val;
    }

    int getVal() {
      return this.val;
    }
  }

  @Override
  public int getNumApplesEaten() {
    return numApplesEaten;
  }

  @Override
  public void moveSnake() {
    Coordinate newComponent = createNewComponent();
    if (isOutOfBounds(newComponent)) {
      this.gameOver = true;
      throw new HitBoundaryException();
    } else if (newComponent.equals(apple.getLocation())) {
      components.remove(0);
      apple.updateLocation();
      numApplesEaten++;
    }
    components.add(newComponent);
  }

  // Returns true if the given location is outside the bounds set
  // by the size of the game grid
  private boolean isOutOfBounds(Coordinate location) {
    boolean outOfBoundsX = location.getX() < 0 || location.getX() >= gridSize;
    boolean outOfBoundsY = location.getY() < 0 || location.getY() >= gridSize;
    return outOfBoundsX && outOfBoundsY;
  }

  // Returns a new head for the snake
  private Coordinate createNewComponent() {
    if (direction == Direction.UP || direction == Direction.DOWN) {
      return new CoordinateImpl(components.get(0).getX(),
              components.get(0).getY() + direction.getVal());
    } else {
      return new CoordinateImpl(components.get(0).getX() + direction.getVal(),
              components.get(0).getY());
    }
  }

  @Override
  public Map<Integer, Integer> getComponents() {
    Map<Integer, Integer> copy = new HashMap<>();
    for (Coordinate coordinate: components) {
      copy.put(coordinate.getX(), coordinate.getY());
    }
    return copy;
  }

  @Override
  public void updateDirection(Direction newDirection) {
    this.direction = newDirection;
  }

  @Override
  public int getGridSize() {
    return gridSize;
  }

  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  @Override
  public Point getAppleLocation() {
    return new Point(apple.getLocation().getX(), apple.getLocation().getY());
  }

}
