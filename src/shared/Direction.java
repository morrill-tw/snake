package shared;

/**
 * The directions that the snake can travel in.
 */
public enum Direction {

  LEFT(-1),
  RIGHT(1),
  UP(-1),
  DOWN(1);

  private final int val;

  Direction(int val) {
    this.val = val;
  }

  /**
   * Returns the value of this direction.
   * @return the value of this direction
   */
  public int getVal() {
    return this.val;
  }
}
