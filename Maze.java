import java.util.Random;
public class Maze{
  public int[][] output;
  public int length;
  public int[][] data = new int[49][49];


  public Maze(int n){
    length = n;
  }

  public void route(){
    output = new int[2][length+1];
    output[0][0] = 24;
    output[1][0] = 24;
    Random rand = new Random();

    boolean isdone = false;
    while(!isdone){
      data[24][24] = 1;
      int presentRow = 24;
      int presentCol = 24;
      int[] recordOfDirec = new int[length];
      int numberOfOpen =0;
      int formerDirec = 0;

      for(int i=0;i<length;i++){
        int direc = rand.nextInt(4)+1;
        while(direc+formerDirec==5){
          direc = rand.nextInt(4)+1;
        }
        int nextRow = presentRow;
        int nextCol = presentCol;
        switch(direc){
          case 1: nextRow -=1;break;
          case 2: nextCol -=1;break;
          case 3: nextCol +=1;break;
          case 4: nextRow +=1;break;
        }
        formerDirec = direc;

        if(isQualified(nextRow,nextCol)){
          recordOfDirec[i] = direc;
          data[nextRow][nextCol] = 1;
          presentRow = nextRow;
          presentCol = nextCol;
          numberOfOpen++;
          output[0][numberOfOpen] = nextRow;
          output[1][numberOfOpen] = nextCol;
        }else{
          break;
        }
      }
      if(numberOfOpen == length){
        isdone = true;
      }else{
        for(int i=numberOfOpen-1;i>=0;i--){
          data[presentRow][presentCol] = 0;
          int backDirec = 5-recordOfDirec[i];
          switch(backDirec){
            case 1: presentRow -=1;break;
            case 2: presentCol -=1;break;
            case 3: presentCol +=1;break;
            case 4: presentRow +=1;break;
          }

          data[presentRow][presentCol] = 0;
        }
      }
    }
  }

  public boolean isQualified(int row, int col){
    int count = 0;
    if(data[row+1][col]==0) count++;
    if(data[row-1][col]==0) count++;
    if(data[row][col+1]==0) count++;
    if(data[row][col-1]==0) count++;

    if(count>=3){
      return true;
    }else{
      return false;
    }
  }

}
