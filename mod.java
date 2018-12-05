import java.awt.*;
import javax.swing.*;
import mosaic.Mosaic;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
public class mod{
  public static void main(String[] args)throws FileNotFoundException{

    File file = new File(args[0]);
    Scanner scan = new Scanner(file);
    int[][] points = new int[49*49][2];
    int count = 0;

    Mosaic.open(49,49,10,10);

    final int row = 7;
    final int col = 10;
    /*read file into coordinate array*/
    while(scan.hasNext()){
      for(int i=0; i<row; i++){ //
        String line = scan.next();
        for(int j=0; j<col; j++){
          if(line.charAt(j)=='x'){
            points[count]= new int[]{24-row/2+i,24-col/2+j};
            count++;
          }
        }
      }
    }
    points = Arrays.copyOf(points,count);
    for(int i=0; i<points.length; i++){
      Mosaic.setColor(points[i][0],points[i][1],Color.BLUE);
    }

    /*print out array*/
    System.out.print("{");
    int i=0;
    for(; i<points.length-1; i++){
      System.out.print("{"+points[i][0]+","+points[i][1]+"}"+",");
    }
    System.out.println("{"+points[i][0]+","+points[i][1]+"}"+"}");
  }
}
