package model;

class CoordinateImpl implements Coordinate {
  private final int x;
  private final int y;

  CoordinateImpl(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) {
      return false;
    } else {
      Coordinate other = (Coordinate) object;
      return (this.getX() == other.getX() && this.getY() == other.getY());
    }
  }

}
