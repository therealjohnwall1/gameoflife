import java.util ArrayList;
public class test{
    public static void main(String[] args){
        boolean[][] ppop = readCellFile()
    }
public static boolean[][] readCellFile(String fileName) {
    ArrayList<String> fromTxt = scanThrough(".cells.txt");
    int longest = 0;
    for (int i = 0; i < fromTxt.size(); i++) {
      String unit = fromTxt.get(i);
      if (unit.substring(0, 1).equals("!")) {
        fromTxt.remove(i);
        i--;
      }
      if (unit.length() > fromTxt.get(longest).length()) {
        longest = i;
      }
    }
    int checkx = fromTxt.get(0).length();
    boolean[][] statuses = new boolean[longest][fromTxt.size()];
    for (int row = 0; row < fromTxt.size(); row++) {
      if (fromTxt.get(row).length() != checkx) {
       return null;
      }
      for (int col = 1; col <= fromTxt.get(row).length(); col++) {
        String unit = fromTxt.get(row).substring(col - 1, col);
        if (!unit.equals("O") || unit.equals(".")) {
          statuses[row][col] = false;
         return null;
        } 
        else {
          statuses[row][col] = unit.charAt(col) =='O';
        }
      }
    }
    return statuses;
  }
}