/********************************
* File: tests.java
* Description: This class provides tests for the
* ConditionalProbabilityTree class
* Author: B. Marlin and M. Lanighan. UMass Amherst CS240.
* Date: Sept. 19, 2015.
*********************************/

import java.util.*;
import javax.swing.tree.DefaultMutableTreeNode;
import static org.junit.Assert.*;
import org.junit.Test;

public class Tests { 
  
  
  
    @Test
    public void testValidity(){
      System.out.println(" Validity Check Tests:");
        
      ConditionalProbabilityTree tree = Examples.MedicalTestExample();
      boolean output = Computations.checkValid(tree);
      boolean shouldBe = true;
      System.out.printf("    %s: Validity Check Test. Should be: %b. Returned: %b \n",tree.treeName,shouldBe,output); 
      org.junit.Assert.assertTrue(tree.treeName + ": Validity Check should be true",output);  

      tree = Examples.CoinFlipExample1();
      output = Computations.checkValid(tree);
      shouldBe = true;
      System.out.printf("    %s: Validity Check Test. Should be: %b. Returned: %b \n",tree.treeName,shouldBe,output); 
      org.junit.Assert.assertTrue(tree.treeName + ": Validity Check should be true",output);  

      tree = Examples.CoinFlipExample2();
      output = Computations.checkValid(tree);
      shouldBe = false;
      System.out.printf("    %s: Validity Check Test. Should be: %b. Returned: %b \n",tree.treeName,shouldBe,output); 
      org.junit.Assert.assertFalse(tree.treeName + ": Validity Check should be false",output);  

      tree = Examples.CoinFlipExample3();
      output = Computations.checkValid(tree);
      shouldBe = false;
      System.out.printf("    %s: Validity Check Test. Should be: %b. Returned: %b \n",tree.treeName,shouldBe,output); 
      org.junit.Assert.assertFalse(tree.treeName + ": Validity Check should be false",output);  

      tree = Examples.CoinFlipExample4();
      output = Computations.checkValid(tree);
      shouldBe = true;
      System.out.printf("    %s: Validity Check Test. Should be: %b. Returned: %b \n",tree.treeName,shouldBe,output); 
      org.junit.Assert.assertTrue(tree.treeName + ": Validity Check should be false",output);  
      
      
    }
    
    @Test
    public void testIndependence(){
      System.out.println(" Independence Check Tests:");

      ConditionalProbabilityTree tree = Examples.MedicalTestExample();
      boolean output = Computations.checkIndependent(tree);
      boolean shouldBe = false;
      System.out.printf("    %s: Independence Check Test. Should be: %b. Returned: %b \n",tree.treeName,shouldBe,output);
      org.junit.Assert.assertFalse(tree.treeName + ": Independence Check should be false",output);  

      tree = Examples.CoinFlipExample1();
      output = Computations.checkIndependent(tree);
      shouldBe = true;
      System.out.printf("    %s: Independence Check Test. Should be: %b. Returned: %b \n",tree.treeName,shouldBe,output);
      org.junit.Assert.assertTrue(tree.treeName + ": Independence Check should be true",output);  

      tree = Examples.CoinFlipExample4();
      output = Computations.checkIndependent(tree);
      shouldBe = true;
      System.out.printf("    %s: Independence Check Test. Should be: %b. Returned: %b \n",tree.treeName,shouldBe,output);
      org.junit.Assert.assertTrue(tree.treeName + ": Independence Check should be true",output);  
      
      
    }

    @Test
    public void testProbCalcs(){
 
      System.out.println(" Probability Calculation Tests:");
 
      ConditionalProbabilityTree tree = Examples.MedicalTestExample();
      
      double output = Computations.computeProbability(new String[]{"HD","TP"},tree);
      double shouldBe = 0.000099;
      System.out.printf("    MedicalTestExample: P(HD n TP) should be %f. Returned: %f\n", shouldBe, output);
      org.junit.Assert.assertEquals("MedicalTestExample: P(HD n TP) compuation failed.",output,shouldBe, 1e-6 );  
      
      output = Computations.computeProbability(new String[]{"","TN"},tree);
      shouldBe = 0.949906;
      System.out.printf("    MedicalTestExample: P(TN) should be %f. Returned: %f\n", shouldBe, output);
      org.junit.Assert.assertEquals("MedicalTestExample: P(TN) compuation failed.",output,shouldBe, 1e-6 );  
      
      output = Computations.computeProbability(new String[]{"ND",""},tree);
      shouldBe = 0.999900;
      System.out.printf("    MedicalTestExample: P(ND) should be %f. Returned: %f\n", shouldBe, output);
      org.junit.Assert.assertEquals("MedicalTestExample: P(ND) compuation failed.",output,shouldBe, 1e-6 );        

      
      tree = Examples.CoinFlipExample4();

      output = Computations.computeProbability(new String[]{"H1","H2","H3"},tree);
      shouldBe = 0.6*0.6*0.6;
      System.out.printf("    CoinFlipExample4: P(H1nH2nH3) should be %f. Returned: %f\n", shouldBe, output);
      org.junit.Assert.assertEquals("CoinFlipExample4: P(H1nH2nH3) compuation failed.",output,shouldBe, 1e-6 );        

      
      
    }
    

    @Test
    public void testCondProbCalcs(){
 
      System.out.println(" Conditional Probability Calculation Tests:");
 
      ConditionalProbabilityTree tree = Examples.MedicalTestExample();
      
      double output = Computations.computeConditionalProbability(new String[]{"HD",""},new String[]{"","TP"},tree);
      double shouldBe = 0.001976;
      System.out.printf("    MedicalTestExample: P(HD|TP) should be %f. Returned: %f\n", shouldBe, output);
      org.junit.Assert.assertEquals("MedicalTestExample: P(HD|TP) compuation failed.",output,shouldBe, 1e-6 );  
      
      output = Computations.computeConditionalProbability(new String[]{"","TN"},new String[]{"ND",""},tree);
      shouldBe = 0.950000;
      System.out.printf("    MedicalTestExample: P(TN|ND) should be %f. Returned: %f\n", shouldBe, output);
      org.junit.Assert.assertEquals("MedicalTestExample: P(TN|ND) compuation failed.",output,shouldBe, 1e-6 );  

      tree = Examples.CoinFlipExample4();
      output = Computations.computeConditionalProbability(new String[]{"H1","",""},new String[]{"","H2","H3"},tree);
      shouldBe = 0.6;
      System.out.printf("    CoinFlipExample4: P(H1|H2nH3) should be %f. Returned: %f\n", shouldBe, output);
      org.junit.Assert.assertEquals("CoinFlipExample4: P(H1nH2nH3) compuation failed.",output,shouldBe, 1e-6 );        
      
      
    }

    
  public static void main(String [ ] args){
  
  
    org.junit.runner.JUnitCore.main("Tests");
    
  }  

}