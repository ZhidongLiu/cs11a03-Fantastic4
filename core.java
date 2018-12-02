import java.awt.*;
import javax.swing.*;
import java.util.Scanner;
import mosaic.Mosaic;
import java.util.Arrays;
public class core{
  public static void main(String[] args){

    Maze maze = new Maze(10); //min_length = 10, min_level = 1;
    maze.route();
    int[][] route = new int[maze.output[0].length][2];

    for(int i=0;i<route.length;i++){
      route[i][0] = maze.output[0][i];
    }
//    int[][] route = new int[10][2];
    Scanner scan = new Scanner(System.in);
/*
    route[0] = new int[]{25,25};
    route[1] = new int[]{25,26};
    route[2] = new int[]{25,27};
    route[3] = new int[]{25,28};
    route[4] = new int[]{25,29};
    route[5] = new int[]{24,29};
    route[6] = new int[]{23,29};
    route[7] = new int[]{22,29};
    route[8] = new int[]{21,29};
    route[9] = new int[]{20,29};
    */
    //Starting from center coordinate 25,25
    char level = 1;
    Mosaic.open(49,49,10,10);
    while(true){
      Mosaic.setColor(25,25,Color.YELLOW);
      for(int i=1; i<route[0].length; i++){
        Mosaic.setColor(route[0][i],route[1][i],Color.WHITE);
      }
      String input = scan.next();
      boolean legalInput = true;
      input = input.toUpperCase();
      for(int i=0; i<input.length(); i++){
        if(input.charAt(i)!='W'&&input.charAt(i)!='A'&&input.charAt(i)!='S'&&input.charAt(i)!='D'){
          legalInput = false;
          break;
        }
      }
      if(legalInput){
        int x=25,y=25;
        Mosaic.setColor(x,y,Color.GREEN);
        Mosaic.delay(100);
        int i=0;
        for(; i<input.length(); i++){
          switch(input.charAt(i)){
            case 'W':y--;break;
            case 'A':x--;break;
            case 'S':y++;break;
            case 'D':x++;break;
          }
          if(i+1<route.length && y==route[i+1][0] && x==route[i+1][1]){
            Mosaic.setColor(y,x,Color.GREEN);
            Mosaic.delay(100);
          }
          else{
            break;
          }
        }
        for(int j=0; j<5; j++){
          Mosaic.setColor(y,x,Color.RED);
          Mosaic.delay(80);
          Mosaic.setColor(y,x,Color.BLACK);
          Mosaic.delay(80);
        }
        for(; i>=0; i--){
          Mosaic.setColor(y,x,Color.RED);
          Mosaic.delay(100);
          System.out.println("!!");
            y=route[i][0];
            x=route[i][1];
        }
      }
      else{
        System.out.println("Wrong input.");
      }
      //Mosaic.clear();
      input = scan.next();
    }
  }
}
