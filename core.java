import java.awt.*;
import javax.swing.*;
import java.util.Scanner;
import mosaic.Mosaic;
import java.util.Arrays;
public class core{

  public static int[][]route;
  public static Scanner scan = new Scanner(System.in);
  public static String input;
  public static int checkI;
  public static int x=25,y=25;

  public static void flashEffect(int x, int y,Color c){
    Mosaic.setColor(y,x,c);
    Mosaic.delay(80);
    Mosaic.setColor(y,x,Color.BLACK);
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

  public static boolean checkInput(){
    input = scan.next();
    boolean legalInput = true;
    input = input.toUpperCase();
    for(int i=0; i<input.length(); i++){
      if(input.charAt(i)!='W'&&input.charAt(i)!='A'&&input.charAt(i)!='S'&&input.charAt(i)!='D'){
        legalInput = false;
        break;
      }
    }
    return legalInput;
  }

  public static boolean testPlayerRoute(){
    x=25;
    y=25;
    Mosaic.setColor(y,x,Color.GREEN);
    Mosaic.delay(100);

    for(checkI=0; checkI<input.length(); checkI++){
      switch(input.charAt(checkI)){
        case 'W':y--;break;
        case 'A':x--;break;
        case 'S':y++;break;
        case 'D':x++;break;
      }
      if(checkI+1==route.length-1){
        if(y==route[checkI+1][0] && x==route[checkI+1][1]){
          Mosaic.setColor(y,x,Color.GREEN);
          Mosaic.delay(100);
          if(checkI==input.length()-1){
            return true;
          }
          else{
            switch(input.charAt(checkI+1)){
              case 'W':y--;break;
              case 'A':x--;break;
              case 'S':y++;break;
              case 'D':x++;break;
            }
            checkI++;
            return false;
          }
        }
        else{
          break;
        }
      }
      if(y==route[checkI+1][0] && x==route[checkI+1][1]){
        Mosaic.setColor(y,x,Color.GREEN);
        Mosaic.delay(100);
      }
      else{
        break;
      }
    }
    return false;
  }

  public static void showFail(int x, int y, int i){
    for(int j=0; j<5; j++){
      flashEffect(x,y,Color.RED);
    }
    for(; i>=0; i--){
      Mosaic.setColor(y,x,Color.RED);
      Mosaic.delay(100);
      //System.out.println("!!");
        y=route[i][0];
        x=route[i][1];
    }
    Mosaic.setColor(25,25,Color.RED);
  }

  public static void main(String[] args){
    //Open a 49X49 window
    Mosaic.open(49,49,10,10);
    //Generte the first level map(route)
    System.out.println("You have 3 lives for 5 levels.");
    int level =1;
    int life = 3;
    init(level);
    while(true){
      System.out.println("Level "+level);
      //Draw the map(route)
      Mosaic.setColor(25,25,Color.YELLOW);
      for(int i=1; i<route.length; i++){
        Mosaic.setColor(route[i][0],route[i][1],Color.WHITE);
      }
      //Check if the input only have characters: W A S D w a s d
      while(!checkInput()){
        System.out.println("Wrong input.");
      }
      //Test whther the player goes the right route
      if(testPlayerRoute()){
        if(level<5){
          System.out.println("Level "+level+" clear, starting Level "+(level+1));
          init(++level);
          Mosaic.delay(1000);
          Mosaic.clear();
          Mosaic.delay(500);
        }
        else{
          System.out.println("Congratulations, you have complete the game!");
          Mosaic.delay(3000);
          break;
        }
      }
      else{
        showFail(x,y,checkI);
        if(life>1){
          System.out.println("Try again, "+(--life)+" lives remain.");
          Mosaic.delay(1000);
          Mosaic.clear();
        }
        else{
          System.out.println("Game Over");
          Mosaic.delay(3000);
          break;
        }
      }
    }
    Mosaic.close();
  }
}
