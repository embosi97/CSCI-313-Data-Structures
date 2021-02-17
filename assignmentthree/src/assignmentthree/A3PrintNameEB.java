package assignmentthree;

/**@author Emiljano Bodaj (Section 37 Tue/Thu 5:00 pm -6:15 pm)
 * 
 * @project assignment 3 Part 1b
 * 
 */ 
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
public class A3PrintNameEB{
	
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
      List<String> names = readInFile("A3input.txt"); //reads the file and puts its contents in an ArrayList      
      //    key     value
      Map<String, Integer> nameMap = new LinkedHashMap<String, Integer>(); //I did LinkedHashMap so it'll be in alphabetical order
      
      for(String nameKey : names){ //converting the ArrayList to a HashMap
    	  nameMap.put(nameKey, nameKey.length()); //name of the person is the key and the # of characters is the value
      }
              
      //     key       value
      Map<Integer, List<String>> valuesMap = new HashMap<Integer, List<String>>(); //new HashMap with a List as its values
      
      //A map entry (key-value pair). The Map.entrySet method returns a collection-view of the map, whose elements are of this class.
      
      for(Entry<String,Integer> entrySet : nameMap.entrySet()){
    	  List<String> n = new ArrayList<String>(); //list to store the grouped values based off key
    	  
    	  if(valuesMap.containsKey(entrySet.getValue())){ //checks whether a particular value is being mapped by a single or more than one key in the HashMap.
              n = valuesMap.get(entrySet.getValue()); //get returns the value to which the specified key is mapped and adds that to a list
    	  }
    	  n.add(entrySet.getKey()); 
          valuesMap.put(entrySet.getValue(), n);
      }
      Integer frontKey = valuesMap.keySet().iterator().next(); //removing the 0 key 
      valuesMap.remove(frontKey);

       
    	  
      
      System.out.print("Without grouping: " + nameMap + "\n" + "\n");
      
      System.out.println("With Grouping: " + valuesMap);
   }
}