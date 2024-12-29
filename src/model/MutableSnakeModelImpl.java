package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import shared.Direction;

public class MutableSnakeModelImpl implements MutableSnakeModel {
  private final List<Coordinate> components; // 0-based, last element represents snake head
  private final Apple apple;
  private int numApplesEaten;
  private final int gridSize;
  private Direction direction;
  private boolean gameOver;

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

    public int getVal() {
      return this.val;
    }
  }

  /**
   * Creates a new SnakeModel with the given gridSize. The grid is always a square, and it's size
   * is equal to its side length. The initial head location of the snake is one unit left from the
   * center of the grid, the initial size of the snake is three units long, the initial direction
   * of the snake is right, and the initial location of the apple is three units right from the
   * center of the grid.
   * @param size the side length of the game grid
   */
  public MutableSnakeModelImpl(GridSize size) {
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

  @Override
  public void moveSnake() {
    if (gameOver) {
      throw new IllegalStateException("Cannot move after game has ended.");
    }
    Coordinate newComponent = createNewComponent();
    if (isOutOfBounds(newComponent)) {
      this.gameOver = true;
      return;
    } else if (newComponent.equals(apple.getLocation())) {
      apple.updateLocation();
      while(isAppleOnTopOfSnake()) {
        apple.updateLocation();
      }
      numApplesEaten++;
    } else {
      components.remove(0);
    }
    components.add(newComponent);
  }

  // Determines whether the apple has the same coordinate as a snake component.
  // Returns true if yes, and false otherwise.
  private boolean isAppleOnTopOfSnake() {
    for (Coordinate component : components) {
      if (apple.getLocation().equals(component)) {
        return true;
      }
    }
    return false;
  }

  // Returns a new head for the snake
  private Coordinate createNewComponent() {
    if (direction == Direction.UP || direction == Direction.DOWN) {
      return new CoordinateImpl(components.get(components.size() - 1).getX(),
              components.get(components.size() - 1).getY() + direction.getVal());
    } else {
      return new CoordinateImpl(components.get(components.size() - 1).getX() + direction.getVal(),
              components.get(components.size() - 1).getY());
    }
  }

  // Returns true if the given location is outside the bounds set
  // by the size of the game grid
  private boolean isOutOfBounds(Coordinate location) {
    boolean outOfBoundsX = location.getX() < 0 || location.getX() >= gridSize;
    boolean outOfBoundsY = location.getY() < 0 || location.getY() >= gridSize;
    return outOfBoundsX || outOfBoundsY;
  }

  @Override
  public void updateDirection(Direction newDirection) {
    if (gameOver) {
      throw new IllegalStateException("Cannot update direction after game has ended.");
    }
    if (isInvalidDirection(newDirection)) {
      throw new IllegalArgumentException("Cannot update direction to: " + newDirection + " as it " +
              "would cause the snake to fold in on itself.");
    }
    this.direction = newDirection;
  }

  // Determines whether the given direction would cause the snake to fold in on itself.
  // Returns true if it would and false otherwise
  private boolean isInvalidDirection(Direction direction) {
    switch (direction) {
      case UP:
        return this.direction == Direction.DOWN;
      case DOWN:
        return this.direction == Direction.UP;
      case LEFT:
        return this.direction == Direction.RIGHT;
      case RIGHT:
        return this.direction == Direction.LEFT;
      default:
        return false;
    }
  }

  @Override
  public int getNumApplesEaten() {
    return numApplesEaten;
  }

  @Override
  public List<Point> getComponents() {
    List<Point> copy = new ArrayList<Point>();
    for (Coordinate coordinate: components) {
      copy.add(new Point(coordinate.getX(), coordinate.getY()));
    }
    return copy;
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
