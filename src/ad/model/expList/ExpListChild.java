package ad.model.expList;

/**
 * ExpListChild class
 * This class form the children in the group, which forms the content in the expandable list.
 * Class content:
 * Instances: String label, String tag	
 * Methods: getLabel, setLabel, getTag, setTag
 *
 *Developers Note: There are no sections in this model, because it only consists of getters and setters methods.
 *
 * @author Stefan Arvidsson
 *
 * © 2012 Stefan Arvidsson
 */
public class ExpListChild {
	// Initiates the child's instances
    private String label;  	//name 
    private String tag;  //tag 

    /**
     * getLabel - Returns the label of the child.
     * @return String - the child's label.
     */
    public String getLabel() {  
        return label;  
    }  
    /**
     * setLabelContent - Sets the incoming String as the child's new label.
     * @param content - A String with the child's new label
     */
    public void setLabel(String label) {  
        this.label = label;  
    }  
    /**
     * getTag - Returns the child's tag.
     * @return String - the child's tag
     */
   public String getTag() {  
       return tag;  
       
   }  
   /**
    * setTag - Sets the incoming String as the child's new tag.
    * @param tag - A String with the child's new tag.
    */
    public void setTag(String tag) {  
        this.tag = tag;  
   }  
      
	
}
