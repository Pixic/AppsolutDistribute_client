package ad.model.expList;

import java.util.ArrayList;  


/**
 * ExpListGroup
 * This class make up the content of the expandable list, by forming the groups.
 * The children are placed in a group with the ExpListAdapter.
 * A group can be empty, but that would be useless unless elements are added later on with addItem() in ExpListAdapter.
 * 
 * Developers Note: There are no sections in this model, because it only consists of getters and setters methods.
 * 
 * Class content:
 * Instances: String label, ArrayList<ExpListChild> content	
 * Methods: getLabel, setLabel, getContent, setContent
 * 
 * @author Stefan Arvidsson
 *
 *  © 2012 Stefan Arvidsson
 */
public class ExpListGroup {  
	// Initiates the group's instances
     private String label;  					//group name
     private ArrayList<ExpListChild> content;   //items
     /**
      * getLabel - Returns the label of the group.  
      * @return	String - the groups label.
      */
     public String getLabel() {  
         return label;  
     }  
     /**
      * setLabel - Sets the incoming String as the group's new label.
      * @param label - A String with the groups new label.
      */
     public void setId(String label) {  
         this.label = label;  
     }  
     /**
      * getContent - Returns an ArrayList with the content.
      * @return	content - ArrayList<ExpListChild> , that makes up the content of the group.
      */
     public ArrayList<ExpListChild> getContent() {  
         return content;  
     }  
     /**
      * setContent - Sets the incoming ArrayList<ExpListChild> as the groups's new content.
      * @param content - ArrayList<ExpListChild>, the groups's new content.
      */
     public void setContent(ArrayList<ExpListChild> content) {  
		this.content = content;  
     }          

} 
