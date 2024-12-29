import controller.SnakeController;
import controller.SnakeControllerImpl;
import model.ImmutableSnakeModel;
import model.MutableSnakeModel;
import model.MutableSnakeModelImpl;
import model.MutableToImmutableSnakeModel;
import view.SnakeViewImpl;

public class Snake {

  // Game loop
  public static void main(String[] args) throws InterruptedException {
    MutableSnakeModel model = new MutableSnakeModelImpl(MutableSnakeModelImpl.GridSize.NINE);
    ImmutableSnakeModel immutableModel = new MutableToImmutableSnakeModel(model);
    SnakeController controller = new SnakeControllerImpl(model, new SnakeViewImpl(immutableModel));
    controller.run();
  }

}