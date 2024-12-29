import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;
import java.util.List;

import model.MutableSnakeModel;
import model.MutableSnakeModelImpl;
import shared.Direction;

public class ModelTests {
  private MutableSnakeModel modelNine;
  private MutableSnakeModel modelThirteen;
  private MutableSnakeModel modelSeventeen;

  @Before
  public void init() {
    modelNine = new MutableSnakeModelImpl(MutableSnakeModelImpl.GridSize.NINE);
    modelThirteen = new MutableSnakeModelImpl(MutableSnakeModelImpl.GridSize.THIRTEEN);
    modelSeventeen = new MutableSnakeModelImpl(MutableSnakeModelImpl.GridSize.SEVENTEEN);
  }

  @Test
  public void testModelConstruction() {
    runModelConstructionTests(MutableSnakeModelImpl.GridSize.NINE);
    runModelConstructionTests(MutableSnakeModelImpl.GridSize.THIRTEEN);
    runModelConstructionTests(MutableSnakeModelImpl.GridSize.SEVENTEEN);
  }

  // Ensures that all observations return the appropriate values directly
  // after construction
  private void runModelConstructionTests(MutableSnakeModelImpl.GridSize size) {
    MutableSnakeModel model = new MutableSnakeModelImpl(size);

    Assert.assertEquals("Initial num apples eaten should be zero.", 0, model.getNumApplesEaten());
    Assert.assertFalse("Game should not be over after model construction.", model.isGameOver());
    Assert.assertEquals("Game size should match that which was passed to the constructor.",
            size.getVal(), model.getGridSize());

    int gridCenter = (model.getGridSize() - 1) / 2;

    Assert.assertEquals("The location of the apple should be three units right from the middle " +
            "and centered on the y-axis.",
            new Point(gridCenter + 3, gridCenter), model.getAppleLocation());

    List<Point> components = model.getComponents();
    for (int i = 0; i < components.size(); i++) {
      Assert.assertEquals(new Point(gridCenter + (i - 3), gridCenter), components.get(i));
    }
  }

  @Test
  public void testMoveSnakeNoApple() {
    int gridCenter = (modelNine.getGridSize() - 1) / 2;
    Point headLocation = new Point(gridCenter - 1, gridCenter);
    Point middleLocation = new Point(gridCenter - 2, gridCenter);
    Point tailLocation = new Point(gridCenter - 3, gridCenter);

    // Ensure that components are in the right location prior to move
    List<Point> components = modelNine.getComponents();
    Assert.assertEquals(tailLocation, components.get(0));
    Assert.assertEquals(middleLocation, components.get(1));
    Assert.assertEquals(headLocation, components.get(2));

    // Check location of components after moving right
    tailLocation.x++;
    middleLocation.x++;
    headLocation.x++;
    runMoveSnakeNoAppleTests(Direction.RIGHT, tailLocation, middleLocation, headLocation);


    // Check location of components after moving up
    tailLocation.x++;
    middleLocation.x++;
    headLocation.y--;
    runMoveSnakeNoAppleTests(Direction.UP, tailLocation, middleLocation, headLocation);


    // Check location of components after moving left
    tailLocation.x++;
    middleLocation.y--;
    headLocation.x--;
    runMoveSnakeNoAppleTests(Direction.LEFT, tailLocation, middleLocation, headLocation);

    // Check location of components after moving down
    tailLocation.y--;
    middleLocation.x--;
    headLocation.y++;
    runMoveSnakeNoAppleTests(Direction.DOWN, tailLocation, middleLocation, headLocation);
  }

  private void runMoveSnakeNoAppleTests(Direction dir, Point tailLocation,
                                        Point middleLocation, Point headLocation) {
    modelNine.updateDirection(dir);
    modelNine.moveSnake();
    List<Point> components = modelNine.getComponents();

    Assert.assertEquals(tailLocation, components.get(0));
    Assert.assertEquals(middleLocation, components.get(1));
    Assert.assertEquals(headLocation, components.get(2));
  }

  @Test(expected=IllegalStateException.class)
  public void testMoveSnakeAfterGameEnded() {
    modelNine.moveSnake();
    modelNine.moveSnake();
    modelNine.moveSnake();
    modelNine.moveSnake();
    modelNine.moveSnake();
    modelNine.moveSnake();
    modelNine.moveSnake();
  }

  @Test
  public void testHitNorthBoundaryRendersGameOver() {
    runSnakeHitBoundaryTests(modelNine, Direction.UP);
    runSnakeHitBoundaryTests(modelThirteen, Direction.UP);
    runSnakeHitBoundaryTests(modelSeventeen, Direction.UP);
  }

  @Test
  public void testHitSouthBoundaryRendersGameOver() {
    runSnakeHitBoundaryTests(modelNine, Direction.DOWN);
    runSnakeHitBoundaryTests(modelThirteen, Direction.DOWN);
    runSnakeHitBoundaryTests(modelSeventeen, Direction.DOWN);
  }

  @Test
  public void testHitEastBoundaryRendersGameOver() {
    runSnakeHitBoundaryTests(modelNine, Direction.RIGHT);
    runSnakeHitBoundaryTests(modelThirteen, Direction.RIGHT);
    runSnakeHitBoundaryTests(modelSeventeen, Direction.RIGHT);
  }

  @Test
  public void testHitWestBoundaryRendersGameOver() {
    runSnakeHitBoundaryTests(modelNine, Direction.LEFT);
    runSnakeHitBoundaryTests(modelThirteen, Direction.LEFT);
    runSnakeHitBoundaryTests(modelSeventeen, Direction.LEFT);
  }

  private void runSnakeHitBoundaryTests(MutableSnakeModel model, Direction dir) {
    Assert.assertFalse(model.isGameOver());
    moveSnakeIntoBoundary(model, dir);
    Assert.assertTrue(model.isGameOver());
  }

  private void moveSnakeIntoBoundary(MutableSnakeModel model, Direction dir) {
    int numMoves = ((model.getGridSize() - 1) / 2) - 1;

    if (dir == Direction.LEFT) {
      model.updateDirection(Direction.UP);
      model.moveSnake();
      numMoves--;
    } else if (dir == Direction.RIGHT) {
      model.moveSnake();
    }

    model.updateDirection(dir);
    model.moveSnake();
    model.updateDirection(dir);
    for (int i = 0; i <= numMoves; i++) {
      model.moveSnake();
    }
  }

  @Test
  public void testMoveSnakeEatsApple() {
    Assert.assertEquals("Before eating apple there should be three components",
            3, modelNine.getComponents().size());

    modelNine.moveSnake();
    modelNine.moveSnake();
    modelNine.moveSnake();
    modelNine.moveSnake();

    Assert.assertEquals("After eating apple there should be an additional component",
            4, modelNine.getComponents().size());
  }

  @Test
  public void testNumApplesUpdates() {
    Assert.assertEquals(0, modelThirteen.getNumApplesEaten());

    modelThirteen.moveSnake();
    modelThirteen.moveSnake();
    modelThirteen.moveSnake();
    modelThirteen.moveSnake();

    Assert.assertEquals(1, modelThirteen.getNumApplesEaten());
  }

  @Test
  public void testAppleLocationUpdated() {
    Point originalLocation = modelSeventeen.getAppleLocation();

    modelSeventeen.moveSnake();
    modelSeventeen.moveSnake();
    modelSeventeen.moveSnake();
    modelSeventeen.moveSnake();

    Assert.assertNotEquals(modelSeventeen.getAppleLocation(), originalLocation);
  }

}
