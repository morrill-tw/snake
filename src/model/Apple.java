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
   */
  void updateLocation();

}
