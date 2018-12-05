import java.awt.*;
import javax.swing.*;
import java.util.Scanner;
import mosaic.Mosaic;
import java.util.Arrays;

public class Try{
  private static class Point{
    int a, b;
    public Point(int a, int b){
      this.a = a;
      this.b = b;
    }
    public void setPoint(int x, int y){
      this.a = x;
      this.b = y;
    }
  }
  public static int[][]route;
  public static Scanner scan = new Scanner(System.in);
  public static String input;
  public static String[] inputStr = new String[50];
  public static int checkI;
  public static int x=24,y=24;
  public static int index;

  public static void main(String[] args){
    Mosaic.open(49, 49, 10, 10);
    System.out.println("You can try how to play the game.");
    System.out.println("Which level you want to try (from 1 to 5)?");
    int level =scan.nextInt();
    init(level);

    System.out.println("Level "+level);
    //Draw the map(route)
    Mosaic.setColor(24,24,Color.YELLOW);
    for(int i=1; i<route.length; i++){
      Mosaic.setColor(route[i][0],route[i][1],Color.WHITE);
    }
    index = 0;
    Point p = new Point(24, 24);
    System.out.println(route.length);
    while(index < route.length - 1){
      if (!checkSingleInput()){
          System.out.println("Wrong Input.");
          System.out.println("Please use 'u', 's', 'a','d', 'U', 'S', 'A', 'D' to control.");
      }else{
        if (testPlayRoute2(index, p)){
          lightPath(p);
          index++;
          System.out.println(index);
        }else{
          System.out.println("The route is invalid.");
        }
      }
    }

}


  public static boolean testPlayRoute2(int index, Point p){
    char currentChar = inputStr[index].charAt(0);
    int x = p.a;
    int y = p.b;
    switch(currentChar){
      case 'W':
        x--;break;
      case 'S':
        x++;break;
      case 'A':
        y--;break;
      case 'D':
        y++;break;
    }

      if (x == route[index + 1][0] && y == route[index + 1][1]){
        p.setPoint(x, y);
        return true;
      }
    return false;
  }
  public static void flashEffect(int x, int y,Color c){
    Mosaic.setColor(x,y,c);
    Mosaic.delay(80);
    Mosaic.setColor(x,y,Color.BLACK);
    Mosaic.delay(80);
  }
  public static void init(int lv){
    Maze maze = new Maze(4+2*lv); //min_length = 10, min_level = 1;
    maze.route();
    route = new int[maze.output[0].length][2];
    for(int i=0;i<route.length;i++){
      route[i][0] = maze.output[0][i];
      route[i][1] = maze.output[1][i];
    }
  }
  public static boolean checkSingleInput(){
    String inputTmp = scan.next();
    inputTmp = inputTmp.toUpperCase();
    if (inputTmp.equals("A") || inputTmp.equals("W") || inputTmp.equals("S")||inputTmp.equals("D")){
      inputStr[index] = inputTmp;
      return true;
    }
    return false;
  }
  public static void lightPath(Point p){
    Mosaic.setColor(p.a, p.b, Color.BLUE);
  }

}
