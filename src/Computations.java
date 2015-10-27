/********************************
 * File: Computations.java
 * Description: This class contains student provided code
 * implementing computations for conditional probability trees.
 * Author: You, B. Marlin and M. Lanighan. UMass Amherst CS240.
 * Date: Sept. 19, 2015.
 *********************************/

import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;

public class Computations {

	/********************************
	 * Method: computeProbability() Description: This method computes the
	 * probability of the intersection of the specified events.
	 *
	 * Inputs: String[] Events - an array of length equal to the number of
	 * levels in the tree. Events[l] must either specify the name of a valid
	 * event on level l of the tree, or be the empty string "".
	 * ConditionalProbabilityTree tree - a conditional probability tree data
	 * structure.
	 *
	 * Outputs: The output must be the probability of the intersection of the
	 * valid events specified in the array Events. For example, if
	 * Events=["A","B","C"], then this function must return P(AnBnC). If
	 * Events=["A","","C"], then this function must return P(AnC).
	 *********************************/
	public static double computeProbability(String[] Events,
			ConditionalProbabilityTree tree) {
		//get the last index that actually contains an event
		int lastindex = Events.length - 1;
		while (Events[lastindex] == "")
			lastindex--;
       
		//final return value
		double toReturn = 0;
  
		Stack<ConditionalEventNode> stack = new Stack<ConditionalEventNode>();
        //create an enumeration to traverse the tree
		Enumeration traverse = tree.root.postorderEnumeration();

		/*this while loop traverses the tree to get every event 
		 *that matches the last event in "Events" Array,
		 *and push them into a stack.
		 *For example, if Events=["A","B","C"], it will get every "C" in the tree
		 *and push them into a stack
		 */
		while (traverse.hasMoreElements()) {
			ConditionalEventNode node = (ConditionalEventNode) traverse
					.nextElement();
			if (node.eventName == Events[lastindex])
				stack.push(node);
		}
		/*this while loop does following:
		 * 
		 * get each event out of stack and traverse back to the "Root",
		 * if their ancestors match the corresponding events in "Events" array
		 * or the corresponding events in "Events" array are ""
		 * times their probabilities together.
		 * 
		 * Add result to "toReturn" at the end.
		 */
		while (!stack.isEmpty()) {
			ConditionalEventNode node = stack.pop();
			double result = 0;
			if (((ConditionalEventNode) node.getParent()).eventName == "Root") {
				toReturn = node.prob;
			} 
			else {		
				result = node.prob;
				for(int i = lastindex; i>0; i--){
					ConditionalEventNode parent = (ConditionalEventNode) node.getParent();					
					if(parent.eventName == Events[i-1] || Events[i-1] == ""){
						result *= parent.prob;
						node = parent;
					}
					else{
						result = 0;
						break;
					}				
				}
				toReturn +=result;
			}

		}
		//return the final value
		return toReturn;
	}

	/********************************
	 * Method: computeConditionalProbability() Description: This method computes
	 * the probability of the intersection of the specified events.
	 *
	 * Inputs: String[] targetEvents - an array of length equal to the number of
	 * levels in the tree. Events[l] must either specify the name of a valid
	 * event on level l of the tree, or be the empty string "". String[]
	 * conditioningEvents - an array of length equal to the number of levels in
	 * the tree. Events[l] must either specify the name of a valid event on
	 * level l of the tree, or be the empty string "".
	 * ConditionalProbabilityTree tree - a conditional probability tree data
	 * structure.
	 *
	 * Outputs: The output must be the conditional probability of the
	 * intersection of the valid target events given the intersection of the
	 * valid conditioning events. For example, if targetEvents=["A",""], and
	 * conditioningEvents=["","B"], then this function must return P(A|B). If
	 * targetEvents=["A","B",""], and conditioningEvents=["","","C"], then this
	 * function must return P(AnB|C). If targetEvents=["A","",""], and
	 * conditioningEvents=["","B","C"], then this function must return P(A|BnC).
	 *********************************/
	public static double computeConditionalProbability(String[] targetEvents,
			String[] conditioningEvents, ConditionalProbabilityTree tree) {
		
		String[] all = new String[targetEvents.length];
		/*this for loop combines targetEvents and conditioningEvent together
		 * For example, If targetEvents=["A","",""], and conditioningEvents=["","B","C"]
		 * all = ["A","B","C"]
		 */
		for(int i=0; i<targetEvents.length; i++){
			if(targetEvents[i]!="")
				all[i] = targetEvents[i];
			if(conditioningEvents[i]!="")
				all[i] = conditioningEvents[i];
		}
		/*If targetEvents=["A","",""], and conditioningEvents=["","B","C"]
		 * allprob = P(AnBnC)
		 */
		double allprob = computeProbability(all,tree);
		/*If targetEvents=["A","",""], and conditioningEvents=["","B","C"]
		 * conditioningprob = P(BnC)
		 */
		double conditioningprob = computeProbability(conditioningEvents,tree);
		//check if conditioningprob == 0
		if(conditioningprob == 0) return Double.NaN;
		else return allprob/conditioningprob;
	}

	/********************************
	 * Method: checkValid() Description: This method checks whether the
	 * probability law specified by the conditional probability tree is a valid
	 * probability law.
	 *
	 * Inputs: ConditionalProbabilityTree tree - a conditional probability tree
	 * data structure.
	 *
	 * Outputs: If the probability distribution specified by the tree satisfies
	 * all axioms of probability theory, this function should return true.
	 * Otherwise, it should return false.
	 *********************************/
	public static boolean checkValid(ConditionalProbabilityTree tree) {
		boolean toReturn = true;
		Stack<ConditionalEventNode> stack = new Stack<ConditionalEventNode>();
		//push the root into stack
		stack.push((ConditionalEventNode)tree.root);
		
		while(!stack.isEmpty()){
			ConditionalEventNode node = stack.pop();
			if(node.isLeaf()) break;
			//get the number of children
			int childCount = node.getChildCount();
			double total = 0;
			//add probabilities of each child together
			for(int i=0; i<childCount; i++){
				total+=((ConditionalEventNode) node.getChildAt(i)).prob;
			}
			//if the result not equal to one, then false
			if(total != 1) toReturn = false;
			//otherwise, push children into stack
			else{
				for(int i=0; i<childCount; i++){
					stack.push((ConditionalEventNode) node.getChildAt(i));
				}
			}
		}
		
		return toReturn;
	}

	/********************************
	 * Method: checkIndependent() Description: This method checks whether the
	 * steps in the sequential experiment represented by the conditional
	 * probability tree are probabilistically independent of each other.
	 *
	 * Inputs: ConditionalProbabilityTree tree - a conditional probability tree
	 * data structure.
	 *
	 * Outputs: If the steps in the sequential experiment represented by the
	 * conditional probability tree are probabilistically independent of each
	 * other, this function should return true. Otherwise, it should return
	 * false.
	 *********************************/
	public static boolean checkIndependent(ConditionalProbabilityTree tree) {
		// Your code goes here
		boolean toReturn = true;
		//get the number of children
		int childCount = tree.root.getChildCount();
		double[] probs = new double[childCount];
		Stack<ConditionalEventNode> stack = new Stack<ConditionalEventNode>();
		//store children's prob into Array
		for(int i=0; i<childCount; i++){
			probs[i] = ((ConditionalEventNode) tree.root.getChildAt(i)).prob;
		}
		//push the root into stack
		stack.push((ConditionalEventNode)tree.root);
		while(!stack.isEmpty()){
			ConditionalEventNode node = stack.pop();
			if(node.isLeaf()) break;
			//check if probability matches, if does not match. then false
			for(int i=0; i<childCount; i++){
				if(probs[i] != ((ConditionalEventNode) node.getChildAt(i)).prob){
					toReturn = false;
					break;
				}
			}
			//otherwise push children into stack
			if(toReturn){
				for(int i=0; i<childCount; i++){
					 stack.push((ConditionalEventNode) node.getChildAt(i));
				}
			}
		}
		return toReturn;
	}

}