import java.awt.*;

public class core{
  public static void main(String[] args){
    boolean[][] isOccupied = new boolean[128][128];
    //Starting from center coordinate 63,63
    char level = 1;
    Route r = new Route(level);
    long time = System.currentTimeMillis();
    Mosaic.open();
    System.out.println(isOccupied[0][0]+" "+r.length);
    while(System.currentTimeMillis()-time<1000){
      ;
    }
    Mosaic.setColor(0,0,Color.RED);
    class Route{
      public int length;
      public Route(char level){
        length = level*3*(int)(10.0*Math.random());
      }
    }
  }
}
