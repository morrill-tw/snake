package model;

/**
 * An apple.
 */
interface Apple {

  /**
   * Returns a coordinate representing the location of this apple.
   * @return the location of this apple
   */
  Coordinate getLocation();

  /**
   * Updates the location of this apple to a new, random location. The new
   * location of this apple will always be different from its previous location.
   * The new location will have an x value between 0 and xBound inclusive, and a
   * y value between 0 and yBound inclusive.
   * @param xBound the x boundary of the new location
   * @param yBound the y boundary of the new location
   */
  void updateLocation(int xBound, int yBound);

}
