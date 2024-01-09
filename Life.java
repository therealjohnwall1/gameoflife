public class Life {
  public Grid grid;

  //makes boolean 2d array, overlaps with grid to see
  // if they are alive or in bounce
  // if true = alive and in will become alive
  
  public Life(boolean[][] array, int size) {
  grid = new Grid(array.length, array[0].length,size);
    for(int row = 0; row < array.length;row++){
      for(int col = 0; col < array[0].length;col++){
        if(array[row][col]){
          grid.getCell(row,col).makeAlive();
        }
      }
    }
  }
  



  public void updateGrid() {
    int[][] counts = countNeighbors();
    for (int row = 0; row < counts.length; row++) {
			for (int col = 0; col < counts[row].length; col++) {
				int count = counts[row][col];
				Cell cell = grid.getCell(row, col);

				if (cell != null)
					updateCell(cell, count);
			}
		}
    
    
    
  }

  /**
   * Method to update if the cell is dead or alive based on the Game of Life rules
   * @param cell - the cell
   * @param r - 
   * @param c -
   */
  public void updateCell(Cell cell, int neighborAmt) {
    if(cell.isDead()){
      if(neighborAmt ==3){
        cell.makeAlive();
      }
    }
    else{
      if(neighborAmt < 2 || neighborAmt >3){
        cell.kill();
      }
    }
    }
  
  /**
   * Counts the number of alive neighbors next to the given cell. This is NOT THE SAME as the
   * Grid.countAlive function, even tho it has the same name
  */
  public int countAlive(int row, int col) {
		int n = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i != 0 || j != 0)
					n += grid.test(row + i, col + j);
			}
		}
		return n;
	}
  
  /**
   * Counts the number of neighbors next to
   */
  public int[][] countNeighbors() {
    int[][] counts = new int[grid.getRows()][grid.getCols()];
		for (int row = 0; row < counts.length; row++) {
			for (int col = 0; col < counts[0].length; col++) {
				counts[row][col] = countAlive(row, col);
			}
		}
		return counts;
  }
  
  public void mainloop() {
    while (true) {
      updateGrid();
      grid.repaint();
      
      try {
        Thread.sleep(500);
      } catch(InterruptedException e) {
        // Do nothing
      }
    }
  }
}