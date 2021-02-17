package assignment1;

/*
 * Emiljano Bodaj - CSCI 313-37
 * 37-Tue/Thur 5:00 class
 */

public class PolynomialLinkedlistEB{
	   private static class PNode{
	      private int coe;
	      private int exp;
	      private PNode next;
	      public PNode(int c, int e){
		 this(c, e, null);
	      }
	      public PNode(int c, int e, PNode n){
		 coe = c;
		 exp = e;
		 next = n;
	      }
	      public void setCoe(int c){ coe = c;}
	      public void setExp(int e){ exp = e;}
	      public void setNext(PNode n){ next = n;}
	      public int getCoe(){ return coe;}
	      public int getExp(){ return exp;}
	      public PNode getNext(){ return next;}
	   }
	   private PNode head;
	   public PolynomialLinkedlistEB(){
	      head = null;
	   }
	   private void add(int c, int e){
	      PNode tempn = new PNode(c, e, head);
	      head = tempn;
	   }
	  
	   public void removeDuplicate(){
	      if (head == null) return;
	      PNode lookUp, checkPrev;
	      lookUp = checkPrev = head;
	      while (lookUp.getNext() != null){
		 while (checkPrev.getNext() != null){
		     if (checkPrev.getNext().getExp() == lookUp.getExp()){//found a duplicate
			//lookUp.coe += checkPrev.next.coe;
			lookUp.setCoe(lookUp.getCoe() + checkPrev.getNext().getCoe());
			checkPrev.setNext(checkPrev.getNext().getNext());
		     }
		     else checkPrev = checkPrev.getNext();
		 }
	         lookUp = lookUp.getNext();
		 checkPrev = lookUp;
	      }	     	     
	   }
	   public PolynomialLinkedlistEB add(PolynomialLinkedlistEB pl){
	      PNode addTerm = this.head;
	      PolynomialLinkedlistEB ans = new PolynomialLinkedlistEB();
	      for (int i = 0; i < 2; i++){
		 while (addTerm != null){
		    ans.add(addTerm.getCoe(), addTerm.getExp());
		    addTerm = addTerm.next;
		 }
	         addTerm = pl.head;
	      }
	      ans.removeDuplicate();
	      return ans;	 
	   }
	   public PolynomialLinkedlistEB multiply(PolynomialLinkedlistEB otherLL){ //my attempt
		      PolynomialLinkedlistEB ans = new PolynomialLinkedlistEB();//the product LL
		      PNode firstLL = this.head; //node reference to the head node of the this LL
		      PNode secondLL = otherLL.head; //node reference to the first node in the parameter LL
		      PNode dummyNode = new PNode(0, 0); //a dummy node we'll use to fill-in the product LL = 0
		      while(firstLL != null){ //while current node isn't null
		        while(secondLL != null){
		          dummyNode.setCoe((firstLL.getCoe()) * (secondLL.getCoe())); //multiply the coefficients
		          dummyNode.setExp((firstLL.getExp()) + (secondLL.getExp())); //add the exponents 
		          ans.add(dummyNode.getCoe(), dummyNode.getExp()); //then add the result to the newly created LL
		          secondLL = secondLL.getNext(); //proceed to the next node in the otherLL
		        }//when the current value of the this LL had been multiplied with 
		        //all the values of the other LL, proceed to the next value in the this LL
		        secondLL = otherLL.head;
		        firstLL = firstLL.getNext();
		      }
		      return ans;//return the product LL
		 }//end of multiply method
	   
	   public void print(){
		      if (head == null){
		         System.out.println();
		         return;
		      }
		      removeDuplicate();
		      PNode temp = head;
		      PNode maxNode = temp.getNext();
		      PNode checkNode = head;
		      //Algorithm to sort it in descending order
		    	  if(temp.getExp() < maxNode.getExp() && temp == head){
		    		  temp.setNext(maxNode.getNext());
		    		  maxNode.setNext(temp);		    		
		    		  checkNode = maxNode;
		    		  maxNode = temp.getNext();	    			    			  
		    		  }	 
		    	  else {
		    		  temp = temp.getNext();
		    		  maxNode = maxNode.getNext();
		    	  }
		    	  while(temp.getNext() != null){
		    	  if(checkNode.getExp() < maxNode.getExp() && maxNode.getNext() != null){
		    		  temp.setNext(maxNode.getNext());
		    		  maxNode.setNext(checkNode);		    		  
		    		  checkNode = maxNode;
		    		  maxNode = temp.getNext();
		    	  }
		    	  if(checkNode.getExp() < maxNode.getExp() && maxNode.getNext() == null ){
		    		  temp.setNext(null);
		    		  maxNode.setNext(checkNode);		    		  
		    		  checkNode = maxNode;		    		  
		    	  	}
		    	  if(checkNode.getExp() > maxNode.getExp()){
		    		  temp = temp.getNext();
		    		  maxNode = maxNode.getNext();
		    	  }
		    	  }
		    	temp = checkNode;	 
		      while (temp.getNext() != null){
		         if (temp.getCoe() != 0){
			    System.out.print("(" + temp.getCoe() + ")");
			    if (temp.getExp() != 0){
			       System.out.print("X^" + temp.exp);
			    }
			    System.out.print(" + ");
		  	 }
			 temp = temp.next;
		      }
		      if (temp != null){
		         System.out.print("(" + temp.coe + ")");
			 if (temp.exp != 0) System.out.print("X^" + temp.exp);
		         System.out.println();
		      }	      

		   }
	   
	   public static void main(String argc[]){
	      PolynomialLinkedlistEB pn1 = new PolynomialLinkedlistEB();
	      PolynomialLinkedlistEB pn2 = new PolynomialLinkedlistEB();
	      pn1.add(1, 2);
	      pn1.add(2, 3);
	      pn2.add(1, 0);
	      pn2.add(5, 1);
	      pn2.add(-6, 1);
	      pn2.add(4, 2);
	      System.out.print("Polynomial 1: ");
	      pn1.print();
	      System.out.print("Polynomial 2: ");
	      pn2.print();
	      PolynomialLinkedlistEB sum = pn1.add(pn2);
	      PolynomialLinkedlistEB prod = pn1.multiply(pn2);
	      System.out.print("Sum: ");
	      sum.print();   
	      System.out.print("Product: ");
	      prod.print();   
	   }
	}
