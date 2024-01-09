import java.awt.Color;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Dimension;

public class Grid extends Canvas {
  private Cell[][] array;
  
  /**
   * Creates a new grid object
   */
  public Grid (int rows, int cols, int size) { 
    array = new Cell[rows][cols];
    for (int r = 0; r < rows; r++) {
      int y = r * size;
      for (int c = 0; c < cols; c++) {
        int x = c * size;
        array[r][c] = new Cell(x, y, size);
      }
    }
    // set the canvas size
    setPreferredSize(new Dimension(cols * size, rows * size));
  }
  
  /**
   * Method to draw the grid
   */
  public void draw(Graphics g) {
    for (Cell[] row : array) {
      for (Cell cell : row) {
        cell.draw(g);
      }
    }
    g.setColor(Color.RED);
    g.drawString("Life Count = " + countAlive(), 10, 15);
  }
  
	/**
   * Method to draw the cells
   */
  public void paint(Graphics g) {
    draw(g);
  }

  /**
   * Method to get the number of rows
   * @return the number of rows
   */
  public int getRows() {
    return array.length;
  }

  /**
   * Method to get the number of columns
   * @return the number of columns
   */
  public int getCols() {
    return array[0].length;
  }

  /**
   * Method to get the cell at a certain location if it is within the boundaries
   * @param r - the row of the cell
   * @param c - the column of the cell
   * @return the cell at the location if it is within the boundaries
   */
  public Cell getCell(int r, int c) {
    if (r < 0 || r >= array.length || c < 0 || c >= array[0].length) {
      return null;
    }
    return array[r][c];
  }

  /**
   * Method to make the cell alive at a certain location by calling the makeAlive method of the Cell class
   * @param r - the row of the cell
   * @param c - the column of the cell
   */
  public void makeAlive(int r,int c) {
    Cell cell = getCell(r, c);
    if (cell != null) {
      array[r][c].makeAlive();
    }
  }

  /**
	 * Checks if the cell exists at a certain location, ad 1 is returned if it's alive while 0 is returned if it's dead
	 * @param r - the row of the cell
   * @param c - the column of the cell
	 * @return if the cell is dead or alive at a certain location
	 */
  public int test ( int r, int c) {
    if (r >= 0 && r < array.length && c >= 0 && c < array[0].length) {
      return array[r][c].getState();
    } else {
      return 0;
    }
  } 

  /**
   * Method to get find the total number of cells that are alive
   * @return the number of alive cells
   */
  public int countAlive() {
    int total = 0;
    for (Cell[] row : array) {
      for (Cell cell : row) {
        if (cell.isAlive()) {
          total++;
        }
      }
    }
    return total;
  }
}