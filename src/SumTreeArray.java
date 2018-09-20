package src;

import java.util.concurrent.RecursiveTask;
/**
*@author Callum Joffee
*/
public class SumTreeArray extends RecursiveTask<Float> {
   private int lo; // arguments
   private int hi;
   private int rhi;
   private int rlo;
   private float[][] arr;
   private static final int SEQUENTIAL_CUTOFF=5000;
     
   float ans = 0;
     
   SumTreeArray(float[][] a, int l, int rl, int h, int rh){
      lo = l;
      hi = h;
      arr = a;
      rlo = rl;
      rhi = rh;
   }
   
   protected Float compute(){
      if((hi-lo)<SEQUENTIAL_CUTOFF){
         ans = 0;
         for(int i=lo; i < hi; i++)
            if((rhi-rlo)<SEQUENTIAL_CUTOFF){
               float row = 0;
               for(int j=rlo; j < rhi; j++){
                  row += arr[i][j];
               }
               ans += row;
            }
            else{
               SumTreeArray left = new SumTreeArray(arr, lo, rlo, hi, (rhi+rlo)/2);
               SumTreeArray right = new SumTreeArray(arr, lo, (rhi+rlo)/2, hi, rhi);
               
               left.fork();
               float rightAns = right.compute();
               float leftAns = left.join();
               return leftAns + rightAns;
            }
         return ans;
      }
      else{
         SumTreeArray left = new SumTreeArray(arr, lo, rlo, (hi+lo)/2, rhi);
         SumTreeArray right = new SumTreeArray(arr, (lo+hi)/2, rlo, hi, rhi);
               
         left.fork();
         float rightAns = right.compute();
         float leftAns = left.join();
         return leftAns + rightAns;
      }
   }  
}