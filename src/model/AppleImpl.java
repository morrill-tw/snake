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
    int newX = 0;
    int newY = 0;
    this.location = new CoordinateImpl(newX, newY);
  }
}
