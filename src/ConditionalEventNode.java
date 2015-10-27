/********************************
* File: ConditionalEventNode.java
* Description: This class provides a node type for
* conditional probability trees.
* Author: B. Marlin and M. Lanighan. UMass Amherst CS240.
* Date: Sept. 19, 2015.
*********************************/

import java.util.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class ConditionalEventNode extends DefaultMutableTreeNode {
  double prob;
  String eventName;

  /********************************
  * method: ConditionalEvent
  * Description: This method is a constructor for the ConditionalEventNode object. 
  * It takes the name of the event and a probability as input. This object 
  * extends DefaultMutableTreeNode.  
  *
  * inputs: string eventName  - the name of the event
  *         double prob - a probability between 0 and 1      
  *********************************/
  public ConditionalEventNode(String eventName, double prob){
    super(eventName);
    this.prob = prob;
    this.eventName = eventName;
  }

}