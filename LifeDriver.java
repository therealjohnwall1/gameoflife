
import javax.swing.JFrame;
import java.io.File;
public class LifeDriver {
    /*
* AP CSA Semester 2 Project - Game of Life
*
* Names: Emmy Wei and Andy Van
* Period: 4
*/
    public static void main(String[] args) {
      if (args.length == 0) {
        System.out.println("no file provided");
        return;
      }
      boolean[][] positions = Reader.readFile(new File(args[0]));
        if(positions == null){
          // the reader returns null if theres an error also, not just if the file isnt found
            System.out.println("unable to read file");
        return;
      }
        Life main = new Life(positions,20);
        JFrame board = new JFrame("Conway's Game of Life");
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    board.setResizable(false);
	    board.add(main.grid);
      board.pack();
	    board.setVisible(true);
      System.out.println("entering game");
        main.mainloop();
    }
    
}
