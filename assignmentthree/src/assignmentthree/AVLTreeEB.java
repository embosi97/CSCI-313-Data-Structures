package assignmentthree;
/**@author Emiljano Bodaj (Section 37 Tue/Thu 5:00 pm -6:15 pm)
 * 
 * @project assignment 3 Part 1a
 * 
 */ 
import java.lang.Math; 

public class AVLTreeEB<E extends Comparable<? super E>>{
   private static class AVLNode<E extends Comparable<? super E>>{
      private E element;
      private AVLNode<E> parent;
      private AVLNode<E> left;
      private AVLNode<E> right;
      int height;
      public AVLNode(E e){
	 this(e, null, null);
      }
      public AVLNode(E e, AVLNode<E> l, AVLNode<E> r){
	 element = e;
	 left = l;
	 right = r;
         height = 0;
         if (l != null && r != null) height = Math.max(l.height, r.height) + 1;
         else if (r != null) height = r.height + 1;
         else if (l != null) height = l.height + 1;
      }
      public E getE(){
	 return element;
      }
      public AVLNode<E> getParent(){
	 return parent;
      }
      public AVLNode<E> getLeft(){
	 return left;
      }
      public AVLNode<E> getRight(){
	 return right;
      }
      public int getHeight(){
	 return height;
      }
      public void setE(E e){
	 element = e;
      }
      public void setParent(AVLNode<E> p){
	 parent = p;
      }
      public void setLeft(AVLNode<E> l){
	 left = l;
      }
      public void setRight(AVLNode<E> r){
	 right = r;
      }
      public void setHeight(int h){
	 height = h;
      }
   }
   private AVLNode<E> root;
   private int size;
   public AVLTreeEB(){
      root = null;
      size = -1;
   }
   private AVLNode<E> balance(AVLNode<E> n){
      if (n == null || isLeaf(n))
         return n;
      int bFactor = height(n.left) - height(n.right);
      if (bFactor > 1){
         if (height(n.left.left) >= height(n.left.right))
            n = rotateLeftChild(n);
         else
            n = doubleRotateLeftChild(n);
      }
      else if (bFactor < -1){
         if (height(n.right.right) >= height(n.right.left))
	    n = rotateRightChild(n);
         else
	    n = doubleRotateRightChild(n);
      }
      n.height = Math.max(height(n.left), height(n.right)) + 1;
      return n;
   }
   public int height(){
      return height(root);
   }
   private int height(AVLNode<E> n){
      if (n == null) return -1;
      return n.height;
   }
   private boolean isLeaf(AVLNode<E> n){
      if (n == null || n.left != null || n.right != null) return false;
      return true;
   }
  
   /**part 1: range method
    * 
    * 
    * @return
    */ 
   private int range(){
	   AVLNode<E> start = root;
	   AVLNode<E> element1 = findMin(start); //most rightward node
	   AVLNode<E> element2 = findMax(start); //most leftward node
	   Integer minData = (Integer) element1.getE(); //add Integer cast to make it an int value
	   Integer maxData = (Integer) element2.getE();
	   return maxData - minData; //compareTo yielded a different number for some reason
   }
   
   /**part 2: rotateLeftChild method
    * 
    * 
    * @return
    */ 
   private AVLNode<E> rotateLeftChild(AVLNode<E> n1){
      AVLNode<E> n2 = n1.left;
      n1.left = n2.right;
      n2.right =  n1;
      n1.height =  Math.max(height(n1.left), height(n1.right)) + 1;
      n2.height = Math.max(height(n2.left), height(n2.right)) + 1;
      n2.setParent(n1.getParent());
      n1.setParent(n2);
      if (n1.left != null)
          n1.left.setParent(n1);
      return n2;
  }
   private AVLNode<E> rotateRightChild(AVLNode<E> n1){
      AVLNode<E> n2 = n1.right;
      n1.right = n2.left;
      n2.left =  n1;
      n1.height =  Math.max(height(n1.left), height(n1.right)) + 1;
      n2.height = Math.max(height(n2.left), height(n2.right)) + 1;
      n2.setParent(n1.getParent());
      n1.setParent(n2);
      if (n1.right != null)
         n1.right.setParent(n1);
      return n2;
  }
   private AVLNode<E> doubleRotateLeftChild(AVLNode<E> n1){
      n1.left = rotateRightChild(n1.left);
      return rotateLeftChild(n1);
   }
   private AVLNode<E> doubleRotateRightChild(AVLNode<E> n1){
      n1.right = rotateLeftChild(n1.right);
      return rotateRightChild(n1);
   }

   public void add(E e){
      root = add(e, root);
      root.setParent(null);
   }
   private AVLNode<E> add(E e, AVLNode<E> n){
      if (n == null){
         size++;
         return  new AVLNode <E>(e);
      }
      int compare = e.compareTo(n.getE());
      if (compare < 0){
	 n.left = add(e, n.left);
	 n.left.setParent(n);
      }
      else if (compare > 0){
	 n.right = add(e, n.right);
	 n.right.setParent(n);
      }
      n = balance(n); 
      return n;
   }
   public AVLNode<E> getRoot(){
      return root;
   }
   public AVLNode<E> findMin(AVLNode<E> n){
      if (n == null) return n;
      while (n.left != null) n = n.left;
      return n;
   }
   //similarly how the min value is the leftmost node
   //the max is the rightmost node
   public AVLNode<E> findMax(AVLNode<E> n){
	      if (n == null) return n;
	      while (n.right != null) n = n.right;
	      return n;
	   }
   
   public int size(){ return size;}
   public void print(){
         System.out.println("Total: " + size);
	 inOrder(root);
         System.out.println();
   }
   public void print2(){
         System.out.println("Total: " + size);
	 preOrder(root);
         System.out.println();
   }
   public void inOrder(AVLNode<E> n){
         if (n == null) return;
         inOrder(n.left);
         System.out.print(n.getE() + ", ");
         inOrder(n.right);
   }
   public void preOrder(AVLNode<E> n){
         if (n == null) return;
  	 System.out.print(n.getE() + ", " );
 	 preOrder(n.left);
 	 preOrder(n.right);
 }
   public static void main(String argc[]){
      AVLTreeEB<Integer> tr = new AVLTreeEB<Integer>();
      tr.add(4);
      tr.add(5);
      System.out.println("Tree Height:" + tr.height());
      tr.print();
      tr.add(6);
      System.out.println("Tree Height:" + tr.height());
      tr.print();
      tr.add(1);
      System.out.println("Tree Height:" + tr.height());
      tr.print();
      tr.add(2);
      System.out.println("Tree Height:" + tr.height());
      tr.print();
      tr.add(0);
      System.out.println("Tree Height:" + tr.height());
      tr.print();
      tr.add(3);
      System.out.println("Tree Height:" + tr.height());
      tr.print();
      tr.print2();
        
      System.out.print("\n" + "Range of Tree: " + tr.range());
   }
}