package assignmenttwo;
/*
 * Emiljano Bodaj - CSCI 313-37
 * 37-Tue/Thur 5:00 class
 */

import java.util.EmptyStackException;

public class LinkedStackEB<E> { //LinkedStack from class
	public class Node<E> {
		protected E element;
		protected Node<E> next;
  	  //no argument constructor for Node
		public Node(){
			element = null;
			next = null;
		}
		//1-argument constructor 
		public Node(E element){
			this.element = element;
			next = null;
		}
		}
	private Node<E> top; //essentially the head node
	private int size;
	public LinkedStackEB() {
		top = null;
		size = 0;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public int size() {
		return size;
	}

	public E top() {
		if(isEmpty() == true) throw new EmptyStackException();
		return top.element;
	}
	
	public E pop() {
	if(isEmpty() == true) throw new EmptyStackException();
	E temp= top.element;
	top = top.next;
	size--;
	return temp;
	}
	
	public void push(E element){
		Node<E> temp = new Node<>(element);
		temp.next = top;
		top = temp;
		size++;
	}
  public static boolean isMatch(String str){
	  LinkedStackEB<Character> charStack = new LinkedStackEB<>();
	  int n = str.length();
	  for(int i = 0; i < n; i++){  
		   //if a character is an opening, push it into the stack
		    if (str.charAt(i) == '{' || str.charAt(i) == '(' || str.charAt(i) == '[')
		       {
		          charStack.push(str.charAt(i));
		       }
		    //if a character is a closing
		    if (str.charAt(i) == '}' || str.charAt(i) == ')' || str.charAt(i) == ']')
		       {
		           if (charStack.isEmpty()) //in case there are no openings on the stack
		               return false;
		           if ((str.charAt(i) == '}' && charStack.top() == '{') || (str.charAt(i) == ')' && charStack.top() == '(') || (str.charAt(i) == ']' && charStack.top() == '[')){
		               charStack.pop(); //pop it off since there's a match
		           }            
		       }
		   }
		    if (charStack.isEmpty() == true) return true; //if the stack is empty after the loop, it is balanced
		    else 
		    return false;
		}
		public static void main(String argc[]){
		     
		     String[] expression = new String[]{"{5*(x+2)+(y-1);}", "32*(20+(x[i]*3)-1",

		     "((){([][])})", "({}((0))", "{([]})", "{}())", "{", "}", "((((()))))"};

		      for (String st: expression)

		      System.out.println(st + " is " + isMatch(st));

		     }

		}
