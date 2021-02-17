package assignmenttwo;
import java.util.*;

/*
 * Emiljano Bodaj - CSCI 313-37
 * 37-Tue/Thur 5:00 class
 */


public class TreeEB {
	
	   private static class TNode{
	      private int data;
	      private TNode parent;
	      private List<TNode> children;
	      public TNode(){
	         this(0, null);
	      }
	      public TNode(int e){
	         this(e, null);
	      }
	      public TNode(int e, TNode p){
	         data = e;
		 parent = p;
		 children = new ArrayList<TNode>();
	      }
	      public int getData(){
		 return data;
	      }
	      public TNode getParent(){
	         return parent;
	      }
	      public List<TNode> getChildren(){
	         return children;
	      }
	   }
	   private TNode root;
	   private int size;
	   TreeEB(){
	      root = null;
	      size = 0;
	   }
	   public TNode createNode(int e, TNode p){
	      return new TNode(e, p);
	   }
	   public TNode addChild(TNode n, int e){
		 TNode temp = createNode(e, n);
	      n.children.add(temp);
	      size++;
	      return temp;
	   }
	   public TNode addRoot(int e) throws IllegalArgumentException{
	      if (root != null) throw new IllegalArgumentException("Root is full");
	      root = createNode(e, null);
	      size++;
	      return root;
	   }
	   public void levelOrder(){   
		      System.out.print("Level Order: ");
		      List<TNode> queue = new LinkedList<>();
		      queue.add(root); //add the root to the queue
		      levelOrderPrint(queue); //the tree will be printed in levelorderprint
		      System.out.println();
		   }
		   private void levelOrderPrint(List<TNode> list){
			  if (list.isEmpty() == true) return;
			  int ancestor = root.getData();
			  TNode children = list.remove(ancestor);
			  //prints out the internal nodes
			  System.out.print(children.getData() + " ");
			  //add descendant nodes (children of the children) to the list
			  list.addAll(children.getChildren());
			  //then recursively call this method
			  levelOrderPrint(list);	
			  }

	   public void preOrder(){
		  System.out.print("Pre-Order: ");
	      preOrderPrint(root);
	      System.out.println();
	   }
	   private void preOrderPrint(TNode n){
	      if (n == null) return;
	      System.out.print(n.getData() + " "); //data then traverse n's children
	      for (TNode cn: n.getChildren())
	         preOrderPrint(cn);
	   }
	   public void postOrder(){
		   System.out.print("Post-Order: ");
	       postOrderPrint(root);
	   }
	   private void postOrderPrint(TNode n){ //traverse n's children then data
		   if (n == null) return;
		      for (TNode cn: n.getChildren()) //prints the nodes starting from the leftmost leaf node (7)
		    	  //up then down again to the rightmost leaf node then the root (0)
		         postOrderPrint(cn);
		      System.out.print(n.getData() + " ");
	   }
	   public boolean isSubTree(TNode tn){
		   if (tn == null) return false;
		   TNode rootCheck = root;
		   
		   

	      return true;
	   }
	   public void makeTree(){
	      TNode rt = addRoot(0);
	      buildTree(rt, 3);
	   }
	   public void makeTree2(){
	      TNode rt = addRoot(0);
	      buildTree(rt, 1);
	   }
	   public TNode getRoot(){
	      return root;
	   }
	   public int height(){ //The last number is the height of the root
		        return height(root);
		     }
	   private int height(TNode tn){
		    if (tn == null) return 0;
		    LinkedStackEB<Integer> LL = new LinkedStackEB<>(); //a LL stack to contain the height values
		    int counter, hei = 0;
		    //to get to the leaf nodes
		    for (TNode cn: tn.getChildren()){ //enhanced for-loop
		          counter = height(cn); //recursively call height and store the values into counter
		          if(hei < counter){ //in other tree traversals, they have 'left' and 'right' references
		        	  //and they compare the heights to see which ones yields the highest height
		        	  hei = counter; //update the max value, hei is the initialized value we will use
		    }
		  }  
	      LL.push(hei + 1); //add 1 to count the root
	      int popped = LL.top();
	      System.out.print(Math.max(popped, popped) + " - "); //just for display purposes
		       return hei + 1;
		    }
	   private void buildTree(TNode n, int i){
	      if (i <= 0) return;
	      TNode fc = addChild(n, size);
	      TNode sc = addChild(n, size);
	      TNode tc = addChild(n, size);
	      buildTree(fc, i - 1);
	      buildTree(sc, i - 2);
	      if (i % 2 == 0)
	         buildTree(tc, i - 1);
	   }

	   public static void main(String[] args){
	      TreeEB t = new TreeEB();
	      TreeEB t2 = new TreeEB();
	      t.makeTree();
	      t.levelOrder();	//0 1 2 3 4 5 6 13 14 15 7 8 9 10 11 12
	      t.preOrder();	//0 1 4 7 8 9 5 6 10 11 12 2 13 14 15 3
	      t.postOrder();	//7 8 9 4 5 10 11 12 6 1 13 14 15 2 3 0
	      TNode subT = t.getRoot().getChildren().get(1);
	      t.isSubTree(subT);
	      t2.makeTree2();
	      System.out.print("\n");
	      t2.levelOrder();	//0 1 2 3 
	      t2.preOrder();	//0 1 2 3
	      t2.postOrder(); 	//1 2 3 0
	      System.out.print("\n");
		  System.out.print("Height of t nodes: ");
	      t.height(); //height should be 4
	      System.out.print("\n");
		  System.out.print("Height of t2 nodes: ");
	      t2.height(); //height should be 2
	      t2.isSubTree(subT);
	      t.isSubTree(t2.getRoot());
	   }

	}
