package JFFCAL001_CSC2002S_Assignment1;

import java.util.concurrent.ForkJoinPool;

/**
*@author Callum Joffee
*
*Class used to sum the square 2D array representing the average daily sunlight 
*received by the foliage of a tree.
*/
public class SumTreeSunlight{
   static long startTime = 0;
	
   private static void tick(){
      startTime = System.currentTimeMillis();
   }
   private static float tock(){
      return (System.currentTimeMillis() - startTime) / 1000.0f; 
   }
   
   static final ForkJoinPool fjPool = new ForkJoinPool();
   static float sum(float[][] arr){
      return fjPool.invoke(new SumTreeArray(arr,0,0,arr.length,arr.length));
   }
   
   public static void main(String[] args){
      int len = 1000;
      float[][] tree1 = new float[len][len];
   
      //float[][] tree1 = {{2.5f, 2.5f}, {2.5f, 2.5f}};
      //float sumTree1 = sum(tree1);
         
      //for (int i = 0; i < tree1.length; i++){
      for (int i = 0; i < len; i++){
         //System.out.print("[ ");
         float row = 0;
         //for (int j = 0; j < tree1[i].length; j++){
         for (int j = 0; j < len; j++){
            tree1[i][j] = 1.5f;
            //System.out.print(tree1[i][j]+" ");
            row += tree1[i][j];
         }
         //System.out.println("] = "+row);
      }
      tick();
      float sumTree1 = sum(tree1);
      float time = tock();
      System.out.println("Sum of tree1 is: "+sumTree1+" taking "+time+" s to calculate.");
   }
}