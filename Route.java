import java.util.ArrayList;
public class Route{
  public static int data[][] = new int[127][127];//0-new, 1234-directioins, 5-wrongway
  public Route(int targetLength){
    //start 63,63
  }
  public static boolean checkPoint(int x, int y, int step){
    ArrayList available = new ArrayList();
    if(step==0){
      return true;
    }
    else{
      /*
      if(checkPoint(x, y-1, step--)){ //up 1
        available.add(1);
      }
      if(checkPoint(x, y+1, step--)){ //down 2
        available.add(2);
      }
      if(checkPoint(x-1, y, step--)){ //left 3
        available.add(3);
      }
      if(checkPoint(x+1, y, step--)){ //right 4
        available.add(4);
      }
      if(available.size()==0){
        data[x][y] = 5;
        return false;
      }
      else{
        int roll = (int)(available.size()*Math.random())+1;
        int x1=x, y1=y;
        if(roll==1){
          y--;
        }
        else if(roll==2){
          y++
        }
        else if(roll==3){
          x--;
        }
        else if(roll==4){
          x++;
        }
        return checkPoint(x, y, step--);
      }
      */
    }
  }
}
