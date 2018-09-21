//package src;

import java.util.concurrent.ForkJoinPool;
import java.io.*;

/**
*@author Callum Joffee
*
*Class used to sum the square 2D array representing the average daily sunlight 
*received by the foliage of a tree.
*/
public class SumTreeSunlightSeq{
   static long startTime = 0;
	
   private static void tick(){
      startTime = System.currentTimeMillis();
   }
   private static float tock(){
      return (System.currentTimeMillis() - startTime) / 1000.0f; 
   }
   
   /**
   static final ForkJoinPool fjPool = new ForkJoinPool();
   static float sum(float[][] arr, int[] param){
      int endVert = param[0]+param[2];
      int endHori = param[1]+param[2];
      if (endVert>arr.length){
         endVert=arr.length;
      }
      if (endHori>arr[param[0]].length){
         endHori=arr[param[0]].length;
      }
      return fjPool.invoke(new SumTreeArray(arr,param[0],param[1],endVert,endHori));
   }*/
   
   public static void main(String[] args) throws IOException{
      //String inFile = args[0];
      //String inFile = "sample_input.txt";
      String inFile = "src/sample_input.txt";
      //System.out.println(inFile);
      //String outFile = args[1];
      
      try{
         //Input file reader
         BufferedReader inF = new BufferedReader(new FileReader(inFile));
         String line = inF.readLine().trim();
         while (line!=null){
            String[] field = line.split(" ");
            int n = Integer.parseInt(field[0]);
            int m = Integer.parseInt(field[1]);
            float[][] area = new float[n][m];
         
            line = inF.readLine();
            field = line.split(" ");
            for (int i = 0; i < n; i++){
               for (int j = 0; j < m; j++){
                  area[i][j] = Float.parseFloat(field[m*i+j]);
               }
            }
         
            int trees = Integer.parseInt(inF.readLine());
            int[][] treeParam = new int[trees][3];
            for (int i = 0; i < trees; i++){
               field = inF.readLine().trim().split(" ");
               treeParam[i][0] = Integer.parseInt(field[0]);
               treeParam[i][1] = Integer.parseInt(field[1]);
               treeParam[i][2] = Integer.parseInt(field[2]);
            }
            //array of exposure per tree
            float[] treeExpos = new float[trees];
            double totExpos = 0;
            double avgExpos;
            float time;
               
            tick(); //timing loop started
            for (int i = 0; i < trees; i++){
               treeExpos[i] = 0;
               int endVert = treeParam[i][0]+treeParam[i][2];
               int endHori = treeParam[i][1]+treeParam[i][2];
               if (endVert>area.length){
                  endVert=area.length;
               }
               if (endHori>area[treeParam[i][0]].length){
                  endHori=area[treeParam[i][0]].length;
               }
            
               for (int j = treeParam[i][0]; j < endVert; j++){
                  for (int k = treeParam[i][1]; k< endHori; k++){
                  //treeExpos[i] = sum(area, treeParam[i]);
                     treeExpos[i] += area[j][k];
                  }
               }
               totExpos += treeExpos[i];
            }
            avgExpos = totExpos/trees;
            time = tock();
            
            System.out.println(avgExpos);
            System.out.println(trees);
            //for (int i = 0; i<trees; i++){
            //   System.out.println(treeExpos[i]);
            //}
            
            System.out.println("Trees: "+trees);
            System.out.println(time+" s");
            
            line = null;
         }
         
         inF.close();
      }
      catch(FileNotFoundException e){
         System.out.println("File Not Found.");
      }
         
   }
}
