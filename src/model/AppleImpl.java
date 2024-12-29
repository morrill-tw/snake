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
  public void updateLocation() {
    int newX = location.getX();
    int newY = location.getY();
    while (newX == location.getX() && newY == location.getY()) {
      newX = (int) (Math.random() * 10) + 1;
      newY = (int) (Math.random() * 10) + 1;
    }
    this.location = new CoordinateImpl(newX, newY);
  }
}
