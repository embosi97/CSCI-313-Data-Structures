package assignment1;

/*
 * Emiljano Bodaj - CSCI 313-37
 * 37-Tue/Thur 5:00 class
 */

public class DLinkedlistEB<E>{
	   private static class DNode<E>{
	      private E element;
	      private DNode<E> prev;
	      private DNode<E> next;
	      public DNode(E e){
		 this(e, null, null);
	      }
	      public DNode(E e, DNode<E> p, DNode<E> n){
		 element = e;
		 prev = p;
		 next = n;
	      }
	      public E getE(){
		 return element;
	      }
	      public DNode<E> getPrev(){
		 return prev;
	      }
	      public DNode<E> getNext(){
		 return next;
	      }
	      public void setE(E e){
		 element = e;
	      }
	      public void setPrev(DNode<E> p){
		 prev = p;
	      }
	      public void setNext(DNode<E> n){
		 next = n;
	      }
	   }
	   private DNode<E> header;
	   private DNode<E> trailer;
	   private int size;
	   public DLinkedlistEB(){  
	      header = new DNode<E>(null);
	      trailer = new DNode<E>(null, header, null);
	      header.setNext(trailer);
	      size = 0;
	   }
	   public void addFirst(E e){
	      DNode<E> headerNext = header.getNext();
	      DNode<E> tempN = new DNode <E>(e, header, headerNext);
	      headerNext.setPrev(tempN);
	      header.setNext(tempN);
	      size++;
	   }
	   public void addLast(E e){
	      DNode<E> trailerPrev = trailer.getPrev();
	      DNode<E> temp = new DNode<E>(e, trailerPrev, trailer);
	      trailer.setPrev(temp);
	      trailerPrev.setNext(temp);
	      size++; 
	  }
	   
	   public E get(int i) throws Exception{ //my attempt 
	      if (i < 0 || i >= size) throw new Exception("Out of Bounds!");
	      DNode<E> ansNode; //iterator node
	      int sizeN; //counter
	      if (i < size/2){ //if the int value in the parameter is before the middle node, start from the beginning
	    	ansNode = header.getNext();
		 	sizeN = 0;
		 while(sizeN != i){
			 ansNode = ansNode.getNext();
			 sizeN++; 
		 	}
	      }	  
	     else {
	 	 ansNode = trailer.getPrev();
	 	 sizeN = size;
	 	 while(sizeN != i+1){
	 		 ansNode = ansNode.getPrev();
	 		 sizeN--;
	 	 }
	    }
	      return ansNode.getE();
	   }//end of get method
	   
	   
	   public void print(){
	      DNode<E> temp = header.getNext();
	      while (temp != trailer){
	         System.out.print(temp.getE() + ", ");
		 temp = temp.getNext();
	      }
	         System.out.println();
	   }
	   public static void main(String argc[]){
	      DLinkedlistEB<String> l = new DLinkedlistEB<String>();
	      l.addFirst("Three");
	      l.addLast("Four");
	      l.addFirst("Two");
	      l.addFirst("One");
	      l.addLast("Five");
	      l.addFirst("Zero");
	      System.out.print("Numbers: ");
	      l.print();
	      try {
	    	  //I added more to test if my method worked for all cases
	    	 System.out.println("at 0: " + l.get(0));
	         System.out.println("at 1: " + l.get(1));
	         System.out.println("at 2: " + l.get(2));
	         System.out.println("at 3: " + l.get(3));
	         System.out.println("at 4: " + l.get(4));
	         System.out.println("at 5: " + l.get(5));
	         System.out.println("at 6: " + l.get(6));//bad index
	      }
	      catch (Exception e) {
	         System.out.println(e.getMessage());
	      }
	   }
	}