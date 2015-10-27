/********************************
* File: examples.java
* Description: This class provides example trees for the
* ConditionalProbabilityTree class
* Author: B. Marlin and M. Lanighan. UMass Amherst CS240.
* Date: Sept. 19, 2015.
*********************************/

import java.util.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class Examples {
  
  /********************************
  * method: Depth2BinaryTree
  * Description: This method produces a conditional probability tree
  * that is a depth 2 binary tree 
  *
  * inputs: treeName - name of tree
  *         eventNames - array of event names for each level in the tree
  *         probs1 - first level probabilities
  *         probs2 - second level probabilities
  *********************************/  
  public static ConditionalProbabilityTree Depth2BinaryTree(String treeName, String[][] eventNames, double[] probs1, double[][] probs2){
    int numberOfLevels = 2;

    ConditionalEventNode root = new ConditionalEventNode("Root",1);
    
    ConditionalEventNode H1 = new ConditionalEventNode(eventNames[0][0],probs1[0]);
    ConditionalEventNode T1 = new ConditionalEventNode(eventNames[0][1],probs1[1]);
    
    ConditionalEventNode H2gH1 = new ConditionalEventNode(eventNames[1][0],probs2[0][0]);
    ConditionalEventNode T2gH1 = new ConditionalEventNode(eventNames[1][1],probs2[0][1]);

    ConditionalEventNode H2gT1 = new ConditionalEventNode(eventNames[1][0],probs2[1][0]);
    ConditionalEventNode T2gT1 = new ConditionalEventNode(eventNames[1][1],probs2[1][1]);
    
    root.add(H1);
    root.add(T1);
    H1.add(H2gH1);
    H1.add(T2gH1);
    T1.add(H2gT1);
    T1.add(T2gT1);
    
    ConditionalProbabilityTree tree = new ConditionalProbabilityTree(root, numberOfLevels, eventNames,treeName);
    return(tree); 
    
  }

  /********************************
  * method: MedicalTestExample
  * Description: This method produces a conditional probability tree
  * for the medical test example. 
  *
  * inputs: none     
  *********************************/
  public static ConditionalProbabilityTree MedicalTestExample(){
    String treeName = "MedicalTestExample";
    String[][] eventNames = {{"HD","ND"},{"TP","TN"}};  
    int numberOfLevels = 2;
    double[] probs1={0.0001,0.9999};
    double[][] probs2 = {{0.99,0.01},{0.05,0.95}};  
    return Depth2BinaryTree(treeName, eventNames, probs1, probs2);
   
  }
  

  /********************************
  * method: CoinExample
  * Description: This method produces a conditional probability tree
  * for the two coin flips problem. 
  *
  * inputs: none     
  *********************************/
  public static ConditionalProbabilityTree CoinFlipExample1(){
    String treeName = "CoinFlipExample1";
    int numberOfLevels = 2;
    String[][] eventNames = {{"H1","T1"},{"H2","T2"}};  
    double[] probs1={0.5,0.5};
    double[][] probs2 = {{0.5,0.5},{0.5,0.5}};  
    return Depth2BinaryTree(treeName, eventNames, probs1, probs2);
  }
  

  /********************************
  * method: CoinFlipExample2
  * Description: This method produces a conditional probability tree
  * for the two coin flips problem. 
  *
  * inputs: none     
  *********************************/
  public static ConditionalProbabilityTree CoinFlipExample2(){
    String treeName = "CoinFlipExample2";
    int numberOfLevels = 2;
    String[][] eventNames = {{"H1","T1"},{"H2","T2"}};  
    double[] probs1={0.5,0.6};
    double[][] probs2 = {{0.5,0.5},{0.5,0.5}};  
    return Depth2BinaryTree(treeName, eventNames, probs1, probs2);
  }

  
  /********************************
  * method: CoinFlipExample3
  * Description: This method produces a conditional probability tree
  * for the two coin flips problem. 
  *
  * inputs: none     
  *********************************/
  public static ConditionalProbabilityTree CoinFlipExample3(){
    String treeName = "CoinFlipExample3";
    int numberOfLevels = 2;
    String[][] eventNames = {{"H1","T1"},{"H2","T2"}};  
    double[] probs1={0.5,0.5};
    double[][] probs2 = {{0.5,0.5},{0.2,0.5}};  
    return Depth2BinaryTree(treeName, eventNames, probs1, probs2);
  }
 
  /********************************
  * method: CoinExample
  * Description: This method produces a conditional probability tree
  * for the three coin flips problem using a biased coin that comes
  * up heads 60% of the time.
  *
  * inputs: none     
  *********************************/
  public static ConditionalProbabilityTree CoinFlipExample4(){
    String treeName = "CoinFlipExample4";
    int numberOfLevels = 2;
    String[][] eventNames = {{"H2","T2"},{"H3","T3"}};  
    double[] probs1={0.6,0.4};
    double[][] probs2 = {{0.6,0.4},{0.6,0.4}};  
    ConditionalProbabilityTree sub1= Depth2BinaryTree(treeName, eventNames, probs1, probs2);
    ConditionalProbabilityTree sub2= Depth2BinaryTree(treeName, eventNames, probs1, probs2);

    ConditionalEventNode root = new ConditionalEventNode("Root",1);
    
    ConditionalEventNode H1 = sub1.root;
    H1.eventName = "H1";
    H1.prob=0.6;
    
    ConditionalEventNode T1 = sub2.root;
    T1.eventName = "T1";
    T1.prob=0.4;
    
    root.add(H1);
    root.add(T1);
    eventNames = new String[][]{{"H1","T1"},{"H2","T2"},{"H3","T3"}}; 
    
    return(new ConditionalProbabilityTree(root, 3, eventNames,"CoinFlipExample4"));

  }

 
 
  public static void main(String [ ] args){
    
    ConditionalProbabilityTree tree;
    tree = MedicalTestExample();
    tree.show();
    
    tree = CoinFlipExample1();
    tree.show();
    
    tree = CoinFlipExample2();
    tree.show();

    tree = CoinFlipExample3();
    tree.show();

    tree = CoinFlipExample4();
    tree.show();    
  }
  
}