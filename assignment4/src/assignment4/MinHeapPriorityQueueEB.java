package assignment4;

public class MinHeapPriorityQueueEB<E extends Comparable<? super E>>{
	   private E data[];
	   private int size;
	   public MinHeapPriorityQueueEB(){
	      this(100);
	   }
	   public MinHeapPriorityQueueEB(int cap){
	      size = 0;
	      data = (E[]) new Comparable[cap];
	   }
	   public MinHeapPriorityQueueEB(E[] a){
		   size  = a.length;
		   data = (E[]) new Comparable[a.length + 1];
		   for (int i = 0; i < size; i++)
			   data[i + 1] = a[i];   
		   for (int i = 0; i < size; i++){
		   bubbleUp(i);
	   }
	   }
	   public void add(E d) throws Exception{
	      if (size >= data.length - 1) throw new Exception("Full");
	      data[++size] = d;
	      bubbleUp(size);
	   }
	   public E peek() throws Exception{
	      if (size <= 0) throw new Exception("Empty");
	      return data[1];
	   }
	   public E remove() throws Exception{
	      if (size <= 0) throw new Exception("Empty");
	      E ans = data[1];
	      data[1] = data[size--];
	      bubbleDown(1);
	      return ans;
	   }
	   private void swap2(int i, int j){
	      E temp = data[i];
	      data[i] = data[j];
	      data[j] = temp;
	   }
	   private void bubbleUp(int i){
	      if (i <= 1) return;
	      if (data[i].compareTo(data[i / 2]) >= 0) return;
	      swap2(i, i / 2);
	      bubbleUp(i / 2);
	   }
	   private void bubbleDown(int i){
	      if ((i * 2) > size) return;
	      int min_i = i * 2;
	      if (min_i + 1 <= size) {
	         if (data[min_i].compareTo(data[min_i + 1]) > 0)
		    min_i += 1;
	      }
	      if (data[i].compareTo(data[min_i]) <= 0) return;
	      swap2(i, min_i);
	      bubbleDown(min_i);
	   }
	   public static void main(String[] args){
	      Integer[] a = new Integer[]{3,2,7,5,1,6,4,8,9};
	      try {
	         long time, nextTime;
	         time = System.nanoTime();
	         MinHeapPriorityQueueEB minHeap = new MinHeapPriorityQueueEB(a.length + 1);
	         for (int i = 0; i < a.length; ++i){
	            minHeap.add(a[i]);
	         }
	         nextTime = System.nanoTime();
	         System.out.println("\tTime used: " + (nextTime - time) + " nseconds");
	         for (int i = 0; i < a.length; ++i){
		    System.out.print(minHeap.remove() + ",");
		 }
	         System.out.println();
	      }catch (Exception e){
		 e.printStackTrace();
	      }
	      try {
	         long time, nextTime;
	         time = System.nanoTime();
	         MinHeapPriorityQueueEB minHeap = new MinHeapPriorityQueueEB(a);
	         nextTime = System.nanoTime();
	         System.out.println("\tTime used: " + (nextTime - time) + " nseconds");
	         for (int i = 0; i < a.length; ++i){
		    System.out.print(minHeap.remove() + ",");
		 }
	         System.out.println();
	      }catch (Exception e){
		 e.printStackTrace();
	      }
	   }

	}