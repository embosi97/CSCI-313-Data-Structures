package assignment4;

	import java.io.*;
import java.util.*;

	public class A4PrintNameEB{
		
	final static long startTime = System.nanoTime();

		
	   public static List<String> readInFile(String filename){
	      List<String> input = new ArrayList<>();
	      try (Scanner sin = new Scanner(new FileReader(filename))){
	         while (sin.hasNextLine()){
		    input.add(sin.nextLine());
	         }
	      } catch (FileNotFoundException e){
		 e.printStackTrace();
	      }
	      return input;
	  }
	   public static void main(String[] args){
	      List<String> namelist1 = readInFile("A4input1.txt");
	      List<String> namelist2 = readInFile("A4input2.txt");
	      List<String> namelist3 = readInFile("A4input3.txt");
	      Set<String> names = new HashSet<>(); //HashSet does not allow for duplicate values
	      //making it ideal for this situation 
	      
	      names.addAll(namelist1); //add the entire list to the HashSet
	      names.addAll(namelist2); //do that with the other lists
	      names.addAll(namelist3);
						
	      for (String s : names) { //advanced for loop to print all the contents in the HashSet 
	    	    System.out.println(s);
	    	}
	      
	      System.out.print("\n" + startTime);
	}
	}


