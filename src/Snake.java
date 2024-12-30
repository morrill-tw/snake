import controller.SnakeController;
import controller.SnakeControllerImpl;
import model.ImmutableSnakeModel;
import model.MutableSnakeModel;
import model.MutableSnakeModelImpl;
import model.MutableToImmutableSnakeModel;
import view.SnakeView;
import view.SnakeViewImpl;

public class Snake {

  public static void main(String[] args) throws InterruptedException {
    MutableSnakeModel model = new MutableSnakeModelImpl(MutableSnakeModelImpl.GridSize.NINE);
    ImmutableSnakeModel immutableModel = new MutableToImmutableSnakeModel(model);
    SnakeView view = new SnakeViewImpl(immutableModel);
    SnakeController controller = new SnakeControllerImpl(model, view);
    view.setPlayerActionLister(controller);
    controller.run();
  }

}