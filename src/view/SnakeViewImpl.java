package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;

import model.ImmutableSnakeModel;
import shared.Direction;

/**
 * A graphical representation of snake.
 */
public class SnakeViewImpl extends JFrame implements SnakeView {
  private final ImmutableSnakeModel model;
  private PlayerActions playerActionListener;
  private JLabel score;
  private JPanel grid;
  private final Component[][] gridCells;
  private Point tail;

  /**
   * Constructs a graphical snake view. The view opens upon construction.
   * @param model the model represented by this view
   */
  public SnakeViewImpl(ImmutableSnakeModel model) {
    this.model = model;
    this.gridCells = new Component[model.getGridSize()][model.getGridSize()];
    this.tail = model.getComponents().get(0);

    setSize(model.getGridSize() * 70, model.getGridSize() * 70 + 50);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setupLayout();
    setupKeyBindings();

    renderGame();
    setVisible(true);
  }

  @Override
  public void setPlayerActionLister(PlayerActions listener) {
    this.playerActionListener = listener;
  }

  private void setupLayout() {
    setLayout(new BorderLayout());
    setFocusTraversalPolicy(new DefaultFocusTraversalPolicy());

    JPanel header = new JPanel();
    header.setLayout(new BorderLayout());
    header.setBackground(Color.WHITE);
    header.setPreferredSize(new Dimension(600, 30));

    this.score = new JLabel("Score: " + model.getNumApplesEaten());
    header.add(score, BorderLayout.WEST);

    JButton start = new JButton("Start");
    start.addActionListener(e -> playerActionListener.startGame());
    header.add(start, BorderLayout.EAST);

    grid = new JPanel();
    grid.setBackground(Color.WHITE);
    grid.setPreferredSize(new Dimension(600, 600));
    grid.setLayout(new GridLayout(model.getGridSize(), model.getGridSize()));

    List<Point> componentLocations = model.getComponents();

    for (int col = 0; col < model.getGridSize(); col++) {
      for (int row = 0; row < model.getGridSize(); row++) {
        JPanel cell = new JPanel();
        if (componentLocations.contains(new Point(row, col))) {
          cell.setBackground(Color.GREEN);
        } else if (model.getAppleLocation().equals(new Point(row, col))) {
          cell.setBackground(Color.RED);
        } else {
          cell.setBackground(Color.BLACK);
        }
        cell.setBorder(new LineBorder(Color.GRAY, 1));
        gridCells[row][col] = cell;
        grid.add(cell);
      }
    }

    add(header, BorderLayout.NORTH);
    add(grid, BorderLayout.CENTER);
  }

  private void setupKeyBindings() {
    // Set key bindings for arrow keys
    InputMap inputMap = grid.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap actionMap = grid.getActionMap();

    inputMap.put(KeyStroke.getKeyStroke("UP"), "moveUp");
    inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
    inputMap.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
    inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");

    actionMap.put("moveUp", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (playerActionListener != null) {
          try {
            playerActionListener.changeDirection(Direction.UP);
          } catch (IllegalStateException | IllegalArgumentException ie) {
            // do nothing - player just made a faulty move but no action needs to be taken
          }
        }
      }
    });

    actionMap.put("moveDown", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (playerActionListener != null) {
          try {
            playerActionListener.changeDirection(Direction.DOWN);
          } catch (IllegalStateException | IllegalArgumentException ie) {
            // do nothing - player just made a faulty move but no action needs to be taken
          }
        }
      }
    });

    actionMap.put("moveLeft", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (playerActionListener != null) {
          try {
            playerActionListener.changeDirection(Direction.LEFT);
          } catch (IllegalStateException | IllegalArgumentException ie) {
            // do nothing - player just made a faulty move but no action needs to be taken
          }
        }
      }
    });

    actionMap.put("moveRight", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (playerActionListener != null) {
          try {
            playerActionListener.changeDirection(Direction.RIGHT);
          } catch (IllegalStateException | IllegalArgumentException ie) {
            // do nothing - player just made a faulty move but no action needs to be taken
          }
        }
      }
    });
  }

  @Override
  public void renderGame() {
    List<Point> componentLocations = model.getComponents();

    score.setText("Score: " + model.getNumApplesEaten());

    Point newTail = componentLocations.get(0);
    if (!tail.equals(newTail)) {
      gridCells[tail.x][tail.y].setBackground(Color.BLACK);
      tail = newTail;
    }

    for (Point location : componentLocations) {
      gridCells[(int)location.getX()][(int)location.getY()].setBackground(Color.GREEN);
    }
    int appleX = (int)model.getAppleLocation().getX();
    int appleY = (int)model.getAppleLocation().getY();
    gridCells[appleX][appleY].setBackground(Color.RED);

    revalidate();
    repaint();
  }
}
