import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.Map;

import model.SnakeModel;
import model.SnakeModelImpl;
import shared.Direction;

public class ModelTests {

  @Test
  public void testModelConstruction() {
    SnakeModel model = new SnakeModelImpl(SnakeModelImpl.GridSize.NINE);

    Assert.assertEquals("Initial num apples eaten should be zero.", 0, model.getNumApplesEaten());
    Assert.assertFalse("Game should not be over after model construction.", model.isGameOver());
    Assert.assertEquals("Game size should match that which was passed to the constructor.",
            9, model.getGridSize());
    Assert.assertEquals("The location of the apple should be three units right from the middle " +
                    "and centered on the y-axis.", new Point(7, 4), model.getAppleLocation());
    int gridCenter = (model.getGridSize() - 1) / 2;
    Map<Integer, Integer> components = model.getComponents();
    for (int i = 0; i < components.size(); i++) {
      Assert.assertTrue(components.containsKey(gridCenter + (i -  3)));
      Assert.assertEquals((Integer) gridCenter, components.getOrDefault(gridCenter + (i - 3), -1));
    }
  }

}
