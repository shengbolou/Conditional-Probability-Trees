/********************************
* File: ConditionalProbabilityTree.java
* Description: This class provides and object and member functions
* that implement probability computations over Conditional Probability Trees
* Author: B. Marlin and M. Lanighan. UMass Amherst CS240.
* Date: Sept. 19, 2015.
*********************************/

import java.util.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.lang.String;

public class ConditionalProbabilityTree {
  ConditionalEventNode root; //Root of the conditional probability tree data structure
  int numberOfLevels;        //Number of levels in the tree 
  String[][] eventNames;     //Array holding the names of the events at each level.
                             //The first index is over levels. The second index is over
                             //events in the level.
  String treeName;           //String holding the name of the tree
    
  /********************************
  * method: ConditionalProbabilityTree
  * Description: This method is a constructor for the ConditionalProbabilityTree object. 
  * It takes the root of the tree, the number of levels, and an array of arrays of strings
  * giving the names of the events used at each level. 
  *
  * inputs: ConditionalEventNode root - the root of the tree
  *         numberOfLevels - the number of levels in the tree
  *         String[][] eventNames - array of array of event names
  *         String treeName - Name for the tree
  *********************************/
  public ConditionalProbabilityTree(ConditionalEventNode root, int numberOfLevels, String[][] eventNames,String treeName ){

    this.root = root; 
    this.numberOfLevels = numberOfLevels;  
    this.eventNames=eventNames;   
    this.treeName = treeName;
  }
  
  /********************************
  * method: showTree
  * Description: This method shows information about the conditional probability tree 
  *
  * inputs: None       
  *********************************/
  public void show(){
    int[] eventsPerLevel = new int[this.numberOfLevels];
    int totalAtomicEvents = 1 ;
    System.out.println(" ");
    System.out.println(this.treeName);
    System.out.printf("Number of Levels: %d\n",this.numberOfLevels);
    for(int i=0;i<this.numberOfLevels;i++){
      System.out.printf("Level %d Events: ",i+1);
      eventsPerLevel[i] = this.eventNames[i].length;
      totalAtomicEvents = totalAtomicEvents * eventsPerLevel[i];
      for(int j=0;j<this.eventNames[i].length;j++){
        System.out.printf(" %s",this.eventNames[i][j]);
      }
      System.out.printf("\n");
    } 
    this.print();
    
  }
  
  /********************************
  * method: print()
  * Description: This function prints the tree from the root including probabilities
  * and node names. 
  *
  * inputs: None       
  *********************************/  
  public void print(){
    print(this.root,0,"",true);
  }

  /********************************
  * method: print(ConditionalEventNode node, ...)
  * Description: This function prints the tree recursively starting from the given node.
  * and node names.
  *
  * inputs: ConditionalEventNode node - node to start printing from
  *         int depth - depth of the node in the tree
  *         String indent - indent string for current position in tree
  *         boolean up - whether the event is up or down from the previous.  
  *********************************/  
  public void print(ConditionalEventNode node, int depth, String indent, boolean up){
    int i = 0;
    int num = node.getChildCount(); 
    String newindent = indent;
    for(i=0; i<num/2;i++){
        
        ConditionalEventNode child = (ConditionalEventNode) node.getChildAt(i);
        print(child, depth+1, indent+ "               ", true);
    }  
    if(depth==0){
      System.out.printf("(%s)---------|\n", node.eventName);
    }
    else{
      if(up) {System.out.printf("%s/--[%.4f]--(%s)\n",indent ,node.prob, node.eventName);}
      else {System.out.printf("%s\\--[%.4f]--(%s)\n",indent ,node.prob, node.eventName);}
    }    
    for(i=i; i<num;i++){
        
        ConditionalEventNode child = (ConditionalEventNode) node.getChildAt(i);
        print(child, depth+1, indent+"               ",false);
    }
  }

  
  /********************************
  * method: printUnicode(ConditionalEventNode node, ...)
  * Description: This function prints the tree recursively starting from the given node.
  * and node names.
  *
  * inputs: ConditionalEventNode node - node to start printing from
  *         int depth - depth of the node in the tree
  *         String indent - indent string for current position in tree
  *         boolean up - whether the event is up or down from the previous.  
  *********************************/  
  public void printUnicode(ConditionalEventNode node, int depth, String indent, boolean up){
    int i = 0;
    int num = node.getChildCount(); 
    String newindent = indent;
    for(i=0; i<num/2;i++){
        
        ConditionalEventNode child = (ConditionalEventNode) node.getChildAt(i);
        print(child, depth+1, indent+ "               ", true);
    }  
    if(depth==0){
      System.out.printf("(%s)─────────┤\n", node.eventName);
    }
    else{
      if(up) {System.out.printf("%s┌──[%.4f]──(%s)\n",indent ,node.prob, node.eventName);}
      else {System.out.printf("%s└──[%.4f]──(%s)\n",indent ,node.prob, node.eventName);}
    }    
    for(i=i; i<num;i++){
        
        ConditionalEventNode child = (ConditionalEventNode) node.getChildAt(i);
        print(child, depth+1, indent+"               ",false);
    }
  }
  
  
  
}  