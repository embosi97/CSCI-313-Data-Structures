package assignment4;

import java.util.Arrays;

public class A4SortEB {
	
	public static void merge(int[] b, int low, int mid, int high){
		int i = low; 
		int j = mid; 
		int k = 0;
		   int[] arr = new int[low + high + 1]; 		   
		   for (int x = 0; x < b.length; x++) {
	            for (int y = x+1; y < b.length; y++) {
	                if ( (b[x] > b[y]) && (x != y) ) {
	                    int temp = b[y];
	                    b[y] = b[x];
	                    b[x] = temp;
	                }
	            }
	        }	   
	    }           
	            
	
		   public static void mergeSort(int[] a, int l, int h){
		      if (l >= h) return;
		      int mid = (h + l) / 2;
		      mergeSort(a, l, mid); 
		      mergeSort(a, mid + 1, h);
		      merge(a, l, mid + 1, h); 
		   }
		   
		   public static void main(String[] args){
		      int[] a = {3,5,1,8,7,6,4,2,7};
		      System.out.println("mergeSort a: ");
		      long time, nextTime;
		      time = System.nanoTime();
		      mergeSort(a, 0, a.length - 1);
		      nextTime = System.nanoTime();
		      System.out.println("\tTime used: " + (nextTime - time) + " nseconds");
		      for (int i = 0; i < a.length; i++) System.out.print(a[i] + ",");
		      System.out.println();
		   }

		}

