import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Reads the scanner cell text file
 */
public class Reader {

  public static boolean[][] readFile(File file){
    String fileName = file.getName();
    String fileType = fileEnd(fileName);
    System.out.println(fileType);
    //System.out.printf("filename: '%s', filetype: '%s'\n", fileName, fileType);
    if(fileType.equals(".cells")){
      return readCellFile(fileName); 
    }
    else if (fileType.equals(".rle")){
      return readRle(fileName);
  
    }
    else{
      System.out.println("Invalid file type.");
      return null;
    }
  }

  public static ArrayList<String> scanThrough(String filePath) {
    ArrayList<String> fromTxt = new ArrayList<String>();
    try {
      File file = new File(filePath);
      Scanner scan = new Scanner(file);
      while (scan.hasNextLine()) {
        String s = scan.nextLine();
        fromTxt.add(s);
      }
      scan.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return fromTxt;
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

  public static boolean[][] readRle(String fileName) {
    int x = -1;
    int y = -1;
    String pattern = "";
    String unit;
    ArrayList<String> fromTxt = scanThrough(fileName);
    for (int i = 0; i < fromTxt.size(); i++) {
      unit = fromTxt.get(i);
      if (unit.substring(0, 1).equals("#")) {
        fromTxt.remove(i);
        i--;
      } else if (unit.substring(0, 1).equalsIgnoreCase("x")) {
        x = strToInt(unit.substring(unit.indexOf("x = ") + 4, unit.indexOf(", ")));
        y = strToInt(unit.substring(unit.indexOf("y = ") + 4, unit.indexOf(", ", unit.indexOf(", ") + 1)));
        fromTxt.remove(i);
        i--;
      } else {
        pattern = pattern + unit;
      }
    }
    if(x == -1||y==-1){
      System.out.println("invalid dimensions");
      return null;
    }
  int x_pos = 0;
  int y_pos = 0;
  int w = x;
  int h = y;
  boolean[][]array = new boolean[w][h];
int num_chars = pattern.length();
  for(int i = 0; i < num_chars; i++){
    if((pattern.charAt(i) == '!') || (pattern.charAt(i) == '$')){
      //if pattern ends before reaching end, fills rest with dead
      while(x_pos < w){
    array[x_pos][y_pos] = false;
    x_pos++;
    //move down array
      }
      x_pos = 0;
      y_pos++;
      if(pattern.charAt(i) == '$'){ //detects if for  new lines in a row ($$$)
    continue;
      }else{
    break;
      }
    }else{
      int number = 1;
      if(Character.isDigit(pattern.charAt(i))){
    number = 0;
    while(Character.isDigit(pattern.charAt(i))){
      //gets int before digit using isDigit to check 
      number *= 10;
      number += pattern.charAt(i) - '0';
      i++;
    }
      }
      if(pattern.charAt(i)!= 'o' && pattern.charAt(i)!= 'b'){
        System.out.println("invalid character '" + pattern.charAt(i) + "' in pattern");
        return null;
      }
      //determines if dead or alive
      if(pattern.charAt(i) == 'o'){
        for(int j = 0; j < number; j++){
          array[x_pos][y_pos] = true;
          x_pos++;
        }
          }
    
      if(pattern.charAt(i) == 'b'){
    for(int j = 0; j < number; j++){
      array[x_pos][y_pos] = false;
      x_pos++;
    }
      }
      
    }
  }
    return array;   
    } 
  public static String fileEnd(String pattern) {
    int dotIndex = 0;
    for (int i = 0; pattern.length() > i; i++) {
      if (pattern.substring(i, i + 1).equals(".")) {
        dotIndex = i;
      }
    }
    return pattern.substring(dotIndex, pattern.length());
  }

  public static int strToInt(String pattern) {
    int result = 0;
    int zeroVal = 1;
    for (int i = pattern.length() - 1; i >= 0; i--) {
      char c = pattern.charAt(i);
      // Check if the character is a digit
      if (c >= '0' && c <= '9') {
        result += (c - '0') * zeroVal;
        zeroVal *= 10;
      } else {
        // If the character is not a valid digit, return 0
        return -1;
      }
    }
    return result;
  }
}