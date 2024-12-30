package model;

class AppleImpl implements Apple {
  private Coordinate location;

  public AppleImpl(Coordinate location) {
    this.location = location;
  }

  @Override
  public Coordinate getLocation() {
    return location;
  }

  @Override
  public void updateLocation(int xBound, int yBound) {
    int newX = location.getX();
    int newY = location.getY();
    while (newX == location.getX() && newY == location.getY()) {
      newX = (int) (Math.random() * xBound);
      newY = (int) (Math.random() * yBound);
    }
    this.location = new CoordinateImpl(newX, newY);
  }
}
