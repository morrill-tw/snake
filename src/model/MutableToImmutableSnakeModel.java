package model;

import java.awt.Point;
import java.util.List;

public class MutableToImmutableSnakeModel implements ImmutableSnakeModel {
  MutableSnakeModel adaptee;

  public MutableToImmutableSnakeModel(MutableSnakeModel model) {
    this.adaptee = model;
  }

  @Override
  public int getNumApplesEaten() {
    return adaptee.getNumApplesEaten();
  }

  @Override
  public List<Point> getComponents() {
    return adaptee.getComponents();
  }

  @Override
  public int getGridSize() {
    return adaptee.getGridSize();
  }

  @Override
  public boolean isGameOver() {
    return adaptee.isGameOver();
  }

  @Override
  public Point getAppleLocation() {
    return adaptee.getAppleLocation();
  }
}
