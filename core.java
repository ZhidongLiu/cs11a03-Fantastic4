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
  public static int x=24,y=24;

  public static void flashEffect(int x, int y,Color c){
    Mosaic.setColor(x,y,c);
    Mosaic.delay(80);
    Mosaic.setColor(x,y,Color.BLACK);
    Mosaic.delay(80);
  }

  public static void drawLevel(int lv){
    Mosaic.clear();

      for(int i=0;i<draw.level[lv-1].length;i++){
        int x= draw.level[lv-1][i][0];
        int y= draw.level[lv-1][i][1];
        Mosaic.setColor(x,y,Color.PINK);
      }
      Mosaic.delay(400);
      Mosaic.clear();


      for(int i=0;i<draw.level[lv-1].length;i++){
        int x= draw.level[lv-1][i][0];
        int y= draw.level[lv-1][i][1];
        Mosaic.setColor(x,y,Color.RED);
      }
      Mosaic.delay(400);
      Mosaic.clear();


      for(int i=0;i<draw.level[lv-1].length;i++){
        int x= draw.level[lv-1][i][0];
        int y= draw.level[lv-1][i][1];
        Mosaic.setColor(x,y,Color.BLUE);
      }
      Mosaic.delay(400);
      Mosaic.clear();
  }

 public static void smile(){
   Mosaic.clear();
   for(int i=0;i<draw.smile.length;i++){
     int x= draw.smile[i][0];
     int y= draw.smile[i][1];
     Mosaic.setColor(x,y,Color.ORANGE);
   }

   Mosaic.delay(1500);
   Mosaic.clear();

 }

 public static void cry(){
   Mosaic.clear();
   for(int i=0;i<draw.cry.length;i++){
     int x= draw.cry[i][0];
     int y= draw.cry[i][1];
     Mosaic.setColor(x,y,Color.GREEN);
   }

   Mosaic.delay(1500);
   Mosaic.clear();

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
    System.out.print("Please input your command: ");
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
    x=24;
    y=24;
    Mosaic.setColor(x,y,Color.GREEN);
    Mosaic.delay(100);

    for(checkI=0; checkI<input.length(); checkI++){
      switch(input.charAt(checkI)){
        case 'W':x--;break;
        case 'A':y--;break;
        case 'S':x++;break;
        case 'D':y++;break;
      }
      if(checkI+1==route.length-1){
        if(x==route[checkI+1][0] && y==route[checkI+1][1]){
          Mosaic.setColor(x,y,Color.GREEN);
          Mosaic.delay(100);
          if(checkI==input.length()-1){
            return true;
          }
          else{
            switch(input.charAt(checkI+1)){
              case 'W':x--;break;
              case 'A':y--;break;
              case 'S':x++;break;
              case 'D':y++;break;
            }
            checkI++;
            return false;
          }
        }
        else{
          break;
        }
      }
      if(x==route[checkI+1][0] && y==route[checkI+1][1]){
        Mosaic.setColor(x,y,Color.GREEN);
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
      Mosaic.setColor(x,y,Color.RED);
      Mosaic.delay(100);
      //System.out.println("!!");
        x=route[i][0];
        y=route[i][1];
    }
    Mosaic.setColor(24,24,Color.RED);
  }

  public static void main(String[] args){
    //Open a 49X49 window
    Mosaic.open(49,49,10,10);
    System.out.println("Maze Game");
    //Generte the first level map(route)
    System.out.println("Type a string includes w(go up),a(go left),s(go down),d(go right) (or upper case) to give commands.");
    System.out.println("You will know the result after enter the command...");
    System.out.println("You have 3 lives for 5 levels.");
    int level =1;
    int life = 3;
    init(level);
    while(true){
      System.out.println("Level "+level);
      drawLevel(level);
      //Draw the map(route)
      Mosaic.setColor(24,24,Color.YELLOW);
      for(int i=1; i<route.length; i++){
        Mosaic.setColor(route[i][0],route[i][1],Color.WHITE);
      }
      //Check if the input only have characters: W A S D w a s d
      while(!checkInput()){
        System.out.println("Wrong input. Your input should only contains w,a,s,d. (either lower or upper case)");
      }
      //Test whther the player goes the right route
      if(testPlayerRoute()){
        if(level<5){
          System.out.println("Level "+level+" clear, starting Level "+(level+1));
          smile();
          init(++level);
        }
        else{
          System.out.println("Congratulations, you have complete the game!");
          smile();
          Mosaic.delay(1000);
          break;
        }
      }
      else{
        showFail(x,y,checkI);
        if(life>1){
          System.out.println("Try again, "+(--life)+" lives remain.");
          cry();
        }
        else{
          System.out.println("Game Over");
          cry();
          Mosaic.delay(1000);
          break;
        }
      }
    }
    Mosaic.close();
  }
}
