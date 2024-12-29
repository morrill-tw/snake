package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import model.ImmutableSnakeModel;

/**
 * A graphical representation of snake.
 */
public class SnakeViewImpl extends JFrame implements SnakeView {
  private final ImmutableSnakeModel model;
  private JPanel header;
  private JPanel grid;

  /**
   * Constructs a graphical snake view. The view opens upon construction.
   * @param model the model represented by this view
   */
  public SnakeViewImpl(ImmutableSnakeModel model) {
    this.model = model;

    setSize(model.getGridSize() * 20, model.getGridSize() * 20 + 30);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setupLayout();

    renderGame();
    setVisible(true);
  }

  private void setupLayout() {
    setLayout(new BorderLayout());

    header = new JPanel();
    header.setBackground(Color.WHITE);
    header.setPreferredSize(new Dimension(600, 30));

    JLabel score = new JLabel("Score: " + model.getNumApplesEaten());
    header.add(score);

    grid = new JPanel();
    grid.setBackground(Color.WHITE);
    grid.setPreferredSize(new Dimension(600, 600));
    grid.setLayout(new GridLayout(model.getGridSize(), model.getGridSize()));

    for (int row = 0; row < model.getGridSize(); row++) {
      for (int col = 0; col < model.getGridSize(); col++) {
        JPanel cell = new JPanel();
        cell.setBackground(Color.BLACK);
        cell.setBorder(new LineBorder(Color.GRAY, 1));
        grid.add(cell);
      }
    }

    add(header, BorderLayout.NORTH);
    add(grid, BorderLayout.SOUTH);
  }

  @Override
  public void renderGame() {
    revalidate();
    repaint();
  }
}
