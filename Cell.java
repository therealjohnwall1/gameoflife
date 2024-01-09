import java.awt.Graphics;
import java.awt.Color;

public class Cell {
    private int x;
    private int y;
    private final int size;
    private int state = 0;
  
    /**
     * Creates a new cell object
    */
    public Cell(int xLoc, int yLoc, int cellSize) {
      x = xLoc;
      y = yLoc;
      size = cellSize;
    }
  
    /**
     * Checks if the cell is dead
     * @return true if the cell is dead
    */
    public boolean isDead() {
      return state == 0;
    }
    public int getState(){
      return state;
    }
  
    /**
     * Checks if the cell is alive
     * @return true if the cell is alive
    */
    public boolean isAlive() {
      return state == 1;
    }
  
    /**
     * Method to kill the cell
    */
    public void kill() {
      state = 0;
    }
  
    /**
     * Method to make the cell alive
    */
    public void makeAlive() {
      state = 1;
    }
  
    /**
     * Method to color the cell
    */
    public static final Color[] COLORS = {Color.WHITE, Color.BLACK};
  
    /**
     * Method to draw the cell
    */
    public void draw(Graphics g) {
      g.setColor(COLORS[state]);
      g.fillRect(x + 1, y + 1, size - 1, size - 1);
      g.setColor(Color.LIGHT_GRAY);
      g.drawRect(x, y, size, size);
    }
  }